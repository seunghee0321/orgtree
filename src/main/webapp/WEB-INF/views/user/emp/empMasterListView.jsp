<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<!-- meta -->
<%@ include file="/WEB-INF/views/user/include/meta.jsp" %>

<body id="page-top" class="sidebar-toggled">
<div id="wrapper">
	<!-- Main Sidebar -->
	<%@ include file="/WEB-INF/views/user/include/sidebar.jsp" %>

	<!-- Content Wrapper. Contains page content -->
	<div id="content-wrapper" class="d-flex flex-column">
		<!-- Header -->
		<%@ include file="/WEB-INF/views/user/include/header.jsp" %>
		<!-- Main Content -->
		<div id="content">
			<!-- Content Header (Page header) -->
			<nav class="navbar navbar-expand navbar-light bg-white topbar static-top">
				<h1 class="h4 mb-0 text-gray-800">
					<spring:message code="text.emp.search" text="사용자조회"/>
				</h1>
				<ul class="navbar-nav ml-auto">
					<button type="button" id="btnSearchList" class="card border-left-primary shadow h-100 py-2">
						<i class="fas fa-search"></i> <spring:message code="text.search" text="조회"/>
					</button>
					<button type="button" id="btnAddToEmp" class="card border-left-success shadow h-100 py-2" onclick="fnAddToBox('to')">
						<i class="fa fa-plus"></i> <spring:message code="text.add.recipient" text="수신자추가"/>
					</button>
					<button type="button" id="btnAddCcEmp" class="card border-left-info shadow h-100 py-2" onclick="fnAddToBox('cc')">
						<i class="fas fa-plus"></i> <spring:message code="text.add.reference" text="참조추가"/>
					</button>
					<button type="button" id="btnAddBccEmp" class="card border-left-info shadow h-100 py-2" onclick="fnAddToBox('bcc')">
						<i class="fas fa-plus"></i> <spring:message code="text.add.hidden.reference" text="숨은참조추가"/>
					</button>

					<div class="nav-item dropdown no-arrow">
						<button class="card border-left-success shadow h-100 py-2 dropdown-toggle"
								type="button"
								id="teamFeatureDropdown"
								data-toggle="dropdown"
								aria-haspopup="true"
								aria-expanded="false">
							<i class="fas fa-users"></i> <spring:message text="팀 기능"/>
						</button>
						<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
							 aria-labelledby="teamFeatureDropdown">
							<button class="dropdown-item" id="btnAddToTeamEmp" onclick="fnAddToTeamMail('to')">
								<i class="fas fa-plus"></i> <spring:message code="text.add.team.recipient" text="팀수신자추가"/>
							</button>
							<button class="dropdown-item" id="btnAddCcTeamEmp" onclick="fnAddToTeamMail('cc')">
								<i class="fas fa-plus"></i> <spring:message code="text.add.team.reference" text="팀참조추가"/>
							</button>
							<button class="dropdown-item" id="btnAddBccTeamEmp" onclick="fnAddToTeamMail('bcc')">
								<i class="fas fa-plus"></i> <spring:message code="text.add.team.hidden.reference" text="팀숨은참조추가"/>
							</button>
						</div>
					</div>

					<button type="button" id="btnSendMail" class="card border-left-warning shadow h-100 py-2" onclick="fnAddToMail()">
						<i class="fas fa-envelope mr-1"></i> <spring:message code="text.write.mail" text="메일작성"/>
					</button>
					<button type="button" id="btnUndo" class="card border-left-info shadow h-100 py-2">
						<i class="fa fa-undo"></i> <spring:message text="되돌리기"/>
					</button>
					<button type="button" id="btnClose" class="card border-left-danger shadow h-100 py-2">
						<i class="fas fa-times"></i> <spring:message code="text.close" text="닫기"/>
					</button>
				</ul>
			</nav>

			<!-- Main content -->
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<!-- general form elements -->
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-folder-open"></i> <spring:message
										code="text.search.cond" text="조회조건"/></h6>
							</div>

							<div class="card-body">
								<div class="form-group row" style>
									<label for="selScCompanyCd" class="col-sm-1 col-form-label"><spring:message
											code="text.company" text="법인"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScCompanyCd" name="selScCompanyCd"
												onchange="fnChangeCompany()">
											<c:forEach items="${companyList}" var="option">
												<option value="${option.companyCd}">${option.companyNm}</option>
											</c:forEach>
										</select>
									</div>

									<label for="selScSearchCond" class="col-sm-1 col-form-label"><spring:message
											code="text.find.cond" text="검색조건"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScSearchCond" name="selScSearchCond">
											<option value="empNm" selected><spring:message code="text.emp.name"
																						   text="성명"/></option>
											<option value="email"><spring:message code="text.emp.email"
																				  text="이메일"/></option>
											<option value="empNo"><spring:message code="text.emp.no"
																				  text="사번"/></option>
											<option value="jobTelNo"><spring:message code="text.emp.job.tel"
																					 text="사내전화"/></option>
											<option value="mobileTelNo"><spring:message code="text.emp.mobile.tel"
																						text="휴대폰"/></option>
										</select>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="txtScSearchTxt"
											   name="txtScSearchTxt" onblur="javascript:fnRegExpChk(this);"
											   placeholder="<spring:message code="text.search.word" text="검색어"/>">
									</div>

									<label for="selScCompanyStatusCd" class="col-sm-1 col-form-label"><spring:message
											code="text.emp.status" text="재직상태"/></label>
									<div class="col-sm-2">
										<select class="form-control" id="selScCompanyStatusCd"
												name="selScCompanyStatusCd">
										</select>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
				</div>

				<div class="row">
					<div class="col-md-3 col-sm-5">
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-sitemap"></i> <spring:message code="text.org.chart"
																															 text="조직도"/></h6>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-sm-12 d-flex align-items-center" style="gap: 5px; flex-wrap: nowrap;">
										<!-- 부서명 입력박스 -->
										<input type="text" class="form-control" id="txtDeptSearchTxt"
											   name="txtDeptSearchTxt"
											   placeholder="<spring:message code='text.dept.name' text='부서명'/>"
											   style="max-width: 300px; height: 38px;">

										<!-- 조회 버튼 -->
										<button type="button" id="btnSearchTree" class="btn btn-secondary" style="height: 38px; white-space: nowrap;">
											<spring:message code="text.search" text="조회"/>
										</button>

										<!-- 초기화 버튼 -->
										<button type="button" id="btnResetTree" class="btn btn-secondary" style="height: 38px; white-space: nowrap;">
											<spring:message code="text.reset" text="초기화"/>
										</button>
									</div>
								</div>

								<div id="treeDeptList" style="height:380px;overflow:auto;"></div>
								<input type="checkbox" id="chkIncludeSub" name="chkIncludeSub"><span> <spring:message
									code="text.dept.include" text="하위부서포함조회"/></span>
							</div>
						</div>
					</div>

					<!-- general form elements -->
					<div class="col-md-6 col-sm-7">
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-users"></i> <spring:message code="text.emp.list"
																														   text="사용자목록"/></h6>
							</div>
							<!-- /.card-header -->

							<!-- form start -->
							<form class="form-horizontal">
								<div id="dvGrdBox" class="card-body grid-box">
									<table id="grdEmpList"></table>
									<div id="grdEmpListPager"></div>
								</div>
								<!-- /.card-body -->
							</form>
						</div>
					</div>
					<!-- /.card -->

					<!-- Mail 세로배치 -->
					<div class="col-md-3">
						<div class="card shadow mb-4">
							<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary"><i class="fas fa-envelope"></i> <spring:message code="text.write.mail" text="이메일"/> (<span id="emailCnt"></span>)</h6>
							</div>
							<div class="card-body">
								<div class="d-flex align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">
										<spring:message code="text.recipient" text="받는사람"/>
									</h6>
									<ul class="navbar-nav d-flex flex-row gap-2 ml-auto">
										<button class="btn btn-outline-danger btn-sm" onclick="fnDelAllMail('to')">
											<i class="ace-icon fa fa-minus"></i> <spring:message code="text.delete.all" text="전체삭제"/>
										</button>
										<button class="btn btn-outline-primary btn-sm" onclick="fnDelSelMail('to')">
											<i class="ace-icon fa fa-minus"></i> <spring:message code="text.delete.selection" text="선택삭제"/>
										</button>
									</ul>
								</div>
								<select id="toEmpList" name="toEmpList" style="width:100%;height:200px; margin-bottom:10px;" multiple></select>

								<div class="d-flex align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">
										<spring:message code="text.reference" text="참조"/>
									</h6>
									<ul class="navbar-nav d-flex flex-row gap-2 ml-auto">
										<button class="btn btn-outline-danger btn-sm" onclick="fnDelAllMail('cc')">
											<i class="ace-icon fa fa-minus"></i> <spring:message code="text.delete.all" text="전체삭제"/>
										</button>
										<button class="btn btn-outline-primary btn-sm" onclick="fnDelSelMail('cc')">
											<i class="ace-icon fa fa-minus"></i> <spring:message code="text.delete.selection" text="선택삭제"/>
										</button>
									</ul>
								</div>
								<select id="ccEmpList" name="ccEmpList" style="width:100%;height:200px; margin-bottom:10px;" multiple></select>

								<div class="d-flex align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">
										<spring:message code="text.hidden.reference" text="숨은참조"/>
									</h6>
									<ul class="navbar-nav d-flex flex-row gap-2 ml-auto">
										<button class="btn btn-outline-danger btn-sm" onclick="fnDelAllMail('bcc')">
											<i class="ace-icon fa fa-minus"></i> <spring:message code="text.delete.all" text="전체삭제"/>
										</button>
										<button class="btn btn-outline-primary btn-sm" onclick="fnDelSelMail('bcc')">
											<i class="ace-icon fa fa-minus"></i> <spring:message code="text.delete.selection" text="선택삭제"/>
										</button>
									</ul>
								</div>
								<select id="bccEmpList" name="bccEmpList" style="width:100%;height:200px; margin-bottom:10px;" multiple></select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.content -->
		</div>
		<!-- Main Footer -->
		<%@ include file="/WEB-INF/views/user/include/footer.jsp" %>
	</div>
</div>
<!-- ./wrapper -->
<form id="frmHiddenParam">
	<input type="hidden" id="pDeptCd" name="pDeptCd" value=""/> <!-- 트리에서 선택한 부서코드 저장 -->
</form>
</body>
</html>

<!-- script -->
<%@ include file="/WEB-INF/views/user/include/script.jsp" %>

<script type="text/javascript">
	let gDomainId = "";
	let gEmailCnt = 0;
	let emailCnt = 0;

	$(function () {
		fnSetMenuSelection();
		fnSetComponent();
		fnSetEvent();
	}) // $(function()

	function fnSetMenuSelection() {
		gfnSelectMenu("mgEmpMasterList", "");
	}

	// 화면 컴포넌트 초기화
	function fnSetComponent() {

		//그리드 RESIZE
		$('.grid-box').on('resize', $.noop);
		$(window).on('resize.jqGrid', function () {
			$('#grdEmpList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
		});

		// 회사 선택 및 domainId 저장
		$("#selScCompanyCd").val('${sessionUser.companyCd}').attr('selected', true);

		//선택한 법인의 domainId 저장해둠
		<c:forEach items="${companyList}" var="item">
		if ("${item.companyCd}" == $("#selScCompanyCd").val()) {
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
		$("#dvGrdBox").on("resize", function () {
			setTimeout(fnSetGridSize, 200);
		})

		//검색조건 조회
		$("#txtScSearchTxt").keydown(function (e) {
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
		$("#txtDeptSearchTxt").keydown(function (e) {
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

		$("#btnUndo").off("click").on("click", function (e) {
			e.preventDefault();
			fnSendUndo();
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
			type: 'get',
			url: apiUrl,
			data: params,
			dataType: 'json',
			success: function (result) {
				let data = result.data;
				let option = '<option value="" selected>-<spring:message code="text.all" text="전체"/>-</option>';
				$("#selScCompanyStatusCd").append(option);
				//조회한 데이터 option에 추가
				for (let i = 0; i < data.length; i++) {
					option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
					$("#selScCompanyStatusCd").append(option);
				}
			}
		});
	}

	// 그리드 사이즈 초기화
	function fnSetGridSize() {
		$('#grdEmpList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
		$("#gbox_grdEmpList").css("width", $('.grid-box').width());

	}

	// 검색 조건 및 그리드 초기화
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
		$("#treeDeptList").jstree(true).clear_search();

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
		if ($("#chkIncludeSub").is(":checked"))
			params.includeSub = "Y";
		else
			params.includeSub = "N";
		$("#grdEmpList").jqGrid('clearGridData');
		$("#grdEmpList").jqGrid('setGridParam', {
			postData: params,
			success: function (data) {
				if (data.resultCode == "SUCCESS") {
					let msgTitle = "";
					let msg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
					gfnSuccessAlert(msgTitle, msg, 2000);
				} else {
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
			datatype: "json",
			mtype: "POST",
			height: 350,
			jsonReader: {
				page: 'page',
				total: 'total',
				records: 3,
				root: 'data',
				repeatitems: false,
				id: 'rowId'
			},
			colNames: [
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
			colModel: [
				{name: 'companyNm', index: 'companyNm', width: '50%', align: 'left', sortable: false, hidden: true}
				, {name: 'deptNm', index: 'deptNm', width: '55%', align: 'left', sortable: false}
				, {name: 'empNm', index: 'empNm', width: '50%', align: 'left', sortable: false}
				, {name: 'addYn', index: 'addYn', width: '25%', align: 'center', sortable: false}
				, {name: 'posNm', index: 'posNm', width: '30%', align: 'center', sortable: false}
				, {name: 'empNo', index: 'empNo', width: '40%', align: 'center', sortable: false}
				, {name: 'email', index: 'email', width: '60%', align: 'left', sortable: false}
				, {name: 'jobTelNo', index: 'jobTelNo', width: '50%', align: 'left', sortable: false}
				, {name: 'mobileTelNo', index: 'mobileTelNo', width: '50%', align: 'left', sortable: false}
				, {name: 'empStatusNm', index: 'empStatusNm', width: '30%', align: 'center', sortable: false}
			],
			rowNum: 10,
			rowList: [5, 10, 100],
			rownumbers: true,
			gridview: true,
			viewrecords: true,
			emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
			pager: '#grdEmpListPager',
			multiselect: true,
			shrinkToFit: true,
			gridComplete: function () {
				fnSetGridSize();
			},
			loadComplete: function (data) {
				if (data.resultMsg == "SUCCESS") {
					let msgTitle = "";
					let msg = '<spring:message code="text.search.success" text="조회 성공하였습니다."/>';
					gfnSuccessAlert(msgTitle, msg, 2000);
				}
			},
			loadError: function (jqXHR, textStatus, errorThrown) {
				let title = "";
				let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>' + jqXHR.status.toString();
				gfnFailAlert(msgTitle, msg, 2000);
			},
			resizeStop: gfnResizeStop
		});
	}//fnInitGrid

	//검색조건 텍스트 유효성체크
	function fnRegExpChk(obj) {
		let val = obj.value;
		let re = /[^ㄱ-ㅎ가-힣a-zA-Z0-9\-\@]/gi; // 한글, 영문자, 숫자, @, -만 허용
		obj.value = val.replace(re, "");
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
		if ("${item.companyCd}" == $("#selScCompanyCd").val()) {
			gDomainId = "${item.domainId}";
		}
		</c:forEach>
		params.domainId = gDomainId;
		params.companyCd = $("#selScCompanyCd").val();
		let deptList = new Array();
		$.ajax({
			type: 'GET',
			url: apiUrl,
			data: params,
			dataType: 'json',
			success: function (data) {
				$.each(data.data, function (idx, item) {
					if (item.parentDeptCd == '0') deptList[idx] = {
						id: item.deptCd,
						parent: '#',
						text: item.deptNm,
						icon: '/common/common/images/company.png'
					};
					else deptList[idx] = {id: item.deptCd, parent: item.parentDeptCd, text: item.deptNm};
				});
				$('#treeDeptList').jstree(true).settings.core.data = deptList;
				$('#treeDeptList').jstree(true).refresh();
			}
		});
	}//fnReselTree

	// 트리 생성
	function fnInitTree() {
		let apiUrl = "/rest/user/emp/selectDeptList.do";
		let params = new Object();
		let deptList = new Array();
		params.domainId = gDomainId;
		params.companyCd = $("#selScCompanyCd").val();
		$.ajax({
			type: 'get',
			url: apiUrl,
			data: params,
			dataType: 'json',
			success: function (data) {
				$.each(data.data, function (idx, item) {
					if (item.parentDeptCd == '0') deptList[idx] = {
						id: item.deptCd,
						parent: '#',
						text: item.deptNm,
						icon: '/common/common/images/company.png'
					};
					else deptList[idx] = {id: item.deptCd, parent: item.parentDeptCd, text: item.deptNm};
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
					'plugins': ["search", "types"]
				})
						// 노드 선택시 발생하는 이벤트
						.bind('select_node.jstree', function (event, data) {
							fnResetSearchCond();
							$("#pDeptCd").val(data.instance.get_node(data.selected).id);
							fnReselGrid();
						})
			},
			error: function (data) {
			}
		});
	}//fnInitTree

	function fnAddToBox(flag) {
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
		//gEmailCnt = 0; // 이메일 개수도 초기화
		//gEmailCnt = gEmailCnt + idx.length; // 100명 안넘기면 총 인원에 추가
		for(let i = 0; i < idx.length; i++) {
			let rowId = idx[i];
			let email = $('#grdEmpList').jqGrid("getCell", rowId, "email");
			let empNm = $('#grdEmpList').jqGrid("getCell", rowId, "empNm");
			let deptNm = $('#grdEmpList').jqGrid("getCell", rowId, "deptNm");
			let posNm = $('#grdEmpList').jqGrid("getCell", rowId, "posNm");
			let dupChk = "N";
			$("#toEmpList option").each(function(idx, item){
				if ($(this).val() == email) {
					dupChk = "Y";
					return false;
				}
			});
			$("#ccEmpList option").each(function(){
				if ($(this).val() == email) {
					dupChk = "Y";
					return false;
				}
			});
			$("#bccEmpList option").each(function(){
				if ($(this).val() == email) {
					dupChk = "Y";
					return false;
				}
			});
			if(dupChk == "N") {
				gEmailCnt = gEmailCnt + 1;
				$("#" + flag + "EmpList").append("<option value='" + email + "'>" + empNm + " " + posNm + " / " + deptNm + "</option>");
			}
		}
		$("#emailCnt").text(gEmailCnt);
	}

	function fnAddToTeamMail(flag) {
		let deptCd = $("#pDeptCd").val();
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
					let empNm = data[i].empNm;
					let deptNm = data[i].deptNm;
					let posNm = data[i].posNm;
					let dupChk = "N";
					// 이미 추가되어있는 사용자인지 체크
					$("#toEmpList option").each(function(){
						if ($(this).val() == email) {
							dupChk = "Y";
							return false;
						}
					});
					$("#ccEmpList option").each(function(){
						if ($(this).val() == email) {
							dupChk = "Y";
							return false;
						}
					});
					$("#bccEmpList option").each(function(){
						if ($(this).val() == email) {
							dupChk = "Y";
							return false;
						}
					});

					if(dupChk == "N") {
						gEmailCnt = gEmailCnt + 1;
						$("#" + flag + "EmpList").append("<option value='" + email + "'>" + empNm + " " + posNm + " / " + deptNm + "</option>");
					}
				}
				$("#emailCnt").text(gEmailCnt);
			}
		});
	}
	// 메일 전체삭제
	function fnDelAllMail(flag) {
		let delCnt = $("#" + flag + "EmpList option").length;
		gEmailCnt = gEmailCnt - delCnt;
		$("#emailCnt").text(gEmailCnt);
		$("#" + flag + "EmpList option").remove();
	}

	// 메일 선택삭제
	function fnDelSelMail(flag) {
		let delCnt = $("#" + flag + "EmpList option:selected").length;
		gEmailCnt = gEmailCnt - delCnt;
		$("#emailCnt").text(gEmailCnt);
		$("#" + flag + "EmpList option:selected").remove();
	}

	let userList = [];  // 이메일을 저장할 배열
	// 메일 삽입 처리
	function fnAddToMail() {
		$("#toEmpList option, #ccEmpList option, #bccEmpList option").each(function () { //수신, 참조, 숨은 참조 이메일값 가져오기
			userList.push($(this).val());
		});

		let idx = userList.slice(); //userList에서 선택된 이메일을 가져옴

		if (idx.length <= 0) {
			let msgTitle = "";
			let msg = '<spring:message code="text.emp.select.fail" text="사용자가 선택되지 않았습니다."/>';
			gfnFailAlert(msgTitle, msg, 2000);
			return false;
		}

		if (idx.length + gEmailCnt > 100) {
			let msgTitle = "";
			let msg = '<spring:message code="text.send.mail.fail" text="메일 전송 인원이 너무 많습니다."/>';
			gfnFailAlert(msgTitle, msg);
			return false;
		}

		let selectedEmails = { to: [], cc: [], bcc: [] };

		$("#toEmpList option").each(function () {
			selectedEmails.to.push({ flag: "to", email: $(this).val() });
		});
		$("#ccEmpList option").each(function () {
			selectedEmails.cc.push({ flag: "cc", email: $(this).val() });
		});
		$("#bccEmpList option").each(function () {
			selectedEmails.bcc.push({ flag: "bcc", email: $(this).val() });
		});

		const event = new CustomEvent("sendDataToExtension", {
			detail: {
				action: "sendEmailData",
				data: selectedEmails
			}
		});
		window.dispatchEvent(event);
		userList = [];  // 초기화
	}//fnAddToMail

	function fnSendUndo() {//되돌리기
		const event = new CustomEvent("sendDataToExtension", {
		   detail: {
			  action: "sendEmailDataUndo",
			  data: {flagValue: "undo"}
		   }
		});
		window.dispatchEvent(event);
	}//fnSendUndo
</script>
</body>
</html>