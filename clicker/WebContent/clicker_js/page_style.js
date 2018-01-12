$( document ).ready( function() {
  jq_all_pages.css( {
    width: $( window ).width(),
    height: $( window ).height(),
    paddingBottom: jq_page_icon_bar_container.height()
  } );
  

  $( window ).resize( function() {
    jq_all_pages.css( {
      width: $( window ).width(),
      height: $( window ).height(),
      paddingBottom: jq_page_icon_bar_container.height()
    } );
  } );




  $( document ).on( "pageshow", "#group_page", function() {
    jq_group_page.css( {
      width: $( window ).width(),
      height: $( window ).height(),
      paddingBottom: jq_page_icon_bar_container.height()
    } );
  } );

  $( document ).on( "pageshow", "#answer_page", function() {
    jq_answer_page.css( {
      width: $( window ).width(),
      height: $( window ).height(),
      paddingBottom: jq_page_icon_bar_container.height()
    } );
  } );

  $( document ).on( "pageshow", "#rank_page", function() {
    jq_rank_page.css( {
      width: $( window ).width(),
      height: $( window ).height(),
      paddingBottom: jq_page_icon_bar_container.height()
    } );
  } );
} );