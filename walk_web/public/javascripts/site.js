$(function(){

function Page() {
  var self = this;
}
Page.prototype.start = function() {
  $('#flash').empty().html('Loading...').show();
}
Page.prototype.stop = function() {
  $('#flash').hide().empty();
}

var Errors = function() {

}
var Locator = Backbone.Model.extend({
  defaults: {
    lat: false,
    lon: false,
    scale: .001,  //very poor "moved" algo, .001=300ft @40'N
    status: false  //tracking for denied/not now, to avoid multiple bubbles of changed
  },
  initialize: function(opts) {
    // how to handle Firefox "Not now" *feature*
    //https://bugzilla.mozilla.org/show_bug.cgi?id=675533
  },
  locate: function() {
    console.log("calling Metro::locate()");
    //@TODO check that they don't have a cookie set that they've denied 
    //updates...if ok, then we can call, else return
    //cook = $.cookie('geo:permission_denied');
    
    try {
      this.sync();
    } catch(err) {
      console.log(err);
      //@TODO degrade gracefully on error
      msg = "Oops, we couldn't get your location:  " + err.
      alert(msg);
    }
  },
  sync: function(callback) {
    if (!navigator.geolocation) 
      throw new Error("Geolocation not supported.  Upgrade your browser.");

    var self = this;
    navigator.geolocation.getCurrentPosition(
      function(position) {
        //update old status
        this.set('status', false); 
        self.updateCoordinates(position.coords.latitude, position.coords.longitude);
      },
      function(error) {
        msg = "Unknown error.";
        switch(error.code) {
          case error.TIMEOUT:
            msg = "Timeout.";
            break;
          case error.POSITION_UNAVAILABLE:
            msg = "Position Unavailable.";
            break;
          case error.PERMISSION_DENIED:
            return self.denied();
            break;
          case error.UKNOWN_ERROR:
            break;
        }
        console.log("Error", msg); 
        //edge case that they went from denied -> ok but throws error
        this.set('status', false);  
        throw new Error(msg);
    });
  },
  denied: function() {
    //note, closing browser on firefox kills state (or even cookie) 
    //but not API choice, so test fails and user sees 
    //alert message on next visit..probably a good thing given 
    //Firefox's lack of indicator
    st = this.get('status');
    if (st == 'denied')
      return;

    this.set('status', 'denied');
    msg = "We use location to improve relevancy. Read the FAQs to learn more.";
    alert(msg); 
  },
  updateCoordinates: function(lat, lon) {
    changed = false;
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
  setDefaultCoordinates: function() {
    //@TODO make server call and get per market name
    this.updateCoordinates(41.87, -87.62);  //Meigs
  },
  bubble: function(action) {
    console.log("Called locator:" + action);
    var ev = "locator:" + action;
    this.trigger(ev); 
  }
});
var Deal = Backbone.Model.extend({});
var DealCollection = Backbone.Collection.extend({model: Deal});
var Metro = Backbone.Model.extend({
  defaults: {
    market: 'chicago',
    deals: new DealCollection(),
    lat:  41.87,
    lon: -87.62
 },
  initialize: function(opts) {
    _.bindAll(this, 'getUrl', 'update', 'load', 'bubble', 'getParams');
    this._locator = new Locator(); 
    this._page = new Page();
    this._locator.on("locator:changed", this.load);
    
    //should get lat, lon defaults passed in with opts
    //load defaults to account for Not Now issues
    var o = { params: this.getParams(this.get('lat'), this.get('lon')) };
    this.load(o);
  },
  getParams: function(lat, lon) {
    var self = this;
    return {
      lat: lat || self._locator.get('lat'),
      lon: lon || self._locator.get('lon'),
      radius: 2, 
    }
  },
  getUrl: function() {
    if (!this.get('market')) throw new Error("Undefined market!");
    return '/api/deals/' + this.get('market');
  },
  update: function(market, id) {
    //@TODO check if market changed
    this._locator.locate();
    if (id) {
      //get the item from collection  
      deal = this.get('deals').get(id);
      console.log(deal);
      this._selected = deal;
      this.bubble('itemized');
    }
  }, 
  load: function(opts) {
    console.log("calling Metro::load()");
    var self = this;
    var params = (opts && opts.params) ? opts.params : this.getParams();
    $.ajax({
      async: true,
      dataType: 'json',
      type: 'GET',
      url: this.getUrl(),
      data: params,
      beforeSend: function() { self._page.start(); },
      success: function(json) {
        self._page.stop();
        coll = self.get('deals');
        coll.reset(json);
        self.set('deals', coll);
        self.bubble("loaded");
      },
      error: function(a, b, c) {
        self._page.stop();
        // @TODO bubble an error event
        // which views can react to accordingly
        alert("Error: Unable to load deals!");
      }
    }); 
      
  },
  // trigger via router, views respond accodingly
  bubble: function(action) {
    ev = "metro:" + action
    console.log("bubbling " + ev);
    this.trigger(ev);
  },
 /*
  setMarket: function(mkt) {
    //@TODO trigger some not_found event unless mkt is in some predefined array
    this.set('market', mkt);
    return this;
  }, */
});

var HeaderView = Backbone.View.extend({
  el: '#header',
  initialize: function(opts) {
    this.model.bind("metro:show", this.vendor, this);
    this.model.bind("metro:list metro:not_found", this.walkable, this); 
  },
  vendor: function() {
    $('#vendor').empty('').html('some vendor');
  },
  walkable: function() {
    $('#vendor').empty('').html('walkable');
  }
});


var DealView = Backbone.View.extend({
  tagName: 'li',
  template: _.template('<a href="/deals/chicago/<%= did %>"><%= title %></a>'),
  events: {
  },
  initialize: function(opts) {
    _.bindAll(this, 'render');
    this.attributes = {"data-id": this.model.get('did')};
  },
  render: function() {
    $(this.el).append(this.template(this.model.toJSON()));
    return this;
  },
});
var DealsView = Backbone.View.extend({
  el: '#deals',
  tagName: 'ul',
  initialize: function(opts) {
    _.bindAll(this, 'render', 'addAll', 'addOne');
    this.model.bind("metro:show metro:not_found", this.hide, this);
    this.model.bind("metro:list", this.show, this);
    this.model.bind("metro:loaded", this.render, this);
  },
  render: function() {
    this.collection = this.model.get('deals');
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
  hide: function() {
    $(this.el).hide();
    console.log('called dealsView hide');
  },
  show: function() {
    $(this.el).show();
    console.log('called dealsView show');
  }
});

var ItemView = Backbone.View.extend({
  el: '#item',
  initialize: function(opts) {
    this.model.bind("metro:show", this.show, this);
    this.model.bind("metro:list", this.hide, this);
    this.render();
  },
  render: function() {
    $(this.el).empty('').html("Item View!!");
  },
  hide: function() {
    $(this.el).hide();
  },
  show: function() {
    console.log("Called itemView show");
    $(this.el).show();
  },
  open: function(deal) {
    console.log("Called itemView:open()");
  }
});

var App = Backbone.Router.extend({
  routes: {
    "deals/:market" : "listDeals",
    "deals/:market/:id" : "showDeal",
    "about/:page"   : "about",
    "*splat"        : "notFound"
  },
  initialize: function(el, opts) {
    var self = this;
    //self.page    = new Page();
    this._el = el;
    //this._errors  = opts.errors ? opts.errors : new Errors();
    //this._locator = opts.locator ? opts.locator : new Locator();
    this._metro = new Metro();
    this._views = {
      deals: new DealsView({model: this._metro}),
      item: new ItemView({model: this._metro}),
      header: new HeaderView({model: this._metro}),
    };
    
   $('h1').hide();

    Backbone.history.start({pushState: true});
  },

  listDeals: function(market) {
    var params = {lat: 47, lon: -87, radius: 2 } 
    this._metro.bubble("list");
    this._metro.update(market);
  },

  showDeal: function(market, id) {
    this._metro.bubble("show");
    this._metro.update(market);
  },
  
  notFound: function(splat) {
    alert("Page not found");
  } 
});

   
  window.app = new App($('#main'), {market: 'chicago'});

  //make Backbone work with normal links, degrade gracefully 
  //see:  https://github.com/documentcloud/backbone/issues/456
  window.document.addEventListener('click', function(e) {
    e = e || window.event;
    var target = e.target || e.srcElement
    if ( target.nodeName.toLowerCase() === 'a' ) {
      e.preventDefault();
      var uri = target.getAttribute('href');
      window.app.navigate(uri.substr(1), true)
      return false;
    }
  });
  
  window.addEventListener('popstate', function(e) {
    window.app.navigate(location.pathname.substr(1), true);
  });
});
