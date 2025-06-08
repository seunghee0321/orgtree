<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<!-- meta -->
<%@ include file="/WEB-INF/views/admin/include/meta.jsp" %>

<body class="hold-transition sidebar-mini sidebar-collapse">
<div id="wrapper">
	<!-- main side menu -->
	<%@ include file="/WEB-INF/views/admin/include/sidebar.jsp" %>

	<!-- Content Wrapper. Contains page content -->
	<div id="content-wrapper" class="d-flex flex-column">
		<!-- Header -->
		<%@ include file="/WEB-INF/views/admin/include/header.jsp" %>
		<!-- Main Content -->
		<div id="content">
			<!-- Content Header (Page header) -->
			<nav class="navbar navbar-expand navbar-light bg-white topbar static-top">
				<h1 class="h4 mb-0 text-gray-800">
					<spring:message code="text.emp.mng" text="사용자등록" />
				</h1>
				<ul class="navbar-nav ml-auto">
					<button type="button" id="btnAdd" class="card border-left-success shadow h-100 py-2">
						<i class="fa fa-paper-plane"></i> <spring:message code="text.registration" text="등록"/>
					</button>
					<button type="button" id="btnClose" class="card border-left-info shadow h-100 py-2">
						<i class="fas fa-plus"></i> <spring:message code="text.list" text="목록"/>
					</button>
				</ul>
			</nav>

			<!-- Main content -->
			<div class="container-fluid">
				<!-- 기본 정보 -->
				<div class="row">
					<div class="col-md-12">

						<!-- general form elements -->
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i> <spring:message code="text.emp.info" text="사용자정보"/></h6>
							</div>
							<!-- /.box-header -->
							<div class="card-body">
								<form id="frmEmpMasterInfo" class="card-body">

									<div class="form-group row">
										<label for="txtCompanyName" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
										<div class="col-sm-2">
											<select class="form-control is-valid-jihun" id="selScCompanyCd" name="selScCompanyCd" onchange="fnResetTable()" required>
												<c:forEach items="${companyList}" var="option">
													<option value="${option.companyCd}">${option.companyNm}</option>
												</c:forEach>
											</select>
										</div>

										<label for="txtDeptName" class="col-sm-1 control-label"><spring:message code="text.dept" text="부서"/></label>
										<div class="col-sm-2">
											<div class="input-group">
												<input type="text" id="txtDeptName" name="txtDeptName" class="form-control is-valid-jihun" placeholder="<spring:message code="text.dept.name" text="부서명"/>" readonly required>
												<span class="input-group-btn">
													<button type="button" id="btnDeptSearch" class="btn btn-primary"><spring:message code="text.find" text="찾기"/></button>
												</span>

											</div>
										</div>

										<label for="selHeadYn" class="col-sm-1 control-label"><spring:message code="text.emp.head" text="부서장 구분"/></label>
										<div class="col-sm-2">
											<select class="form-control" id="selHeadYn" name="selHeadYn">
												<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
												<option value="Y"><spring:message code="text.emp.head.yes" text="부서장"/></label></option>
												<option value="N"><spring:message code="text.emp.head.not" text="팀원"/></label></option>
											</select>
										</div>

									</div>

									<div class="form-group row">
										<label for="txtEmail" class="col-sm-1 control-label"><spring:message code="text.emp.email" text="이메일"/></label>
										<div class="col-sm-2">
											<input type="text" class="form-control is-valid-jihun" id="txtEmail" name="txtEmail" placeholder="<spring:message code="text.emp.email" text="이메일"/>" required onblur="javascript:gfnRegExpChk(this);">
										</div>

										<label for="txtEmpName" class="col-sm-1 control-label"><spring:message code="text.emp.name" text="성명"/></label>
										<div class="col-sm-2">
											<input type="text" class="form-control is-valid-jihun" id="txtEmpName" name="txtEmpName" placeholder="<spring:message code="text.emp.name" text="성명"/>" required onblur="javascript:gfnRegExpChk(this);">
										</div>

										<label for="txtEmpEngName" class="col-sm-1 control-label"><spring:message code="text.emp.eng.name" text="영문성명"/></label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="txtEmpEngName" name="txtEmpEngName" placeholder="<spring:message code="text.emp.eng.name" text="영문성명"/>" onblur="javascript:gfnRegExpChk(this);">
										</div>

										<label for="txtEmpNo" class="col-sm-1 control-label"><spring:message code="text.emp.no" text="사번"/></label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="txtEmpNo" name="txtEmpNo" placeholder="<spring:message code="text.emp.no" text="사번"/>" onblur="javascript:gfnRegExpChk(this);">
										</div>
									</div>

									<div class="form-group row">
										<label for="selPosName" class="col-sm-1 control-label"><spring:message code="text.emp.pos" text="직위"/></label>
										<div class="col-sm-2">
											<select class="form-control" id="selPosName" name="selPosName">
											</select>
										</div>

										<label for="selEmpStatus" class="col-sm-1 control-label"><spring:message code="text.emp.status" text="재직상태"/></label>
										<div class="col-sm-2">
											<select class="form-control is-valid-jihun" id="selEmpStatus" name="selEmpStatus" required>
											</select>
										</div>

										<label for="txtJobTelNo" class="col-sm-1 control-label"><spring:message code="text.emp.job.tel" text="사내전화"/></label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="txtJobTelNo" name="txtJobTelNo" placeholder="<spring:message code="text.emp.job.tel" text="사내전화"/>" onblur="javascript:gfnRegExpChk(this,2);">
										</div>

										<label for="txtMobileTelNo" class="col-sm-1 control-label"><spring:message code="text.emp.mobile.tel" text="휴대폰"/></label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="txtMobileTelNo" name="txtMobileTelNo" placeholder="<spring:message code="text.emp.mobile.tel" text="휴대폰"/>" onblur="javascript:gfnRegExpChk(this,2);">
										</div>
									</div>

									<div class="form-group row">
										<label for="dpWoEnterDt" class="col-sm-1 control-label"><spring:message code="text.emp.enter.date" text="입사일"/></label>
										<div class="col-sm-2">
											<div class="input-group">
												<input type="text" class="form-control pull-right date" id="dpWoEnterDt">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
										</div>

										<label for="dpWoQuitDt" class="col-sm-1 control-label"><spring:message code="text.emp.quit.date" text="퇴사일"/></label>
										<div class="col-sm-2">
											<div class="input-group">
												<input type="text" class="form-control pull-right date" id="dpWoQuitDt">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
										</div>

										<label for="selManualMngYn" class="col-sm-1 control-label"><spring:message code="text.manual.manage" text="수동관리"/></label>
										<div class="col-sm-2">
											<select class="form-control" id="selManualMngYn" name="selManualMngYn">
												<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
												<option value="Y">Y</option>
												<option value="N">N</option>
											</select>
										</div>

										<label for="selHiddenYn" class="col-sm-1 control-label"><spring:message code="text.emp.hidden" text="숨김"/></label>
										<div class="col-sm-2">
											<select class="form-control" id="selHiddenYn" name="selHiddenYn">
												<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
												<option value="Y">Y</option>
												<option value="N">N</option>
											</select>
										</div>
									</div>
								<!-- /.card-body -->
							</form>
							</div>
						</div>
						<!-- /.card -->
					</div>
				</div>
			</div>
		<!-- /.content -->
	</div>
	<!-- Main Footer -->
	<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>
	</div>
</div>
<!--2429 임시삭제 -->
<!-- /.modal start -->
<div class="modal fade " id="mdlDeptPicker">
	<div id="mdlBody" class="modal-dialog modal-md">
		<div class="modal-content">

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal end -->

<!-- ./wrapper -->
<form id="frmHiddenParam">
  <input type="hidden" id="pDomainId" name="pDomainId" value="<c:out value="${domainId}"/>"/>
  <input type="hidden" id="pCompanyCd" name="pCompanyCd" value="<c:out value="${companyCd}"/>"/>
  <input type="hidden" id="pDeptCd" name="pDeptCd" value="<c:out value="${deptCd}"/>"/>
  <input type="hidden" id="deptCode" name="deptCode" value=""/> <!-- 부서트리 Modal에 필요한 변수 -->
</form>

<!-- script -->
<%@ include file="/WEB-INF/views/admin/include/script.jsp" %>

<script type ="text/javascript">

$(function() {
	fnSetMenuSelection();
	fnSetComponent();
	fnSetData();
	fnSetEvent();
}) // $(function()

function fnSetMenuSelection() {
	gfnSelectMenu("mgEmpMasterList", "");
}

function fnSetComponent() {
	$("#selScCompanyCd").val('${companyCd}').attr('selected', true);
	$("#txtDeptName").val('<c:out value="${deptNm}"/>');
	
	// 선택한 법인의 domainId 저장해둠
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			$("#pDomainId").val("${item.domainId}");
		}
	</c:forEach>
	
	// datepicker 설정
	let langCd = "";
	if ('<c:out value="${languageCd}"/>' == 'ko-KR') {
		langCd = "ko";
	}
	
    $(".date").datepicker({
		autoclose: true,
		format : "yyyy-mm-dd",
		clearBtn : true,
		todayHighlight : true,
		language : langCd
	});
}

//이벤트
function fnSetEvent() {
	// 목록버튼
	$("#btnClose").off("click").on("click", function (e) {
		e.preventDefault();
		location.href = "/admin/emp/empMasterListView.do";
	});
	
	// 저장버튼
	$("#btnAdd").off("click").on("click", function (e) {
		e.preventDefault();
		if (gfnCheckRequired($("#frmEmpMasterInfo"))) {
			let size = "DEFAULT";
			let gMsgCfmTitle = '<spring:message code="text.registration" text="등록" />';
			let message = '<spring:message code="text.registration.chk" text="등록 하시겠습니까?" />';
			let callback = fnInsertEmpInfo;
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
	
	// 부서찾기버튼
	$("#btnDeptSearch").off("click").on("click", function (e) {
		e.preventDefault();
		fnShowMdlDeptPicker();
	});
}

//법인별 Data Set
function fnSetData() {
	fnSetEmpStatus(); // 재직상태
	fnSetEmpPos(); // 직위
}

//법인별 재직상태 Set
function fnSetEmpStatus() {
	$('#selEmpStatus').children('option').remove();
	let apiUrl = "/rest/admin/emp/selectCodeDetailList.do";
	let params = new Object();
	params.domainId = $('#pDomainId').val();
	params.companyCd = $('#pCompanyCd').val();
	params.codeDiv = "CM001";
 	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(result) {
			let data = result.data;
			let option = '<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>';
			$("#selEmpStatus").append(option);
			for(let i=0;i<data.length;i++) {
				option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
				$("#selEmpStatus").append(option);
			}
		}
	});
}

//법인별 직위 Set
function fnSetEmpPos() {
	$('#selPosName').children('option').remove();
	let apiUrl = "/rest/admin/emp/selectCodeDetailList.do";
	let params = new Object();
	params.domainId = $('#pDomainId').val();
	params.companyCd = $('#pCompanyCd').val();
	params.codeDiv = "CM002";
 	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(result) {
			let data = result.data;
			let option = '<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>';
			$("#selPosName").append(option);
			for(let i=0;i<data.length;i++) {
				option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
				$("#selPosName").append(option);
			}
		}
	});
}

//find 버튼 (법인 찾기 팝업)
function fnShowMdlDeptPicker() {
	$('#mdlDeptPicker .modal-content').load('/admin/dept/deptTreeViewModal.do', function () {
		$('#mdlDeptPicker').modal('show');
	});
}

//사용자정보테이블 초기화
function fnResetTable() {
	$("#pCompanyCd").val($("#selScCompanyCd").val());
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			$("#pDomainId").val("${item.domainId}");
		}
	</c:forEach>
	$("#pDeptCd").val("");
	$("#txtDeptName").val("");
	fnSetData();
}

//부서정보저장
function fnSetDeptInfo(selDeptInfo) {
	$("#pDeptCd").val(selDeptInfo.deptCd);
	$("#txtDeptName").val(selDeptInfo.deptNm);
}

//사용자정보 등록
function fnInsertEmpInfo() {
	let apiUrl = "/rest/admin/emp/insertEmpInfo.do";
	
	let params = new Object();
	
	params.domainId = $('#pDomainId').val();
	params.companyCd = $('#pCompanyCd').val();
	params.companyNm = $("#txtCompanyName").val();
	params.deptCd = $('#pDeptCd').val();
	params.deptNm = $("#txtDeptName").val();
	params.headYn = $("#selHeadYn").val();
	params.email = $("#txtEmail").val();
	params.empNm = $("#txtEmpName").val();
	params.empEngNm = $("#txtEmpEngName").val();
	params.empNo = $("#txtEmpNo").val();
	params.posCd = $("#selPosName").val();
	params.empStatusCd = $("#selEmpStatus").val();
	params.jobTelNo = $("#txtJobTelNo").val();
	params.mobileTelNo = $("#txtMobileTelNo").val();
	params.enterDt = gfnDate2YYYYMMDD($("#dpWoEnterDt").datepicker("getDate"));
	params.quitDt = gfnDate2YYYYMMDD($("#dpWoQuitDt").datepicker("getDate"));;
	//params.manualMngYn = $("#selManualMngYn").val();
	//params.hiddenYn = $("#selHiddenYn").val();
	if($("#selHiddenYn").val() == '') {
		params.hiddenYn = 'N';
	}
	else {
		params.hiddenYn = $("#selHiddenYn").val();
	}
	if($("#selManualMngYn").val() == '') {
		params.manualMngYn = 'N';
	}
	else {
		params.manualMngYn = $("#selManualMngYn").val();
	}
	
	let msgTitle = "";
	let msg = "";
	gfnShowLoadingBar();
	$.ajax({
		type:'POST',
		url:apiUrl,
		data: params,
		dataType:'json',
	}).done(function(result) {
		if (result.resultCode == "SUCCESS") {
			msg = '<spring:message code="text.save.success" text="저장 성공하였습니다."/>';
			gfnSuccessAlert(msgTitle, msg, 2000);
			setTimeout(function() {
				location.href = "/admin/emp/empMasterListView.do";
			}, 2000);
		}
		else if (result.resultCode = "FAIL") {
			msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);
		}
		else {
			msg = '<spring:message code="text.error.emp.dup" text="이미 등록된 사용자입니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);		
		}			
	}).fail(function(jqXHR, textStatus) {
    	msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
		gfnFailAlert(msgTitle, msg, 2000);
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}

</script>

</body>
</html>