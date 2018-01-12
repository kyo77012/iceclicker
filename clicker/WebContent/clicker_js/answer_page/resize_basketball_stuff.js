// $( document ).ready( function() {
$( document ).ready( function() {
  Resize_and_basketball_court_and_place_stuff();
} );

/*
$( document ).on( "pagebeforeshow.my_resize_basketball_court",
                  "#" + jq_answer_page.attr( "id" ),
                  Resize_and_basketball_court_and_place_stuff );
*/

$( window ).resize( function() {
  if ( jq_answer_page.hasClass( "ui-page-active" ) )
    Resize_and_basketball_court_and_place_stuff();
} );


function Resize_and_basketball_court_and_place_stuff() {
  // console.log( "resize basketball court" );

  basketball_court_naturalWidth = jq_basketball_court[0].naturalWidth;
  basketball_court_naturalHeight = jq_basketball_court[0].naturalHeight;
  basketball_court_currentWidth = $( window ).width();
  magnification = ( basketball_court_currentWidth / basketball_court_naturalWidth ); // 放大比率
  
  // if ( magnification < 0.65 ) magnification = 0.65;
  
  basketball_court_currentHeight = basketball_court_naturalHeight * magnification; 

  player_1_naturalWidth = jq_player_1[0].naturalWidth;
  player_1_naturalHeight = jq_player_1[0].naturalHeight;

  basketball_naturalWidth = jq_basketball[0].naturalWidth;
  basketball_naturalHeight = jq_basketball[0].naturalHeight;

  basketball_stands_naturalWidth = jq_basketball_stands[0].naturalWidth;
  basketball_stands_naturalHeight = jq_basketball_stands[0].naturalHeight;

  basketball_rim_near_naturalWidth = jq_basketball_rim_near[0].naturalWidth;
  basketball_rim_near_naturalHeight = jq_basketball_rim_near[0].naturalHeight;

  basketball_rim_far_naturalWidth = jq_basketball_rim_far[0].naturalWidth;
  basketball_rim_far_naturalHeight = jq_basketball_rim_far[0].naturalHeight;

  jq_basketball_court.css( {
    width: basketball_court_currentWidth,
    height: basketball_court_currentHeight
  } );

  jq_player_1.css( {
    width: player_1_naturalWidth * magnification,
    height: player_1_naturalHeight * magnification
  } );

  jq_basketball.css( {
    width: basketball_naturalWidth * magnification,
    height: basketball_naturalHeight * magnification
  } );

  jq_basketball_stands.css( {
    width: basketball_stands_naturalWidth * magnification,
    height: basketball_stands_naturalHeight * magnification
  } );

  jq_basketball_rim_near.css( {
    width: basketball_rim_near_naturalWidth * magnification,
    height: basketball_rim_near_naturalHeight * magnification
  } );

  jq_basketball_rim_far.css( {
    width: basketball_rim_far_naturalWidth * magnification,
    height: basketball_rim_far_naturalHeight * magnification
  } );




  jq_end_point.css( {
    top: basketball_court_currentHeight * 0.17,
    left: basketball_court_currentWidth * 0.86
  } );

  jq_begin_point.css( {
    top: basketball_court_currentHeight * 0.5,
    left: basketball_court_currentWidth * 0.35
  } );

  jq_ball_point.css( {
    top: basketball_court_currentHeight * 0.5,
    left: basketball_court_currentWidth * 0.35
  } );




  jq_basketball.css( {
    top: basketball_court_currentHeight * 0.5 - jq_basketball.height() * 0.5,
    left: basketball_court_currentWidth * 0.35 - jq_basketball.width() * 0.5
  } );

  jq_player_1.css( {
    top: basketball_court_currentHeight * 0.5 - jq_player_1.height() * 0.75,
    left: basketball_court_currentWidth * 0.35 - jq_player_1.width() * 0.75
  } );

  jq_basketball_stands.css( {
    top: basketball_court_currentHeight * 0.5 - jq_basketball_stands.height() * 0.9,
    left: basketball_court_currentWidth * 0.88
  } );

  jq_basketball_rim_near.css( {
    top: basketball_court_currentHeight * 0.5 - jq_basketball_stands.height() * 0.7,
    left: basketball_court_currentWidth * 0.82
  } );

  jq_basketball_rim_far.css( {
    top: basketball_court_currentHeight * 0.5 - jq_basketball_stands.height() * 0.7,
    left: basketball_court_currentWidth * 0.82
  } );
} // Resize_and_basketball_court_and_place_stuff()