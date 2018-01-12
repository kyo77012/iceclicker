


var ans_already_check = false;

$( document ).ready( function() {
  Add_options_form_observer();
  $( window ).resize( Resize_options_form );
  Resize_options_form();
  
  jq_answer_page.on( "answer_page.ResetOptionBtn", ResetOptionBtn );
  jq_answer_page.on( "answer_page.CheckAns", CheckAns );
} );



function Add_options_form_observer() {
  var options_form_observer = new MutationObserver( function( mutations ) {
    // mutations.forEach( function( mutation ) {
      jq_answer_page = $( "#answer_page" );
      jq_options_form_container = jq_answer_page.children( "#options_form_container" );
      jq_options_form = jq_options_form_container.children( "#options_form" );
      jq_options = $( "#options_form input[type=radio]" );
      jq_options_labels = $( "#options_form label.my-input-label" );
      Resize_options_form();
      console.log( "#answer_page LoadView" );
      
    // $( document ).trigger( "Reset_clock" );
    // } );
  } );

  var options_form_observer_config = { childList:true, subtree: true, attributes: true, attributeFilter: ["value"] };

  options_form_observer.observe( jq_options_form[0], options_form_observer_config );
} // Add_options_form_observer()



function on_option_click( obj ) {
  if ( currentQuestion == null || currentQuestion.done ) return ;
  
  jq_selected_option = $( obj );
  jq_selected_option_label = jq_selected_option.parent().children( "label.my-input-label" );

  // console.log( jq_selected_option_label );
  
  studentAns = { student_id: student.id,
                 question_id: currentQuestion.id,
                 option_id: jq_selected_option.attr( "value" ) };
  
  $.post( "/clicker/ReceiveAns",
          studentAns,
          function( result, status ) {
            if ( status == "success" ) {
              if ( result.response_type != "OK" ) {
                console.log( result.response_type + " " + JSON.parse( result.json_data ) );
              } // if
              else {
                jq_options_labels.css( {
                  "box-shadow": "none",
                  "background-color": "#F6F6F6"
                } );
        
                jq_options_labels.removeClass( "ui-radio-on" );
                jq_options_labels.addClass( "ui-radio-off" );
        
                jq_selected_option_label.css( {
                  "box-shadow": "0px 0px 25px #3366CC",
                  "background-color": "#FFFF66"
                } );
        
                jq_selected_option_label.removeClass( "ui-radio-off" );
                jq_selected_option_label.addClass( "ui-radio-on" );

                var player = $( obj ).attr( "player" );
                $( "#player_1" ).attr( "src", "/clicker/body/" + player + ".png" );
                $( "#player_1" ).css( "display", "" );
                
                console.log( JSON.parse( result.json_data ) );
              } // else
            } // if
            else {
              alert( "http status:" + status );
            } // else
          } );
} // on_option_click()



function Resize_options_form() {
  var magnification = $( window ).height() / 626;
  if ( magnification > 1 ) magnification = 1;
  else if ( magnification < 0.75 ) magnification = 0.75;

  var option_label_fontSize = parseFloat( jq_options_labels.css( "font-size" ) );
  var option_label_height = 44.375 * magnification;
  var option_label_borderWidth = parseFloat( jq_options_labels.css( "border-width" ) );
  var option_label_paddingTop = ( option_label_height - option_label_fontSize - 2 * option_label_borderWidth ) / 2;
  var option_label_paddingBottom = option_label_paddingTop;

  jq_options_labels.css( {
    height: option_label_height,
    paddingTop: option_label_paddingTop,
    paddingBottom: option_label_paddingBottom,
  } );


  $( "#options_form_container table tr td" ).css( {
    borderWidth: 5 * magnification
  } );

  $( "#options_form_container table tr .player-face" ).css( {
    borderRightWidth: 0
  } );

  $( "#options_form_container table tr .player-name" ).css( {
    borderLeftWidth: 0
  } );


  $( ".player-name .ui-radio" ).css( {
    marginTop: 12 * magnification,
    marginBottom: 12 * magnification,
  } );


  $( ".player-face" ).css( {
    height: 73 * magnification
  } );


  $( ".player-face img" ).css( {
    width: 48 * magnification,
    height: 60 * magnification
  } );
} // Resize_options_form()








var answer_page_app = angular.module( "answer_page_app", [] );
answer_page_app.controller( "answer_page_ctrl", function( $rootScope, $scope, $http, $interval ) {
  function GetCurrentQuestion() {
    $http.post( "CurrentQuestion" )
         .then( function( response ) {
           console.log( response.data );
           var oldCurrentQuestion = currentQuestion;
           currentQuestion = JSON.parse( response.data.json_data );
           console.log( currentQuestion );
           if ( currentQuestion != null ) {
             $scope.question_content = currentQuestion.content;
             $scope.options = currentQuestion.options;
           } // if
           else {
             $scope.question_content = "等待題目";
             $scope.options = [];
           } // else
           
           
           if ( currentQuestion != null &&
                ( oldCurrentQuestion == null ||
                  oldCurrentQuestion.id != currentQuestion.id ||
                  oldCurrentQuestion.done != currentQuestion.done ) ) {
             jq_answer_page.trigger( "answer_page.ResetOptionBtn" );
             ans_already_check = false;
           } // if
           
           if ( !ans_already_check &&
               currentQuestion != null &&
               currentQuestion.done ) {
            jq_answer_page.trigger( "answer_page.CheckAns" );
            ans_already_check = true;
          } // if
           
         }, function( response ) { 
           console.log( response.statusText );
         } );
  } // GetCurrentQuestion()
  
  GetCurrentQuestion();
  $interval( GetCurrentQuestion, 3000 );
} );


function CheckAns() {
  if ( student != null && currentQuestion != null ) {
    /*
    jq_options.attr( "disabled", true );
    */
    if ( jq_selected_option_label != null ) {
      jq_selected_option_label.addClass( "ui-radio-on" );
      jq_selected_option_label.css( {
        "box-shadow": "0px 0px 25px #3366CC",
      } );
    } // if
    
    $.post( "/clicker/CheckAns",
            { student_id: student.id,
              question_id: currentQuestion.id },
            function( result, status ) {
              if ( status == "success" ) {
                if ( result.response_type != "OK" ) {
                  console.log( result.response_type + " " + JSON.parse( result.json_data ) );
                } // if
                else {
                  var ans_and_record = JSON.parse( result.json_data );
                  
                  if ( jq_options_labels != null ) {
                    jq_options_labels.css( {
                      "background-color": "#FF6666"
                    } );
                  } // if
                  
                  $( "#options_form input[value=" + ans_and_record.ans_option_id + "]" ).parent().children( "label.my-input-label" ).css( {
                    "background-color": "#66FF66"
                  } );
                  
                  if ( ans_and_record.record != null ) {
                    $( "#options_form input[value=" + ans_and_record.record.option_id + "]" ).parent().children( "label.my-input-label" ).css( {
                      "box-shadow": "0px 0px 25px #3366CC"
                    } ).removeClass( "ui-radio-off" ).addClass( "ui-radio-on" );
                    
                    var player = $( "#options_form input[value=" + ans_and_record.record.option_id + "]" ).attr( "player" );
                    if ( player != null ) {
                      $( "#player_1" ).attr( "src", "/clicker/body/" + player + ".png" );
                      $( "#player_1" ).css( "display", "" );
                    } // if
                  } // if
                  
                  if ( ans_and_record.record != null &&
                       ans_and_record.record.option_id == ans_and_record.ans_option_id ) {
                    correct = true;
                  } // if
                  else correct = false;
                  
                  if ( ans_and_record.record != null )
                    jq_answer_page.trigger( "Throw_ball" );
                } // else
              } // if
              else {
                alert( "http status:" + status );
              } // else
            } );
  } // if
} // CheckAns()

function ResetOptionBtn() {
  jq_options_labels.removeClass( "ui-radio-on" ).addClass( "ui-radio-off" );
  /*
  jq_options.attr( "disabled", false );
  */
  jq_options_labels.css( {
    "box-shadow": "none",
    "background-color": "#F6F6F6"
  } );
  
  $( "#player_1" ).css( "display", "none" );
} // ResetOptionBtn()
