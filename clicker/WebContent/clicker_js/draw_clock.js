function Draw_clock_border_light() {
  var clock = jq_clock[0];
  var ctx = clock.getContext( "2d" );
  var clock_border_light_off_color = "#282828"; 
  ctx.lineWidth = 10;
  ctx.lineCap = "round";
  ctx.lineJoin = "round";

  ctx.beginPath();

  ctx.moveTo( ctx.lineWidth / 2, ctx.lineWidth / 2 );
  ctx.lineTo( ctx.lineWidth / 2, clock.height - ctx.lineWidth / 2 );

  ctx.moveTo( clock.width - ctx.lineWidth / 2, ctx.lineWidth / 2 );
  ctx.lineTo( clock.width - ctx.lineWidth / 2, clock.height - ctx.lineWidth / 2 );

  /*
  var grd = ctx.createLinearGradient( 0, 0, 0, clock.height );
  grd.addColorStop( 0, "#A80000 "  );
  grd.addColorStop( 0.25, "#FF0000"  );
  grd.addColorStop( 0.5, "#A80000 " );
  grd.addColorStop( 0.75, "#FF0000" );
  grd.addColorStop( 1, "#A80000 " );
  ctx.strokeStyle = grd;
  ctx.stroke();
  ctx.closePath();
  */


  /*
  ctx.beginPath();
  */
  ctx.moveTo( ctx.lineWidth / 2, ctx.lineWidth / 2 );
  ctx.lineTo( clock.width - ctx.lineWidth / 2, ctx.lineWidth / 2 );

  ctx.moveTo( ctx.lineWidth / 2, clock.height - ctx.lineWidth / 2 );
  ctx.lineTo( clock.width - ctx.lineWidth / 2, clock.height - ctx.lineWidth / 2 );

  /*
  grd = ctx.createLinearGradient( 0, 0, clock.width, 0 );
  grd.addColorStop( 0, "#A80000 "  );
  grd.addColorStop( 0.25, "#FF0000"  );
  grd.addColorStop( 0.5, "#A80000 " );
  grd.addColorStop( 0.75, "#FF0000" );
  grd.addColorStop( 1, "#A80000 " );
  ctx.strokeStyle = grd;
  */

  ctx.strokeStyle = clock_border_light_off_color;

  ctx.stroke();
  ctx.closePath();
} // Draw_clock_border_light()



function Update_clock_size_and_position() {
  // clock width:height = 400:250
  var clock_aspect_ratio = 400 / 250;
  var clock_width = window.innerWidth * 0.3;
  var clock = {
    width: clock_width,
    height: clock_width / clock_aspect_ratio,
    borderTopLeftRadius: 15 / 400 * clock_width,
    borderTopRightRadius: 110 / 400 * clock_width,
    borderBottomRightRadius: 60 / 400 * clock_width,
    borderBottomLeftRadius: 110 / 400 * clock_width,
    padding: 17 / 400 * clock_width
  };

  $( "#clock" ).css( {
    width: clock.width,
    height: clock.height,
    borderTopLeftRadius: clock.borderTopLeftRadius,
    borderTopRightRadius: clock.borderTopRightRadius,
    borderBottomRightRadius: clock.borderBottomRightRadius,
    borderBottomLeftRadius: clock.borderBottomLeftRadius,
    padding: clock.padding
  } );


  // number width:height = 100:200
  var numbers = {
    width: 100 / 400 *  $( "#clock" ).width(),
    height: 200 / 400 * $( "#clock" ).width(),
    borderRadius: 10 / 400 * $( "#clock" ).width(),
    tens: null,
    digits: null,
    sections: null
  };

  $( ".number" ).css( {
    width: numbers.width,
    height: numbers.height,
    borderRadius: numbers.borderRadius
  } );

  numbers.tens = {
    left: ( clock_width - 2 * numbers.width ) / 5 * 2
  };

  numbers.digits = {
    left: ( clock_width - 2 * numbers.width ) / 5 * 3 + numbers.width
  };

  $( "#tens" ).css( {
    left: numbers.tens.left
  } );

  $( "#digits" ).css( {
    left: numbers.digits.left
  } );

  numbers.sections = {
    borderWidth: 17 / 100 * numbers.width,
    top: {
      top: 4 / 100 * numbers.width,
      left: 6 / 100 * numbers.width,
      width: 88 / 100 * numbers.width 
    },
    topRight: {
      top: 6 / 100 * numbers.width,
      right: 4 / 100 * numbers.width,
      height: 90 / 100 * numbers.width
    },
    topLeft: {
      top: 6 / 100 * numbers.width,
      left: 4 / 100 * numbers.width,
      height: 90 / 100 * numbers.width
    },
    middle: null,
      bottomRight: {
      bottom: 6 / 100 * numbers.width,
      right: 4 / 100 * numbers.width,
      height: 90 / 100 * numbers.width
    },
    bottomLeft: {
      bottom: 6 / 100 * numbers.width,
      left: 4 / 100 * numbers.width,
      height: 90 / 100 * numbers.width
    },
    bottom: {
      bottom: 4 / 100 * numbers.width,
      left: 6 / 100 * numbers.width,
      width: 88 / 100 * numbers.width 
    }
  }

  numbers.sections.middle = {
    middleTop: {
      top: numbers.height / 2 - numbers.sections.borderWidth - numbers.sections.borderWidth / 2,
      left: 6 / 100 * numbers.width,
      width: 88 / 100 * numbers.width,
      borderRightWidth: numbers.sections.borderWidth,
      borderLeftWidth: numbers.sections.borderWidth,
      borderTopWidth: numbers.sections.borderWidth,
      borderBottomWidth: numbers.sections.borderWidth / 2 + numbers.sections.bottom.bottom / 2
    },
    middleBottom: {
      top: numbers.height / 2 - numbers.sections.bottom.bottom / 2,
      left: 6 / 100 * numbers.width,
      width: 88 / 100 * numbers.width,
      borderRightWidth: numbers.sections.borderWidth,
      borderLeftWidth: numbers.sections.borderWidth,
      borderBottomWidth: numbers.sections.borderWidth,
      borderTopWidth: numbers.sections.borderWidth / 2 + numbers.sections.top.top
    }
  };

  $( ".section" ).css( {
    borderWidth: numbers.sections.borderWidth
  } );

  $( ".top" ).css( numbers.sections.top );
  $( ".top-right" ).css( numbers.sections.topRight );
  $( ".top-left" ).css( numbers.sections.topLeft );
  $( ".middle-top" ).css( numbers.sections.middle.middleTop );
  $( ".middle-bottom" ).css( numbers.sections.middle.middleBottom );
  $( ".bottom-right" ).css( numbers.sections.bottomRight );
  $( ".bottom-left" ).css( numbers.sections.bottomLeft );
  $( ".bottom" ).css( numbers.sections.bottom );
} // Update_clock_size_and_position()



var number_define_table = [ { number:0, top:1, topRight:1, topLeft:1, middle:0, bottomRight:1, bottomLeft:1, bottom:1 },
                            { number:1, top:0, topRight:1, topLeft:0, middle:0, bottomRight:1, bottomLeft:0, bottom:0 },
                            { number:2, top:1, topRight:1, topLeft:0, middle:1, bottomRight:0, bottomLeft:1, bottom:1 },
                            { number:3, top:1, topRight:1, topLeft:0, middle:1, bottomRight:1, bottomLeft:0, bottom:1 },
                            { number:4, top:0, topRight:1, topLeft:1, middle:1, bottomRight:1, bottomLeft:0, bottom:0 },
                            { number:5, top:1, topRight:0, topLeft:1, middle:1, bottomRight:1, bottomLeft:0, bottom:1 },
                            { number:6, top:1, topRight:0, topLeft:1, middle:1, bottomRight:1, bottomLeft:1, bottom:1 },
                            { number:7, top:1, topRight:1, topLeft:0, middle:0, bottomRight:1, bottomLeft:0, bottom:0 },
                            { number:8, top:1, topRight:1, topLeft:1, middle:1, bottomRight:1, bottomLeft:1, bottom:1 },
                            { number:9, top:1, topRight:1, topLeft:1, middle:1, bottomRight:1, bottomLeft:0, bottom:1 } ];

function UpdateNumber( num ) {
  var tens = parseInt( num / 10 );
  var digits = num % 10;
  // console.log( tens + " " + digits );

  if ( number_define_table[tens].top == 0 ) {
    $( "#tens .top" ).removeClass( "top-on" );
    $( "#tens .top" ).addClass( "top-off" );
  } // if
  else {
    $( "#tens .top" ).removeClass( "top-off" );
    $( "#tens .top" ).addClass( "top-on" );
  } // else

  if ( number_define_table[tens].topRight == 0 ) {
    $( "#tens .top-right" ).removeClass( "top-right-on" );
    $( "#tens .top-right" ).addClass( "top-right-off" );
  } // if
  else {
    $( "#tens .top-right" ).removeClass( "top-right-off" );
    $( "#tens .top-right" ).addClass( "top-right-on" );
  } // else

  if ( number_define_table[tens].topLeft == 0 ) {
    $( "#tens .top-left" ).removeClass( "top-left-on" );
    $( "#tens .top-left" ).addClass( "top-left-off" );
  } // if
  else {
    $( "#tens .top-left" ).removeClass( "top-left-off" );
    $( "#tens .top-left" ).addClass( "top-left-on" );
  } // else

  if ( number_define_table[tens].middle == 0 ) {
    $( "#tens .middle-top" ).removeClass( "middle-top-on" );
    $( "#tens .middle-bottom" ).removeClass( "middle-bottom-on" );

    $( "#tens .middle-top" ).addClass( "middle-top-off" );
    $( "#tens .middle-bottom" ).addClass( "middle-bottom-off" );
  } // if
  else {
    $( "#tens .middle-top" ).removeClass( "middle-top-off" );
    $( "#tens .middle-bottom" ).removeClass( "middle-bottom-off" );

    $( "#tens .middle-top" ).addClass( "middle-top-on" );
    $( "#tens .middle-bottom" ).addClass( "middle-bottom-on" );
  } // else

  if ( number_define_table[tens].bottomRight == 0 ) {
    $( "#tens .bottom-right" ).removeClass( "bottom-right-on" );
    $( "#tens .bottom-right" ).addClass( "bottom-right-off" );
  } // if
  else {
    $( "#tens .bottom-right" ).removeClass( "bottom-right-off" );
    $( "#tens .bottom-right" ).addClass( "bottom-right-on" );
  } // else

  if ( number_define_table[tens].bottomLeft == 0 ) {
    $( "#tens .bottom-left" ).removeClass( "bottom-left-on" );
    $( "#tens .bottom-left" ).addClass( "bottom-left-off" );
  } // if
  else {
    $( "#tens .bottom-left" ).removeClass( "bottom-left-off" );
    $( "#tens .bottom-left" ).addClass( "bottom-left-on" );
  } // else

  if ( number_define_table[tens].bottom == 0 ) {
    $( "#tens .bottom" ).removeClass( "bottom-on" );
    $( "#tens .bottom" ).addClass( "bottom-off" );
  } // if
  else {
    $( "#tens .bottom" ).removeClass( "bottom-off" );
    $( "#tens .bottom" ).addClass( "bottom-on" );
  } // else






  if ( number_define_table[digits].top == 0 ) {
    $( "#digits .top" ).removeClass( "top-on" );
    $( "#digits .top" ).addClass( "top-off" );
  } // if
  else {
    $( "#digits .top" ).removeClass( "top-off" );
    $( "#digits .top" ).addClass( "top-on" );
  } // else

  if ( number_define_table[digits].topRight == 0 ) {
    $( "#digits .top-right" ).removeClass( "top-right-on" );
    $( "#digits .top-right" ).addClass( "top-right-off" );
  } // if
  else {
    $( "#digits .top-right" ).removeClass( "top-right-off" );
    $( "#digits .top-right" ).addClass( "top-right-on" );
  } // else

  if ( number_define_table[digits].topLeft == 0 ) {
    $( "#digits .top-left" ).removeClass( "top-left-on" );
    $( "#digits .top-left" ).addClass( "top-left-off" );
  } // if
  else {
    $( "#digits .top-left" ).removeClass( "top-left-off" );
    $( "#digits .top-left" ).addClass( "top-left-on" );
  } // else

  if ( number_define_table[digits].middle == 0 ) {
    $( "#digits .middle-top" ).removeClass( "middle-top-on" );
    $( "#digits .middle-bottom" ).removeClass( "middle-bottom-on" );

    $( "#digits .middle-top" ).addClass( "middle-top-off" );
    $( "#digits .middle-bottom" ).addClass( "middle-bottom-off" );
  } // if
  else {
    $( "#digits .middle-top" ).removeClass( "middle-top-off" );
    $( "#digits .middle-bottom" ).removeClass( "middle-bottom-off" );

    $( "#digits .middle-top" ).addClass( "middle-top-on" );
    $( "#digits .middle-bottom" ).addClass( "middle-bottom-on" );
  } // else

  if ( number_define_table[digits].bottomRight == 0 ) {
    $( "#digits .bottom-right" ).removeClass( "bottom-right-on" );
    $( "#digits .bottom-right" ).addClass( "bottom-right-off" );
  } // if
  else {
    $( "#digits .bottom-right" ).removeClass( "bottom-right-off" );
    $( "#digits .bottom-right" ).addClass( "bottom-right-on" );
  } // else

  if ( number_define_table[digits].bottomLeft == 0 ) {
    $( "#digits .bottom-left" ).removeClass( "bottom-left-on" );
    $( "#digits .bottom-left" ).addClass( "bottom-left-off" );
  } // if
  else {
    $( "#digits .bottom-left" ).removeClass( "bottom-left-off" );
    $( "#digits .bottom-left" ).addClass( "bottom-left-on" );
  } // else

  if ( number_define_table[digits].bottom == 0 ) {
    $( "#digits .bottom" ).removeClass( "bottom-on" );
    $( "#digits .bottom" ).addClass( "bottom-off" );
  } // if
  else {
    $( "#digits .bottom" ).removeClass( "bottom-off" );
    $( "#digits .bottom" ).addClass( "bottom-on" );
  } // else
} // UpdateNumber()


function Control_clock_border_light( on ) {
  var clock = document.getElementById( "clock" );
  var ctx = clock.getContext( "2d" );
  ctx.lineWidth = 10;
  ctx.lineCap = "round";
  ctx.lineJoin = "round";

  var begin_x = ctx.lineWidth / 2;
  var begin_y = ctx.lineWidth / 2;
  var end_x = clock.width - ctx.lineWidth / 2;
  var end_y = clock.height - ctx.lineWidth / 2;


  ctx.beginPath();
  if ( on ) {
    ctx.moveTo( begin_x, begin_y );
    ctx.lineTo( begin_x, end_y );

    ctx.moveTo( end_x, begin_y );
    ctx.lineTo( end_x, end_y );

    var grd = ctx.createLinearGradient( begin_x, begin_y, begin_x, end_y );
    grd.addColorStop( 0, "#800000"  );
    grd.addColorStop( 0.25, "#FF0000"  );
    grd.addColorStop( 0.5, "#800000" );
    grd.addColorStop( 0.75, "#FF0000" );
    grd.addColorStop( 1, "#800000" );
    ctx.strokeStyle = grd;
    ctx.stroke();
    ctx.closePath();

    ctx.beginPath();

    ctx.moveTo( begin_x, begin_y );
    ctx.lineTo( end_x, begin_y );

    ctx.moveTo( begin_x, end_y );
    ctx.lineTo( end_x, end_y );

    grd = ctx.createLinearGradient( begin_x, begin_y, end_x, end_y );
    grd.addColorStop( 0, "#800000"  );
    grd.addColorStop( 0.25, "#FF0000"  );
    grd.addColorStop( 0.5, "#800000 " );
    grd.addColorStop( 0.75, "#FF0000" );
    grd.addColorStop( 1, "#800000 " );
    ctx.strokeStyle = grd;
  } // if
  else {
    ctx.moveTo( begin_x, begin_y );
    ctx.lineTo( begin_x, end_y );

    ctx.moveTo( end_x, begin_y );
    ctx.lineTo( end_x, end_y );

    ctx.moveTo( begin_x, begin_y );
    ctx.lineTo( end_x, begin_y );

    ctx.moveTo( begin_x, end_y );
    ctx.lineTo( end_x, end_y );

    ctx.strokeStyle = "#282828";
  } // else

  ctx.stroke();
  ctx.closePath();
} // Control_clock_border_light()


function Count_down( begin, end, delay ) {
  var num = begin;
  var clock_border_light_on = false;
  Control_clock_border_light( clock_border_light_on );

  clock_interval = setInterval( function() {
    UpdateNumber( num );
    if ( num == 0 ) {
      clock_border_light_on = true;
      Control_clock_border_light( clock_border_light_on );
    } // if
    else {
      if ( clock_border_light_on ) {
        clock_border_light_on = false;
        Control_clock_border_light( clock_border_light_on );
      } // if
    } // else if


    if ( num == end ) {
      // num = begin;
      clearInterval( clock_interval );
    } // if
    else num--;
  }, delay, clock_interval );
} // Count_down()

var clock_interval = null;

$( document ).ready( function() {
  Update_clock_size_and_position();
  
  /*
  var begin, end, delay;
  Count_down( begin = 20, end = 0, delay = 300 );
  */
} );

$( document ).on( "Reset_clock", function() {
  if ( clock_interval != null ) {
    clearInterval( clock_interval );
    clock_interval = null;
  } // if
  
  var begin, end, delay;
  Count_down( begin = 20, end = 0, delay = 1000 );
} );

$( window ).resize( Update_clock_size_and_position );