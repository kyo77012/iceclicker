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

var num = 60;
var clock_border_light_on = false;
Control_clock_border_light( clock_border_light_on );

setInterval( function() {
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


  if ( num == 0 ) num = 60;
  else num--;
}, 300 );


var jq_tens_contain = {
  top: jq_tens.children( ".top" ),
  topRight: jq_tens.children( ".top-right" ),
  topLeft: jq_tens.children( ".top-left" ),
  middleTop: jq_tens.children( ".middle-top" ),
  middleBottom: jq_tens.children( ".middle-bottom" ),
  bottomRight: jq_tens.children( ".bottom-right" ),
  bottomLeft: jq_tens.children( ".bottom-left" ),
  bottom: jq_tens.children( ".bottom" )
};

var jq_digits_contain = {
  top: jq_digits.children( ".top" ),
  topRight: jq_digits.children( ".top-right" ),
  topLeft: jq_digits.children( ".top-left" ),
  middleTop: jq_digits.children( ".middle-top" ),
  middleBottom: jq_digits.children( ".middle-bottom" ),
  bottomRight: jq_digits.children( ".bottom-right" ),
  bottomLeft: jq_digits.children( ".bottom-left" ),
  bottom: jq_digits.children( ".bottom" )
};


function UpdateNumber( num ) {
  var tens = parseInt( num / 10 );
  var digits = num % 10;
  // console.log( tens + " " + digits );

  if ( number_define_table[tens].top == 0 ) {
    jq_tens_contain.top.removeClass( "top-on" );
    jq_tens_contain.top.addClass( "top-off" );
  } // if
  else {
    jq_tens_contain.top.removeClass( "top-off" );
    jq_tens_contain.top.addClass( "top-on" );
  } // else

  if ( number_define_table[tens].topRight == 0 ) {
    jq_tens_contain.topRight.removeClass( "top-right-on" );
    jq_tens_contain.topRight.addClass( "top-right-off" );
  } // if
  else {
    jq_tens_contain.topRight.removeClass( "top-right-off" );
    jq_tens_contain.topRight.addClass( "top-right-on" );
  } // else

  if ( number_define_table[tens].topLeft == 0 ) {
    jq_tens_contain.topLeft.removeClass( "top-left-on" );
    jq_tens_contain.topLeft.addClass( "top-left-off" );
  } // if
  else {
    jq_tens_contain.topLeft.removeClass( "top-left-off" );
    jq_tens_contain.topLeft.addClass( "top-left-on" );
  } // else

  if ( number_define_table[tens].middle == 0 ) {
    jq_tens_contain.middleTop.removeClass( "middle-top-on" );
    jq_tens_contain.middleBottom.removeClass( "middle-bottom-on" );

    jq_tens_contain.middleTop.addClass( "middle-top-off" );
    jq_tens_contain.middleBottom.addClass( "middle-bottom-off" );
  } // if
  else {
    jq_tens_contain.middleTop.removeClass( "middle-top-off" );
    jq_tens_contain.middleBottom.removeClass( "middle-bottom-off" );

    jq_tens_contain.middleTop.addClass( "middle-top-on" );
    jq_tens_contain.middleBottom.addClass( "middle-bottom-on" );
  } // else

  if ( number_define_table[tens].bottomRight == 0 ) {
    jq_tens_contain.bottomRight.removeClass( "bottom-right-on" );
    jq_tens_contain.bottomRight.addClass( "bottom-right-off" );
  } // if
  else {
    jq_tens_contain.bottomRight.removeClass( "bottom-right-off" );
    jq_tens_contain.bottomRight.addClass( "bottom-right-on" );
  } // else

  if ( number_define_table[tens].bottomLeft == 0 ) {
    jq_tens_contain.bottomLeft.removeClass( "bottom-left-on" );
    jq_tens_contain.bottomLeft.addClass( "bottom-left-off" );
  } // if
  else {
    jq_tens_contain.bottomLeft.removeClass( "bottom-left-off" );
    jq_tens_contain.bottomLeft.addClass( "bottom-left-on" );
  } // else

  if ( number_define_table[tens].bottom == 0 ) {
    jq_tens_contain.bottom.removeClass( "bottom-on" );
    jq_tens_contain.bottom.addClass( "bottom-off" );
  } // if
  else {
    jq_tens_contain.bottom.removeClass( "bottom-off" );
    jq_tens_contain.bottom.addClass( "bottom-on" );
  } // else






  if ( number_define_table[digits].top == 0 ) {
    jq_digits.contain.top.removeClass( "top-on" );
    jq_digits.contain.top.addClass( "top-off" );
  } // if
  else {
    jq_digits.contain.top.removeClass( "top-off" );
    jq_digits.contain.top.addClass( "top-on" );
  } // else

  if ( number_define_table[digits].topRight == 0 ) {
    jq_digits.contain.topRight.removeClass( "top-right-on" );
    jq_digits.contain.topRight.addClass( "top-right-off" );
  } // if
  else {
    jq_digits.contain.topRight.removeClass( "top-right-off" );
    jq_digits.contain.topRight.addClass( "top-right-on" );
  } // else

  if ( number_define_table[digits].topLeft == 0 ) {
    jq_digits.contain.topLeft.removeClass( "top-left-on" );
    jq_digits.contain.topLeft.addClass( "top-left-off" );
  } // if
  else {
    jq_digits.contain.topLeft.removeClass( "top-left-off" );
    jq_digits.contain.topLeft.addClass( "top-left-on" );
  } // else

  if ( number_define_table[digits].middle == 0 ) {
    jq_digits.contain.middleTop.removeClass( "middle-top-on" );
    jq_digits.contain.middleBottom.removeClass( "middle-bottom-on" );

    jq_digits.contain.middleTop.addClass( "middle-top-off" );
    jq_digits.contain.middleBottom.addClass( "middle-bottom-off" );
  } // if
  else {
    jq_digits.contain.middleTop.removeClass( "middle-top-off" );
    jq_digits.contain.middleBottom.removeClass( "middle-bottom-off" );

    jq_digits.contain.middleTop.addClass( "middle-top-on" );
    jq_digits.contain.middleBottom.addClass( "middle-bottom-on" );
  } // else

  if ( number_define_table[digits].bottomRight == 0 ) {
    jq_digits.contain.bottomRight.removeClass( "bottom-right-on" );
    jq_digits.contain.bottomRight.addClass( "bottom-right-off" );
  } // if
  else {
    jq_digits.contain.bottomRight.removeClass( "bottom-right-off" );
    jq_digits.contain.bottomRight.addClass( "bottom-right-on" );
  } // else

  if ( number_define_table[digits].bottomLeft == 0 ) {
    jq_digits.contain.bottomLeft.removeClass( "bottom-left-on" );
    jq_digits.contain.bottomLeft.addClass( "bottom-left-off" );
  } // if
  else {
    jq_digits.contain.bottomLeft.removeClass( "bottom-left-off" );
    jq_digits.contain.bottomLeft.addClass( "bottom-left-on" );
  } // else

  if ( number_define_table[digits].bottom == 0 ) {
    jq_digits.contain.bottom.removeClass( "bottom-on" );
    jq_digits.contain.bottom.addClass( "bottom-off" );
  } // if
  else {
    jq_digits.contain.bottom.removeClass( "bottom-off" );
    jq_digits.contain.bottom.addClass( "bottom-on" );
  } // else
} // UpdateNumber()


function Control_clock_border_light( on ) {
  var clock = jq_clock[0];
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