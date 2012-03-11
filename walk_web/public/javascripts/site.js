$(function(){
  
var Page = function() {};
Page.prototype.start = function stop() {
    $('#flash').empty().html('Loading...').show();
};
Page.prototype.stop = function() {
    $('#flash').hide().empty();
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

        console.log(a, b, c);
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
    console.log("calling watch()");
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
      console.log(err);
      msg = "Oops, we couldn't get your location:  " + err.
      alert(msg);
    }
  },
  read: function() {
    console.log("calling read()");
    try {
      if (!navigator.geolocation) 
        throw new Error("Geolocation not supported.  Upgrade your browser.");

      var self = this;
      navigator.geolocation.getCurrentPosition(
        self.onSuccess, 
        self.onError
      );
    } catch(err) {
      console.log(err);
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
    console.log("scale: " , scale, "lat, lon", c_lat, lat, c_lon, lon);

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
      console.log("Coords set!", this.get('lat'), this.get('lon'));
      this.bubble('changed');
    } else {
      console.log("Coords haven't changed!!!");
    }
  },
  bubble: function(action) {
    console.log("Called locator:" + action);
    var ev = "locator:" + action;
    this.trigger(ev); 
  }
});
var Deal = Backbone.Model.extend({
  defaults: {
    active: false
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
    this.locator.on("locator:changed", this.load);
    this.load();
    this.locator.read();  //have to load defaults first, then read
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
    console.log("bubbling " + ev);
    this.trigger(ev);
  },
});

var HeaderView = Backbone.View.extend({
  el: '#header',
  events: {
  "click #sync": "update", 
  },
  initialize: function(opts) {
    this.sync = $('#sync');
  },
  update: function() {
    this.model.read();
  }
});

var DealHeaderView = Backbone.View.extend({
  tagName: 'div',
  className: 'deal-header',
  template: _.template('<div class="left ptr"><h4 class="deal-merchant"><%= name %>&nbsp;<small class="deal-dist"><%= distance %> mi.</small></h4><span class="deal-title"><%= title %></span></div><div class=""><span class="deal-vendor"><%= vendor %></span></div>'),
  initialize: function(opts) {
    _.bindAll(this, 'render');
  },
  render: function() {
    //crappy yelp hack
    /*if (this.model.get('vendor') == 'yelp') {
      tmp = this.model.get('title');
      this.model.set('title', this.model.get('name'));
      this.model.set('name', tmp);
    }*/
    
    $(this.el).append(this.template(this.model.toJSON()));
    return this;
  }
});
var DealBodyView = Backbone.View.extend({
  tagName: 'div',
  className: 'deal-body',

  initialize: function(opts) {
    this._deal_options = new DealOptionsView({model: this.model});
  },
  render: function() {
    //$(this.el).append('Body!!!');
    $(this.el).empty('').append(this._deal_options.render().el);
    return this;
  }
});
var DealOptionsView = Backbone.View.extend({
  tagName: 'ul',
  className: 'deal-options unstyled',
  template: _.template('<li><div class="row"><div class="span3"><%= title %></div><div class="span1"><a href="<%= buyUrl %>" title="Buy It!" class="btn btn-primary btn-mini">Buy!</a></div></div></li>'),
  template_no_title: _.template('<li><a href="<% buyUrl %>" title="Buy It!" class="btn btn-primary">Buy!</a></li>'),
  template_no_url:   _.template('<li><%= title %></li>'),
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
      if (items.options.length > 1 )
        console.log(this.model.get('did'), this.model.get('name'));

      _.each(items["options"], function(o) { 
        if( o.title && o.buyUrl)
          t = self.template;
        else if (o.buyUrl)
          t = self.template_no_title;
        else
          t = self.template_no_url;

        $(self.el).append(t(o)); 
      });
      //this.addAll(items.options);
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

var DealView = Backbone.View.extend({
  tagName: 'li',
  events: {
    "click .ptr"  : "toggleActive"
  },
  initialize: function(opts) {
    _.bindAll(this, 'render');
    this.attributes = {"data-id": this.model.get('did')};
    dist = this.model.get('distance');
    this.model.set('distance', dist.toFixed(2));
    this._header = new DealHeaderView({model: this.model});
    this._body   = new DealBodyView({model: this.model});
  },
  render: function() {
    $(this.el).empty()
      .append(this._header.render().el)
      .append(this._body.render().el);
    return this;
  },
  toggleActive: function() {
    console.log("called toggle!");
    this.model.toggle();
    st = this.model.get('active');
    if (st == true) {
      $('.deal-body', this.el).show();
    }else {
      $('.deal-body', this.el).hide();
    }
  }
});
var DealsView = Backbone.View.extend({
  el: '#deals',
  initialize: function(opts) {
    _.bindAll(this, 'render', 'addAll', 'addOne');
    this.collection.bind("reset", this.render, this);
  },
  render: function() {
    $(this.el).empty();
    this.addAll();
  },
  addOne: function(deal) {
    view = new DealView({model: deal});
    view.render();
    $(this.el).append(view.el); 
  },
  addAll: function() {
    this.collection.each(this.addOne);
  },
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
    this._deals = new Deals(opts);
    this._views = {
      deals: new DealsView({collection: this._deals}),
      header: new HeaderView({model: this._deals.locator}),
    };
    //this._deals.load();
 
    $('h1').hide();
    $('.nav').show();
    Backbone.history.start({pushState: true});
  },

  list: function(market) {
    this._deals.update(market);
    //this._metro.bubble("list");
  },

  show: function(market, id) {
    this._deals.update(market);
    //this._metro.updateActive(id);
    //this._metro.bubble("show");
  },
  notFound: function(splat) {
    console.log(splat);
              alert("Page not found");
  } 
});

   
  window.app = new App($('#main'), {market: 'chicago'});

   //make Backbone work with normal links, degrade gracefully 
  //see:  https://github.com/documentcloud/backbone/issues/456
  window.document.addEventListener('click', function(e) {
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
          window.app.navigate(uri.substr(1), true)
          return false;
        }
  });

  window.addEventListener('popstate', function(e) {
    window.app.navigate(location.pathname.substr(1), true);
  });
});
