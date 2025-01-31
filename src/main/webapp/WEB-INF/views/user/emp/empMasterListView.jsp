<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>

<!-- meta -->
<%@ include file="/WEB-INF/views/user/include/meta.jsp" %>

<body class="hold-transition skin-blue sidebar-mini sidebar-collapse">
<div class="wrapper">

<!-- Header -->
<%@ include file="/WEB-INF/views/user/include/header.jsp" %>

<!-- main side menu -->
<%@ include file="/WEB-INF/views/user/include/sidebar.jsp" %>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
	
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="row">
				<div class="col-sm-3">
					<h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.emp.search" text="사용자조회" /></h3>
				</div>
				<div class="col-sm-9" style="text-align:right;">
		            <span class="pull-right">
		              <button type="button" id="btnSearchList" class="btn btn-primary float-right"><i class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
		              <button type="button" id="btnAddToEmp" class="btn btn-success float-right" onclick="fnAddToMail('to')" ><i class="ace-icon fa fa-plus"></i> <spring:message code="text.add.recipient" text="수신자추가"/></button>
		              <button type="button" id="btnAddToTeamEmp" class="btn btn-success float-right" onclick="fnAddToTeamMail('to')" ><i class="ace-icon fa fa-plus"></i> <spring:message code="text.add.team.recipient" text="팀수신자추가"/></button>
		             
		              <button type="button" id="btnAddCcEmp" class="btn btn-success float-right" onclick="fnAddToMail('cc')" ><i class="ace-icon fa fa-plus"></i> <spring:message code="text.add.reference" text="참조추가"/></button>
		              <button type="button" id="btnAddCcTeamEmp" class="btn btn-success float-right" onclick="fnAddToTeamMail('cc')" ><i class="ace-icon fa fa-plus"></i> <spring:message code="text.add.team.reference" text="팀참조추가"/></button>
		             
		              <button type="button" id="btnAddBccEmp" class="btn btn-success float-right" onclick="fnAddToMail('bcc')" ><i class="ace-icon fa fa-plus"></i> <spring:message code="text.add.hidden.reference" text="숨은참조추가"/></button>
		              <button type="button" id="btnAddBccTeamEmp" class="btn btn-success float-right" onclick="fnAddToTeamMail('bcc')" ><i class="ace-icon fa fa-plus"></i> <spring:message code="text.add.team.hidden.reference" text="팀숨은참조추가"/></button>
		             
		              <button type="button" id="btnClose" class="btn btn-primary float-right"><i class="ace-icon fa fa-remove"></i> <spring:message code="text.close" text="닫기"/></button>
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
 							<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.search.cond" text="조회조건" /></h3>
						</div>

						
						<!-- form start -->
						<form class="form-horizontal">
							<div class="box-body">
								<div class="form-group">
									<label for="selScCompanyCd" class="col-sm-1 control-label"><spring:message code="text.company" text="법인"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScCompanyCd" name="selScCompanyCd" onchange="fnChangeCompany()">
											<c:forEach items="${companyList}" var="option">
												<option value="${option.companyCd}">${option.companyNm}</option>  
											</c:forEach>
										</select>
									</div>
									
									<label for="selScSearchCond" class="col-sm-1 control-label"><spring:message code="text.find.cond" text="검색조건"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScSearchCond" name="selScSearchCond">
											<!--  <option value="">-<spring:message code="text.select" text="선택"/>-</option> -->
											<option value="empNm" selected> <spring:message code="text.emp.name" text="성명"/></option>
											<option value="email"> <spring:message code="text.emp.email" text="이메일"/></option>
											<option value="empNo"> <spring:message code="text.emp.no" text="사번"/></option>
											<option value="jobTelNo"> <spring:message code="text.emp.job.tel" text="사내전화"/></option>
											<option value="mobileTelNo"> <spring:message code="text.emp.mobile.tel" text="휴대폰"/></option>
										</select>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="txtScSearchTxt" name="txtScSearchTxt" onblur="javascript:fnRegExpChk(this);" placeholder="<spring:message code="text.search.word" text="검색어"/>">
									</div>									
									
									<label for="selScCompanyStatusCd" class="col-sm-1 control-label"><spring:message code="text.emp.status" text="재직상태"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScCompanyStatusCd" name="selScCompanyStatusCd">
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
				<div class="col-md-3 col-sm-5">

					<div class="box box-primary" style="height:500px;">
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
					            	<button type="button" id="btnSearchTree" class="btn btn-secondary"><spring:message code="text.search" text="조회"/></button>
					            	<button type="button" id="btnResetTree" class="btn btn-secondary"><spring:message code="text.reset" text="초기화"/> </button>
					            </span>	
							</div>
							<div id="treeDeptList" style="height:380px;overflow:auto;"></div>
							<input type="checkbox" id="chkIncludeSub" name="chkIncludeSub"><span> <spring:message code="text.dept.include" text="하위부서포함조회"/></span>	
						</div>
					</div>
				
				</div>
				
				<!-- general form elements -->
				<div class="col-md-9 col-sm-7">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.emp.list" text="사용자목록"/></h3>
						</div>
						<!-- /.box-header -->
						<!-- /.box-header -->
						
						<!-- form start -->
						<form class="form-horizontal">
							<div id="dvGrdBox" class="box-body grid-box">
								<table id="grdEmpList"></table>
								<div id="grdEmpListPager"></div>
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
<%@ include file="/WEB-INF/views/user/include/footer.jsp" %>

</div>
<!-- ./wrapper -->
<form id="frmHiddenParam">
  <input type="hidden" id="pDeptCd" name="pDeptCd" value="" /> <!-- 트리에서 선택한 부서코드 저장 -->
</form>
<!-- script -->
<%@ include file="/WEB-INF/views/user/include/script.jsp" %>

<script type ="text/javascript">
let gDomainId = "";
let gEmailCnt = 0;

$(function() {
	fnSetMenuSelection();
	fnSetComponent();
	fnSetEvent();
}) // $(function()

function fnSetMenuSelection() {
	gfnSelectMenu("mgEmpMasterList", "");
}

//화면 컴포넌트 초기화 
function fnSetComponent() {
	
	//그리드 RESIZE
	$('.grid-box').on('resize', $.noop);
    $(window).on('resize.jqGrid', function () {
    	$('#grdEmpList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
    });
    
    $("#selScCompanyCd").val('${sessionUser.companyCd}').attr('selected', true);
 	//선택한 법인의 domainId 저장해둠
	<c:forEach items="${companyList}" var="item">
		if("${item.companyCd}" == $("#selScCompanyCd").val()) {
			gDomainId = "${item.domainId}";
		}
	</c:forEach>
	
	$("#emailCnt").text(gEmailCnt);

	fnSetEmpStatus();
	fnInitGrid();
	fnInitTree();
	fnSetGridSize();
}


//이벤트
function fnSetEvent() {
	$("#dvGrdBox").on("resize", function(){
		setTimeout(fnSetGridSize, 200);
	})	
	
	//검색조건 조회
	$("#txtScSearchTxt").keydown(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
			fnRegExpChk(this);
			fnResetDeptSearch();
			fnReselGrid();
		}
	});
	$("#btnSearchList").off("click").on("click", function (e) {
		e.preventDefault();
		fnResetDeptSearch();
		fnReselGrid();
	});
	
	//부서 검색조건 조회
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
	
	//조직도 트리 초기화
	$("#btnResetTree").off("click").on("click", function (e) {
		e.preventDefault();
		fnResetDeptSearch();
		fnReselGrid();
	});
	
	$("#btnClose").off("click").on("click", function (e) {
		e.preventDefault();
		window.close();
	});
}

//법인별 재직상태 Selectbox에 Set
function fnSetEmpStatus() {
	$('#selScCompanyStatusCd').children('option').remove(); // 기존 option 제거
	let apiUrl = "/rest/user/emp/selectEmpStatusList.do";
	let params = new Object();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
 	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(result) {
			let data = result.data;
			let option = '<option value="" selected>-<spring:message code="text.all" text="전체"/>-</option>';
			$("#selScCompanyStatusCd").append(option);
			//조회한 데이터 option에 추가
			for(let i=0;i<data.length;i++) {
				option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
				$("#selScCompanyStatusCd").append(option);
			}
		}
	});
}

//그리드 사이즈 초기화 
function fnSetGridSize() {
	$('#grdEmpList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
	$("#gbox_grdEmpList").css("width", $('.grid-box').width());
}

//검색 조건 및 그리드 초기화
function fnResetSearchCond() {
	fnResetDeptSearch();
	fnResetSearchCond();
	$("#grdEmpList").jqGrid('clearGridData');
}

//부서 검색 초기화
function fnResetDeptSearch() {
	$("#pDeptCd").val("");
	$("#txtDeptSearchTxt").val("");
	$("#treeDeptList").jstree("deselect_all");
	$("#treeDeptList").jstree(true).clear_search()
}

//검색조건 초기화
function fnResetSearchCond() {
	$("#selScSearchCond").val("empNm").attr('selected', true);
	$("#txtScSearchTxt").val("");
	$("#selScCompanyStatusCd").val("").attr('selected', true);
}

//그리드 재조회
function fnReselGrid() {
	let params = new Object();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.searchCond = $("#selScSearchCond").val();
	params.searchTxt = $("#txtScSearchTxt").val();
	params.companyStatusCd = $("#selScCompanyStatusCd").val();
	params.deptCd = $("#pDeptCd").val();
	if($("#chkIncludeSub").is(":checked"))
		params.includeSub = "Y";
	else
		params.includeSub = "N";
	$("#grdEmpList").jqGrid('clearGridData');
	$("#grdEmpList").jqGrid('setGridParam',{
        postData : params,
        success: function(data) {
		  	if(data.resultCode == "SUCCESS") {
				let msgTitle = "";
				let msg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
				gfnSuccessAlert(msgTitle, msg, 2000);
			}
			else {
				let msgTitle = "";
				let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>';
				gfnFailAlert(msgTitle, msg, 2000);
			}
    	}
    }).trigger('reloadGrid');
}

//그리드 생성
function fnInitGrid() {
	let apiUrl = "/rest/user/emp/selectEmpList.do";
	let params = new Object();
	
	$("#grdEmpList").jqGrid({
        url: apiUrl,
        postData: params,
        datatype:"json",
        mtype : "POST",
        height:  350,
        jsonReader: {
	        page: 'page',
	        total: 'total',
	        records: 3,
	        root: 'data',
	        repeatitems: false,
	        id: 'rowId'
	    },
        colNames:[
        	'<spring:message code="text.company" text="법인"/>'
	    	, '<spring:message code="text.dept" text="부서"/>'
	    	, '<spring:message code="text.emp.name" text="성명"/>'
	    	, '<spring:message code="text.emp.add.yn" text="겸직여부"/>'
	    	, '<spring:message code="text.emp.position" text="직위"/>'
	    	, '<spring:message code="text.emp.no" text="사번"/>'
	    	, '<spring:message code="text.emp.email" text="이메일"/>'
	    	, '<spring:message code="text.emp.job.tel" text="사내전화"/>'
	    	, '<spring:message code="text.emp.mobile.tel" text="휴대폰"/>'
	    	, '<spring:message code="text.emp.status" text="재직상태"/>'
	    ],
        colModel:[
        	{ name: 'companyNm',		index: 'companyNm',			width: '50%', 	align: 'left', 		sortable: false, hidden : true}
	        , { name: 'deptNm',			index: 'deptNm',			width: '55%', 	align: 'left', 		sortable: false}
	        , { name: 'empNm',			index: 'empNm',				width: '50%', 	align: 'left', 		sortable: false}
	        , { name: 'addYn', 			index: 'addYn', 			width: '25%', 	align: 'center', 	sortable: false}
	        , { name: 'posNm',			index: 'posNm',				width: '30%', 	align: 'center', 	sortable: false}
	        , { name: 'empNo', 			index: 'empNo', 			width: '40%', 	align: 'center', 	sortable: false}
	        , { name: 'email',			index: 'email',				width: '60%', 	align: 'left', 		sortable: false}
	        , { name: 'jobTelNo',		index: 'jobTelNo',			width: '50%', 	align: 'left', 		sortable: false}
	        , { name: 'mobileTelNo', 	index: 'mobileTelNo', 		width: '50%', 	align: 'left', 		sortable: false}
	        , { name: 'empStatusNm',	index: 'empStatusNm',		width: '30%', 	align: 'center', 	sortable: false}
        ],
        rowNum:10,
        rowList: [5, 10, 100],
        rownumbers : true,
        gridview: true,
        viewrecords:true,
        emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
        pager: '#grdEmpListPager',
        multiselect : true,
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

	    },
	    loadError: function(jqXHR, textStatus, errorThrown) {
      		let title = "";
      		let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>' + jqXHR.status.toString();
      		gfnFailAlert(msgTitle, msg, 2000);
	  	},
	    resizeStop: gfnResizeStop
    });
}

//검색조건 텍스트 유효성체크
function fnRegExpChk(obj) {
	let val = obj.value;
	let re=/[^ㄱ-ㅎ가-힣a-zA-Z0-9\-\@]/gi; // 한글, 영문자, 숫자, @, -만 허용
	obj.value=val.replace(re,"");
}
	
//법인 셀렉트박스 변경
function fnChangeCompany() {
	fnSetEmpStatus();
	fnReselTree();
	fnResetSearchCond();
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
	let deptList = new Array();
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
	  		$('#treeDeptList').jstree(true).settings.core.data = deptList;
	  		$('#treeDeptList').jstree(true).refresh();
		}
	});
}

// 트리 생성
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
	        	else deptList[idx] = {id:item.deptCd, parent:item.parentDeptCd, text:item.deptNm};
	        });
	        $('#treeDeptList').jstree({
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
	        	fnResetSearchCond();
	            $("#pDeptCd").val(data.instance.get_node(data.selected).id);
	        	fnReselGrid();
	        })
		},
		error:function (data) {
		}
	});
}
let teamUserList = [];  // 이메일을 저장할 배열
let teamPrevFlag = null;  // 이전 flag 값을 저장
// 팀 메일 추가
function fnAddToTeamMail(flag) {
	let deptCd = $("#pDeptCd").val(); //부서코드
	if(deptCd == "") {
		let msgTitle = "";
		let msg = '부서가 선택되지 않았습니다.';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	
	let apiUrl = "/rest/user/emp/selectDeptEmpList.do";
	let params = new Object();
	params.domainId = gDomainId;
	params.companyCd = $("#selScCompanyCd").val();
	params.deptCd = deptCd;

	// flag 값이 변경되면 teamUserList 초기화
	if (teamPrevFlag !== flag) {
		teamUserList = [];
		gEmailCnt = 0; // 이메일 개수도 초기화
	}
	teamPrevFlag = flag; // 현재 flag 값을 저장

 	$.ajax({
		type:'get',
		url:apiUrl,
		data: params,
		dataType:'json',
		success: function(result) {
			let data = result.data;
			if(data.length + gEmailCnt > 100) {
				let msgTitle = "";
				let msg = '<spring:message code="text.send.mail.fail" text="메일 전송 인원이 너무 많습니다."/>';
				gfnFailAlert(msgTitle, msg);
				return false;
			}

			for(let i = 0; i < data.length; i++) {
				let email = data[i].email;
				// 중복된 이메일이면 추가하지 않음
				if (!teamUserList.includes(email)) {
					teamUserList.push(email);  // 새로운 이메일만 추가
					gEmailCnt = gEmailCnt + 1;
				}
			}
			$("#emailCnt").text(gEmailCnt);
			let flagValue = flag;
			// Content Script로 메시지 전달
			const event = new CustomEvent("sendDataToExtension", {
				detail: {
					action: "sendEmailData",
					data: { flagValue, teamUserList }
				}
			});
			window.dispatchEvent(event);
		}
	});
}

let userList = [];  // 이메일을 저장할 배열
let prevFlag = null;  // 이전 flag 값을 저장
// 메일 삽입 처리
function fnAddToMail(flag) {
	let idx = $('#grdEmpList').jqGrid("getGridParam", "selarrrow");
	if(idx.length <= 0) {
		let msgTitle = "";
		let msg = '<spring:message code="text.emp.select.fail" text="사용자가 선택되지 않았습니다."/>';
		gfnFailAlert(msgTitle, msg, 2000);
		return false;
	}
	
	if(idx.length + gEmailCnt > 100) {
		let msgTitle = "";
		let msg = '<spring:message code="text.send.mail.fail" text="메일 전송 인원이 너무 많습니다."/>';
		gfnFailAlert(msgTitle, msg);
		return false;
	}
	//gEmailCnt = gEmailCnt + idx.length; // 100명 안넘기면 총 인원에 추가

	// flag 값이 변경되면 teamUserList 초기화
	if (prevFlag !== flag) {
		userList = [];
		gEmailCnt = 0; // 이메일 개수도 초기화
	}
	prevFlag = flag; // 현재 flag 값을 저장
	for(let i = 0; i < idx.length; i++) {
		let rowId = idx[i];  
		let email = $('#grdEmpList').jqGrid("getCell", rowId, "email");
		// 중복된 이메일이면 추가하지 않음
		if (!userList.includes(email)) {
			userList.push(email);  // 새로운 이메일만 추가
			gEmailCnt = gEmailCnt + 1;
		}
  		gEmailCnt = gEmailCnt + 1;
	}
	$("#emailCnt").text(gEmailCnt);
	let flagValue = flag;
	// Content Script로 메시지 전달
	const event = new CustomEvent("sendDataToExtension", {
		detail: {
			action: "sendEmailData",
			data: { flagValue, userList }
		}
	});
	window.dispatchEvent(event);
}
</script>

</body>
</html>