(function( $ ) {

  var methods = {
    init : function( opts ) {
      console.log( opts );
      lat = this.data('lat'),
      lon = this.data('lon');
      console.log(lat, lon);
      return this;
    },
    destroy : function( ) {
      //healhty cleanup?
      $(window).unbind('.walkloc');
      $this.removeData('lat');
      $this.removeData('lon');
      return this;
    }, 
    activate: function( el ) {
        
      
    }
  };


  $.fn.walkloc = function( method ) {
    
    if ( methods[method] ) {
      return methods[method].apply( this, Array.prototype.slice.call( arguments, 1 ));
    } else if ( typeof method === 'object' || ! method ) {
      return methods.init.apply( this, arguments );
    } else {
      $.error( 'Method ' +  method + ' does not exist on jQuery.walkLoc' );
    } 

  };

})( jQuery );
