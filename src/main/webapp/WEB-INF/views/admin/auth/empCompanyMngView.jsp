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
				<h1 id="contentTitle" class="h4 mb-0 text-gray-800">
					<spring:message code="text.admin.company.mng" text="관리자별법인할당"/>
				</h1>
				<ul class="navbar-nav ml-auto">
					<button type="button" id="btnSearchList" class="card border-left-primary shadow h-100 py-2"><i class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
					<button type="button" id="btnAdd" class="card border-left-success shadow h-100 py-2"><i class="ace-icon fa fa-plus"></i><spring:message code="text.assign" text="할당"/></button>
					<button type="button" id="btnCancel" class="card border-left-info shadow h-100 py-2"><i class="ace-icon fa fa-minus"></i><spring:message code="text.cancel" text="취소"/></button>
				</ul>
			</nav>
		<!-- Main content -->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="card shadow mb-4">
						<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i> <spring:message code="text.search.cond" text="조회조건"/></h6>
						</div>
						<!-- /.card-header -->
						
						<!-- form start -->
						<div class="card-body">
							<div class="form-group row">
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
						<!-- /.card-body -->
					</div>
					<!-- /.box -->			
				</div>
			</div>
			
			<div class="row">
				<!-- general form elements -->
				<div class="col-md-12 col-sm-7">
					<div class="card shadow mb-4">
						<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i><spring:message code="text.admin.list" text="관리자목록"/></h6>
						</div>
						<!-- form start -->
						<form class="form-horizontal">
							<div id="dvGrdBox" class="box-body grid-box">
								<table id="grdAdminList"></table>
								<div id="grdAdminListPager"></div>
							</div>
							<!-- /.card-body -->
						</form>
					</div>
				</div>
				<!-- /.card -->
			</div>
			
			<div class="row">
				<!-- general form elements -->
				<div class="col-md-12 col-sm-7">
					<div class="card shadow mb-4">
						<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i><spring:message code="text.assign.company.list" text="관리자 할당 법인목록"/></h6>
						</div>
						<!-- form start -->
						<form class="form-horizontal">
							<div id="dvGrdBox2" class="box-body grid-box">
								<table id="grdEmpCompanyList"></table>
							</div>
							<!-- /.card-body -->
						</form>
					</div>
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.content -->		
	</div>
	<!-- Main Footer -->
	<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>
	</div>
</div>

<div class="modal fade " id="mdlEmpCompanyAdd">
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
	gfnSelectMenu("mgEmpCompanyMng", "");
}

//화면 컴포넌트 초기화 
function fnSetComponent() {
  	$("#selScCompanyCd").val('${sessionUser.companyCd}').attr('selected', true);
 	// 선택한 법인의 domainId 저장해둠
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			gDomainId = "${item.domainId}";
		}
	</c:forEach>
	
	fnInitGrid();
	fnInitGrid2();
	fnSetGridSize();
}

//이벤트
function fnSetEvent() {
	$("#dvGrdBox, #dvGrdBox2").on("resize", function(){
		setTimeout(fnSetGridSize, 500);
	})
	
	// 검색조건 조회
	$("#txtScSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			fnReselGrid();
			fnReselGrid2();
		}
	});
	$("#btnSearchList").off("click").on("click", function (e) {
		e.preventDefault();
		fnReselGrid();
		fnReselGrid2();
	});
	
	// 법인할당버튼 클릭
	$("#btnAdd").off("click").on("click", function (e) {
		e.preventDefault();
		fnEmpCompanyAddChk();
	});
	
	// 할당취소버튼 클릭
	$("#btnCancel").off("click").on("click", function (e) {
		e.preventDefault();
		fnEmpCompanyCancelChk();
	});
}

//그리드 사이즈 초기화 
function fnSetGridSize() {
	$('#grdAdminList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
	$("#gbox_grdAdminList").css("width", $('.grid-box').width());

	$('#grdEmpCompanyList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
	$("#gbox_grdEmpCompanyList").css("width", $('.grid-box').width());
}

//법인할당체크
function fnEmpCompanyAddChk() {
	let idx = $("#grdAdminList").jqGrid("getGridParam", "selarrrow");
	if (idx.length <= 0) {
		let title = "";
    	let msg = '<spring:message code="text.select.emp.msg" text="대상 사용자를 선택해주세요."/>';
    	gfnFailAlert(title, msg, gDelay2);
    	return false;
	}
	fnShowMdlEmpCompanyXref();
}

//법인할당팝업 Open
function fnShowMdlEmpCompanyXref() {
	$('#mdlEmpCompanyAdd .modal-content').load('/admin/auth/empCompanyAddModal.do', function () {
		$('#mdlEmpCompanyAdd').modal('show');
	});
}

//법인할당취소체크
function fnEmpCompanyCancelChk() {
	let title = "";
	let msg = "";
	let idx = $("#grdEmpCompanyList").jqGrid("getGridParam", "selarrrow");
	if (idx.length <= 0) {
		msg = '<spring:message code="text.select.company.msg" text="대상 법인을 선택해주세요."/>';
    	gfnFailAlert(title, msg, gDelay2);
    	return false;
	}
	
	let empIdx = $("#grdAdminList").jqGrid("getGridParam", "selarrrow");
	let empCompanyCd = $('#grdAdminList').jqGrid("getCell", empIdx[0], "companyCd");
	for(let i=0;i<idx.length;i++)
	{
		let grdCompanyCd = $('#grdEmpCompanyList').jqGrid("getCell", idx[i], "companyCd");
		if(grdCompanyCd == empCompanyCd) {
			msg = '<spring:message code="text.company.cancle.error" text="해당 법인은 취소할 수 없습니다."/>';
	    	gfnFailAlert(title, msg, gDelay2);
	    	return false;
		}
	}
	
	let size = "DEFAULT";
	let message = '<spring:message code="text.assign.cancle.msg" text="할당을 취소하시겠습니까?"/>';
	let callback = fnDeleteEmpCompanyXref;
	gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
}

//할당취소버튼
function fnDeleteEmpCompanyXref() {
	let idx = $("#grdEmpCompanyList").jqGrid("getGridParam", "selarrrow");
	let users = new Array();
	
	for(let i=0;i<idx.length;i++)
	{
		let params = new Object();
		params.domainId = $('#grdEmpCompanyList').jqGrid("getCell", idx[i], "domainId");
		params.companyCd = $('#grdEmpCompanyList').jqGrid("getCell", idx[i], "companyCd");
		params.email = $('#grdEmpCompanyList').jqGrid("getCell", idx[i], "email");
		users.push(params);
	}
	let apiUrl = "/rest/admin/auth/deleteEmpCompanyXref.do";
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
				fnReselGrid2();
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

//사용자목록 그리드 재조회
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
      height:  180,
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
	  resizeStop: gfnResizeStop,
	  onSelectRow: function(rowId, status, e) {
	  	fnReselGrid2();
	  },
	  beforeSelectRow: function(rowid, e) {
	    jQuery("#grdAdminList").jqGrid('resetSelection');
	    return(true);
	  }
  });
}

//할당법인목록 그리드 재조회
function fnReselGrid2() {
	let rowId = $("#grdAdminList").jqGrid('getGridParam','selrow');
	let rowData = $("#grdAdminList").jqGrid("getRowData", rowId);
	let params = new Object();
	params.domainId = rowData.domainId;
	params.email = rowData.email;
	$("#grdEmpCompanyList").jqGrid('clearGridData');
	$("#grdEmpCompanyList").jqGrid('setGridParam',{
  		postData : params,
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
	  	}
	}).trigger('reloadGrid');
}

//그리드 생성
function fnInitGrid2() {
	let apiUrl = "/rest/admin/auth/selectEmpCompanyXref.do";
	let params = new Object();
	
	$("#grdEmpCompanyList").jqGrid({
      url: apiUrl,
      postData: params,
      datatype:"json",
      mtype : "GET",
      height:  140,
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
				, 'email'
				, '<spring:message code="text.company" text="법인"/>'
				, '<spring:message code="text.company.eng.name" text="법인영문명"/>'
				, '<spring:message code="text.use" text="사용여부"/>'
	    ],
      colModel:[
				  { name: 'rowId',				index: 'rowId',					width: '30%', 	align: 'center', 	sortable: false, 	hidden: true}
				, { name: 'domainId',			index: 'domainId',				width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'companyCd',			index: 'companyCd',				width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'email',				index: 'email',					width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'companyNm',			index: 'companyNm',				width: '50%', 	align: 'left', 		sortable: false}
				, { name: 'companyEngNm',		index: 'companyEngNm',			width: '50%', 	align: 'left', 		sortable: false}
				, { name: 'useYn',				index: 'useYn',					width: '30%', 	align: 'center', 	sortable: false}
      ],
      rowNum:10,
      rowList: [5, 10, 100],
      rownumbers : true,
      multiselect : true,
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