<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- jQuery  -->
<script src="/sbadmin2/vendor/jquery/jquery.min.js"></script>
<!-- Main Header -->
<header class="main-header">

    <!-- Header Navbar -->
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
        <a class="btn btn-link rounded-circle mr-3" data-widget="pushmenu" id="sidebarToggle" role="button"><i class="fa fa-bars"></i></a>
        <!-- Navbar Right Menu -->
        <ul class="navbar-nav ml-auto">
            <!-- Navbar Right Menu -->

            <!-- 매뉴얼 링크 -->
            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link" href="https://docs.google.com/document/d/e/2PACX-1vSRn24tTkUVJvtFGsTq71hq652QEUmCyflbesb650g9cOg5sVK_TmJ3nq-i-ZvqLap0nvEpK-feCWdm/pub" target="_blank"><i class="fa fa-question"></i></a>
            </li>

            <!-- Sing out Menu -->
            <li class="nav-item dropdown no-arrow mx-1">
                <!-- Menu toggle button -->
                <a class="nav-link dropdown-toggle" href="" id="userDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-power-off"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                    aria-labelledby="userDropdown">
                    <!-- <li class="header">You have 10 notifications</li> -->
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
                </ul>
                <!-- <li class="footer"><a href="#">View all</a></li> -->
            </li>
        </ul>
    </nav>
</header>

<form id="frmSessionRefresh" action="/logoutProcess.do" method="POST" style="display:none;">
<%-- 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
</form>

<div class="modal fade" id="gMdlCfmDialog">
	<div id="gMdlCfmDialogBody" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
                <h6 id="gMdlTitle" class="modal-title">CLOUD OFFICE ORG TREE CONFIRM.</h6>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
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