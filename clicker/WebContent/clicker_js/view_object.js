$( document ).ready( LoadView );

// $( document ).on( "DOMSubtreeModified", LoadView );

function LoadView() {
  /*
  jq_clock_container = $( "#clock_container" );
  jq_clock = jq_clock_container.children( "#clock" );
  jq_tens = jq_clock.children( "#tens" );
  jq_digits = jq_clock.children( "#digits" );
  */
// ---------------------------------------------------------------------------------

  jq_page_icon_bar_container = $( "#page_icon_bar_container" );
  jq_page_icon_bar = jq_page_icon_bar_container.children( "#page_icon_bar" );
  jq_page_icons = jq_page_icon_bar.children( ".ui-btn" );
  jq_group_page_icon = jq_page_icon_bar.children( "#group_page_icon" );
  jq_answer_page_icon = jq_page_icon_bar.children( "#answer_page_icon" );
  jq_rank_page_icon = jq_page_icon_bar.children( "#rank_page_icon" );
// ---------------------------------------------------------------------------------



  jq_pagecontainer = $( ":mobile-pagecontainer" );
  jq_all_pages = $( "[data-role='page']" );
// ---------------------------------------------------------------------------------
  jq_group_page = $( "#group_page" );



// ---------------------------------------------------------------------------------
  jq_answer_page = $( "#answer_page" );
  jq_options_form_container = jq_answer_page.children( "#options_form_container" );
  jq_options_form = jq_options_form_container.children( "#options_form" );
  jq_options = $( "#options_form input[type=radio]" );
  jq_options_labels = $( "#options_form label.my-input-label" );
  
  jq_selected_option = null;
  jq_selected_option_label = null;
  
  
  
  jq_basketball_court = $( "#basketball_court" );
  jq_player_1 = $( "#player_1" );
  jq_basketball = $( "#basketball" );
  jq_basketball_stands = $( "#basketball_stands" );
  jq_basketball_rim_near = $( "#basketball_rim_near" );
  jq_basketball_rim_far = $( "#basketball_rim_far" );

  jq_begin_point = $( "#begin_point" );
  jq_end_point = $( "#end_point" );
  jq_ball_point = $( "#ball_point" );
  magnification = 1;
// ---------------------------------------------------------------------------------




  jq_rank_page = $( "#rank_page" );
// ---------------------------------------------------------------------------------

  // console.log( "view_object.js LoadView" );
} // LoadView()

