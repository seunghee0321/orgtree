<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- modal Header -->
<div class="modal-header">
	<h6 class="modal-title">CLOUD OFFICE EMP INFO.</h6>
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<!-- modal Header -->

<!-- modal body -->
<div id="mdlEmpRoleAddCP" class="modal-body" style="background-color:#f9f9f9;">

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-6">
				<h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.emp.role.mng" text="관리자할당"/></h3>
			</div>
			<div class="col-sm-6" style="text-align:right;">
	            <span class="pull-right">
		            <button type="button" id="btnMdlSearchList" class="btn btn-info float-right"><i class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
	            </span>	
			</div>
		</div>
	</section>

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
							<label for="selMdlScCompanyCd" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
							<div class="col-sm-3">
								<select class="form-control" id="selMdlScCompanyCd" name="selMdlScCompanyCd" onchange="fnMdlReselGrid()">
									<c:forEach items="${companyList}" var="option">
										<option value="${option.companyCd}">${option.companyNm}</option>
									</c:forEach>
								</select>
							</div>

							<label for="txtMdlScSearchTxt" class="col-sm-1 control-label"><spring:message code="text.emp.name" text="성명"/></label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="txtMdlScSearchTxt" name="txtMdlScSearchTxt" onblur="javascript:gfnRegExpChk(this);" placeholder="<spring:message code="text.search.word" text="검색어"/>">
							</div>

						</div>
					</div>
					<!-- /.card-body -->
				</div>
			</div>
		</div>
		
		<div class="row">
			<!-- general form elements -->
			<div class="col-md-12 col-sm-7">
				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i> <spring:message code="text.emp.list" text="사용자목록"/></h6>
					</div>

					<!-- form start -->
					<form class="form-horizontal">
						<div id="dvGrdBox" class="box-body grid-box-cp">
							<table id="grdRoleUserList"></table>
							<div id="grdRoleUserListPager"></div>
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
<!-- modal body -->

<!-- modal footer -->
<div class="modal-footer">
	<button class="btn btn-primary float-right" id="btnMdlAdd"><spring:message code="text.registration" text="등록" /></button>
  	<button id="btnMdlCancel" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.cancel" text="취소"/></button>
</div>
<!-- modal footer -->

<form id="frmHiddenParam">
  <input type="hidden" id="pModalDomainId2" name="pModalDomainId2" value=""/>
  <input type="hidden" id="pModalDeptCd2" name="pModalDeptCd2" value=""/>
</form>

<script type ="text/javascript">
//let gMdlDomainId = "";
$(function() {
	fnSetComponent();
	fnSetEvent();
}) // $(function()

//화면 컴포넌트 초기화 
function fnSetComponent() {
	// 그리드 RESIZE
	$('.grid-box-cp').on('resize', $.noop);
  	$(window).on('resize.jqGrid', function () {
  		$('#grdRoleUserList').jqGrid('setGridWidth', $('.grid-box-cp').width() - 2);
  	});
  	
  	$("#selMdlScCompanyCd").val($('#selScCompanyCd').val());
 	// 선택한 법인의 domainId 저장해둠
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selMdlScCompanyCd").val()) {
			gMdlDomainId = "${item.domainId}";
		}
	</c:forEach>
	
	fnInitGrid();
	fnSetGridSize();
}

//이벤트
function fnSetEvent() {
	$("#dvGrdBox").on("resize", function(){
		setTimeout(fnSetGridSizeCP, 200);
	})		
	
	// 검색조건 조회
	$("#txtMdlScSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			fnMdlReselGrid();
		}
	});
	$("#btnMdlSearchList").off("click").on("click", function (e) {
		e.preventDefault();
		fnMdlReselGrid();
	});
	
	// 법인할당버튼 클릭
	$("#btnMdlAdd").off("click").on("click", function (e) {
		e.preventDefault();
		fnCheckRoleAdmin();
	});
	
	$('#mdlEmpRoleAdd').on('shown.bs.modal', function (event) {
		$("#txtMdlScSearchTxt").val("");
		fnMdlReselGrid();
	});
}

//그리드 사이즈 초기화 
function fnSetGridSizeCP() {
	$('#grdRoleUserList').jqGrid('setGridWidth', $('.grid-box-cp').width() - 2);
	$("#gbox_grdRoleUserList").css("width", $('.grid-box-cp').width());
}

function fnCheckRoleAdmin() {
	let idx = $('#grdRoleUserList').jqGrid("getGridParam", "selarrrow");
	if(idx.length == 0) {
		let msgTitle = "";
		let msg = '<spring:message code="text.emp.select.fail" text="사용자가 선택되지 않았습니다."/>';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	
	let size = "DEFAULT";
	let message = '<spring:message code="text.assign.msg" text="할당하시겠습니까?"/>';
	let callback = fnInsertRoleAdmin;
	$("#gMdlCfmDialog").css("z-index", "3000");
	gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
}

//관리자 할당
function fnInsertRoleAdmin() {
	let idx = $('#grdRoleUserList').jqGrid("getGridParam", "selarrrow");
	let users = new Array();
	for(let i=0;i<idx.length;i++)
	{
		let params = new Object();
		params.domainId = $('#grdRoleUserList').jqGrid("getCell", idx[i], "domainId");
		params.companyCd = $('#grdRoleUserList').jqGrid("getCell", idx[i], "companyCd");
		params.email = $('#grdRoleUserList').jqGrid("getCell", idx[i], "email");
		users.push(params);
	}
	let apiUrl = "/rest/admin/auth/insertEmpRoleAdmin.do";
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
				let msg = '<spring:message code="text.save.success" text="저장 성공하였습니다."/>';
				gfnSuccessAlert("", msg, gDelay3);
				$('#mdlEmpRoleAdd').modal("hide");
				fnReselGrid();
			}
			else {
				let msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>' + result.resultMsg;
				gfnFailAlert("", msg, gDelay2);
			}
		}
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}

//그리드 재조회
function fnMdlReselGrid() {
	let params = new Object();
	// 선택한 법인의 domainId 저장해둠
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selMdlScCompanyCd").val()) {
			gMdlDomainId = "${item.domainId}";
		}
	</c:forEach>
	params.domainId = gMdlDomainId;
	params.companyCd = $("#selMdlScCompanyCd").val();
	params.empNm = $("#txtMdlScSearchTxt").val();
	$("#grdRoleUserList").jqGrid('clearGridData');
	$("#grdRoleUserList").jqGrid('setGridParam',{
    	postData : params
  	}).trigger('reloadGrid');
}

//그리드 생성
function fnInitGrid() {
	let apiUrl = "/rest/admin/auth/selectEmpRoleUserList.do";
	let params = new Object();
	params.domainId = gMdlDomainId;
	params.companyCd = $("#selMdlScCompanyCd").val();
	params.empNm = $("#txtMdlScSearchTxt").val();
	
	$("#grdRoleUserList").jqGrid({
      url: apiUrl,
      postData: params,
      datatype: 'json',
      mtype : 'GET',
      height:  350,
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
				, { name: 'deptNm',				index: 'deptNm',				width: '55%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'posNm',				index: 'posNm',					width: '30%', 	align: 'center', 	sortable: false, 	hidden: true}
				, { name: 'empNo', 				index: 'empNo', 				width: '40%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'empStatusNm',		index: 'empStatusNm',		width: '30%', 	align: 'center', 	sortable: false}
				, { name: 'authorityName',		index: 'authorityName',		width: '30%', 	align: 'center', 	sortable: false}
      ],
      rowNum:10,
      rowList: [5, 10, 100],
      rownumbers : true,
      autoencode: true,
      multiselect : true,
      gridview: true,
      viewrecords:true,
      loadonce: false,
      emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
      pager: '#grdRoleUserListPager',
      shrinkToFit : true,
      gridComplete: function() {
		  fnSetGridSizeCP();
      },
	  resizeStop: gfnResizeStop
  });
}

</script> 