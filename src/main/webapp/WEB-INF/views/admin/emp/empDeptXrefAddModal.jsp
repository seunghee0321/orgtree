<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- modal Header -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">CLOUD OFFICE EMP INFO.</h4>
</div>
<!-- modal Header -->

<!-- modal body -->
<div id="mdlEmpDeptXrefAddCP" class="modal-body" style="background-color:#f9f9f9;">

	<!-- Main content -->
	<section class="content container-fluid">
	
		<div class="row">
			<!-- 조직도 트리 -->
			<div class="col-md-12">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.find.cond" text="검색조건"/></h3>
					</div>
					<!-- /.box-header -->
					
					<div class="box-body">
						<div class="form-group">
							<label for="mdlSelScCompanyCd2" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
							<div class="col-sm-4">
								<select class="form-control" id="mdlSelScCompanyCd2" name="mdlSelScCompanyCd2" onchange="fnReselEmpDeptXrefTree2()">
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
			<!-- 조직도 트리 -->
			<div class="col-md-5">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.dept.select" text="부서선택"/></h3>
					</div>
					<!-- /.box-header -->
					
					<div class="box-body">
						<!-- 트리 -->
						<div class="input-group">
							<input type="text" class="form-control" id="mdlXrefDeptSearchTxt2" name="mdlXrefDeptSearchTxt2" placeholder="<spring:message code="text.dept.name" text="부서명"/>">
							<span class="input-group-btn">
								<button type="button" id="mdlBtnXrefSearchTree2" class="btn btn-info"> <spring:message code="text.search" text="조회"/></button>
							</span>
						</div>
						<div id="mdlXrefTreeDeptList2" style="height:300px;overflow:auto;"></div>
			            
					</div>
				</div>
			</div>
			
			<div class="col-md-7">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.emp.xref.detail" text="겸직상세정보"/></h3>
					</div>
					<!-- /.box-header -->
					
					<div class="box-body" style="height:350px;">
						
						<!-- 테이블 -->
						<div id="mdlTable2" style="padding:0;">
			                <table id="simple-table" class="table">
			                  <colgroup>
			                    <col width="35%" />
			                    <col width="65%" />
			                  </colgroup>
			                  <tbody>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlDeptName2"><spring:message code="text.dept.name" text="부서명"/></label></td>
			                      <td><input type="text" class="form-control is-valid-jihun" id="mdlDeptName2" name="mdlDeptName2" value='${deptList.deptNm}' readonly required/></td>
			                    </tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlPosCd2"><spring:message code="text.emp.pos" text="직위"/></label></td>
			                      <td>
			                      	<select class="form-control" id="mdlPosCd2" name="mdlPosCd2">
									</select>
			                      </td>
			                    </tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlEmpNo2"><spring:message code="text.emp.no" text="사번"/></label></td>
			                      <td><input type="text" class="form-control" id="mdlEmpNo2" name="mdlEmpNo2" value='' placeholder="<spring:message code="text.emp.no" text="사번"/>" onblur="javascript:gfnRegExpChk(this);"/></td>
			                    </tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlHeadYn2"><spring:message code="text.emp.head" text="부서장 구분"/></label></td>
			                      <td>
			                        <select class="form-control" id="mdlHeadYn2" name="mdlHeadYn2">
										<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
										<option value="Y"><spring:message code="text.emp.head.yes" text="부서장"/></label></option>
										<option value="N"><spring:message code="text.emp.head.not" text="팀원"/></label></option>
									</select>
			                      </td>
			                    </tr>
			                    <tr>
			                    <tr>
			                      <td class="table_padding font-bold confer_title_color"><label for="mdlAddYn2"><spring:message code="text.emp.add.yn" text="겸직여부"/></label></label></td>
			                      <td>
			                        <select class="form-control is-valid-jihun" name="mdlAddYn2" id="mdlAddYn2" required readonly onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
			                          	<option value="Y" selected>Y</option>
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
		
		
	</section>
	<!-- /.content -->

</div>
<!-- modal body -->

<!-- modal footer -->
<div class="modal-footer">
	<button class="btn btn-primary float-right" id="mdlBtnAdd"><spring:message code="text.registration" text="등록" /></button>
  	<button id="mdlBtnCancel" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.cancel" text="취소"/></button>
</div>
<!-- modal footer -->

<form id="frmHiddenParam">
  <input type="hidden" id="pModalDomainId2" name="pModalDomainId2" value=""/>
  <input type="hidden" id="pModalDeptCd2" name="pModalDeptCd2" value=""/>
</form>

<script type ="text/javascript">

$(function() {
	fnSetComponentCP();
	fnSetEventCP();
}) // $(function()

//화면 컴포넌트 초기화 
function fnSetComponentCP() {
    fnResetMdlData();
    fnSetEmpPos2();
    fnInitEmpDeptXrefAddTree();
}

function fnSetEventCP() {
	// 부서 검색조건 조회
	$("#mdlXrefDeptSearchTxt2").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			let searchString = $("#mdlXrefDeptSearchTxt2").val();
			$('#mdlXrefTreeDeptList2').jstree(true).search(searchString);
		}
	});
	$("#mdlBtnXrefSearchTree2").off("click").on("click", function (e) {
		e.preventDefault();
		let searchString = $("#mdlXrefDeptSearchTxt2").val();
		$('#mdlXrefTreeDeptList2').jstree(true).search(searchString);
	});
	
	// 등록버튼
	$("#mdlBtnAdd").off("click").on("click", function(e) {
		e.preventDefault();
		if (gfnCheckRequired($("#mdlTable2"))) {
			let size = "DEFAULT";
			let gMsgCfmTitle = '<spring:message code="text.registration" text="등록" />';
			let message = '<spring:message code="text.registration.chk" text="등록 하시겠습니까?" />';
			let callback = fnInsertEmpDeptXref;
			$("#gMdlCfmDialog").css("z-index", "3000");
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
	
	$('#mdlEmpDeptXrefAdd').on('shown.bs.modal', function (event) {
		fnResetMdlData();
		fnSetEmpPos2();
		fnReselEmpDeptXrefTree2();
	});
}

//팝업 데이터 Reset
function fnResetMdlData() {
	$("#mdlSelScCompanyCd2").val($("#pCompanyCd").val());
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#mdlSelScCompanyCd2").val()) {
			$("#pModalDomainId2").val("${item.domainId}");
		}
	</c:forEach>
	$("#mdlDeptName2").val("");;
	$("#pModalDeptCd2").val("");
	$("#mdlPosCd2").val("");
	$("#mdlEmpNo2").val("");
	$("#mdlHeadYn2").val("");
}

//법인별 직위 Set
function fnSetEmpPos2() {
	$('#mdlPosCd2').children('option').remove();
	let apiUrl = "/rest/admin/emp/selectCodeDetailList.do";
	let params = new Object();
	params.domainId = $("#pModalDomainId2").val();
	params.companyCd = $("#mdlSelScCompanyCd2").val();
	params.codeDiv = "CM002";
	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(result) {
			let data = result.data;
			let option = '<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>';
			$("#mdlPosCd2").append(option);
			for(let i=0;i<data.length;i++) {
				option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
				$("#mdlPosCd2").append(option);
			}
		}
	});
}

// 겸직상세정보 Insert
function fnInsertEmpDeptXref() {
	let apiUrl = "/rest/admin/emp/insertEmpDeptXrefDetail.do";
	let params = new Object();
	params.domainId = $("#pModalDomainId2").val();
	params.companyCd = $("#mdlSelScCompanyCd2").val();
	params.deptCd = $('#pModalDeptCd2').val();
	params.email = $("#pEmail").val();
	params.posCd = $('#mdlPosCd2').val();
	params.empNo = $("#mdlEmpNo2").val();
	params.headYn = $('#mdlHeadYn2').val();
	params.addYn = $("#mdlAddYn2").val();
	
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
			$('#mdlEmpDeptXrefAdd').modal("hide");
			fnReselEmpDeptXref(params);
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

//트리 재조회
function fnReselEmpDeptXrefTree2() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	params.companyCd = $("#mdlSelScCompanyCd2").val();
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#mdlSelScCompanyCd2").val()) {
			$("#pModalDomainId2").val("${item.domainId}");
		}
	</c:forEach>
	params.domainId = $("#pModalDomainId2").val();
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
	  		$('#mdlXrefTreeDeptList2').jstree(true).settings.core.data = deptList;
	  		$('#mdlXrefTreeDeptList2').jstree(true).refresh();
		}
	});
}

//트리 생성
function fnInitEmpDeptXrefAddTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	let deptList = new Array();
	params.domainId = $("#pModalDomainId2").val();
	params.companyCd = $("#mdlSelScCompanyCd2").val();
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
	        $('#mdlXrefTreeDeptList2').jstree({
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
	        	$("#pModalDeptCd2").val(data.instance.get_node(data.selected).id);
	        	$("#mdlDeptName2").val(data.instance.get_node(data.selected).text);
	        	fnSetEmpPos2();
	        })
		},
		error:function (data) {
		}
	});
}
</script> 