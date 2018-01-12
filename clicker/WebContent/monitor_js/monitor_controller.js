
var currentQuestion = null;
var ReloadCache_url = "/clicker/ReloadCache";
var CurrentQuestion_url = "/clicker/CurrentQuestion";
var QuestionSwitcher_url = "/clicker/QuestionSwitcher";
var CurrentQuestionDone_url = "/clicker/CurrentQuestionDone";
var GetCurrentQuestionAllStudentsRecord_url = "/clicker/GetCurrentQuestionAllStudentsRecord";

var time = 0;
var clock_interval = null;

var monitor_app = new angular.module( "monitor_app", [] );
monitor_app.controller( "monitor_ctrl", function( $scope, $http ) {
  var updateInterval = null;
  function update() {
    $http.post(
        GetCurrentQuestionAllStudentsRecord_url )
        .then(
            function( response ) {
              studentRecordSet = JSON.parse( response.data.json_data );
              // console.log( studentRecordSet );
              $( "a.a_student" ).css( {
                backgroundColor : ""
              } );
              
              for ( var i = 0; i < studentRecordSet.length; i++ ) {
                $( "#" + studentRecordSet[i].remote_ctrl_number ).css( {
                  backgroundColor : "#6699FF"
                } );

                $( "#" + studentRecordSet[i].remote_ctrl_number )
                  .attr( "option_id", studentRecordSet[i].option_id );
              } // for
              
              if ( currentQuestion != null &&
                   currentQuestion.done ) CheckStudentAns();
            }, function( response ) {
              console.log( "update failed!" );
            } );
  } // update()

  $scope.students = studentSet;

  $scope.CheckStudentAns = CheckStudentAns;
  
  
  function CheckStudentAns() {
    /*
    if ( updateInterval != null ) {
      clearInterval( updateInterval );
      updateInterval = null;
    } // if
    */
    
    clearInterval( clock_interval );
    clock_interval = null;
    
    if ( currentQuestion == null ) alert( "目前沒有題目!" );
    else {
      if ( !currentQuestion.done ) {
        CurrentQuestionDone();
        currentQuestion.done = true;
      } // if
      else DrawColor();
    } // else 
  } // CheckStudentAns()
  
  
  function CurrentQuestionDone() {
    $http.post( CurrentQuestionDone_url ).then(
      function( response ) {
        console.log( response.data );
        if ( response.data.response_type != "OK" ) {
          console.log( result.data.response_type + " " + JSON.parse( result.data.json_data ) );
        } // if
        else {
          $.post( GetCurrentQuestionAllStudentsRecord_url,
                  {},
                  function( data, status ) {
                    if ( status == "success" ) {
                      var studentRecordSet = JSON.parse( data.json_data );
                      if ( data.response_type != "OK" ) {
                        console.log( data.response_type + " " + JSON.parse( data.json_data ) );
                      } // if
                      else {
                        $( "a.a_student" ).css( {
                          backgroundColor : ""
                        } );
                        
                        for ( var i = 0; i < studentRecordSet.length; i++ ) {
                          $( "#" + studentRecordSet[i].remote_ctrl_number ).css( {
                            backgroundColor : "#6699FF"
                          } );

                          $( "#" + studentRecordSet[i].remote_ctrl_number )
                            .attr( "option_id", studentRecordSet[i].option_id );
                        } // for
                        
                        DrawColor();
                        // console.log( "currentQuestionDone re-get studentRecordSet done." );
                      } // else
                   } // if
                   else {
                     console.log( "currentQuestionDone re-get studentRecordSet failed!" );
                   } // else
          } );
        } // else
      }, function( response ) {
        console.log( "currentQuestion done failed!" );
      } );
  } // CurrentQuestionDone()
    
  function DrawColor() {
    $( "a.a_student" ).css( {
      backgroundColor : "#FF6666"
    } );
    
    $( "a.a_student[option_id='']" ).css( {
      backgroundColor : "#FFCC66"
    } );

    $( "a.a_student[option_id=" + currentQuestion.ans_option_id + "]" ).css( {
      backgroundColor : "#66FF66"
    } );
  } // DrawColor()

  $scope.Reset = function() {
    if ( updateInterval == null ) {
      $( "a.a_student" ).css( {
        backgroundColor : ""
    } );
      
    update();
    if ( currentQuestion != null &&
         !currentQuestion.done )
      updateInterval = setInterval( update, 2000 );
    } // if
  } // function()
} );

$( document ).ready( function() {
  $( "[data-role=footer]" ).toolbar( {
    position : "fixed",
    fullscreen : true,
    disablePageZoom : true,
    tapToggle : false,
    tapToggleBlacklist : ".do-not-toggle-fixed-toolbar",
    hideDuringFocus : ""
  } );
} );

$( document ).ready( function() {
  $.post( QuestionSwitcher_url,
          { action: "current_question" },
          function( data, status ) {
    if ( status == "success" ) {
      var response = JSON.parse( data.json_data );
      if ( data.response_type != "OK" ) {
        console.log( data.response_type + " " + JSON.parse( data.json_data ) );
      } // if
      else {
        currentQuestion = response;
      } // else
      
      RefreshQuestion();
    } // if
    else {
      console.log( status );
    } // else
  } );
} );

$( "#reload_cache" ).click( function() {
  $.post( ReloadCache_url,
          {},
          function( data, status ) {
    if ( status == "success" ) {
      var response = JSON.parse( data.json_data );
      if ( data.response_type != "OK" ) {
        console.log( data.response_type + " " + JSON.parse( data.json_data ) );
      } // if

      alert( JSON.parse( data.json_data ) );
      location.reload( true );
    } // if
    else {
      console.log( status );
    } // else
  } );
} );


$( "#prev_question" ).click( function() {
  $.post( QuestionSwitcher_url,
          { action : "prev_question" },
          function( data, status ) {
    if ( status == "success" ) {
      var response = JSON.parse( data.json_data );
      if ( data.response_type != "OK" ) {
        console.log( data.response_type + " " + JSON.parse( data.json_data ) );
      } // if
      else {
        currentQuestion = response;
        
        time = 0;
      } // else

      RefreshQuestion();
    } // if
    else {
      console.log( status );
    } // else
  } );
} );

$( "#next_question" ).click( function() {
  $.post( QuestionSwitcher_url,
          { action : "next_question" },
          function( data, status ) {
    console.log( data );
    if ( status == "success" ) {
      var response = JSON.parse( data.json_data );
      if ( data.response_type != "OK" ) {
        console.log( data.response_type + " " + response );
        $( "#response" ).html( response );
      } // if
      else {
        currentQuestion = response;
        
        time = 0;
      } // else
      
      RefreshQuestion();
    } // if
    else {
      console.log( status );
    } // else
  } );
} );

function RefreshQuestion() {
  if ( currentQuestion == null ) {
    $( "#response" ).html( "目前沒有題目!" );
    
    clearInterval( clock_interval );
    clock_interval = null;
  } // if
  else {
    if ( currentQuestion.done ) {
      $( "#response" ).html( currentQuestion.content + "(已作答完畢)" );
      
      clearInterval( clock_interval );
      clock_interval = null;
    } // if
    else {
      $( "#response" ).html( currentQuestion.content );
      
      if ( clock_interval == null ) {
        clock_interval = setInterval( function() {
          var time_str = parseInt( time / 60 ) + ":";
          if ( time % 60 < 10 ) time_str += "0";
          time_str += time % 60;
          $( "#clock" ).html( time_str );
          time++;
        }, 1000 );
      } // if
    } // else
  } // else
  $( "#reset" ).trigger( "click" );
} // function()
