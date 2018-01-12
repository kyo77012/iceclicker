function Control_group_bar() {
  var group_bar = $( "#group-bar" );
  group_bar.toggle( "drop", { direction: "down" }, function() {
    var group_bar_control_btn = $( "#group-bar-control-btn" );

    if ( group_bar_control_btn.hasClass( "ui-icon-carat-d" ) ) {
      group_bar_control_btn.removeClass( "ui-icon-carat-d" );
      group_bar_control_btn.addClass( "ui-icon-carat-u" );
    } // if
    else {
      group_bar_control_btn.removeClass( "ui-icon-carat-u" );
      group_bar_control_btn.addClass( "ui-icon-carat-d" );
    } // else
  } );
} // Control_group_bar()

$( document ).ready( function() {
  $( "#group-bar-control-btn" ).click( Control_group_bar );
} );