<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<!-- meta -->
<%@ include file="/WEB-INF/views/user/include/meta.jsp" %>

<body class="hold-transition sidebar-mini sidebar-collapse">
<div id="wrapper">
	<!-- main side menu -->
	<%@ include file="/WEB-INF/views/user/include/sidebar.jsp" %>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper" class="d-flex flex-column">
		<!-- Header -->
		<%@ include file="/WEB-INF/views/user/include/header.jsp" %>

		<!-- Content Header (Page header) -->
		<div id="content">
			<nav class="navbar navbar-expand navbar-light bg-white topbar static-top">
				<h1 class="h4 mb-0 text-gray-800">
					<spring:message code="text.my.config" text="default text" />
				</h1>
				<ul class="navbar-nav ml-auto" style="text-align:right;">
					<button type="button" id="btnUpdate" class="card border-left-primary shadow h-100 py-2"><i class="fa fa-save"></i> <spring:message code="text.save" text="저장"/></button>
				</ul>
			</nav>

			<!-- Main content -->
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">

						<!-- general form elements -->
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i> <spring:message code="text.my.config.info" text="개인설정정보"/></h6>
							</div>
							<!-- /.box-header -->

							<!-- form start -->
							<form id="frmEmpConfigInfo" class="form-horizontal">
								<div class="card-body">
									<div class="form-group row">
										<label for="selLangCd" class="col-sm-1 control-label"><spring:message code="text.language.cd" text="언어코드"/></label>
										<div class="col-sm-2">
											<select class="form-control is-valid-jihun" id="selLangCd" name="selLangCd" required>
												<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
												<option value="ko-KR"><spring:message code="text.kor" text="한국어"/></option>
												<option value="en-US"><spring:message code="text.eng" text="영어"/></option>
											</select>
										</div>
									</div>
								</div>
								<!-- /.box-body -->
							</form>
						</div>
						<!-- /.box -->
					</div>
				</div>

				<!-- 등록/수정정보 -->
				<div class="row">
					<div class="col-md-12">
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i> <spring:message code="text.ins.upd.info" text="등록 / 수정 정보"/></h6>
							</div>
							<!-- /.box-header -->

							<div class="card-body">
								<div class="form-group row">
									<label for="txtCreateMemberName" class="col-sm-1 control-label"><spring:message code="text.create.usr" text="최초등록자"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="txtCreateUsrNm" name="txtCreateUsrNm" readonly>
									</div>

									<label for="txtCreateDate" class="col-sm-1 control-label"><spring:message code="text.create.dt" text="최초등록일자"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="txtCreateDt" name="txtCreateDt" readonly>
									</div>

									<label for="txtUpdateMemberName" class="col-sm-1 control-label"><spring:message code="text.update.usr" text="최종수정자"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="txtUpdateUsrNm" name="txtUpdateUsrNm" readonly>
									</div>

									<label for="txtUpdateDate" class="col-sm-1 control-label"><spring:message code="text.update.dt" text="최종수정일자"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="txtUpdateDt" name="txtUpdateDt" readonly>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.content -->
		</div>
	<!-- Main Footer -->
	<%@ include file="/WEB-INF/views/user/include/footer.jsp" %>
	</div>
</div>
<!-- script -->
<%@ include file="/WEB-INF/views/user/include/script.jsp" %>

<script type ="text/javascript">
$(function() {
	fnSetMenuSelection();
	fnSetEvent();
	fnSetData();
}) // $(function()
		
//좌측 메뉴 선택
function fnSetMenuSelection() {
	gfnSelectMenu("mgEmpConfig", "");
}

// 이벤트 셋팅 
function fnSetEvent() {
	// 저장버튼 이벤트 
	$("#btnUpdate").off("click").on("click", function(e) {
		e.preventDefault();
		
		if (gfnCheckRequired($("#frmEmpConfigInfo"))) {
			let size = "DEFAULT";
			let message = '<spring:message code="text.save.question" text="Data를 저장하시겠습니까?"/>';
			let callback = fnUpdateEmpConfig;
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
}

function fnSetData() {
	fnSelectEmpConfig();
}

// 데이터 한건 조회 
function fnSelectEmpConfig() {
	gfnShowLoadingBar();
	let apiUrl = "/rest/user/config/selectMyEmpConfig.do";
	let msgTitle = "";
	let successMsg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
	let failMsg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>';
	
	$.ajax({
		url : apiUrl,
		method : "GET",
		dataType : 'json',
		contentTaype: "application/x-www-form-urlencoded; charset=UTF-8",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}		
	}).done(function(result) {
		if (result.resultCode == "SUCCESS") {
			$("#selLangCd").val(result.dataOne.languageCd);
			$("#txtCreateUsrNm").val(result.dataOne.createUsr);
			$("#txtCreateDt").val(result.dataOne.createDtF);
			$("#txtUpdateUsrNm").val(result.dataOne.updateUsr);
			$("#txtUpdateDt").val(result.dataOne.updateDtF);
        	gfnSuccessAlert(msgTitle, successMsg, gDelay1);		
		}
		else {
        	let msg = failMsg + " : " + result.resultMsg;
        	gfnFailAlert(msgTitle, msg, gDelay1);				
		}			
	}).fail(function(jqXHR, textStatus) {
    	let msg = failMsg + " : " + jqXHR.status.toString();
    	gfnFailAlert(msgTitle, msg, gDelay1);				
	}).always(function(msg) {
		gfnHideLoadingBar();
	});	
}

//저장 수행
function fnUpdateEmpConfig() {
	gfnShowLoadingBar();
	let apiUrl = "/rest/user/config/insertMyEmpConfig.do";
	let params = new Object();
	let msgTitle = "";
	let successMsg = '<spring:message code="text.save.success" text="저장 성공하였습니다."/>';
	let failMsg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
	
	params.languageCd = $("#selLangCd").val();
	
	$.ajax({
		url : apiUrl,
		method : "POST",
		dataType : 'json',
		data : params,
		contentTaype: "application/x-www-form-urlencoded; charset=UTF-8",
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}   
	}).done(function(result) {
	if (result.resultCode == gSuccess) {
		gfnSuccessAlert(msgTitle, successMsg, gDelay1);
	}
	else {
		let msg = failMsg + " : " + result.resultMsg;
		gfnFailAlert(msgTitle, msg, gDelay1);
	}     
	}).fail(function(jqXHR, textStatus) {
		let msg = failMsg + " : " + jqXHR.status.toString();
		gfnFailAlert(msgTitle, msg, gDelay1);     
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}
</script>

</body>
</html>