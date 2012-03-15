//$(function(){
  
var Page = function() {
};
Page.prototype.start = function stop() {
  $('#sync').hide();
  $('#deals').removeClass('active');
  $('#blank').addClass('active');
};
Page.prototype.stop = function() {
  $('#blank').removeClass('active');
  $('#deals').addClass('active');
  $('#sync').show();
};

Page.prototype.request = function(opts) {
    var self = this;
    var params = opts.params || {};
    var success = opts.success || null;
    var error = opts.error || null;
    $.ajax({
      async: true,
      dataType: 'json',
      type: opts.method || 'GET',
      url: opts.url,
      data: params,
      beforeSend: function() { self.start(); },
      success: function(json) {
        self.stop();
        if (success) 
          return success(json);
        else
          return json;
      },
      error: function(a, b, c) {
        self.stop();
        // @TODO bubble an error event
        // which views can react to accordingly
        if (error)
          return error(a, b, c);

        //console.log(a, b, c);
        alert("Error: Unable to load deals!");
      }
    });
};
window.page = new Page();

var Locator = Backbone.Model.extend({
  //@TODO abstract data into collection of markers[lat,lon,id]
  // marker...allows for multiple user-defined markers 
  defaults: {
    lat: false,
    lon: false,
    scale: .001,  //very poor "moved" algo, .001=300ft @40'N
    status: false,  //tracking for denied/not now, to avoid multiple bubbles of changed
    watch_id: null
  },

  // watch on startup
  initialize: function() {
    _.bindAll(this, 'watch', 'onSuccess', 'onError');
  },
  unwatch: function() {
    if (!navigator.geolocation) return;
    navigator.geolocation.clearWatch(this.get('watch_id'));
  },
  watch: function() {
    //console.log("calling watch()");
    try {
      if (!navigator.geolocation) 
        throw new Error("Geolocation not supported.  Upgrade your browser.");

      var self = this;
    //time is in milliseconds  
    //https://developer.mozilla.org/En/Using_geolocation#Watching_the_current_position 
      var watch_id = navigator.geolocation.watchPosition(
        self.onSuccess, 
        self.onError,
        // FF needs timeout high or fails often
        {enableHighAccuracy: true, maximumAge:60000, timeout: 120000 }
        //{enableHighAccuracy:true, maximumAge:30000, timeout:27000}
      );
      this.set('watch_id', watch_id);
    } catch(err) {
      //console.log(err);
      msg = "Oops, we couldn't get your location:  " + err.
      alert(msg);
    }
  },
  read: function() {
    //console.log("calling read()");
    try {
      if (!navigator.geolocation) 
        throw new Error("Geolocation not supported.  Upgrade your browser.");

      var self = this;
      navigator.geolocation.getCurrentPosition(
        self.onSuccess, 
        self.onError
      );
    } catch(err) {
      //console.log(err);
      msg = "Oops, we couldn't get your location:  " + err.
      alert(msg);
    }
  },
  onSuccess: function(position) {
    //negate last status
    this.set('status', false); 
    this.updateCoordinates(position.coords.latitude, position.coords.longitude);
  },
  onError: function(error) {
    msg = "Unknown error.";
    switch(error.code) {
      case error.TIMEOUT:
        msg = "Timeout.";
        break;
      case error.POSITION_UNAVAILABLE:
        msg = "Position Unavailable.";
        break;
      case error.PERMISSION_DENIED:
        return this.denied();
        break;
      case error.UKNOWN_ERROR:
        break;
    }
    this.set('status', false);
    throw new Error(msg);
  },
  denied: function() {
    //note, closing browser kills state (or even cookie) 
    //but not API choice in some instances, so test fails and user sees 
    //alert message on next visit..probably a good thing given 
    //Firefox & Safari's lack of indicator
    st = this.get('status');
    if (st == 'denied')
      return;

    this.set('status', 'denied');
    msg = "We use location to improve relevancy. Read the FAQs to learn more.";
    alert(msg); 
  },
  //@TODO move into markers object & update event chain accordingly
  updateCoordinates: function(lat, lon) {
    changed = false;
    if (typeof lat == 'number') lat = lat.toFixed(3);
    if (typeof lon == 'number') lon = lon.toFixed(3);
    c_lat = this.get('lat');
    c_lon = this.get('lon');
    scale = this.get('scale');
    //console.log("scale: " , scale, "lat, lon", c_lat, lat, c_lon, lon);

    if (c_lat && c_lon) {
      var p = Math.abs(lat - c_lat);
      var q = Math.abs(lon - c_lon);
      changed = (p > scale || q > scale);
    } else {
      changed = true;
    }

    if (changed == true) {
      this.set('lat', lat);
      this.set('lon', lon); 
      //console.log("Coords set!", this.get('lat'), this.get('lon'));
      this.bubble('changed');
    } else {
      this.bubble('not_moved');
    }
  },
  bubble: function(action) {
    //console.log("Called locator:" + action);
    var ev = "locator:" + action;
    this.trigger(ev); 
  }
});

var Vendor = Backbone.Model.extend({
defaults: {
  active: false
},
toggle: function() {
  this.set({active: !(this.get('active'))});
}
});
var Vendors = Backbone.Collection.extend({
  model: Vendor,
  initialize: function(opts) {
    this.reset(opts.data); 
  }, 
  
});
var Deal = Backbone.Model.extend({
  defaults: {
    active: true
  },
  toggle: function() {
    this.set({active: !(this.get('active'))});
  }  
});
var Deals = Backbone.Collection.extend({
  market: 'chicago',
  coords: {lat: 41.87, lon: -87.62},
  radius: 3,
  model: Deal,
  locator: false,
  initialize: function(opts) {
    _.bindAll(this, 'load');
    this.locator = new Locator();
    //this.locator.on("locator:changed", this.load);
    this.load();
    this.locator.read();  //have to load defaults first, then read
  },
  count: function(vendor) {
    return this.filter(function(v){  return (v.get('vendor') === vendor)});
  },
  //Custom fetch, just easier this way not knowing internals well
  load: function() {
    //console.log("calling Metro::load()");
    var self = this;
    url = this.url();
    var o = {
      async: true,
      dataType: 'json',
      method: 'GET',
      params: this.params(),
      url: this.url(),
      //beforeSend:  ?,
      //error,
      success: function(json) {
        //@TODO save state of existing UI
        self.reset(json);
      }
    };    
    //console.log("Sending params: ", o);
    page.request(o);    
  }, 
  //Filter list to active items i.e. open
  active: function() {
    return this.filter(function(deal) { return deal.get('active'); });
  },
  //Sould effective compact all items at once
  selectNone: function() {
    return this.each(function(deal) { deal.set('active', false); });
  },
  //url to fetch from
  url: function() {
    return '/api/deals/' + this.market;
  },
  //query params for GET request
  params: function() {
    //use defaults only if locator not found
    lat = this.locator.get('lat') || this.coords.lat;
    lon = this.locator.get('lon') || this.coords.lon;
    return {
      lat: lat,
      lon: lon,
      radius: this.radius
    }
  },
  update: function(market) {
    //@TODO check if market changed
    //this.locator.locate();  //not needed if using watchLocation
  },
  bubble: function(action) {
    ev = "metro:" + action
    //console.log("bubbling " + ev);
    this.trigger(ev);
  },
});

var AppView = Backbone.View.extend({
  el: '#walkapp',
  views: {},
  models: {},
  events: {
    "click #sync": "locationCheck", 
    "click a.settings" : "toggleView",
    "click a.done"     : "showByVendor",
  },
  initialize: function(opts) {
    _.bindAll(this, 'locationCheck', 'loadDeals', 'showByVendor');
    //this._startup();
    this.started = false;
    this.models.deals = new Deals(opts.deals.data || []);
    this.views.deals  = new DealsView({collection: this.models.deals});
    this.views.settings = new SettingsView(opts);
    this.models.deals.locator.on("locator:changed", this.loadDeals);
    this.models.deals.locator.on("locator:not_moved", this.notMoved);
  },
  render: function() {

  },
  notMoved: function() {
    alert('No updates found.  Walk a few blocks and try again.');
  },
  locationCheck: function() {
    this.models.deals.locator.read();
  },
  loadDeals: function() {
    this.models.deals.load();
  },
  showByVendor: function() {
    var selected = this.views.settings.models.vendors.find(function(v) {
      return (v.get('active') == true);
    });
    var vendor = 'walkable'; 
    if (typeof selected != 'undefined') {
      vendor = selected.get('name');
     //count them first
      if (this.models.deals.count(vendor).length < 1) {
        alert('Sorry, that vendor has no deals currently near you.');
        return;
      } 
      this.models.deals.each(function(d) {
        if (d.get('vendor') === vendor)
            d.set('active', true);
         else
            d.set('active', false);
      });
      $('.brand', '.page-footer').hide();
    }else {
      this.models.deals.each(function(d) {
          d.set('active', true);
      });
      $('.brand', '.page-footer').show();
    }
    $('#brand').attr('class', vendor); 
    
    this.toggleView();
  },
  toggleView: function() {
    this.views.deals.toggle();
    this.views.settings.toggle();
  },
  _startup: function() {
    //$('h1').hide();
  }
});

var SettingsView = Backbone.View.extend({
  el: '#settings',
  models: {},
  views: {},
  events: {

  },
  initialize: function(opts) {
    _.bindAll(this, 'render', 'show', 'hide');
    this.models.vendors = new Vendors(opts.vendors.data || []);
    this.views.vendors = new VendorsView({collection: this.models.vendors});
  },
  render: function() {
    this.vendors.render();
  },
  show: function() {
    $(this.el).show();
  },
  hide: function() {
    $(this.el).hide();
  },
  toggle: function() {
    if ($(this.el).hasClass('active')) {
      $(this.el).removeClass('active');
    } else {
      $(this.el).addClass('active');
    }
  },
});

var VendorsView = Backbone.View.extend({
  el: '#set_vendors',
  events: {
    "click .vendor" : "toggleActive"
  },
  initialize: function(opts) {
    _.bindAll(this, 'render', 'addAll', 'addOne');
    this.collection.bind("reset", this.render, this);
    this.render();
  },
  render: function() {
    $('ul', this.el).empty();
    this.addAll();
  },
  addOne: function(vendor) {
    view = new VendorView({model: vendor});
    view.render();
    $('ul', this.el).append(view.el); 
  },
  addAll: function() {
    this.collection.each(this.addOne);
  },
  toggleActive: function(e) {
    /*e = e || window.event;
      var target = e.target || e.srcElement;
    */
    var target = e.target;
    if (target.nodeName.toLowerCase() === 'span') {
        id = $(target).data('id');
    } else if ($(target, 'span').length == 1) {
        id = $(target).parent('span').data('id');
    }
    this.collection.each(function(vendor) {
      name = vendor.get('name');
      if (typeof name != 'undefined' && name == id) return;
      vendor.set('active', false);
    });
  }
});
var VendorView = Backbone.View.extend({
  tagName: 'li',
  template: _.template('<span data-id="<%= name %>" class="vendor"><img alt="<%= display_name %>" height="<%= height %>" width="<%= width %>" src="<%= imgUrl %>" /></span>'),
  events: {
    "click .vendor": "toggleActive"
  },
  initialize: function(opts) {
    _.bindAll(this, 'render');
    this.model.bind('change', this.render, this);
  },
  render: function() {
    $(this.el).html(this.template(this.model.toJSON()));
    $(this.el).attr('class', (this.model.get('active') ? 'active' : ''));
    return this;
  },
  toggleActive: function(e) {
    this.model.toggle(); 
  }
});
var DealHeaderView = Backbone.View.extend({
  tagName: 'div',
  className: 'deal-header ptr',
  template: _.template('<span class="deal-merchant"><%= name %></span><span class="deal-dist"><% print (distance.toFixed(2)); %> mi.</span><br /><span class="deal-title"><%= title %></span>'),
  initialize: function(opts) {
    _.bindAll(this, 'render');
  },
  render: function() {
    $(this.el).html(this.template(this.model.toJSON()));
    return this;
  }
});
var DealBodyView = Backbone.View.extend({
  tagName: 'div',
  className: 'deal-body',
  vendor_template: _.template('<div class="deal-vendor">Provided by <strong><%= vendor %></strong></div>'), 
  initialize: function(opts) {
    this._deal_options = new DealOptionsView({model: this.model});
    this._footer = new DealFooterView({model: this.model}); 
  },
  render: function() {
    $(this.el).empty()
      .append(this._deal_options.render().el)
      .append(this._footer.render().el);
    return this;
  }
});
var DealOptionsView = Backbone.View.extend({
  tagName: 'table',
  className: 'deal-options unstyled',
  template: _.template('<tr><td><%= title %></td><td class="right"><a href="<%= buyUrl %>" target="_blank" title="More" class="btn btn-small">More</a></td></tr>'),
  template_no_title: _.template('<tr><td>&nbsp;</td><td class="right"><a href="<% buyUrl %>" title="Buy It!" class="btn btn-small">More</a></td></tr>'),
  template_no_url:   _.template('<tr><td><%= title %></td><td class="right">&nbsp;</td></tr>'),
  initialize: function(opts) {
    _.bindAll(this, 'render');
  },
  render: function() {
    $(this.el).empty();
    items = JSON.parse(this.model.get('items')) || [];
    if ( items.length < 1 || typeof items.options == 'undefined' ) {
      $(this.el).append('<li>Unable to fetch deal options. Sorry.</li>');
    } else {
      var self = this;
      _.each(items["options"], function(o) { 
        if( o.title && o.buyUrl)
          t = self.template;
        else if (o.buyUrl)
          t = self.template_no_title;
        else
          t = self.template_no_url;
       
        $(self.el).append(t(o)); 
      });
    }
    return this; 
  },
  addOne: function(item) {
    //view = new DealView({model: deal});
    //view.render();
    $(this.el).append(view.el); 
  },
  addAll: function() {
    //this.collection.each(this.addOne);
  }, 
});
var DealFooterView = Backbone.View.extend({
  tagName: 'div',
  className: 'deal-footer',
  template: _.template("<span class='brand <%= vendor %>'></span>"), 
  initialize: function(opts) {
    _.bindAll(this, 'render');
  },
  render: function() {
    $(this.el).html(this.template(this.model.toJSON())); 
    return this;
  }
});
var DealView = Backbone.View.extend({
  tagName: 'li',
  events: {
    "click .deal-header"  : "toggleBody",
  },
  initialize: function(opts) {
    _.bindAll(this, 'render');
    //this.attributes = {"data-id": this.model.get('did')};
    this._header = new DealHeaderView({model: this.model});
    this._body   = new DealBodyView({model: this.model});
    this.model.bind('change', this.render, this);
  },
  render: function() {
    $(this.el).empty('')
      .append(this._header.render().el)
      .append(this._body.render().el);
      //.append(this._footer.render().el);
    $(this.el).attr('class', (this.model.get('active') ? '' : 'hidden'));
    return this;
  },
  toggleBody: function() {
    $('.deal-body', this.el).toggle();
    return this;
  },
  toggleActive: function(e) {
    this.model.toggle();
  }
});
var DealsView = Backbone.View.extend({
  el: '#deals',
  events: {
    "locator:changed" : "Foo"
  },
  Foo: function() {
    alert("Foo");
  },
  initialize: function(opts) {
    _.bindAll(this, 'render', 'addAll', 'addOne');
    this.collection.bind("reset", this.render, this);
  },
  render: function() {
    $('#deal_items').empty();
    this.addAll();
  },
  addOne: function(deal) {
    view = new DealView({model: deal});
    view.render();
    $('#deal_items').append(view.el); 
  },
  addAll: function() {
    this.collection.each(this.addOne);
  },
  show: function() {
    $(this.el).show();
  },
  hide: function() {
    $(this.el).hide();
  },
  toggle: function() {
    if ($(this.el).hasClass('active')) {
      $(this.el).removeClass('active');
    } else {
      $(this.el).addClass('active');
    }
  }
});

var App = Backbone.Router.extend({
  routes: {
    "deals/:market" : "list",
    "deals/:market/:id" : "show",
    "about/:page"   : "about",
    "/*splat"        : "notFound"
  },
  initialize: function(el, opts) {
    var self = this;
    this._el = el;
    //this._errors  = opts.errors ? opts.errors : new Errors();
    //this._locator = opts.locator ? opts.locator : new Locator();
    this.view = new AppView(opts); 
    this.bindLinks();
    Backbone.history.start({pushState: true});
  },

  list: function(market) {
    //this._deals.update(market);
    //this._metro.bubble("list");
  },

  show: function(market, id) {
    //this._deals.update(market);
    //this._metro.updateActive(id);
    //this._metro.bubble("show");
  },
  notFound: function(splat) {
    //console.log(splat);
    alert("Page not found");
  },
  bindLinks: function() {
    var self = this;
    $('a.push').click(function(e) {
      e = e || window.event;
      var target = e.target || e.srcElement;
      var uri = false;
      if (target.nodeName.toLowerCase() === 'a') {
        uri = target.getAttribute('href');
      } else if ($(target, 'a').length == 1) {
        target = $(target).parent('a');
        uri = target.attr('href');
      }
      if ( uri ) {
        e.preventDefault();
        //var uri = target.getAttribute('href');
        //var uri = target.attr('href');
        self.navigate(uri.substr(1), true)
        return false;
      }
    });
  } 
});

   

   //make Backbone work with normal links, degrade gracefully 
  //see:  https://github.com/documentcloud/backbone/issues/456
/*  window.document.addEventListener('click', function(e) {
        e = e || window.event;
        var target = e.target || e.srcElement;
        var uri = false;
        console.log(target, target.getAttribute('href')); 
        if (target.nodeName.toLowerCase() === 'a') {
          uri = target.getAttribute('href');
        } else if ($(target, 'a').length == 1) {
          target = $(target).parent('a');
          uri = target.attr('href');
        }
        
        if ( uri ) {
          e.preventDefault();
          //var uri = target.getAttribute('href');
          //var uri = target.attr('href');
          window.app.navigate(uri.substr(1), true)
          return false;
        }
  });
  window.app = new App($('#main'), {market: 'chicago'});
  window.addEventListener('popstate', function(e) {
    window.app.navigate(location.pathname.substr(1), true);
  });
*/
//});
