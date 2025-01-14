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
					<h3 id="contentTitle" style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.dept.mng" text="부서관리" /></h3>
				</div>
				<div class="col-sm-6" style="text-align:right;">
		            <span class="pull-right" id="btnAdmins">
		            	<button class="btn btn-primary float-right" id="btnAdd"><i class="ace-icon fa fa-plus"></i><spring:message code="text.add" text="추가" /></button>
		            	<button class="btn btn-primary float-right" id="btnModify"><i class="ace-icon fa fa-pencil-square-o"></i><spring:message code="text.modify" text="수정" /></button>
		            	<button type="button" id="btnDownExcelForm" class="btn btn-primary float-right"><i class="fa fa-file-excel-o"></i> <spring:message code="text.excel.upload.form" text="업로드양식"/></button>
			            <!-- 엑셀 업로드 form -->
			            <form id="frmAttachedFiles" class="form-horizontal" enctype="multipart/form-data" style="display:inline;">
							<div class="btn btn-primary btn-file">
			                  <i class="fa fa-paperclip"></i> <spring:message code="text.excel.upload" text="엑셀업로드"/>
			                  <input type="file" id="btnUploadExcel" name="btnUploadExcel">
			                </div>
						</form>	
		            </span>		
		            <span class="pull-right" id="btnAdds" style="display:none;">
		            	<button class="btn btn-primary float-right" id="btnSave"><i class="ace-icon fa fa-plus"></i> <spring:message code="text.registration" text="등록" /></button>
		            	<button class="btn btn-primary float-right" id="btnClose"><i class="ace-icon fa fa-floppy-o"></i> <spring:message code="text.list" text="목록" /></button>
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
										<select class="form-control" id="selScCompanyCd" name="selScCompanyCd" onchange="fnReselTree()">
											<c:forEach items="${companyList}" var="option">
												<option value="${option.companyCd}">${option.companyNm}</option>  
											</c:forEach>
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
			
			<div class="row">
				<!-- 조직도 트리 -->
				<div class="col-md-4">
					<div class="box box-primary" style="height:602px;">
						<div class="box-header with-border">
							<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.org.chart" text="조직도" /></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-8">
								<input type="text" class="form-control" id="txtDeptSearchTxt" name="txtDeptSearchTxt" placeholder=" <spring:message code="text.dept.name" text="부서명"/>">
								</div>
								<span>
					            	<button type="button" id="btnSearchTree" class="btn btn-secondary"><i class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
					            	<button type="button" id="btnResetTree" class="btn btn-secondary"><i class="fa fa-trash-o"></i> <spring:message code="text.reset" text="초기화"/> </button>
					            </span>
							</div>
							<div id="treeDeptList" style="height:480px;overflow:auto;"></div>
							<div>
								<input class="form-check-input" type="checkbox" id="chkDeptUseYn" name="chkDeptUseYn" onclick="fnReselTree()"><span> <spring:message code="text.include.dept.use" text="사용안하는부서포함"/></span>
							</div>
						</div>
					</div>
				
				</div>
				
				<!-- 부서 테이블 -->
				<div class="col-md-8">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.dept.info" text="부서정보" /></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div id="table1" style="padding:0; height:540px;">
				                <table id="simple-table" class="table">
				                  <colgroup>
				                    <col width="35%" />
				                    <col width="65%" />
				                  </colgroup>
				                  <tbody>
				                      <td class="table_padding font-bold confer_title_color"><label for="companyName"><spring:message code="text.emp.company" text="회사"/></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="companyName" name="companyName" readonly value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="parentDeptName"><spring:message code="text.dept.parent" text="상위부서" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="parentDeptName" name="parentDeptName" readonly value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptCode"><spring:message code="text.dept.code" text="부서코드" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptCode" name="deptCode" readonly value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptName"><spring:message code="text.dept.name" text="부서명"/></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptName" name="deptName" readonly value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptEngName"><spring:message code="text.dept.eng" text="부서영문명" /></label></td>
				                      <td><input type="text" class="form-control" id="deptEngName" name="deptEngName" readonly value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptOrd"><spring:message code="text.dept.ord" text="순서" /></label></td>
				                      <td><input type="text" class="form-control" id="deptOrd" name="deptOrd" style="width:85px;" readonly maxlength="3"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="manualMngYn"><spring:message code="text.manual.manage" text="수동관리" /></label></td>
				                      <td>
				                      	<select class="form-control" name="manualMngYn" id="manualMngYn" readonly onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
				                          <option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
				                          <option value="Y" ><spring:message code="text.use.yes" text="사용" /></option>
				                          <option value="N" ><spring:message code="text.use.not" text="사용안함" /></option>
				                        </select>
				                      </td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="useYn"><spring:message code="text.use" text="사용여부" /></label></td>
				                      <td>
				                        <select class="form-control is-valid-jihun" name="useYn" id="useYn" readonly onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
				                          <option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
				                          <option value="Y" ><spring:message code="text.use.yes" text="사용" /></option>
				                          <option value="N" ><spring:message code="text.use.not" text="사용안함" /></option>
				                        </select>
				                      </td>
				                    </tr>
				                  </tbody>
				                </table>
				            </div>
				            
				            <div id="table2" style="padding:0;height:540px;display:none;">
				                <table id="simple-table" class="table">
				                  <colgroup>
				                    <col width="35%" />
				                    <col width="65%" />
				                  </colgroup>
				                  <tbody>
				                      <td class="table_padding font-bold confer_title_color"><label for="companyName2"><spring:message code="text.emp.company" text="회사"/></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="companyName2" name="companyName2" readonly required value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="parentDeptName2"><spring:message code="text.dept.parent" text="상위부서" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="parentDeptName2" name="parentDeptName2" readonly required value='' /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptCode2"><spring:message code="text.dept.code" text="부서코드" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptCode2" name="deptCode2" value='' required onblur="javascript:gfnRegExpChk(this);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptName2"><spring:message code="text.dept.name" text="부서명"/></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptName2" name="deptName2" value='' required onblur="javascript:gfnRegExpChk(this);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptEngName2"><spring:message code="text.dept.eng" text="부서영문명" /></label></td>
				                      <td><input type="text" class="form-control" id="deptEngName2" name="deptEngName2" value='' onblur="javascript:gfnRegExpChk(this);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptOrd2"><spring:message code="text.dept.ord" text="순서" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptOrd2" name="deptOrd2" style="width:85px;" required maxlength="3" onblur="javascript:gfnRegExpChk(this,2);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="manualMngYn2"><spring:message code="text.manual.manage" text="수동관리" /></label></td>
				                      <td>
				                      	<select class="form-control" name="manualMngYn2" id="manualMngYn2" >
				                          <option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
				                          <option value="Y" ><spring:message code="text.use.yes" text="사용" /></option>
				                          <option value="N" ><spring:message code="text.use.not" text="사용안함" /></option>
				                        </select>
				                      </td>
				                    </tr>
				                  </tbody>
				                </table>
				            </div>
				            
						</div>
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
<!-- ./wrapper -->

<form id="frmHiddenParam">
  <input type="hidden" id="pDomainId" name="pDomainId" value=""/>
  <input type="hidden" id="pDeptCd" name="pDeptCd" value=""/>
  <input type="hidden" id="pDeptNm" name="pDeptNm" value=""/>
  <input type="hidden" id="pCompanyCd" name="pCompanyCd" value=""/>
  <input type="hidden" id="pParentDeptCd" name="pParentDeptCd" value=""/>
</form>

<form id="frmExcelDown" method="GET">
	<input type="hidden" id="exDeptCd" name="exDeptCd">
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
	gfnSelectMenu("mgDeptMasterList", "");
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
	fnInitTree();
}

//이벤트
function fnSetEvent() {
	// 부서 검색조건 조회
	$("#txtDeptSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			let searchString = $("#txtDeptSearchTxt").val();
			$('#treeDeptList').jstree(true).search(searchString);
		}
	});
	$("#btnSearchTree").off("click").on("click", function (e) {
		e.preventDefault();
		let searchString = $("#txtDeptSearchTxt").val();
		$('#treeDeptList').jstree(true).search(searchString);
	});
	
	// 추가 버튼
	$("#btnAdd").off("click").on("click", function (e) {
		e.preventDefault();
		fnChangeDeptAddTable();
	});
	
	// 목록 버튼
	$("#btnClose").off("click").on("click", function (e) {
		e.preventDefault();
		fnChangeDeptInfoTable();
	});
	
	// 등록 버튼
	$("#btnSave").off("click").on("click", function (e) {
		e.preventDefault();
		if (gfnCheckRequired($("#table2"))) {
			let size = "DEFAULT";
			let gMsgCfmTitle = '<spring:message code="text.registration" text="등록" />';
			let message = '<spring:message code="text.registration.chk" text="등록 하시겠습니까?" />';
			let callback = fnInsertDeptInfo;
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
	
	// 수정 버튼
	$("#btnModify").off("click").on("click", function (e) {
		e.preventDefault();
		fnMoveDeptModify();
	});
	
	// 조직도 트리 초기화
	$("#btnResetTree").off("click").on("click", function (e) {
		e.preventDefault();
		fnResetData();
	});
	
	// 엑셀Form다운로드 버튼 클릭 
	$("#btnDownExcelForm").off("click").on("click", function (e) {
		e.preventDefault();
		fnDownExcelForm();
	});

	// 파일 선택(업로드) 이벤트 
	$("#btnUploadExcel").on("change", function() {
		fnUploadExcelRegChk();
	});	
}


// 부서 테이블 데이터 초기화
function fnResetData() {
	$("#pDeptCd").val("");
	$("#companyName").val("");
	$("#parentDeptName").val("");
	$("#deptCode").val("");
	$("#deptName").val("");
	$("#deptEngName").val("");
	$("#deptOrd").val("");
	$("#manualMngYn").val("");
	$("#useYn").val("");
	
	$("#pDeptNm").val("");
	$("#pDomainId").val("");
	$("#pCompanyCd").val("");
	$("#pParentDeptCd").val("");
	$("#companyName2").val("");
	$("#parentDeptName2").val("");
	$("#deptCode2").val("");
	$("#deptName2").val("");
	$("#deptEngName2").val("");
	$("#deptOrd2").val("");
	$("#manualMngYn2").val("");
	
	$("#treeDeptList").jstree("deselect_all");
	$("#treeDeptList").jstree(true).clear_search();
}

// 부서추가 테이블로 변경
function fnChangeDeptAddTable() {
	let deptCd = $("#pDeptCd").val();
	if(deptCd == "") {
		let msgTitle = "";
		let msg = '<spring:message code="text.parent.dept.chk" text="상위부서가 선택되지 않았습니다." />';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	$('#contentTitle').text('<spring:message code="text.dept.add" text="부서등록" />');
	$('#table1').css("display","none");
	$('#table2').css("display","");
	$('#btnAdmins').css("display","none");
	$('#btnAdds').css("display","");
	$("#companyName2").val($("#selScCompanyCd option:checked").text());
	$("#parentDeptName2").val($("#pDeptNm").val());
}

//부서관리 테이블로 변경
function fnChangeDeptInfoTable() {
	$('#table2').css("display","none");
	$('#table1').css("display","");
	$('#btnAdds').css("display","none");
	$('#btnAdmins').css("display","");
	$('#contentTitle').text('<spring:message code="text.dept.mng" text="부서관리" />');
}

//부서수정 화면으로 이동
function fnMoveDeptModify() {
	let deptCd = $("#pDeptCd").val();
	if(deptCd == "") {
		let msgTitle = "";
		let msg = '<spring:message code="text.parent.dept.chk" text="상위부서가 선택되지 않았습니다." />';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	let apiUrl = "/admin/dept/deptModifyView.do";
	let params = new Object();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#selScCompanyCd").val();
	params.deptCode = $("#deptCode").val();
	fnPostMove(apiUrl, params);
}

//트리 생성
function fnInitTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	let deptList = new Array();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
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
	        $('#treeDeptList').jstree({
	            'core': {
	                'data': deptList,
	                'check_callback': (operation, node, node_parent, node_position, more) => {
	                	// Drag & Drop
	                	if (operation === "move_node" && more.ref === undefined) {
	                		if(node_parent.id == "#") {
	                			let msgTitle = "";
	                			let msg = '<spring:message code="text.move.top.dept" text="최상위부서로 이동할 수 없습니다."/>';
	                			gfnFailAlert(msgTitle, msg, 2000);
	                			fnReselTree();
	                			return;
	                		}
	                		fnMoveDept(node.original.id, node.original.parent, node_parent.id);      	                  
		                	return true;
	                	}
	                	return true;
	                }
	            },
	            'types': {
	                'default': {
	                	'icon': '/common/common/images/dept.png'
	                }
	            },
	            'plugins' : ["search", "types", "dnd"]
	            
	        })
	        // 노드 선택시 발생하는 이벤트
	        .bind('select_node.jstree', function(event, data){
	        	$("#pDeptCd").val(data.instance.get_node(data.selected).id);
	        	$("#pDeptNm").val(data.instance.get_node(data.selected).text);
	        	fnSelDept();
	        })
		},
		error:function (data) {
		}
	});
}

//트리 재조회
function fnReselTree() {
	let apiUrl = "/rest/user/emp/selectDeptList.do";
	let params = new Object();
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			gDomainId = "${item.domainId}";
		}
	</c:forEach>
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.incDeptUseYn = $('input:checkbox[id="chkDeptUseYn"]').is(":checked");
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
	  		$('#treeDeptList').jstree(true).settings.core.data = deptList;
	  		$('#treeDeptList').jstree(true).refresh();
	  		fnResetData();
		}
	});
}

// 부서 정보 조회
function fnSelDept() {
	let apiUrl = "/rest/admin/dept/selectDeptInfo.do";
	let params = new Object();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.deptCd = $("#pDeptCd").val();
	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
			$("#companyName").val(data.dataOne.companyNm);
			$("#parentDeptName").val(data.dataOne.parentDeptNm);
			$("#deptCode").val(data.dataOne.deptCd);
	  		$("#deptName").val(data.dataOne.deptNm);
	  		$("#deptEngName").val(data.dataOne.deptEngNm);
	  		$("#deptOrd").val(data.dataOne.deptOrd);
	  		$("#manualMngYn").val(data.dataOne.manualMngYn).prop("selected", "selected");
	  		$("#useYn").val(data.dataOne.deptUseYn).prop("selected", "selected");
	  		
	  		$("#pDomainId").val(data.dataOne.domainId);
	  		$("#pCompanyCd").val(data.dataOne.companyCd);
	  		$("#pParentDeptCd").val(data.dataOne.deptCd);
	  		$("#companyName2").val(data.dataOne.companyNm);
			$("#parentDeptName2").val(data.dataOne.deptNm);
	  		let flag = false;
	  		if(data.dataOne.parentDeptCd == "0") flag = true;
	  		disabledForm(flag);
		}
	});
}

//부서 정보 등록
function fnInsertDeptInfo() {
	let apiUrl = "/rest/admin/dept/insertDeptInfo.do";
	let params = new Object();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#pCompanyCd").val();
	params.deptCd = $("#deptCode2").val();
	params.deptNm = $("#deptName2").val();
	params.deptEngNm = $("#deptEngName2").val();
	params.parentDeptCd = $("#pParentDeptCd").val();
	params.deptOrd = $("#deptOrd2").val();
	//params.manualMngYn = $("#manualMngYn2").val();
	if($("#manualMngYn2").val() == '') {
		params.manualMngYn = 'N';
	}
	else {
		params.manualMngYn = $("#manualMngYn2").val();
	}
	
	let msgTitle = "";
	let msg = "";
	
	$.ajax({
		type:'post',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
			if(data.resultCode == "SUCCESS") {
				msg = '<spring:message code="text.save.success" text="저장 성공하였습니다."/>';
				gfnSuccessAlert(msgTitle, msg, 2000);
				setTimeout(function() {
					location.href = "/admin/dept/deptMasterListView.do";
				}, 2000);
			}
			else if (data.resultCode = "DUP_FAIL") {
				msg = '<spring:message code="text.error.dept.reg" text="이미 등록된 부서입니다."/>';
				gfnFailAlert(msgTitle, msg, 2000);		
			}
			else {
				msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>' + data.resultMsg;
				gfnFailAlert(msgTitle, msg, 2000);
			}
		},
		error: function(request, status, error) {
			msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);
		}
	});
}

// 부서 위치 이동
function fnMoveDept(deptCd, parentDeptOld, parentDeptNew) {
	let apiUrl = "/rest/admin/dept/updateDeptParent.do";
	let params = new Object();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.deptCd = deptCd;
	params.parentDeptOld = parentDeptOld;
	params.parentDeptNew = parentDeptNew;
	$.ajax({
		type:'post',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
			fnReselTree();
		}
	});
}

// 최상위부서(법인)은 수정버튼 비활성화
function disabledForm(flag) {
	if(flag) {
		$("#btnModify").css("display","none");
	}
	else {
		$("#btnModify").css("display","");
	}
}

//엑셀 업로드양식 다운로드
function fnDownExcelForm() {
	let frm = $("#frmExcelDown");
	frm.attr("action", "/rest/admin/dept/deptExcelFormDown.do");
	frm.submit();
}

//엑셀업로드 체크
function fnUploadExcelRegChk() {
	let msg = "";
	let input = event.target;
	let reader = new FileReader();
	reader.onload = function() {
		let fdata = reader.result;
		let read_buffer = XLSX.read(fdata, {type : 'binary'});
		read_buffer.SheetNames.forEach(function(sheetName) {
			let rowdata =XLSX.utils.sheet_to_json(read_buffer.Sheets[sheetName]); // Excel 입력 데이터
			// 행 수 만큼 반복
			for(let i=0;i<rowdata.length;i++) {
				// 필수값 체크
				if(rowdata[i].도메인 == null)
					msg += '도메인, ';
				if(rowdata[i].법인코드 == null)
					msg += '법인코드, ';
				if(rowdata[i].부서코드 == null)
					msg += '부서코드, ';
				if(rowdata[i].부서명 == null)
					msg += '부서명, ';
				if(rowdata[i].상위부서코드 == null)
					msg += '상위부서코드, ';
				if(msg != '') {
					msg = msg.substr(0, msg.length-2);
					msg += ' ' + '<spring:message code="text.empty.data" text=" 값이 존재하지 않습니다."/>';
					gfnFailAlert("", msg, gDelay2);
					return;
				}
				// 정규식 체크
				let keys = Object.keys(rowdata[i]);
				let re= /[^ㄱ-ㅎ가-힣a-zA-Z0-9\-\_\.\@\s]/gi;
				let reNum = /[^0-9]/gi; 
				for(let j=0;j<keys.length;j++) {
					let data = rowdata[i][keys[j]];
					if(keys[j] == '부서순서') {
						if(reNum.test(data)) {
							msg = keys[j] + '<spring:message code="text.only.number" text="는 숫자만 입력 가능합니다."/>';
							gfnFailAlert("", msg, gDelay2);
							return;
						}
						if(data.toString().length > 3) {
							msg = keys[j] + '<spring:message code="text.input.length.over" text="이(가) 입력 가능한 길이를 초과하였습니다."/>';
							gfnFailAlert("", msg, gDelay2);
							return;
						}
					}
					else if(keys[j] == '수동관리여부(Y/N)') {
						if((data != 'Y' && data != 'N') || data.toString().length > 1) {
							msg = keys[j] + '<spring:message code="text.input.data.error" text="이(가) 잘못 입력되었습니다."/>';
							gfnFailAlert("", msg, gDelay2);
							return false;
						}
					}
					else {
						if(re.test(data)) {
							msg = keys[j] + '<spring:message code="text.not.accept.text" text="에 허용되지않는 문자가 포함되어있습니다."/>';
							gfnFailAlert("", msg, gDelay2);
							return;
						}
					}
				}
			}
			//console.log(JSON.stringify(rowdata));
			fnUploadExcelChkMsg();
		})
	};
	reader.readAsBinaryString(input.files[0]);
}	

//엑셀 업로드 확인 Message
function fnUploadExcelChkMsg() {
	let size = "DEFAULT";
	let gMsgCfmTitle = '<spring:message code="text.excel.upload" text="엑셀업로드"/>';
	let message = '<spring:message code="text.excel.upload.msg" text="이미 존재하는 데이터는 Update 됩니다. 진행 하시겠습니까?"/>';
	let callback = fnUploadExcel;
	gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
}

//파일 업로드 처리
function fnUploadExcel() {
	gfnShowLoadingBar();
    let apiUrl = "/rest/admin/dept/deptListExcelUp.do";
	$.ajax({
		url : apiUrl,
		type : "POST",
		data : new FormData($("#frmAttachedFiles")[0]),
		dataType: "json",
		processData: false,
		contentType: false,
		success: function(result) {
			if (result.resultCode == "SUCCESS") {
				let msg = '<spring:message code="text.excel.upload.success" text="업로드 성공하였습니다."/>';
				gfnSuccessAlert("", msg, gDelay3);
				setTimeout(function() {
					location.href = "/admin/dept/deptMasterListView.do";
				}, 2000);
			}
			else {
				let msg = '<spring:message code="text.excel.upload.fail" text="업로드 실패하였습니다."/>' + result.resultMsg;
				gfnFailAlert("", msg, gDelay2);
			}
		}
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}
</script>

</body>
</html>