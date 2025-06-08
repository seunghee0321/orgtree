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
<div id="mdlEmpDeptXrefCP" class="modal-body" style="background-color:#f9f9f9;">

	<!-- Main content -->
	<div class="container-fluid">
	
		<div class="row">
			<div class="col-md-12">
				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary"><i class="fa fa-folder-open"></i> <spring:message code="text.find.cond" text="검색조건"/></h6>
					</div>

					<div class="card-body">
						<div class="form-group row">
							<label for="mdlSelScCompanyCd" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
							<div class="col-sm-4">
								<select class="form-control" id="mdlSelScCompanyCd" name="mdlSelScCompanyCd" onchange="fnReselEmpDeptXrefTree()">
									<c:forEach items="${companyList}" var="option">
										<option value="${option.companyCd}">${option.companyNm}</option>  
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-3 col-sm-5">

				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-sitemap"></i> <spring:message code="text.dept.select" text="부서선택"/></h6>
					</div>

					<div class="card-body">
						<div class="row">
							<div class="col-sm-12 d-flex align-items-center" style="gap: 5px; flex-wrap: nowrap;">
								<!-- 부서명 입력박스 -->
								<input type="text" class="form-control" id="mdlXrefDeptSearchTxt" name="mdlXrefDeptSearchTxt" placeholder="<spring:message code="text.dept.name" text="부서명"/>">
								<span class="input-group-btn">
									<button type="button" id="mdlBtnXrefSearchTree" class="btn btn-info"><spring:message code="text.search" text="조회"/></button>
								</span>
							</div>
						<div id="mdlXrefTreeDeptList" style="height:300px;overflow:auto;"></div>
						</div>
					</div>
				</div>
			</div>

			<!-- general form elements -->
			<div class="col-md-9 col-sm-7">
				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-users"></i> <spring:message code="text.emp.xref.detail" text="겸직상세정보"/></h6>
					</div>

					<div class="card-body" style="height:350px;">
						
						<!-- 테이블 -->
						<div id="mdlTable1" style="padding:0;">
			                <table id="simple-table" class="table">
			                  <colgroup>
			                    <col width="35%" />
			                    <col width="65%" />
			                  </colgroup>
			                  <tbody>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlDeptName"><spring:message code="text.dept.name" text="부서명"/></label></td>
			                      <td><input type="text" class="form-control is-valid-jihun" id="mdlDeptName" name="mdlDeptName" value='${deptList.deptNm}' readonly required/></td>
			                    </tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlPosCd"><spring:message code="text.emp.pos" text="직위"/></label></td>
			                      <td>
			                      	<select class="form-control" id="mdlPosCd" name="mdlPosCd"></select>
			                      </td>
			                    </tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlEmpNo"><spring:message code="text.emp.no" text="사번"/></label></td>
			                      <td><input type="text" class="form-control" id="mdlEmpNo" name="mdlEmpNo" value='' placeholder="<spring:message code="text.emp.no" text="사번"/>" onblur="javascript:gfnRegExpChk(this);"/></td>
			                    </tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlHeadYn"><spring:message code="text.emp.head" text="부서장 구분"/></label></td>
			                      <td>
			                        <select class="form-control" id="mdlHeadYn" name="mdlHeadYn">
										<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
										<option value="Y"><spring:message code="text.emp.head.yes" text="부서장"/></label></option>
										<option value="N"><spring:message code="text.emp.head.not" text="팀원"/></label></option>
									</select>
			                      </td>
			                    </tr>
			                    <tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlAddYn"><spring:message code="text.emp.add.yn" text="겸직여부"/></label></td>
			                      <td>
			                        <select class="form-control is-valid-jihun" name="mdlAddYn" id="mdlAddYn" required readonly onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
			                          	<option value="Y">Y</option>
			                          	<option value="N">N</option>
			                        </select>
			                      </td>
			                    </tr>
			                  </tbody>
			                </table>
			            </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.content -->
</div>
<!-- modal body -->

<!-- modal footer -->
<div class="modal-footer">
	<button class="btn btn-primary" id="mdlBtnSave"><spring:message code="text.save" text="저장" /></button>
  	<button class="btn btn-primary" id="mdlBtnDelete"><spring:message code="text.delete" text="삭제" /></button>
  	<button id="mdlBtnCancel" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.cancel" text="취소"/></button>
</div>
<!-- modal footer -->

<form id="frmHiddenParam">
  <input type="hidden" id="pModalDomainId" name="pModalDomainId" value=""/>
  <input type="hidden" id="pModalDeptCd" name="pModalDeptCd" value=""/>
  <input type="hidden" id="pModalEmail" name="pModalEmail" value=""/>
</form>

<script type ="text/javascript">
let gMdlOldDomainId = ""; // 기존겸직정보
let gMdlOldCompanyCd = "";
let gMdlOldDeptCd = "";
let gMdlOldEmail = "";

$(function() {
	fnSetMdlData();
	fnSetComponentCP();
	fnSetEventCP();
}) // $(function()

//화면 컴포넌트 초기화 
function fnSetComponentCP() {
	// 그리드 RESIZE
	$('.grid-box-company-modal').on('resize', $.noop);
    $(window).on('resize.jqGrid', function () {
    	$('#grdCompanyListCP').jqGrid('setGridWidth', $('.grid-box-company-modal').width() - 2);
    });
	
    fnInitEmpDeptXrefViewTree();
}

function fnSetEventCP() {
	// 부서 검색조건 조회
	$("#mdlXrefDeptSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			let searchString = $("#mdlXrefDeptSearchTxt").val();
			$('#mdlXrefTreeDeptList').jstree(true).search(searchString);
		}
	});
	$("#mdlBtnXrefSearchTree").off("click").on("click", function (e) {
		e.preventDefault();
		let searchString = $("#mdlXrefDeptSearchTxt").val();
		$('#mdlXrefTreeDeptList').jstree(true).search(searchString);
	});
	
	// 저장버튼
	$("#mdlBtnSave").off("click").on("click", function(e) {
		e.preventDefault();
		if (gfnCheckRequired($("#mdlTable1"))) {
			let size = "DEFAULT";
			let gMsgCfmTitle = '<spring:message code="text.modify" text="수정" />';
			let message = '<spring:message code="text.modify.chk" text="수정 하시겠습니까?" />';
			let callback = fnUpdateEmpDeptXref;
			$("#gMdlCfmDialog").css("z-index", "3000");
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
	
	// 삭제버튼
	$("#mdlBtnDelete").off("click").on("click", function(e) {
		e.preventDefault();
		let size = "DEFAULT";
		let gMsgCfmTitle = '<spring:message code="text.delete" text="삭제" />';
		let message = '<spring:message code="text.delete.chk" text="삭제 하시겠습니까?" />';
		let callback = fnDeleteEmpDeptXref;
		$("#gMdlCfmDialog").css("z-index", "3000");
		gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
	});
	
	$('#mdlEmpDeptXref').on('shown.bs.modal', function (event) {
		fnSetMdlData();
		fnReselEmpDeptXrefTree();
	});
}

//겸직 그리드->팝업 데이터 Set
function fnSetMdlData() {
	let rowId = $("#grdAddYnList").jqGrid('getGridParam','selrow');
	let rowData = $("#grdAddYnList").jqGrid("getRowData", rowId);
	gMdlOldDomainId = rowData.domainId;
	gMdlOldCompanyCd = rowData.companyCd;
	gMdlOldDeptCd = rowData.deptCd;
	gMdlOldEmail = rowData.email;
	$("#mdlSelScCompanyCd").val(rowData.companyCd);
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#mdlSelScCompanyCd").val()) {
			$("#pModalDomainId").val("${item.domainId}");
		}
	</c:forEach>
	$("#mdlDeptName").val(rowData.deptNm);
	$("#pModalDeptCd").val(rowData.deptCd);
	$("#pModalEmail").val(rowData.email);
	//$("#mdlPosCd").val(rowData.posCd);
	$("#mdlEmpNo").val(rowData.empNo);
	if(rowData.headYn == '<spring:message code="text.emp.head.yes" text="부서장"/>') $("#mdlHeadYn").val("Y");
	else $("#mdlHeadYn").val("N");
	$("#mdlAddYn").val(rowData.addYn);
	// 겸직여부가 'N'이면 삭제 불가능
	if(rowData.addYn == 'N') {
		$("#mdlBtnDelete").hide();
	}
	else {
		$("#mdlBtnDelete").show();
	}
	
	fnSetEmpPos(rowData.posCd); // 직위 Set
}

//법인별 직위 Set
function fnSetEmpPos(posCd) {
	$('#mdlPosCd').children('option').remove();
	let apiUrl = "/rest/admin/emp/selectCodeDetailList.do";
	let params = new Object();
	params.domainId = $("#pModalDomainId").val();
	params.companyCd = $("#mdlSelScCompanyCd").val();
	params.codeDiv = "CM002";
	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(result) {
			let data = result.data;
			let option = '<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>';
			$("#mdlPosCd").append(option);
			for(let i=0;i<data.length;i++) {
				option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
				$("#mdlPosCd").append(option);
			}
			$("#mdlPosCd").val(posCd);
			
		}
	});
}

// 겸직상세정보 Update
function fnUpdateEmpDeptXref() {
	let apiUrl = "/rest/admin/emp/updateEmpDeptXrefDetail.do";
	let params = new Object();
	params.oldDomainId = gMdlOldDomainId;
	params.oldCompanyCd = gMdlOldCompanyCd;
	params.oldDeptCd = gMdlOldDeptCd;
	params.oldEmail = gMdlOldEmail;
	params.companyCd = $("#mdlSelScCompanyCd").val();
	params.domainId = $("#pModalDomainId").val();
	params.deptCd = $('#pModalDeptCd').val();
	params.email = $('#pModalEmail').val();
	params.posCd = $('#mdlPosCd').val();
	params.empNo = $("#mdlEmpNo").val();
	params.headYn = $('#mdlHeadYn').val();
	params.addYn = $("#mdlAddYn").val();
	
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
			$('#mdlEmpDeptXref').modal("hide");
			location.reload();
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
    	let msgTitle = "";
		let msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
		gfnFailAlert(msgTitle, msg, 2000);
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
	
}

//겸직상세정보 Delete
function fnDeleteEmpDeptXref() {
	let apiUrl = "/rest/admin/emp/deleteEmpDeptXrefDetail.do";
	let params = new Object();
	params.domainId = gMdlOldDomainId;
	params.companyCd = gMdlOldCompanyCd;
	params.deptCd = gMdlOldDeptCd;
	params.email = gMdlOldEmail;
	
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
			$('#mdlEmpDeptXref').modal("hide");
			fnReselEmpDeptXref(params);
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
//트리 재조회
function fnReselEmpDeptXrefTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	params.companyCd = $("#mdlSelScCompanyCd").val();
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#mdlSelScCompanyCd").val()) {
			$("#pModalDomainId").val("${item.domainId}");
		}
	</c:forEach>
	params.domainId = $("#pModalDomainId").val();
	let deptList = new Array();
	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
	  		$.each(data.data, function(idx, item){
	  			if(item.parentDeptCd == '0') deptList[idx] = {id:item.deptCd, parent:'#', text:item.deptNm, icon:'/common/common/images/company.png'};
        		else {
	        		if(item.deptUseYn == 'N') deptList[idx] = {id:item.deptCd, parent:item.parentDeptCd, text:item.deptNm+'(N/A)'};
	        		else deptList[idx] = {id:item.deptCd, parent:item.parentDeptCd, text:item.deptNm};
	        	}
	    	});
	  		$('#mdlXrefTreeDeptList').jstree(true).settings.core.data = deptList;
	  		$('#mdlXrefTreeDeptList').jstree(true).refresh();
		}
	});
}

//트리 생성
function fnInitEmpDeptXrefViewTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	let deptList = new Array();
	params.domainId = $("#pModalDomainId").val();
	params.companyCd = $("#mdlSelScCompanyCd").val();
    $.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
	        $.each(data.data, function(idx, item){
	        	if(item.parentDeptCd == '0') deptList[idx] = {id:item.deptCd, parent:'#', text:item.deptNm, icon:'/common/common/images/company.png'};
        		else {
	        		if(item.deptUseYn == 'N') deptList[idx] = {id:item.deptCd, parent:item.parentDeptCd, text:item.deptNm+'(N/A)'};
	        		else deptList[idx] = {id:item.deptCd, parent:item.parentDeptCd, text:item.deptNm};
	        	}
	        });
	        $('#mdlXrefTreeDeptList').jstree({
	            'core': {
	                'data': deptList
	            },
	            'types': {
	                'default': {
	                	'icon': '/common/common/images/dept.png'
	                }
	            },
	            'plugins' : ["search", "types"]
	            
	        })
	        // 노드 선택시 발생하는 이벤트
	        .bind('select_node.jstree', function(event, data){
	        	$("#pModalDeptCd").val(data.instance.get_node(data.selected).id);
	        	$("#mdlDeptName").val(data.instance.get_node(data.selected).text);
	        	fnSetEmpPos();
	        })
		},
		error:function (data) {
		}
	});
}
</script> 