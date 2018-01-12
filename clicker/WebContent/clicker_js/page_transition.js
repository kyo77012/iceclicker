$( document ).ready( function() {
  /*
  var current_page;
  var next_page_id;
  var prev_page_id;
  for ( var i = 0 ; i < jq_all_pages.length ; i++ ) {
    current_page = jq_all_pages[i];
    prev_page_id = null;
    next_page_id = null;

    if ( i - 1 >= 0 ) {
      prev_page_id = "#" + $( jq_all_pages[i-1] ).attr( "id" );
      // console.log( prev_page_id );
    } // if
    if ( i + 1 < jq_all_pages.length ) {
      next_page_id = "#" + $( jq_all_pages[i+1] ).attr( "id" );
      // console.log( next_page_id );
    } // if
    page_transition( current_page, prev_page_id, next_page_id );
  } // for

  function page_transition( current_page, prev_page_id, next_page_id ) {
    if ( prev_page_id != null ) {
      $( current_page ).on( "swiperight", function() {
      // console.log( "right" );
      jq_pagecontainer.pagecontainer( "change",
                                      prev_page_id,
                                      { transition: "slide", reverse: true } );
                                      // reverse == true 向右滑填
                                      //         == false 向左滑填
      } );
    } // if

    if ( next_page_id != null ) {
      $( current_page ).on( "swipeleft", function() {
      // console.log( "left" );
      jq_pagecontainer.pagecontainer( "change",
                                      next_page_id,
                                      { transition: "slide", reverse: false } );
                                      // reverse == true 向右滑填
                                      //         == false 向左滑填
      } );
    } // if
  } // page_transition()
  */


  jq_group_page.on( "swipeleft", function() {
    jq_pagecontainer.pagecontainer( "change",
                                    "#" + jq_answer_page.attr( "id" ),
                                    { transition: "slide", reverse: false } );
                                    // reverse == true 向右滑填
                                    //         == false 向左滑填
  } );



  jq_answer_page.on( "swipeleft", function() {
    jq_pagecontainer.pagecontainer( "change",
                                    "#" + jq_rank_page.attr( "id" ),
                                    { transition: "slide", reverse: false } );
                                    // reverse == true 向右滑填
                                    //         == false 向左滑填
  } );

  jq_answer_page.on( "swiperight", function() {
    jq_pagecontainer.pagecontainer( "change",
                                    "#" + jq_group_page.attr( "id" ),
                                    { transition: "slide", reverse: true } );
                                    // reverse == true 向右滑填
                                    //         == false 向左滑填
  } );



  jq_rank_page.on( "swiperight", function() {
    jq_pagecontainer.pagecontainer( "change",
                                    "#" + jq_answer_page.attr( "id" ),
                                    { transition: "slide", reverse: true } );
                                    // reverse == true 向右滑填
                                    //         == false 向左滑填
  } );



  
  

  jq_group_page_icon.click( function() {
    if ( !jq_group_page.hasClass( "ui-page-active" ) ) {
      jq_pagecontainer.pagecontainer( "change",
                                      "#" + jq_group_page.attr( "id" ),
                                      { transition: "pop" } );
    } // if
  } );

  jq_answer_page_icon.click( function() {
    if ( !jq_answer_page.hasClass( "ui-page-active" ) ) {
      jq_pagecontainer.pagecontainer( "change",
                                      "#" + jq_answer_page.attr( "id" ),
                                      { transition: "pop" } );
    } // if
  } );


  jq_rank_page_icon.click( function() {
    if ( !jq_rank_page.hasClass( "ui-page-active" ) ) {
      jq_pagecontainer.pagecontainer( "change",
                                      "#" + jq_rank_page.attr( "id" ),
                                      { transition: "pop" } );
    } // if
  } );
} );