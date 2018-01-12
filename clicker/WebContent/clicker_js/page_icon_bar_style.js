$( document ).ready( function() {
  jq_page_icon_bar_container.toolbar( {
    position: "fixed",
    fullscreen: true,
    disablePageZoom: true,
    tapToggle: false,
    tapToggleBlacklist: ".do-not-toggle-fixed-toolbar",
    hideDuringFocus: ""
  } );

  jq_page_icon_bar_container.css( {
    borderWidth: 0,
    background: "transparent",
    opacity: 1
  } );

  resize_page_icon_bar();
  $( window ).resize( resize_page_icon_bar );


  var page_active_id = $( "[data-role='page'][class~='ui-page-active']" ).attr( "id" );
  var group_page_id = "group_page";
  var answer_page_id = "answer_page";
  var rank_page_id = "rank_page";

  if ( page_active_id == group_page_id ) {
    jq_group_page_icon.css( "background-color", "#CCCC33" );
  } // if
  else if ( page_active_id == answer_page_id ) {
    jq_answer_page_icon.css( "background-color", "#CCCC33" );
  } // else if
  else if ( page_active_id == rank_page_id ) {
    jq_rank_page_icon.css( "background-color", "#CCCC33" );
  } // else if




  $( document ).on( "pagebeforeshow.my_change_icons_color", "#group_page", function() {
    jq_group_page_icon.css( "background-color", "#CCCC33" );
  } );

  $( document ).on( "pagebeforehide.my_change_icons_color", "#group_page", function() {
    jq_group_page_icon.css( "background-color", "#333" );
  } );




  $( document ).on( "pagebeforeshow.my_change_icons_color", "#answer_page", function() {
    jq_answer_page_icon.css( "background-color", "#CCCC33" );
  } );

  $( document ).on( "pagebeforehide.my_change_icons_color", "#answer_page", function() {
    jq_answer_page_icon.css( "background-color", "#333" );
  } );



  $( document ).on( "pagebeforeshow.my_change_icons_color", "#rank_page", function() {
    jq_rank_page_icon.css( "background-color", "#CCCC33" );
  } );

  $( document ).on( "pagebeforehide.my_change_icons_color", "#rank_page", function() {
    jq_rank_page_icon.css( "background-color", "#333" );
  } );
} );

function resize_page_icon_bar() {
  var magnification = $( window ).height() / 626;
  if ( magnification > 1 ) magnification = 1;
  
  var page_icon_padding = 5 * magnification;
  var page_icon_borderWidth = 5 * magnification;
  var page_icon_first_borderLeftWidth = page_icon_borderWidth;
  var page_icon_first_borderTopLeftRadius = 20 * magnification;
  var page_icon_last_borderTopRightRadius = page_icon_first_borderTopLeftRadius;

  var icon_width = $( "#page_icon_bar img" )[0].naturalWidth * magnification;
  var icon_height = $( "#page_icon_bar img" )[0].naturalHeight * magnification;
  
  $( "#page_icon_bar img" ).attr( "width", icon_width );
  $( "#page_icon_bar img" ).attr( "height", icon_height );
  
  jq_page_icons.css( {
    padding: page_icon_padding,
    borderWidth: page_icon_borderWidth,
    borderStyle: "solid",
    borderColor: "#181818",
    borderLeftWidth: 0
  } );

  jq_page_icons.first().css( {
    borderLeftWidth: page_icon_first_borderLeftWidth,
    borderTopLeftRadius: page_icon_first_borderTopLeftRadius
  } );

  jq_page_icons.last().css( {
    borderTopRightRadius: page_icon_last_borderTopRightRadius
  } );
} // resize_page_icon_bar()