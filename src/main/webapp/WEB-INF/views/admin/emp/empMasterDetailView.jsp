<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<!-- meta -->
<%@ include file="/WEB-INF/views/admin/include/meta.jsp" %>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

<!-- Header -->
<%@ include file="/WEB-INF/views/admin/include/header.jsp" %>

<!-- main side menu -->
<%@ include file="/WEB-INF/views/admin/include/sidebar.jsp" %>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
	
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-6">
					<h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.emp.detail" text="사용자상세"/></h3>
				</div>
				<div class="col-sm-6" style="text-align:right;">
		            <span class="pull-right">
		            	<button class="btn btn-primary float-right" id="btnSave"><i class="ace-icon fa fa-plus"></i><spring:message code="text.save" text="저장" /></button>
		            	<button class="btn btn-primary float-right" id="btnDelete"><i class="ace-icon fa fa-trash-o"></i><spring:message code="text.delete" text="삭제" /></button>
		            	<button class="btn btn-primary float-right" id="btnClose"><i class="ace-icon fa fa-floppy-o"></i><spring:message code="text.list" text="목록" /></button>
		            </span>				
				</div>
			</div>
		</section>
		
		<!-- Main content -->
		<section class="content container-fluid">
			<!-- 기본 정보 -->
			<div class="row">
				<div class="col-md-12">
				
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.emp.info" text="사용자정보"/></h3>
						</div>
						<!-- /.box-header -->
						
						<!-- form start -->
						<form id="frmEmpMasterInfo" class="form-horizontal">
							<div class="box-body">
								<div class="form-group">
									<label for="txtCompanyName" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control is-valid-jihun" id="txtCompanyName" name="txtCompanyName" placeholder="<spring:message code="text.company.name" text="법인명"/>" readonly required>
									</div>									
									
									<label for="txtDeptName" class="col-sm-1 control-label"><spring:message code="text.dept" text="부서"/></label>
									<div class="col-sm-2">
						                <div class="input-group">
											<input type="text" id="txtDeptName" name="txtDeptName" class="form-control is-valid-jihun" placeholder="<spring:message code="text.dept.name" text="법인명"/>" readonly required>
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
								
								<div class="form-group">
									<label for="txtEmail" class="col-sm-1 control-label"><spring:message code="text.emp.email" text="이메일"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control is-valid-jihun" id="txtEmail" name="txtEmail" placeholder="<spring:message code="text.email" text="이메일"/>" readonly required>
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
										<input type="text" class="form-control" id="txtEmpNo" name="txtEmpNo" placeholder="사번" onblur="javascript:gfnRegExpChk(this);">
									</div>
								</div>
								
								<div class="form-group">
									<label for="selPosName" class="col-sm-1 control-label"><spring:message code="text.emp.pos" text="직위"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selPosName" name="selPosName">
											<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
											<c:forEach items="${companyPosList}" var="option">
												<option value="${option.code}">${option.codeNm}</option>  
											</c:forEach>
										</select>
									</div>
									 									
									<label for="selEmpStatus" class="col-sm-1 control-label"><spring:message code="text.emp.status" text="재직상태"/></label>
									<div class="col-sm-2">
										<select class="form-control is-valid-jihun" id="selEmpStatus" name="selEmpStatus" required>
											<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
											<c:forEach items="${companyStatusCdList}" var="option">
												<option value="${option.code}">${option.codeNm}</option>  
											</c:forEach>
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
								
								<div class="form-group">
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
								
								<div class="form-group">
									<label for="selLanguageCd" class="col-sm-1 control-label"><spring:message code="text.language.cd" text="언어코드"/></label>
									<div class="col-sm-2">
										<select class="form-control is-valid-jihun" id="selLanguageCd" name="selLanguageCd" required>
											<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
											<option value="ko-KR" selected><spring:message code="text.kor" text="한국어"/></option>
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

			<div class="row" style="margin-bottom:10px;">
				<div class="col-sm-12" style="text-align:right;">
		            <span class="pull-right">
		              <button class="btn btn-primary float-right" id="btnEmpDeptXrefAdd"><i class="ace-icon fa fa-plus"></i><spring:message code="text.registration" text="등록" /></button>
		            </span>				
				</div>
			</div>				
			<div class="row">
				<div class="col-md-12">
					<div class="box box-primary">
						<!-- box header -->
						<div class="box-header with-border">
							<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.emp.xref.info" text="겸직정보"/></h3>
						</div>				
						<!-- box header end -->	
						
						<form id="frmAddYnList" class="form-horizontal">
							<div id="dvGrdBoxAddYnList" class="box-body grid-box">
								<table id="grdAddYnList"></table>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->	
	</div>

<!-- Main Footer -->
<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>

</div>

<!-- /.modal start -->
<div class="modal fade " id="mdlDeptPicker">
	<div id="mdlBody" class="modal-dialog modal-md">
		<div class="modal-content">
		
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="modal fade " id="mdlEmpDeptXref">
	<div id="mdlBody" class="modal-dialog modal-lg">
		<div class="modal-content">
		
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="modal fade " id="mdlEmpDeptXrefAdd">
	<div id="mdlBody" class="modal-dialog modal-lg">
		<div class="modal-content">
		
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal end -->

<!-- ./wrapper -->
<form id="frmHiddenParam">
  <input type="hidden" id="pCompanyCd" name="pCompanyCd" value="<c:out value="${companyCd}"/>"/>
  <input type="hidden" id="pEmail" name="pEmail" value="<c:out value="${email}"/>"/>
  <input type="hidden" id="pDeptCd" name="pDeptCd" value=""/>
  <input type="hidden" id="deptCode" name="deptCode" value=""/> <!-- 부서트리 Modal에 필요한 변수 -->
  <input type="hidden" id="pDomainId" name="pDomainId" value="<c:out value="${domainId}"/>"/>
</form>

<!-- script -->
<%@ include file="/WEB-INF/views/admin/include/script.jsp" %>

<script type ="text/javascript">
let gOldDeptCd = ""; // 기존부서정보
$(function() {
	fnSetMenuSelection();
	fnSetData();
	fnSetEvent();
	fnSetComponent();
}) // $(function()

function fnSetMenuSelection() {
	gfnSelectMenu("mgEmpMasterList", "");
}

function fnSetComponent() {
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
    
	// 그리드 RESIZE
	$('.grid-box').on('resize', $.noop);
  	$(window).on('resize.jqGrid', function () {
  		$('#grdAddYnList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
  	});
	fnInitGrid();
	fnSetGridSize();
}

//이벤트
function fnSetEvent() {
	$("#dvGrdBoxAddYnList").on("resize", function(){
		setTimeout(fnSetGridSize, 200);
	})	
	
	// 목록버튼
	$("#btnClose").off("click").on("click", function (e) {
		e.preventDefault();
		location.href = "/admin/emp/empMasterListView.do"
	});
	
	// 저장버튼
	$("#btnSave").off("click").on("click", function (e) {
		e.preventDefault();
		if (gfnCheckRequired($("#frmEmpMasterInfo"))) {
			let size = "DEFAULT";
			let gMsgCfmTitle = '<spring:message code="text.modify" text="수정" />';
			let message = '<spring:message code="text.modify.chk" text="수정 하시겠습니까?" />';
			let callback = fnUpdateEmpInfo;
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
	
	// 부서찾기버튼
	$("#btnDeptSearch").off("click").on("click", function (e) {
		e.preventDefault();
		fnShowMdlDeptPicker();
	});
	
	// 겸직그리드 상세버튼 클릭
	$(document).on('click', 'button[name="btnAddYnDetail"]', function(e) {
		e.preventDefault();
		fnShowMdlEmpDeptXref();
   });
	
	// 삭제버튼
	$("#btnDelete").off("click").on("click", function (e) {
		e.preventDefault();
		let size = "DEFAULT";
		let gMsgCfmTitle = '<spring:message code="text.delete" text="삭제" />';
		let message = '삭제 하시겠습니까?';
		let callback = fnDeleteEmpInfo;
		gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
	});
	
	// 등록버튼
	$("#btnEmpDeptXrefAdd").off("click").on("click", function (e) {
		e.preventDefault();
		fnShowMdlEmpDeptXrefAdd();
	});
}

//법인별 Data Set
function fnSetData() {
	fnSetEmpData(); // 사용자 정보
}

//부서선택 팝업 오픈
function fnShowMdlDeptPicker() {
	$("#mdlDeptPicker").modal({
		remote : "/admin/dept/deptTreeViewModal.do"
	});
}

//겸직정보 상세 팝업 오픈
function fnShowMdlEmpDeptXref() {
	$("#mdlEmpDeptXref").modal({
		remote : "/admin/emp/empDeptXrefViewModal.do"
	});
}

//겸직정보 등록 팝업 오픈
function fnShowMdlEmpDeptXrefAdd() {
	$("#mdlEmpDeptXrefAdd").modal({
		remote : "/admin/emp/empDeptXrefAddModal.do"
	});
}

//부서팝업->부서정보저장
function fnSetDeptInfo(selDeptInfo) {
	$("#pDeptCd").val(selDeptInfo.deptCd);
	$("#txtDeptName").val(selDeptInfo.deptNm);
}

//사용자상세 데이터 Set
function fnSetEmpData() {
	let apiUrl = "/rest/admin/emp/selectEmpInfo.do";
	let params = new Object();
	params.domainId = $("#pDomainId").val();
	params.email = $("#pEmail").val();
	$.ajax({
		type:'GET',
		url:apiUrl,
		data: params,
		dataType:'json',
	}).done(function(result) {
		if (result.resultCode == "SUCCESS") {
			gOldDeptCd = result.dataOne.deptCd;
			$("#txtCompanyName").val(result.dataOne.companyNm);
			$("#pCompanyCd").val(result.dataOne.companyCd);
			$("#pDomainId").val(result.dataOne.domainId);
			$("#txtDeptName").val(result.dataOne.deptNm);
			$("#pDeptCd").val(result.dataOne.deptCd);			
			$("#selHeadYn").val(result.dataOne.headYn);
			$("#txtEmail").val(result.dataOne.email);
			$("#txtEmpName").val(result.dataOne.empNm);
			$("#txtEmpEngName").val(result.dataOne.empEngNm);
			$("#txtEmpNo").val(result.dataOne.empNo);
			$("#selPosName").val(result.dataOne.posCd);
			$("#selEmpStatus").val(result.dataOne.empStatusCd);
			$("#txtJobTelNo").val(result.dataOne.jobTelNo);
			$("#txtMobileTelNo").val(result.dataOne.mobileTelNo);
			$("#dpWoEnterDt").datepicker("setDate", gfnYYYYMMDD2Date(result.dataOne.enterDt));
			$("#dpWoQuitDt").datepicker("setDate", gfnYYYYMMDD2Date(result.dataOne.quitDt));
			$("#selManualMngYn").val(result.dataOne.manualMngYn);
			$("#selHiddenYn").val(result.dataOne.hiddenYn);
			$("#selLanguageCd").val(result.dataOne.languageCd);
			
			let msgTitle = "";
			let msg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
			gfnSuccessAlert(msgTitle, msg, 2000);
		}
		else {
			let msgTitle = "";
			let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);		
		}			
	}).fail(function(jqXHR, textStatus) {	
    	let msgTitle = "";
		let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>';
		gfnFailAlert(msgTitle, msg, 2000);
	});	
}

//사용자정보 수정
function fnUpdateEmpInfo() {
	let apiUrl = "/rest/admin/emp/updateEmpInfo.do";
	let params = new Object();
	params.domainId = $('#pDomainId').val();
	params.companyCd = $('#pCompanyCd').val();
	params.companyNm = $("#txtCompanyName").val();
	params.oldDeptCd = gOldDeptCd;
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
	params.manualMngYn = $("#selManualMngYn").val();
	params.hiddenYn = $("#selHiddenYn").val();
	params.languageCd = $("#selLanguageCd").val();
	
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
			fnReselEmpDeptXref();
		}
		else if (result.resultCode = "DUP_FAIL") {
			msg = '<spring:message code="text.error.dept.dup" text="이미 사용중인 부서입니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);		
		}
		else {
			msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);		
		}			
	}).fail(function(jqXHR, textStatus) {	
    	msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
		gfnFailAlert(msgTitle, msg, 2000);
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}

//사용자정보 삭제
function fnDeleteEmpInfo() {
	let apiUrl = "/rest/admin/emp/deleteEmpInfo.do";

	let params = new Object();
	params.domainId = $('#pDomainId').val();
	params.companyCd = $('#pCompanyCd').val();
	params.email = $("#txtEmail").val();
	
	gfnShowLoadingBar();
	$.ajax({
		type:'POST',
		url:apiUrl,
		data: params,
		dataType:'json',
	}).done(function(result) {
		if (result.resultCode == "SUCCESS") {
			let msgTitle = "";
			let msg = '<spring:message code="text.delete.success" text="삭제 성공하였습니다."/>';
			gfnSuccessAlert(msgTitle, msg, 2000);
			setTimeout(function() {
				location.href = "/admin/emp/empMasterListView.do";
			}, 2000);
		}
		else {
			let msgTitle = "";
			let msg = '<spring:message code="text.delete.fail" text="삭제 실패하였습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);		
		}			
	}).fail(function(jqXHR, textStatus) {	
    	let msgTitle = "";
		let msg = '<spring:message code="text.delete.fail" text="삭제 실패하였습니다."/>';
		gfnFailAlert(msgTitle, msg, 2000);
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}

//그리드 사이즈 초기화 
function fnSetGridSize() {
	$('#grdAddYnList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
	$("#gbox_grdAddYnList").css("width", $('.grid-box').width());
}

//그리드 상세 버튼 생성
function formatButton1(cellvalue, options, rowObject) {
	var rowId = rowObject['rowId'];
	var btnStr = '<spring:message code="text.detatil" text="상세"/>';
	var buttonHtml1 = "<button type='button' class='btn btn-block btn-default-jihun btn-sm' name='btnAddYnDetail' data-rowid='" + rowId + "'>";
	buttonHtml1 = buttonHtml1 + "<i class='fa fa-edit'></i>&nbsp;";
	buttonHtml1 = buttonHtml1 + btnStr + "</button>";
    return buttonHtml1;
}

//겸직정보에 부서장구분 표시 변경
function formatterHeadYn(cellvalue, options, rowObject) {      
	var str ="";      
	if (cellvalue == "Y") {
		str += '<spring:message code="text.emp.head.yes" text="부서장"/>';
 	} else {
		str += '<spring:message code="text.emp.head.not" text="팀원"/>';
	}
	return str;
}

//겸직정보에 사용하지않는부서 표시
function formatterDeptNm(cellvalue, options, rowObject) {      
	var str ="";      
	if (rowObject.deptUseYn == "N") {
		str += cellvalue + "(N/A)";
 	} else {
		str += cellvalue;
	}
	return str;
}

// 겸직정보그리드 재조회
function fnReselEmpDeptXref() {
	let apiUrl = "/rest/admin/emp/selectEmpDeptXrefList.do";
	let params = new Object();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#pCompanyCd").val();
	params.email = $("#pEmail").val();

	$("#grdAddYnList").jqGrid('clearGridData');
	$("#grdAddYnList").jqGrid('setGridParam',{
    	postData : params,
    	success: function(data) {
		  	if(data.resultCode == "SUCCESS") {
				let msgTitle = "";
				let msg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
				gfnSuccessAlert(msgTitle, msg, 2000);
				location.href = "/admin/dept/deptMasterListView.do";
			}
			else {
				let msgTitle = "";
				let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>';
				gfnFailAlert(msgTitle, msg, 2000);
			}
    	}
  	}).trigger('reloadGrid');
}

//겸직그리드 생성
function fnInitGrid() {
	$("#selScCompanyCd").val('${sessionUser.companyCd}').attr('selected', true);
	
	let apiUrl = "/rest/admin/emp/selectEmpDeptXrefList.do";
	let params = new Object();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#pCompanyCd").val();
	params.email = $("#pEmail").val();
	$("#grdAddYnList").jqGrid({
      url: apiUrl,
      postData: params,
      datatype:"json",
      mtype : "GET",
      height: 250,
      jsonReader: {
	        page: 'page',
	        total: 'total',
	        records: 'records',
	        root: 'data',
	        repeatitems: false,
	        id: 'rowId'
	    },
      colNames:[
				''
				, 'rowId'
				, 'domainId'
				, 'companyCd'
				, '<spring:message code="text.company" text="법인"/>'
				, 'email'
				, 'deptCd'
				, '<spring:message code="text.dept" text="부서"/>'
				, 'posCd'
				, '<spring:message code="text.emp.position" text="직위"/>'
				, '<spring:message code="text.emp.no" text="사번"/>'
				, '<spring:message code="text.emp.head" text="부서장 구분"/>'
				, '<spring:message code="text.emp.add.yn" text="겸직여부"/>'
	    ],
      colModel:[
				{ name: 'button', 				index: 'button', 			width: '100', 	align: 'center', 	sortable: false, 	hidden: false,	fixed: true, formatter: formatButton1}
				, { name: 'rowId',				index: 'rowId',				width: '30%', 	align: 'center', 	sortable: false, 	hidden: true}
				, { name: 'domainId',			index: 'domainId',			width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'companyCd',			index: 'companyCd',			width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'companyNm',			index: 'companyNm',			width: '50%', 	align: 'left', 		sortable: false}
				, { name: 'email',				index: 'email',				width: '55%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'deptCd',				index: 'deptCd',			width: '55%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'deptNm',				index: 'deptNm',			width: '55%', 	align: 'left', 		sortable: false, 	formatter : formatterDeptNm}
				, { name: 'posCd',				index: 'posCd',				width: '30%', 	align: 'center', 	sortable: false, 	hidden: true}
				, { name: 'posNm',				index: 'posNm',				width: '30%', 	align: 'center', 	sortable: false}
				, { name: 'empNo', 				index: 'empNo', 			width: '40%', 	align: 'center', 	sortable: false}
				, { name: 'headYn',				index: 'headYn',			width: '30%', 	align: 'center', 	sortable: false, 	formatter : formatterHeadYn}
				, { name: 'addYn', 				index: 'addYn', 			width: '30%', 	align: 'center', 	sortable: false}
      ],
      rowNum:10,
      rowList: [5, 10, 100],
      rownumbers : true,
      gridview: true,
      viewrecords:true,
      emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
      shrinkToFit : true,
	    gridComplete: function() {
	    	fnSetGridSize();
	    },
	    resizeStop: gfnResizeStop
  });
}
</script>

</body>
</html>