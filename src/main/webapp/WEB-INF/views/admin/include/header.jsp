<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Main Header -->
<header class="main-header">

<!-- Logo -->
<a href="#" class="logo" style="cursor : default;">
  <!-- mini logo for sidebar mini 50x50 pixels -->
  <span class="logo-mini"></span>
  <!-- logo for regular state and mobile devices -->
  <span class="logo-lg" style="float:left;"><b>Organization Admin</b></span>
</a>

<!-- Header Navbar -->
<nav class="navbar navbar-static-top" role="navigation">
  <!-- Sidebar toggle button-->
  <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
    <span class="sr-only">Toggle navigation</span>
  </a>
  <!-- Navbar Right Menu -->
  <div class="navbar-custom-menu">
    <ul class="nav navbar-nav">
    
      <!-- Messages: style can be found in dropdown.less-->
      <!-- /.messages-menu -->

	  <!-- 매뉴얼 링크 -->
      <li>
        <a href="https://docs.google.com/document/d/e/2PACX-1vSRn24tTkUVJvtFGsTq71hq652QEUmCyflbesb650g9cOg5sVK_TmJ3nq-i-ZvqLap0nvEpK-feCWdm/pub" target="_blank"><i class="fa fa-question"></i></a>
      </li>

      <!-- Sing out Menu -->
      <li class="dropdown notifications-menu">
        <!-- Menu toggle button -->
        <a href="" class="dropdown-toggle" data-toggle="dropdown">
          <i class="fa fa-power-off"></i>
<!--           <span class="label label-warning">10</span> -->
        </a>
        <ul class="dropdown-menu">
<!--           <li class="header">You have 10 notifications</li> -->
          <li>
            <!-- Inner Menu: contains the notifications -->
            <ul class="menu">
  			  <li>
				<a href="/user/emp/empMasterListView.do" >
				  <i class="fa fa-exchange"></i>&nbsp;&nbsp;User Site Home
				</a>
			  </li>              

              <li>
                <a id="aBtnSessionRefresh" style="cursor:pointer;">
                  <i class="fa fa-sign-out"></i> Session Refresh
                </a>
              </li>
              
            </ul>
          </li>
<!--           <li class="footer"><a href="#">View all</a></li> -->
        </ul>
      </li>
      
      <!-- Tasks Menu -->
	  <!-- Task menu end  -->
	        
      <!-- User Account Menu -->
      <!-- User Account Menu end -->
      
    </ul>
  </div>
</nav>
</header>

<form id="frmSessionRefresh" action="/logoutProcess.do" method="POST" style="display:none;">
<%-- 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
</form>

<div class="modal fade" id="gMdlCfmDialog">
	<div id="gMdlCfmDialogBody" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 id="gMdlTitle" class="modal-title">CLOUD OFFICE ORG TREE CONFIRM.</h4>
			</div>
			<div id="gMdlMsg" class="modal-body">
				<p>Some Message</p>
			</div>
			<div class="modal-footer">
				<button id="gMdlBtnOk" type="button" class="btn btn-primary"><spring:message code="text.yes" text="default text"/></button>
				<button id="gMdlBtnCancel" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.no" text="default text"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
        
<!-- jQuery 3 -->
<script src="/AdminLTE2/bower_components/jquery/dist/jquery.min.js"></script>

<script type ="text/javascript">
$(function() {
	$("#aBtnSessionRefresh").off("click").on("click", function(event){
		event.preventDefault();
		
		let size = "DEFAULT";
		let message = '<spring:message code="text.sessionrefresh.question" text="default text"/>';
		let title = "";
		let callback = gfnSessionRefresh;
		gfnInitCfmMdlDialog(size, title, message, callback);		
	});	
}) // $(function()
		
function gfnSessionRefresh() {
	$("#frmSessionRefresh").submit();	
}


function gfnInitCfmMdlDialog(size, title, message, callback) {
	if (typeof size == "undefined" || size == null || size == "") {
		size = "modal-default";	
	}
	else if (size == "1") {
		size = "modal-sm";	
	}
	else if (size == "2") {
		size = "modal-default";	
	}
	else if (size == "3") {
		size = "modal-lg";	
	}
	else if (size == "4") {
		size = "modal-xl";	
	}
	else if (size == "DEFAULT") {
		size = "modal-default";	
	}
	else {
		size = "modal-default";	
	}
	
	if (typeof title == "undefined" || title == null || title == "") {
		title = "CLOUD OFFICE ORG TREE CONFIRM.";	
	}
	
	if (typeof message == "undefined" || message == null || message == "") {
		message = "Confirm?";	
	}
	
	$("#gMdlCfmDialogBody").removeClass();
	$("#gMdlCfmDialogBody").addClass("modal-dialog");
	$("#gMdlCfmDialogBody").addClass(size);
	$("#gMdlTitle").html(title);
	$("#gMdlMsg").html("<i class='fa fa-check'></i>&nbsp;&nbsp;" + message);
	$("#gMdlBtnOk").off("click").on("click", function(event) {
		event.preventDefault();
		callback();
		$("#gMdlCfmDialog").modal("hide");
	});
	
	$("#gMdlCfmDialog").modal();
}	
</script>