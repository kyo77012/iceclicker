<%@page import="com.google.gson.Gson"%>
<%@page import="cache.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Monitor</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script>
  var studentSet = <%
    out.print( ( new Gson() ).toJson( Student_cache.students_out ) );
  %>;
  
  var studentRecordSet = null;
</script>

<style>
  .a_student {
    float: left;
    text-shadow: none;
    font-size: 1.5em;
    margin: 0;
    padding: 0.25em;
    width: 50px;
    height: 25px;
  }
  
  #clock {
    font-size: 3em;
    padding-top: 0;
    padding-bottom: 0;
    padding-left: 0.5em;
    padding-right: 0.5em;
    margin: 0;
    margin-right: 0.5em;
    color: #FFFF33;
    border: 0;
    float: right;
  }
</style>

</head>
<body>
<div data-role="page" ng-app="monitor_app" ng-controller="monitor_ctrl">
  <div class="ui-responsive">
    <a id="{{student.remote_ctrl_num}}"
       class="a_student ui-btn"
       option_id=""
       ng-repeat="student in students"
       style="text-shadow:none;">
      {{student.remote_ctrl_num}}
    </a>
  </div>
  <div data-role="footer">
    <input id="reload_cache" type="button" value="重新載入cache">
    <input id="check_ans" type="button" value="對答案" ng-click="CheckStudentAns()">
    <button id="reset" ng-click="Reset()" style="display:none;">reset</button>
    <input id="prev_question" type="button" value="上一題">
    <input id="next_question" type="button" value="下一題">
    <button id="response"></button>
    <div id="clock" class="ui-btn ui-btn-b ui-corner-all">0:00</div>
    <!--
    <button id="btn_update_question" style="display:none;"></button>
    -->
  </div>
</div>

<script src="monitor_js/monitor_controller.js"></script>

</body>
</html>