/*
$( document ).ready( function() {
  $( "#group_page .list-item .approval" ).click( function() {
    var jq_evaluation = $( this ).siblings( ".evaluation" );
    var evaluation = parseInt( jq_evaluation.html() );
    evaluation++;
    if ( evaluation > 0 ) {
      evaluation = "+" + evaluation;
      jq_evaluation.css( "color", "red" );
    } // if
    else if ( evaluation < 0 ) {
      jq_evaluation.css( "color", "green" );
    } // else if
    else {
      jq_evaluation.css( "color", "#333" );
    } // else

    jq_evaluation[0].innerHTML = evaluation;
  } );

  $( "#group_page .list-item .disapproval" ).click( function() {
    var jq_evaluation = $( this ).siblings( ".evaluation" );
    var evaluation = parseInt( jq_evaluation.html() );
    evaluation--;
    if ( evaluation > 0 ) {
      evaluation = "+" + evaluation;
      jq_evaluation.css( "color", "red" );
    } // if
    else if ( evaluation < 0 ) {
      jq_evaluation.css( "color", "green" );
    } // else if
    else {
      jq_evaluation.css( "color", "#333" );
    } // else
    
    jq_evaluation[0].innerHTML = evaluation;
  } );
} );
*/

var group_page_app = angular.module( "group_page_app", [] );
group_page_app.controller( "group_page_ctrl", function ( $scope ) {
  $scope.group_number = group.number;
  $scope.members = group.members;
  console.log( group.members );
} );


