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
					<h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.dept.modify" text="부서수정" /></h3>
				</div>
				<div class="col-sm-6" style="text-align:right;">
		            <span class="pull-right">
		            	<button class="btn btn-primary float-right" id="btnSave"><i class="ace-icon fa fa-plus"></i><spring:message code="text.save" text="저장" /></button>
		            	<button class="btn btn-primary float-right" id="btnClose"><i class="ace-icon fa fa-floppy-o"></i><spring:message code="text.list" text="목록" /></button>
		            </span>				
				</div>
			</div>
		</section>
		
		<!-- Main content -->
		<section class="content container-fluid">
			<div class="row">
				
				<!-- 부서 테이블 -->
				<div class="col-md-12">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.dept.info" text="부서정보" /></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<div id="table1" style="padding:0;">
				                <table id="simple-table" class="table">
				                  <colgroup>
				                    <col width="35%" />
				                    <col width="65%" />
				                  </colgroup>
				                  <tbody>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="companyName"><spring:message code="text.company" text="법인"/></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="companyName" name="companyName" value='${deptList.companyNm}' readonly required /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="parentDeptNm"><spring:message code="text.dept.parent" text="상위부서" /></label></td>
				                      <td>
				                      	<div class="input-group">
											<input type="text" class="form-control is-valid-jihun" id="parentDeptNm" name="parentDeptNm" value='${deptList.parentDeptNm}' readonly required />
											<span class="input-group-btn">
												<button type="button" id="btnParentDeptSearch" class="btn btn-primary"><spring:message code="text.find" text="찾기" /></button>
											</span>
						                </div>
				                      </td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptCode"><spring:message code="text.dept.code" text="부서코드" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptCode" name="deptCode" value='${deptList.deptCd}' readonly required /></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptName"><spring:message code="text.dept.name" text="부서명"/></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptName" name="deptName" value='${deptList.deptNm}' required onblur="javascript:gfnRegExpChk(this);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptEngName"><spring:message code="text.dept.eng" text="부서영문명" /></label></td>
				                      <td><input type="text" class="form-control" id="deptEngName" name="deptEngName" value='${deptList.deptEngNm}' onblur="javascript:gfnRegExpChk(this);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="deptOrd"><spring:message code="text.dept.ord" text="순서" /></label></td>
				                      <td><input type="text" class="form-control is-valid-jihun" id="deptOrd" name="deptOrd" value='${deptList.deptOrd}' required style="width:85px;" maxlength="3" onblur="javascript:gfnRegExpChk(this,2);"/></td>
				                    </tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="manualMngYn"><spring:message code="text.manual.manage" text="수동관리" /></label></td>
				                      <td>
				                      	<select class="form-control" name="manualMngYn" id="manualMngYn">
				                          	<option value="" selected>-<spring:message code="text.select" text="선택"/>-</option>
				                          	<option value="Y" ><spring:message code="text.use.yes" text="사용" /></option>
				                          	<option value="N" ><spring:message code="text.use.not" text="사용안함" /></option>
				                        </select>
				                      </td>
				                    </tr>
				                    <tr>
				                    <tr>
				                      <td class="table_padding font-bold confer_title_color"><label for="useYn"><spring:message code="text.use" text="사용여부" /></label></td>
				                      <td>
				                        <select class="form-control is-valid-jihun" name="useYn" id="useYn" required>
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
				<!-- /.card -->
			</div>
		</section>
		<!-- /.content -->		
	</div>
<!-- Main Footer -->
<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>
</div>
<!-- ./wrapper -->

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

<form id="frmHiddenParam">
  <input type="hidden" id="pCompanyCd" name="pCompanyCd" value=""/>
  <input type="hidden" id="pParentDeptCd" name="pParentDeptCd" value=""/>
  <input type="hidden" id="pDomainId" name="pDomainId" value=""/>
  <input type="hidden" id="pMdlDeptCd" name="pMdlDeptCd" value=""/> <!-- 상위부서선택시 자기자신 제외하기 위한 변수 -->
</form>
<!-- script -->
<%@ include file="/WEB-INF/views/admin/include/script.jsp" %>

<script type ="text/javascript">
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
	$("#pDomainId").val('${deptList.domainId}');
	$("#pCompanyCd").val('${deptList.companyCd}');
	$("#pParentDeptCd").val('${deptList.parentDeptCd}');
	$("#manualMngYn").val('${deptList.manualMngYn}').prop("selected", "selected");
	$("#useYn").val('${deptList.deptUseYn}').prop("selected", "selected");
	$("#pMdlDeptCd").val('${deptList.deptCd}').prop("selected", "selected");
}

//이벤트
function fnSetEvent() {
	$("#btnSave").off("click").on("click", function (e) {
		e.preventDefault();
		if (gfnCheckRequired($("#table1"))) {
			let size = "DEFAULT";
			let gMsgCfmTitle = '<spring:message code="text.modify" text="수정" />';
			let message = '<spring:message code="text.modify.chk" text="수정 하시겠습니까?" />';
			let callback = fnUpdateDeptInfo;
			gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
		}
	});
	
	$("#btnClose").off("click").on("click", function (e) {
		e.preventDefault();
		location.href = "/admin/dept/deptMasterListView.do"
	});
	
	$("#btnParentDeptSearch").off("click").on("click", function (e) {
		e.preventDefault();
		fnShowMdlDeptPicker();
	});
}

//find 버튼 (법인 찾기 팝업)
function fnShowMdlDeptPicker() {
	$("#mdlDeptPicker").modal({
		remote : "/admin/dept/deptTreeViewModal.do"
	});
}

//부서 팝업 정보 Set
function fnSetDeptInfo(selDeptInfo) {
	$("#pParentDeptCd").val(selDeptInfo.deptCd);
	$("#parentDeptNm").val(selDeptInfo.deptNm);
}

//부서 정보 Update
function fnUpdateDeptInfo() {
	let apiUrl = "/rest/admin/dept/updateDeptInfo.do";
	let params = new Object();
	params.domainId = $("#pDomainId").val();
	params.companyCd = $("#pCompanyCd").val();
	params.parentDeptCd = $("#pParentDeptCd").val();
	params.deptCd = $("#deptCode").val();
	params.deptNm = $("#deptName").val();
	params.deptEngNm = $("#deptEngName").val();
	params.deptOrd = $("#deptOrd").val();
	params.manualMngYn = $("#manualMngYn").val();
	params.useYn = $("#useYn").val();
	gfnShowLoadingBar();
	$.ajax({
		type:'post',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(data) {
			if(data.resultCode == "SUCCESS") {
				let msgTitle = "";
				let msg = '<spring:message code="text.save.success" text="저장 성공하였습니다."/>';
				gfnSuccessAlert(msgTitle, msg, 2000);
			}
			else {
				let msgTitle = "";
				let msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
				gfnFailAlert(msgTitle, msg, 2000);
			}
		},
		error: function(request, status, error) {
			let msgTitle = "";
			let msg = '<spring:message code="text.save.fail" text="저장 실패하였습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);
		}
	}).always(function(msg) {
		gfnHideLoadingBar();
	});
}

</script>

</body>
</html>