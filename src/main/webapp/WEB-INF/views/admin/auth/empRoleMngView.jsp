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
					<h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.admin.assign" text="관리자할당"/></h3>
				</div>
				<div class="col-sm-6" style="text-align:right;">
		            <span class="pull-right">
			            <button type="button" id="btnSearchList" class="btn btn-primary float-right"><i class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
		            	<button type="button" id="btnAdd" class="btn btn-primary float-right"><i class="ace-icon fa fa-plus"></i><spring:message code="text.assign" text="할당"/></button>
		            	<button type="button" id="btnCancel" class="btn float-right btn-primary"><i class="ace-icon fa fa-minus"><spring:message code="text.cancel" text="취소"/></i></button>
		            </span>	
		            			
				</div>
			</div>
			
		</section>
		
		<!-- Main content -->
		<section class="content container-fluid">
			<div class="row">
				<div class="col-md-12">
				
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.search.cond" text="조회조건"/></h3>
						</div>
						<!-- /.box-header -->
						
						<!-- form start -->
						<form class="form-horizontal">
							<div class="box-body">
								<div class="form-group">
									<label for="selScCompanyCd" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScCompanyCd" name="selScCompanyCd" onchange="fnReselGrid()">
											<c:forEach items="${companyList}" var="option">
												<option value="${option.companyCd}">${option.companyNm}</option>  
											</c:forEach>
										</select>
									</div>
									
									<label for="txtScSearchTxt" class="col-sm-1 control-label"><spring:message code="text.emp.name" text="성명"/></label>
									<div class="col-sm-2">
										<input type="text" class="form-control" id="txtScSearchTxt" name="txtScSearchTxt" onblur="javascript:gfnRegExpChk(this);" placeholder="<spring:message code="text.search.word" text="검색어"/>">
									</div>
									
								</div>
								
							</div>
							<!-- /.box-body -->
						</form>
					</div>
					<!-- /.box -->			
				</div>
			</div>
			
			<div class="row">
				<!-- general form elements -->
				<div class="col-md-12">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.admin.list" text="관리자목록"/></h3>
						</div>
						<!-- /.box-header -->
						<!-- /.box-header -->
						
						<!-- form start -->
						<form class="form-horizontal">
							<div id="dvGrdBox" class="box-body grid-box">
								<table id="grdAdminList"></table>
								<div id="grdAdminListPager"></div>
							</div>
							<!-- /.box-body -->
						</form>
					</div>
				</div>
				<!-- /.box -->			
			</div>
		</section>
		<!-- /.content -->		
		
	</div>

<!-- Main Footer -->
<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>

</div>

<div class="modal fade " id="mdlEmpRoleAdd">
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
</form>

<!-- script -->
<%@ include file="/WEB-INF/views/admin/include/script.jsp" %>

<script type ="text/javascript">
let gDomainId = "";
$(function() {
	fnSetMenuSelection();
	fnSetComponent();
	fnSetEvent();
}) // $(function()

function fnSetMenuSelection() {
	gfnSelectMenu("mgEmpRoleMng", "");
}

//화면 컴포넌트 초기화 
function fnSetComponent() {
	// 그리드 RESIZE
	$('.grid-box').on('resize', $.noop);
  	$(window).on('resize.jqGrid', function () {
  		$('#grdAdminList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
  	});
  	
  	$("#selScCompanyCd").val('${sessionUser.companyCd}').attr('selected', true);
 	// 선택한 법인의 domainId 저장해둠
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			gDomainId = "${item.domainId}";
		}
	</c:forEach>
	
	fnInitGrid();
	fnSetGridSize();
}

//이벤트
function fnSetEvent() {
	$("#dvGrdBox").on("resize", function(){
		setTimeout(fnSetGridSize, 200);
	})		
	
	// 검색조건 조회
	$("#txtScSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			fnReselGrid();
		}
	});
	$("#btnSearchList").off("click").on("click", function (e) {
		e.preventDefault();
		fnReselGrid();
	});
	
	// 관리자할당버튼 클릭
	$("#btnAdd").off("click").on("click", function (e) {
		e.preventDefault();
		fnShowMdlEmpRoleAdd();
	});
	
	// 할당취소버튼 클릭
	$("#btnCancel").off("click").on("click", function (e) {
		e.preventDefault();
		fnDeleteRoleAdminChk();
	});
	
	
}

//검색 조건 및 그리드 초기화
function fnResetGrid() {
	// 그리드 초기화
	$("#txtScSearchTxt").val("");
	$("#grdAdminList").jqGrid('clearGridData');
}

//그리드 사이즈 초기화 
function fnSetGridSize() {
	$('#grdAdminList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
	$("#gbox_grdAdminList").css("width", $('.grid-box').width());
}

//법인할당버튼
function fnShowMdlEmpRoleAdd() {
	$("#mdlEmpRoleAdd").modal({
		remote : "/admin/auth/empRoleAddModal.do"
	});
}

//할당취소체크
function fnDeleteRoleAdminChk() {
	let idx = $('#grdAdminList').jqGrid("getGridParam", "selarrrow");
	if(idx.length == 0) {
		let msgTitle = "";
		let msg = '<spring:message code="text.emp.select.fail" text="사용자가 선택되지 않았습니다."/>';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	
	let size = "DEFAULT";
	let message = '<spring:message code="text.assign.cancle.msg" text="할당을 취소하시겠습니까?"/>';
	let callback = fnDeleteRoleAdmin;
	gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
}

//할당취소버튼
function fnDeleteRoleAdmin() {
	let idx = $('#grdAdminList').jqGrid("getGridParam", "selarrrow");
	let users = new Array();
	for(let i=0;i<idx.length;i++)
	{
		let params = new Object();
		params.domainId = $('#grdAdminList').jqGrid("getCell", idx[i], "domainId");
		params.companyCd = $('#grdAdminList').jqGrid("getCell", idx[i], "companyCd");
		params.email = $('#grdAdminList').jqGrid("getCell", idx[i], "email");
		users.push(params);
	}
	let apiUrl = "/rest/admin/auth/deleteEmpRoleAdmin.do";
	gfnShowLoadingBar();
	$.ajax({
		url : apiUrl,
		type : "POST",
		data : JSON.stringify(users),
		dataType: "json",
		traditional: true,
		contentType: "application/json; charset=UTF-8",
		success: function(result) {
			if (result.resultCode == "SUCCESS") {
				let msg = '<spring:message code="text.delete.success" text="삭제 성공하였습니다."/>';
				gfnSuccessAlert("", msg, gDelay3);
				fnReselGrid();
			}
			else {
				let msg = '<spring:message code="text.delete.fail" text="삭제 실패하였습니다."/>' + result.resultMsg;
				gfnFailAlert("", msg, gDelay2);
			}
		}
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}

//그리드 재조회
function fnReselGrid() {
	let params = new Object();
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			gDomainId = "${item.domainId}";
		}
	</c:forEach>
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.empNm = $("#txtScSearchTxt").val();
	$("#grdAdminList").jqGrid('clearGridData');
	$("#grdAdminList").jqGrid('setGridParam',{
    	postData : params
  	}).trigger('reloadGrid');
}

//그리드 생성
function fnInitGrid() {
	let apiUrl = "/rest/admin/auth/selectEmpAuthorityList.do";
	let params = new Object();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.empNm = $("#txtScSearchTxt").val();
	
	$("#grdAdminList").jqGrid({
		url: apiUrl,
		postData: params,
		datatype:"json",
		mtype : "GET",
		height:  450,
		jsonReader: {
			page: 'page',
			total: 'total',
			records: 'records',
			root: 'data',
			repeatitems: false,
			id: 'rowId'
		},
		colNames:[
			  'rowId'
			, 'domainId'
			, 'companyCd'
			, '<spring:message code="text.company" text="법인"/>'
			, '<spring:message code="text.emp.name" text="성명"/>'
			, '<spring:message code="text.emp.email" text="이메일"/>'
			, 'deptCd'
			, '<spring:message code="text.dept" text="부서"/>'
			, '<spring:message code="text.emp.position" text="직위"/>'
			, '<spring:message code="text.emp.no" text="사번"/>'
			, '<spring:message code="text.emp.status" text="재직상태"/>'
			, 'ROLE'
		],
		colModel:[
			  { name: 'rowId',				index: 'rowId',					width: '30%', 	align: 'center', 	sortable: false, 	hidden: true}
			, { name: 'domainId',			index: 'domainId',			width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
			, { name: 'companyCd',			index: 'companyCd',			width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
			, { name: 'companyNm',			index: 'companyNm',			width: '50%', 	align: 'left', 		sortable: false}
			, { name: 'empNm',				index: 'empNm',					width: '50%', 	align: 'center', 	sortable: false}
			, { name: 'email',				index: 'email',					width: '60%', 	align: 'left', 		sortable: false}
			, { name: 'deptCd',				index: 'deptCd',				width: '55%', 	align: 'left', 		sortable: false, 	hidden: true}
			, { name: 'deptNm',				index: 'deptNm',				width: '55%', 	align: 'left', 		sortable: false}
			, { name: 'posNm',				index: 'posNm',					width: '30%', 	align: 'center', 	sortable: false}
			, { name: 'empNo', 				index: 'empNo', 				width: '40%', 	align: 'left', 		sortable: false}
			, { name: 'empStatusNm',		index: 'empStatusNm',		width: '30%', 	align: 'center', 	sortable: false}
			, { name: 'authorityName',		index: 'authorityName',		width: '30%', 	align: 'center', 	sortable: false}
		],
		rowNum:10,
		rowList: [5, 10, 100],
		rownumbers : true,
		multiselect : true,
		gridview: true,
		viewrecords:true,
		emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
		pager: '#grdAdminListPager',
		shrinkToFit : true,
		gridComplete: function() {
			fnSetGridSize();
		},
		loadComplete: function(data) {
	    	if(data.resultMsg == "SUCCESS") {
	    		let msgTitle = "";
				let msg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
				gfnSuccessAlert(msgTitle, msg, 2000);
	    	}
	    	else {
	    		let msgTitle = "";
				let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>' + data.resultMsg;
				gfnFailAlert(msgTitle, msg, 2000);
	    	}
		},
		resizeStop: gfnResizeStop
  });
}

</script>

</body>
</html>