$( document ).ready( function() {
  var group_bar_container = $( "#group-bar-container" );

  group_bar_container.toolbar();

  group_bar_container.css( {
    borderWidth: 0,
    background: "transparent",
    opacity: 1
  } );


  var group_members = $( ".my-group-member" );
  group_members.css( {
    fontSize: "1em",
    margin: "0",
    borderRadius: "0px"
  } );

  group_members.first().css( {
    borderTopLeftRadius: "20px"
  } );

  group_members.last().css( {
    borderTopRightRadius: "20px"
  } );
} );