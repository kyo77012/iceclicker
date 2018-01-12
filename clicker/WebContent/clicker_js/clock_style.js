$( document ).ready( function() {
  // console.log( "clock_style.js" );
  jq_clock_container.toolbar( {
    position: "fixed",
    fullscreen: true,
    disablePageZoom: true,
    tapToggle: false,
    tapToggleBlacklist: ".do-not-toggle-fixed-toolbar",
    hideDuringFocus: ""
  } );
  
  jq_clock_container.css( {
    borderWidth: 0,
    background: "transparent",
    opacity: 1
  } );
} );