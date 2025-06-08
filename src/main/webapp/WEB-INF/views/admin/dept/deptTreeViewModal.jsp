<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- modal Header -->
<div class="modal-header">
	<h6 class="modal-title">CLOUD OFFICE DEPT TREE.</h6>
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<!-- modal Header -->

<!-- modal body -->
<div id="mdlDeptPickerCP" class="modal-body" style="background-color:#f9f9f9;">

	<!-- Main content -->
	<div class="content container-fluid">

		<div class="row">
			<div class="col-md-12">
				<div class="card shadow mb-4">
					<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-sitemap"></i> <spring:message code="text.dept.select" text="부서선택"/></h6>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<div class="row">
							<div class="col-sm-12 d-flex align-items-center" style="gap: 5px; flex-wrap: nowrap;">
								<!-- 부서명 입력박스 -->
								<input type="text" class="form-control" id="txtDeptSearchTxt" name="txtDeptSearchTxt" placeholder="<spring:message code="text.dept.name" text="부서명"/>">
								<span class="input-group-btn">
									<button type="button" id="btnSearchTree" class="btn btn-info"><spring:message code="text.search" text="조회"/></button>
								</span>
							</div>
						</div>
						<div id="mdlTreeDeptList" style="height:350px;overflow:auto;"></div>
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
	<button id="mdlBtnOk" type="button" class="btn btn-primary"><spring:message code="text.select" text="선택"/></button>
	<button id="mdlBtnCancel" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.cancel" text="취소"/></button>
</div>
<!-- modal footer -->

<form id="frmHiddenParam">
  <input type="hidden" id="pModalDeptCd" name="pModalDeptCd" value=""/>
  <input type="hidden" id="pModalDeptNm" name="pModalDeptNm" value=""/>
</form>

<script type ="text/javascript">
$(function() {
	fnSetComponent();
	fnSetEvent();
}) // $(function()

//화면 컴포넌트 초기화 
function fnSetComponent() {
	fnInitMdlTree();
}

function fnSetEvent() {
	// 부서 검색조건 조회
	$("#txtDeptSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			let searchString = $("#txtDeptSearchTxt").val();
			$('#mdlTreeDeptList').jstree(true).search(searchString);
		}
	});
	$("#btnSearchTree").off("click").on("click", function (e) {
		e.preventDefault();
		let searchString = $("#txtDeptSearchTxt").val();
		$('#mdlTreeDeptList').jstree(true).search(searchString);
	});
	
	// 선택 버튼
	$("#mdlBtnOk").off("click").on("click", function(e) {
		e.preventDefault();
		$("#mdlDeptPicker").modal("hide");
		fnSetSelectDeptCd();
	});
	
	$('#mdlDeptPicker').on('shown.bs.modal', function (event) {
		fnDeptMdlReselTree();
	});
}

// 부서 선택 이벤트
function fnSetSelectDeptCd() {
	let selDeptInfo = new Object();
	selDeptInfo.deptCd = $("#pModalDeptCd").val();
	selDeptInfo.deptNm = $("#pModalDeptNm").val();
	fnSetDeptInfo(selDeptInfo); // 기존화면메소드 실행
}

//트리 재조회
function fnDeptMdlReselTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	let deptList = new Array();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#pCompanyCd").val();
	params.deptCd = $("#pMdlDeptCd").val();
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
	  		$('#mdlTreeDeptList').jstree(true).settings.core.data = deptList;
	  		$('#mdlTreeDeptList').jstree(true).refresh();
		}
	});
}

//트리 생성
function fnInitMdlTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	let deptList = new Array();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#pCompanyCd").val();
	params.deptCd = $("#pMdlDeptCd").val();
    $.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
	        $.each(data.data, function(idx, item){
	        	if(item.parentDeptCd == '0') deptList[idx] = {id:item.deptCd, parent:'#', text:item.deptNm, icon:'/common/common/images/company.png'};
	        	else deptList[idx] = {id:item.deptCd, parent:item.parentDeptCd, text:item.deptNm};
	        });
	        $('#mdlTreeDeptList').jstree({
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
	        	$("#pModalDeptNm").val(data.instance.get_node(data.selected).text);
	        })
		},
		error:function (data) {
		}
	});
}
</script> 