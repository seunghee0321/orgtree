<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- modal Header -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">CLOUD OFFICE DEPT INFO.</h4>
</div>
<!-- modal Header -->

<!-- modal body -->
<div id="mdlEmpCompanyAddCP" class="modal-body" style="background-color:#f9f9f9;">

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="row">
			<div class="col-sm-6">
				<h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.company.mng" text="법인할당"/></h3>
			</div>
			<div class="col-sm-6" style="text-align:right;">
	            <span class="pull-right">
		            <button type="button" id="btnMdlSearchList" class="btn btn-info float-right"><i class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
	            </span>	
	            			
			</div>
		</div>
	
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
			
				<!-- general form elements -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.search.cond" text="조회조건"/></h3>
					</div>
					<!-- /.box-header -->
					
					<!-- form start -->
					<form class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
								<label for="txtMdlScSearchTxt" class="col-sm-2 control-label"><spring:message code="text.company.name" text="법인명"/></label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="txtMdlScSearchTxt" name="txtMdlScSearchTxt" onblur="javascript:gfnRegExpChk(this);" placeholder="<spring:message code="text.search.word" text="검색어"/>">
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
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.company.list" text="법인목록"/></h3>
					</div>
					<!-- /.box-header -->
					<!-- /.box-header -->
					
					<!-- form start -->
					<form class="form-horizontal">
						<div id="dvGrdBox" class="box-body grid-box-cp">
							<table id="grdCompanyList"></table>
							<div id="grdCompanyListPager"></div>
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
<!-- modal body -->

<!-- modal footer -->
<div class="modal-footer">
	<button class="btn btn-primary float-right" id="btnMdlAdd"><spring:message code="text.registration" text="등록" /></button>
  	<button id="btnMdlCancel" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.cancel" text="취소"/></button>
</div>
<!-- modal footer -->

<form id="frmHiddenParam">
  <input type="hidden" id="pModalDomainId" name="pModalDomainId" value=""/>
  <input type="hidden" id="pModalCompanyCd" name="pModalCompanyCd" value=""/>
  <input type="hidden" id="pModalEmail" name="pModalEmail" value=""/>
</form>

<script type ="text/javascript">
$(function() {
	fnMdlSetData();
	fnSetComponent();
	fnSetEvent();
}) // $(function()


//화면 컴포넌트 초기화 
function fnSetComponent() {
	// 그리드 RESIZE
	$('.grid-box-cp').on('resize', $.noop);
  	$(window).on('resize.jqGrid', function () {
  		$('#grdCompanyList').jqGrid('setGridWidth', $('.grid-box-cp').width() - 2);
  	});
	
	fnInitGrid();
	fnSetGridSize();
}

//바닥페이지에서 선택한 값 Set
function fnMdlSetData() {
	let rowId = $("#grdAdminList").jqGrid('getGridParam','selrow');
	let rowData = $("#grdAdminList").jqGrid("getRowData", rowId);
	$('#pModalDomainId').val(rowData.domainId);
	$('#pModalCompanyCd').val(rowData.companyCd);
	$('#pModalEmail').val(rowData.email);
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
		fnCheckEmpCompanyXref();
	});
	
	$('#mdlEmpCompanyAdd').on('shown.bs.modal', function (event) {
		fnMdlSetData();
		fnMdlReselGrid();
	});
}

//그리드 사이즈 초기화 
function fnSetGridSizeCP() {
	$('#grdCompanyList').jqGrid('setGridWidth', $('.grid-box-cp').width() - 2);
	$("#gbox_grdCompanyList").css("width", $('.grid-box-cp').width());
}

function fnCheckEmpCompanyXref() {
	let idx = $('#grdCompanyList').jqGrid("getGridParam", "selarrrow");
	if(idx.length == 0) {
		let msgTitle = "";
		let msg = '<spring:message code="text.select.company.msg" text="대상 법인을 선택해주세요."/>';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	let size = "DEFAULT";
	let message = '<spring:message code="text.assign.msg" text="할당하시겠습니까?"/>';
	let callback = fnInsertEmpCompanyXref;
	$("#gMdlCfmDialog").css("z-index", "3000");
	gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
}

//법인 할당
function fnInsertEmpCompanyXref() {
	let idx = $('#grdCompanyList').jqGrid("getGridParam", "selarrrow");
	let users = new Array();
	for(let i=0;i<idx.length;i++)
	{
		let params = new Object();
		params.email = $('#pModalEmail').val();
		params.domainId = $('#grdCompanyList').jqGrid("getCell", idx[i], "domainId");
		params.companyCd = $('#grdCompanyList').jqGrid("getCell", idx[i], "companyCd");
		users.push(params);
	}
	let apiUrl = "/rest/admin/auth/insertEmpCompanyXref.do";
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
				$('#mdlEmpCompanyAdd').modal("hide");
				fnReselGrid2();
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
	params.domainId = $('#pModalDomainId').val();
	params.companyNm = $('#txtMdlScSearchTxt').val();
	$("#grdCompanyList").jqGrid('clearGridData');
	$("#grdCompanyList").jqGrid('setGridParam',{
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
	  	},
	 }).trigger('reloadGrid');
}

//그리드 생성
function fnInitGrid() {
	let apiUrl = "/rest/admin/auth/selectCompanyList.do";
	let params = new Object();
	params.domainId = $('#pModalDomainId').val();
	
	$("#grdCompanyList").jqGrid({
      url: apiUrl,
      postData: params,
      datatype:"json",
      mtype : "GET",
      height:  250,
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
				, '<spring:message code="text.language.cd" text="언어코드"/>'
				, '<spring:message code="text.use" text="사용여부"/>'
	    ],
      colModel:[
	    	  	  { name: 'rowId',				index: 'rowId',					width: '30%', 	align: 'center', 	sortable: false, 	hidden: true}
				, { name: 'domainId',			index: 'domainId',				width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'companyCd',			index: 'companyCd',				width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'email',				index: 'email',					width: '50%', 	align: 'left', 		sortable: false, 	hidden: true}
				, { name: 'companyNm',			index: 'companyNm',				width: '50%', 	align: 'left', 		sortable: false}
				, { name: 'companyEngNm',		index: 'companyEngNm',			width: '50%', 	align: 'left', 		sortable: false}
				, { name: 'languageCd',			index: 'languageCd',			width: '50%', 	align: 'center', 	sortable: false}
				, { name: 'useYn',				index: 'useYn',					width: '30%', 	align: 'center', 	sortable: false}
      ],
      rowNum:10,
      rowList: [5, 10, 100],
      rownumbers : true,
      multiselect : true,
      gridview: true,
      viewrecords:true,
      emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
      pager: '#grdCompanyListPager',
      shrinkToFit : true,
	    gridComplete: function() {
	    	fnSetGridSizeCP();
	    },
	    resizeStop: gfnResizeStop
  });
}

</script> 