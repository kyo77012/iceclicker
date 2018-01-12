var mouse_begin_x;
var mouse_end_x;

var mouse_offset = 80;

$( document ).ready( function() {
  $( document ).on( "mousedown.my_mouse_swipt_detect", function( event ) {
    mouse_begin_x = event.pageX;
    $( document ).on( "mousemove.my_mouse_swipt_detect", function( event ) {
      mouse_end_x = event.pageX;
      // console.log( mouse_end_x );
      if ( mouse_end_x - mouse_begin_x > mouse_offset ) {
        $( $( ":mobile-pagecontainer" ).pagecontainer( "getActivePage" ) ).trigger( "swiperight" );
        $( document ).off( "mousemove.my_mouse_swipt_detect" );
      } // if
      else if ( mouse_end_x - mouse_begin_x < -mouse_offset ) {
        $( $( ":mobile-pagecontainer" ).pagecontainer( "getActivePage" ) ).trigger( "swipeleft" );
        $( document ).off( "mousemove.my_mouse_swipt_detect" );
      } // else if
    } );
  } );

  $( document ).on( "mouseup.my_mouse_swipt_detect", function( event ) {
    $( document ).off( "mousemove.my_mouse_swipt_detect" );
  } );
} );
