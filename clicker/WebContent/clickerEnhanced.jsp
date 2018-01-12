<%@page import="javax.crypto.Cipher"%>
<%@page import="service.*"%>
<%@page import="org.apache.http.HttpHeaders"%>
<%@page import="org.apache.http.HttpStatus"%>
<%@page import="cache.*" %>
<%@page import="data_struct.*" %>
<%@page import="com.google.gson.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    final String redirectURL = "/clicker/login.html";
    String id = request.getParameter( "id" );
    String name = request.getParameter( "name" );
    String str_group = request.getParameter( "group" );
    String cid = request.getParameter( "cid" );
    
    
    if ( ( id == null ||
           name == null ||
           str_group == null ||
           cid == null ) ||
           !Cid.CheckCid( cid, id ) ) {
      response.setStatus( HttpStatus.SC_MOVED_PERMANENTLY );
      response.setHeader( HttpHeaders.LOCATION, redirectURL );
      response.setHeader( HttpHeaders.CONNECTION, "close" );

      // response.sendRedirect( redirectURL );
      return ;
    } // if

    int group = Integer.parseInt( str_group );
    Gson gson = new Gson();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <title>clickerEnhanced</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--
    <link href="./bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="./jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>


    <script src="http://code.jquery.com/jquery-latest.min.js">
    -->


    <!--
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    -->

    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

    
    
    <!--
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    -->

    <script>
      <%
        String js_student = String.format( "var student = {id:'%s',name:'%s',group:%d,cid:'%s'};",
        		                           id, name, group, cid );
        out.println( js_student );
      %>
      <%
        String js_group = String.format( "var group = %s;",
        		                         gson.toJson( Group_cache.group_table.get( group ) ) ); 
        out.println( js_group );
      %>
      
      var currentQuestion = null;
      var studentAns = null;
      var correct = null;
      
      var top_three = null;
      var group_rank = null;
    </script>

    

    <link rel="stylesheet" href="clicker_style/global_style.css">
    <link rel="stylesheet" href="clicker_style/clock_style.css">
    <link rel="stylesheet" href="clicker_style/group_page/group_page_style.css">
    <link rel="stylesheet" href="clicker_style/answer_page/answer_page_style.css">
    <link rel="stylesheet" href="clicker_style/rank_page/rank_page_style.css">
  </head>


  <body oncontextmenu="return false;"
        ondragstart="return false;"
        onselectstart="return false;"
        ng-app="clicker_app">
    <!--
    <div id="clock_container" data-role="header" data-position="fixed" data-fullscreen="true"
         data-tap-toggle="false">
      <canvas id="clock"></canvas>
      <div id="tens" class="number">
        <div class="section top top-off"></div>
        <div class="section top-right top-right-off"></div>
        <div class="section top-left top-left-off"></div>
        <div class="section middle middle-top middle-top-off"></div>
        <div class="section middle middle-bottom middle-bottom-off"></div>
        <div class="section bottom-right bottom-right-off"></div>   
        <div class="section bottom-left bottom-left-off"></div>
        <div class="section bottom bottom-off"></div>
      </div>

      <div id="digits" class="number">
        <div class="section top top-off"></div>
        <div class="section top-right top-right-off"></div>
        <div class="section top-left top-left-off"></div>
        <div class="section middle middle-top middle-top-off"></div>
        <div class="section middle middle-bottom middle-bottom-off"></div>
        <div class="section bottom-right bottom-right-off"></div>   
        <div class="section bottom-left bottom-left-off"></div>
        <div class="section bottom bottom-off"></div>
      </div>
    </div>
    -->
    <div id="page_icon_bar_container" data-role="footer" data-position="fixed" data-tap-toggle="false"
             data-fullscreen="true" data-theme="b">
      <div id="page_icon_bar" class="ui-grid-b">
        <div id="group_page_icon" class="ui-block-a ui-btn">
          <div class="ui-corner-all">
            <img src="/clicker/icons/group_page_icon.png">
          </div>
        </div>
        <div id="answer_page_icon" class="ui-block-b ui-btn">
          <div class="ui-corner-all">
            <img src="/clicker/icons/answer_page_icon.png">
          </div>
        </div>
        <div id="rank_page_icon" class="ui-block-c ui-btn">
          <div class="ui-corner-all">
            <img src="/clicker/icons/rank_page_icon.png">
          </div>
        </div>
      </div>
    </div>

    <div id="answer_page" data-role="page" data-theme="b" class="my-page"
         ng-app="answer_page_app" ng-controller="answer_page_ctrl">
      <div id="options_form_container">
        <form id="options_form" data-iconpos="right">
          <table style="width: 100%;">
            <thead>
              <div class="ui-btn ui-mini">{{question_content}}</div>
            </thead>
            <tbody>
              <tr ng-repeat="option in options track by $index">
                <td class="player-face" data-theme="a">
                  <img src="/clicker/faces/{{$index+1}}.png">
                </td>
                <td class="player-name">
                  <input class="my-input" form="options_form" name="ans" type="radio" data-theme="a"
                         id="player_{{$index}}_name" value="{{option.id}}" onclick="on_option_click( this )"
                         player="{{$index+1}}">
                  <label class="my-input-label ui-mini" for="player_{{$index}}_name" style="color: black;">{{option.content}}</label>
                </td>
              </tr>

              <button id="btn_options_form_update" ng-click="dataUpdate()" style="display: none;">update</button>
            </tbody>
          </table>
        </form>
        <button id="btn_start" style="display: none;">start</button>
      </div>

      <div style="position:relative; top:0; left:0;">
        <img id="basketball_court" src="basketball_stuff/half_basketball_court.png">
        <img id="basketball_stands" src="basketball_stuff/basketball_stands.png">
        <img id="basketball_rim_far" src="basketball_stuff/basketball_rim_far.png">
        <img id="basketball_rim_near" src="basketball_stuff/basketball_rim_near.png">

        <div id="end_point" style="display: none; z-index: 500; position: absolute; left: 0; top: 0; background: red; width: 5px; height: 5px;">
        </div>
        <div id="begin_point" style="display: none; position: absolute; left: 0; top: 0; background: red; width: 5px; height: 5px;">
        </div>
        <div id="ball_point" style="display: none; position: absolute; left: 0; top: 0; background: green; width: 5px; height: 5px;">
        </div>
        <img id="player_1" class="player" src="/clicker/body/0.png" style="display: none;">
        <img id="basketball" src="basketball_stuff/basketball.png">
      </div>
    </div>

    <div id="group_page" data-role="page" data-theme="b" class="my-page"
         ng-app="group_page_app" ng-controller="group_page_ctrl">
      <fieldset data-role="controlgroup" data-theme="a">
        <legend class="ui-btn ui-btn-b ui-corner-all group_page-label">小組成員</legend>
        <div class="ui-btn list-label group_number-label">
          <div>第{{group_number}}組</div>
        </div>
        <div class="ui-btn list-label ui-grid-a">
          <div class="ui-block-a">姓名</div>
          <div class="ui-block-b">遙控器號碼</div>
        </div>
        <div class="ui-btn list-item ui-grid-a"
             ng-repeat="member in members track by $index">
          <div class="ui-block-a">{{member.name}}</div>
          <div class="ui-block-b">{{member.remote_ctrl_num}}</div>
          
          <%
          /*
          <div class="evaluation">0</div>
          <div class="approval">頂</div>
          <div class="disapproval">踹</div>
          */
          %>
        </div>
      </fieldset>
    </div>

    <div id="rank_page" data-role="page" data-theme="b" class="my-page"
         ng-app="rank_page_app" ng-controller="rank_page_ctrl">
      <fieldset data-role="controlgroup" data-theme="a">
        <legend class="ui-btn ui-btn-b ui-corner-all rank_page-label">及時排行榜</legend>
        <legend class="ui-btn ui-btn-a ui-corner-all student-group-info-label"
                ng-model="group_rank">
          <div class="ui-grid-a student-group-info">
            <div class="ui-block-a">
              <div class="group-label">你的組別:</div>
            </div>
            <div class="ui-block-b">
              <div class="group">{{group_rank.number}}</div>
            </div>
          </div>
          <div class="ui-grid-a student-group-info">
            <div class="ui-block-a">
              <div class="rank-label">你的小組名次:</div>
            </div>
            <div class="ui-block-b">
              <div class="rank">{{group_rank.rank}}</div>
            </div>
          </div>
          <div class="ui-grid-a student-group-info">
            <div class="ui-block-a">
              <div class="score-label">你的小組分數:</div>
            </div>
            <div class="ui-block-b">
              <div class="score">{{group_rank.score}}</div>
            </div>
          </div>
        </legend>
        <div class="ui-btn list-label ui-grid-b">
          <div class="ui-block-a">
            <div class="rank-label">名次</div>
          </div>
          <div class="ui-block-b">
            <div class="group-label">組別</div>
          </div>
          <div class="ui-block-c">
            <div class="score-label">分數</div>
          </div>
        </div>
        <div class="ui-btn list-item ui-grid-b"
             ng-repeat="group_rank in top_three track by $index">
          <div class="ui-block-a">
            <div class="rank">{{group_rank.rank}}</div>
          </div>
          <div class="ui-block-b">
            <div class="group">第{{group_rank.number}}組</div>
          </div>
          <div class="ui-block-c">
            <div class="score">{{group_rank.score}}</div>
          </div>
        </div>
      </fieldset>
    </div>
  </body>
  
    <script src="clicker_js/view_object.js"></script>
    
    <script src="clicker_js/page_icon_bar_style.js"></script>
    <script src="clicker_js/page_transition.js"></script>  
    <!--
    <script src="js/mouse_swipe_detect.js"></script>
    -->
    
    <!--
    <script src="clicker_js/clock_style.js"></script>
    <script src="clicker_js/draw_clock.js"></script>
    -->
    
    <script src="clicker_js/answer_page/resize_basketball_stuff.js"></script>
    
    
    <script src="clicker_js/clicker_controller.js"></script>
    <script src="clicker_js/answer_page/answer_page_controller.js"></script>
    <script src="clicker_js/group_page/group_page_controller.js"></script>
    <script src="clicker_js/rank_page/rank_page_controller.js"></script>
    <!--
    <script src="js/show_page_event_sequence.js"></script>
    -->
    <script src="clicker_js/page_style.js"></script>
    
    <script src="clicker_js/answer_page/throw_basketball.js"></script>
</html>