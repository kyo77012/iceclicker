
var rank_page_app = angular.module( "rank_page_app", [] );

rank_page_app.controller( "rank_page_ctrl", function( $scope, $http, $interval ) {
  function GetGroupRank() {
    $.post( "GetGroupRank",
            { group_number: group.number },
            function ( result, status ) {
              if ( status == "success" ) {
                group_rank = JSON.parse( result.json_data );
                console.log( group_rank );
                $scope.group_rank = group_rank;
                console.log( "GetGroupRank done!" );
              } // if
              else {
                console.log( "GetGroupRank failed! http status:" + status );
              } // else
            } );
  } // GetGroupRank()  

  function GetTopThreeGroup() {
    $http.post( "GetTopThreeGroup" )
         .then( function( response ) {
           console.log( response.data );
           top_three = JSON.parse( response.data.json_data );
           console.log( top_three );
           if ( top_three != null ) {
             $scope.top_three = top_three;
           } // if
           else {
             $scope.top_three = [];
           } // else
           
           console.log( "GetTopThreeGroup done!" );
         }, function( response ) { 
           console.log( "GetTopThreeGroup failed! " + response.statusText );
         } );
  } // GetTopThreeGroup()
  
  GetGroupRank();
  $interval( GetGroupRank, 30000 );
  
  GetTopThreeGroup();
  $interval( GetTopThreeGroup, 30000 );
} );
