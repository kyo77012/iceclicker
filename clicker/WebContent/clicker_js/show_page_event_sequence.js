$( document ).on( "pagebeforecreate", function( event ) {
  console.log( "pagebeforecreate" );
} );

$( document ).on( "pagecreate", function( event ) {
  console.log( "pagecreate" );
} );

$( document ).on( "pagecontainerbeforeload", function( event ) {
  console.log( "pagecontainerbeforeload" );
} );

$( document ).on( "pagecontainerload", function( event ) {
  console.log( "pagecontainerload" );
} );

$( document ).on( "pagecontainerloadfailed", function( event ) {
  console.log( "pagecontainerloadfailed" );
} );

$( document ).on( "pagebeforeshow", function( event ) {
  console.log( "pagebeforeshow" );
} );

$( document ).on( "pageshow", function( event ) {
  console.log( "pageshow" );
} );

$( document ).on( "pagebeforehide", function( event ) {
  console.log( "pagebeforehide" );
} );

$( document ).on( "pagehide", function( event ) {
  console.log( "pagehide" );
} );

$( document ).on( "ready", function( event ) {
  console.log( "ready" );
} );