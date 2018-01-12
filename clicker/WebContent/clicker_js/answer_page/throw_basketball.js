$( document ).ready( function() {
  jq_answer_page.on( "Throw_ball", function() {
    $( "#btn_start" ).trigger( "click" );
  } );
} );

$( "#btn_start" ).click( function() {
  jq_player_1.animate( {
    top: "-=" + ( 35 * magnification )
  }, "medium" ).animate( {
    top: "+=" + ( 35 * magnification )
  }, "medium" );

  jq_begin_point.animate( {
    top: "-=" + ( 35 * magnification )
  }, "medium" ).animate( {
    top: "+=" + ( 35 * magnification )
  }, "medium" );

  jq_basketball.css( {
    top: basketball_court_currentHeight * 0.5 - jq_basketball.height() * 0.5,
    left: basketball_court_currentWidth * 0.35 - jq_basketball.width() * 0.5
  } ).animate( {
    top: "-=" + ( 35 * magnification )
  }, "medium", Basketball_travel );
} );

function Basketball_travel() {
  var t = 12;

  var x, x0;
  var vx0;
  var ax = 0 * magnification;

  var y, y0;
  var vy0;
  var ay = 9.8 * magnification;


  var ground_line = basketball_court_currentHeight * 0.5;
        
  /*
    x - x0 = vx0 * t + 0.5 * ax * t * t
           = vx0 * t
           = vx0 * 3

    x = vx0 * t + x0


    y - y0 = vy0 * t + 0.5 * ay * t * t
           = vy0 * 3 + 0.5 * 9.8 * 3 * 3

    y = vy0 * t + 0.5 * ay * t * t + y0
  */

  x0 = parseFloat( jq_begin_point.css( "left" ) );
  y0 = parseFloat( jq_begin_point.css( "top" ) );

        
  x = parseFloat( jq_end_point.css( "left" ) );
  y = parseFloat( jq_end_point.css( "top" ) );

  // console.log( correct );
  if ( !correct ) {
    x -= Math.random() * 100 * magnification + 25;
  } // if
        
  vx0 = ( x - x0 ) / t;
  vy0 = ( ( y - y0 ) / t ) - ( 0.5 * ay * t )

  var basketball_begin_position = jq_basketball.position();

  var wish_over_time = 3;
  var speed_up = t / wish_over_time;
  var fps = 30;
  var fps_time_interval = 1000 / fps;
  var fps_t = 0;
  var ti = 0;
  var ti_x_offset = 0, ti_y_offset = 0; // 記錄上一次反彈的時間點
  var ti_x, ti_y; // 記錄上一次反彈後所經過的時間
  var current_x = x0, current_y = y0;

  var rotate_deg = 0;
  while ( ( current_x - basketball.width / 2 ) < $( window ).width() ) {
    // ti <= t + 4
    ti_x = ti - ti_x_offset;
    ti_y = ti - ti_y_offset;
    current_x = vx0 * ti_x + x0;
    current_y = vy0 * ti_y + 0.5 * ay * ti_y * ti_y + y0;
          
    if ( current_y > ground_line ) {
      var possible_ti_y = Quadratic_formula( 0.5 * ay, vy0, y0 - ground_line );
      /*
        ground_line = vy0 * ti_y + 0.5 * ay * ti_y * ti_y + y0
    ->  vy0 * ti_y + 0.5 * ay * ti_y * ti_y + y0 - ground_line = 0
            
        ax^2 + bx + c = 0
              
        x = ti_y
        a = 0.5 * ay
        b = vy0
        c = y0 - ground_line
      */
      if ( possible_ti_y[0] >= 0 && possible_ti_y[0] < ti_y ) {
        ti_y_offset += possible_ti_y[0];
      } // if
      else {
        ti_y_offset += possible_ti_y[1];
      } // else
      // 算出觸地時間 
            
      ti = ti_y_offset; // 因為時間沒走到ti_y
      ti_x = ti - ti_x_offset;
      ti_y = ti - ti_y_offset;
          
      current_x = vx0 * ti_x + x0;
      current_y = ground_line;
            
      vy0 = - ( vy0 * 0.8 );
      y0 = current_y;
    } // if
          
          
    // console.log( "$" + current_x + " " + current_y );
    fps_t += fps_time_interval;
    setTimeout( function( current_x, current_y, ti, fps_t, rotate_deg ) {
      jq_ball_point.css( {
        top: current_y,
        left: current_x
      } );

      jq_basketball.css( {
        top: current_y - basketball.height / 2,
        left: current_x - basketball.width / 2,
        transform: "rotate( " + rotate_deg + "deg )"
      } );

      // console.log( "!" + current_x + " " + current_y + " " + ti + " " + fps_t );
    }, fps_t, current_x, current_y, ti, fps_t, rotate_deg );

    ti += fps_time_interval / 1000 * speed_up;
    rotate_deg = ( rotate_deg + 30 ) % 360;
  } // while
} // Basketball_travel()
      
      
function Quadratic_formula( a, b, c ) {
  // 一元二次方程式公式解
  var x1 = ( -b + Math.sqrt( b * b - 4 * a * c ) ) / ( 2 * a );
  var x2 = ( -b - Math.sqrt( b * b - 4 * a * c ) ) / ( 2 * a );
  var x = [x1, x2];
  return x;
} // Quadratic_formula()
