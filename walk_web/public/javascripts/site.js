$(function(){

function Page() {
  var self = this;
}
Page.prototype.start = function() {
  $('#modal').empty().html('Loading...').show();
}
Page.prototype.stop = function() {
  $('#modal').hide().empty();
}

var Errors = function() {

}
var Locator = Backbone.Model.extend({
  defaults: {
    lat: false,
    lon: false,
    scale: 5,  //lat/lon scale
  },
  initialize: function(opts) {
    // Server will have to generate default lat, lon on page load
    // so defaults *should* be passed in
  },
  locate: function() {
    console.log("calling Metro::locate()");
    try {
      this.sync();
    } catch(err) {
      console.log("Error is", err);
    }
  },
  sync: function(callback) {
    if (!navigator.geolocation) 
      throw new Error("Not supported.");

    var self = this;
    navigator.geolocation.getCurrentPosition(
      function(position) {
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
        throw new Error(msg);
    });
  },
  denied: function() {
    alert("You denied us!!!");
  },
  updateCoordinates: function(lat, lon) {
    // @TODO check if actually changed!!!
    changed = true;    
    this.set('lat', lat);
    this.set('lon', lon); 
    console.log("Coords set!", this.get('lat'), this.get('lon'));

    if (changed)
      this.bubble('changed');
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
    deals: new DealCollection()
  },
  initialize: function(opts) {
    _.bindAll(this, 'getUrl', 'update', 'load', 'bubble', 'getParams');
    this._locator = new Locator(); 
    this._page = new Page();
    this._locator.on("locator:changed", this.load);
  },
  getParams: function() {
    var self = this;
    return {
      lat: self._locator.get('lat'),
      lon: self._locator.get('lon'),
      radius: 2, 
    }
  },
  getUrl: function() {
    if (!this.get('market')) throw new Error("Undefined market!");
    return '/api/deals/' + this.get('market');
  },
  update: function(market) {
    //@TODO check if market changed
    this._locator.locate();
  }, 
  load: function(opts) {
    console.log("calling Metro::load()");
    var self = this;
    var params = this.getParams();
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
  template: _.template('<%= title %>'),
  initialize: function(opts) {
    _.bindAll(this, 'render');
    this.attributes = {"data-id": this.model.get('did')};
  },
  render: function() {
    $(this.el).append(this.template(this.model.toJSON()));
    return this;
  }
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
    
    //make Backbone work with normal links, degrade gracefully 
    //see:  https://github.com/documentcloud/backbone/issues/456
    $('h1').hide();
    $('a').on('click', function(e) {
      self.navigate($(this).attr('href'), true);
      return false;
    });

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
});
