<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <h3 style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.emp.mng"
                                                                                   text="사용자관리"/></h3>
                </div>
                <div class="col-sm-6" style="text-align:right;">
		            <span class="pull-right">
			            <button type="button" id="btnSearchList" class="btn btn-primary float-right"><i
                                class="fa fa-search"></i> <spring:message code="text.search" text="조회"/></button>
			            <button class="btn btn-primary float-right" id="btnAdd"><i
                                class="ace-icon fa fa-plus"></i> <spring:message code="text.registration"
                                                                                 text="등록"/></button>
			            <button type="button" id="btnDownExcel" class="btn btn-primary float-right"><i
                                class="fa fa-file-excel-o"></i> <spring:message code="text.excel.download"
                                                                                text="엑셀다운로드"/></button>
			            <button type="button" id="btnDownExcelForm" class="btn btn-primary float-right"><i
                                class="fa fa-file-excel-o"></i> <spring:message code="text.excel.upload.form"
                                                                                text="업로드양식"/></button>
                        <!-- 엑셀 업로드 form -->
			            <form id="frmAttachedFiles" class="form-horizontal" enctype="multipart/form-data"
                              style="display:inline;">
							<div class="btn btn-primary btn-file">
			                  <i class="fa fa-paperclip"></i> <spring:message code="text.excel.upload" text="엑셀업로드"/>
			                  <input type="file" id="btnUploadExcel" name="btnUploadExcel">
			                </div>
						</form>	
			            <form id="frmAttachedFilesGoogle" class="form-horizontal" enctype="multipart/form-data"
                              style="display:inline;">
							<div class="btn btn-primary btn-file">
			                  <i class="fa fa-paperclip"></i> Google
			                  <input type="file" id="btnGoogleUpload" name="btnGoogleUpload">
			                </div>
						</form>
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
                            <h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message
                                    code="text.search.cond" text="조회조건"/></h3>
                        </div>
                        <!-- /.box-header -->

                        <!-- form start -->
                        <form class="form-horizontal">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="selScCompanyCd" class="col-sm-1 control-label"><spring:message
                                            code="text.company" text="법인"/></label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="selScCompanyCd" name="selScCompanyCd"
                                                onchange="fnChangeCompany()">
                                            <c:forEach items="${companyList}" var="option">
                                                <option value="${option.companyCd}">${option.companyNm}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <label for="selScSearchCond" class="col-sm-1 control-label"><spring:message
                                            code="text.find.cond" text="검색조건"/></label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="selScSearchCond" name="selScSearchCond">
                                            <!--  <option value="">-<spring:message code="text.select" text="선택"/>-</option> -->
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
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="txtScSearchTxt"
                                               name="txtScSearchTxt" onblur="javascript:gfnRegExpChk(this);"
                                               placeholder="<spring:message code="text.search.word" text="검색어"/>">
                                    </div>

                                    <label for="selScCompanyStatusCd" class="col-sm-1 control-label"><spring:message
                                            code="text.emp.status" text="재직상태"/></label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="selScCompanyStatusCd"
                                                name="selScCompanyStatusCd"></select>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label for="selAddYn" class="col-sm-1 control-label"><spring:message
                                            code="text.emp.add.yn" text="겸직여부"/></label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="selAddYn" name="selAddYn">
                                            <option value="" selected>-<spring:message code="text.all" text="전체"/>-
                                            </option>
                                            <option value="Y">Y</option>
                                            <option value="N">N</option>
                                        </select>
                                    </div>

                                    <label for="selManualMngYn" class="col-sm-1 control-label"><spring:message
                                            code="text.manual.manage" text="수동관리"/></label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="selManualMngYn" name="selManualMngYn">
                                            <option value="" selected>-<spring:message code="text.all" text="전체"/>-
                                            </option>
                                            <option value="Y">Y</option>
                                            <option value="N">N</option>
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
                <div class="col-md-3">

                    <div class="box box-primary" style="height:552px;">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message
                                    code="text.org.chart" text="조직도"/></h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="txtDeptSearchTxt"
                                           name="txtDeptSearchTxt"
                                           placeholder=" <spring:message code="text.dept.name" text="부서명"/>">
                                </div>
                                <span>
							       	<button type="button" id="btnSearchTree" class="btn btn-secondary"><i
                                            class="fa fa-search"></i> <spring:message code="text.search"
                                                                                      text="조회"/></button>
							       	<button type="button" id="btnResetTree" class="btn btn-secondary"><i
                                            class="fa fa-trash-o"></i> <spring:message code="text.reset"
                                                                                       text="초기화"/> </button>
							    </span>
                            </div>
                            <div id="treeDeptList" style="height:430px;overflow:auto;"></div>
                            <div>
                                <input class="form-check-input" type="checkbox" id="chkDeptUseYn" name="chkDeptUseYn"
                                       onclick="fnReselTree()"><span> <spring:message code="text.include.dept.use"
                                                                                      text="사용안하는부서포함"/></span>
                            </div>
                        </div>
                    </div>

                </div>

                <!-- general form elements -->
                <div class="col-md-9">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-folder-open"></i> <spring:message code="text.emp.list"
                                                                                                    text="사용자목록"/></h3>
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
    <%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>

</div>
<!-- ./wrapper -->
<form id="frmHiddenParam">
    <input type="hidden" id="pDeptCd" name="pDeptCd" value=""/>
    <input type="hidden" id="pDeptNm" name="pDeptNm" value=""/>
    <input type="hidden" id="pCompanyCd" name="pCompanyCd" value=""/>
    <input type="hidden" id="pEmail" name="pEmail" value=""/>
</form>

<!-- 엑셀 다운로드 -->
<form id="frmExcelDown" method="GET">
    <input type="hidden" id="exDomainId" name="exDomainId">
    <input type="hidden" id="exCompanyCd" name="exCompanyCd">
    <input type="hidden" id="exSearchCond" name="exSearchCond">
    <input type="hidden" id="exSearchTxt" name="exSearchTxt">
    <input type="hidden" id="exCompanyStatusCd" name="exCompanyStatusCd">
    <input type="hidden" id="exAddYn" name="exAddYn">
    <input type="hidden" id="exManualMngYn" name="exManualMngYn">
    <input type="hidden" id="exDeptCd" name="exDeptCd">
    <input type="hidden" id="exIncludeSub" name="exIncludeSub">
    <input type="hidden" id="exIncDeptUseYn" name="exIncDeptUseYn">
</form>

<!-- script -->
<%@ include file="/WEB-INF/views/admin/include/script.jsp" %>

<script type="text/javascript">
    let gDomainId = "";
    $(function () {
        fnSetMenuSelection()
        fnSetComponent();
        fnSetEvent();
    }) // $(function()

    function fnSetMenuSelection() {
        gfnSelectMenu("mgEmpMasterList", "");
    }

    //화면 컴포넌트 초기화
    function fnSetComponent() {
        // 그리드 RESIZE
        $('.grid-box').on('resize', $.noop);
        $(window).on('resize.jqGrid', function () {
            $('#grdEmpList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
        });

        $("#selScCompanyCd").val('${sessionUser.companyCd}').attr('selected', true);
        // 선택한 법인의 domainId 저장해둠
        <c:forEach items="${companyList}" var="item">
        if ("${item.companyCd}" == $("#selScCompanyCd").val()) {
            gDomainId = "${item.domainId}";
        }
        </c:forEach>

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

        // 검색조건 조회
        $("#txtScSearchTxt").keydown(function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                gfnRegExpChk(this);
                fnResetDeptSearch();
                fnReselGrid();
            }
        });
        $("#btnSearchList").off("click").on("click", function (e) {
            e.preventDefault();
            fnResetDeptSearch();
            fnReselGrid();
        });

        // 부서 검색조건 조회
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

        // 조직도 트리 초기화
        $("#btnResetTree").off("click").on("click", function (e) {
            e.preventDefault();
            fnResetDeptSearch();
            fnReselGrid();
        });

        // 그리드 상세버튼 클릭
        $(document).on('click', 'button[name="btnEmpDetail"]', function (e) {
            let apiUrl = "/admin/emp/empMasterDetailView.do";
            let params = new Object();
            params.companyCd = $("#selScCompanyCd").val();
            let rowId = $("#grdEmpList").jqGrid('getGridParam', 'selrow');
            let rowData = $("#grdEmpList").jqGrid("getRowData", rowId);
            params.email = rowData.email;
            params.domainId = rowData.domainId;
            params.companyCd = rowData.companyCd;
            fnPostMove(apiUrl, params);
        });

        // 등록버튼 클릭
        $("#btnAdd").off("click").on("click", function (e) {
            e.preventDefault();
            let apiUrl = "/admin/emp/empMasterAddView.do";
            let params = new Object();
            params.companyCd = $("#selScCompanyCd").val();
            params.deptCd = $("#pDeptCd").val();
            params.deptNm = $("#pDeptNm").val();
            fnPostMove(apiUrl, params);
        });

        // 엑셀다운로드 버튼 클릭
        $("#btnDownExcel").off("click").on("click", function (e) {
            e.preventDefault();
            fnDownExcel();
        });

        // 엑셀Form다운로드 버튼 클릭
        $("#btnDownExcelForm").off("click").on("click", function (e) {
            e.preventDefault();
            fnDownExcelForm();
        });

        // 파일 선택(업로드) 이벤트
        $("#btnUploadExcel").on("change", function () {
            fnUploadExcelRegChk();
        });

        // Google 업로드 이벤트
        $("#btnGoogleUpload").on("change", function () {
            fnGoogleUploadChkMsg();
        });

    }

    //법인별 재직상태 Set
    function fnSetEmpStatus() {
        $('#selScCompanyStatusCd').children('option').remove();
        let apiUrl = "/rest/admin/emp/selectCodeDetailList.do";
        let params = new Object();
        params.domainId = gDomainId;
        params.companyCd = $("#selScCompanyCd").val();
        params.codeDiv = "CM001";
        $.ajax({
            type: 'get',
            url: apiUrl,
            data: params,
            dataType: 'json',
            success: function (result) {
                let data = result.data;
                let option = '<option value="" selected>-<spring:message code="text.all" text="전체"/>-</option>';
                $("#selScCompanyStatusCd").append(option);
                for (let i = 0; i < data.length; i++) {
                    option = "<option value='" + data[i].code + "'>" + data[i].codeNm + "</option>";
                    $("#selScCompanyStatusCd").append(option);
                }
            }
        });
    }

    //검색 조건 및 그리드 초기화
    function fnResetSearchCond() {
        fnResetDeptSearch(); // 트리 초기화
        // 그리드 초기화
        fnResetSearchCond();
        $("#grdEmpList").jqGrid('clearGridData');
    }

    //부서 검색 초기화
    function fnResetDeptSearch() {
        $("#pDeptCd").val("");
        $("#pDeptNm").val("");
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

    //법인 셀렉트박스 변경
    function fnChangeCompany() {
        fnSetEmpStatus(); // 재직상태코드 Reset
        fnReselTree();
    }

    //그리드 사이즈 초기화
    function fnSetGridSize() {
        $('#grdEmpList').jqGrid('setGridWidth', $('.grid-box').width() - 2);
        $("#gbox_grdEmpList").css("width", $('.grid-box').width());
    }

    //그리드 재조회
    function fnReselGrid() {
        let params = new Object();
        params.domainId = gDomainId;
        params.companyCd = $("#selScCompanyCd").val();
        params.searchCond = $("#selScSearchCond").val();
        params.searchTxt = $("#txtScSearchTxt").val();
        params.companyStatusCd = $("#selScCompanyStatusCd").val();
        params.addYn = $("#selAddYn").val();
        params.manualMngYn = $("#selManualMngYn").val();
        params.deptCd = $("#pDeptCd").val();
        params.includeSub = "N";
        params.incDeptUseYn = $('input:checkbox[id="chkDeptUseYn"]').is(":checked");
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

    //그리드 상세 버튼
    function formatButton1(cellvalue, options, rowObject) {
        var rowId = rowObject['rowId'];
        var btnStr = '<spring:message code="text.detail" text="상세"/>';
        var buttonHtml1 = "<button type='button' class='btn btn-block btn-default-jihun btn-sm' name='btnEmpDetail' data-rowid='" + rowId + "'>";
        buttonHtml1 = buttonHtml1 + "<i class='fa fa-edit'></i>&nbsp;";
        buttonHtml1 = buttonHtml1 + btnStr + "</button>";
        return buttonHtml1;
    }

    //그리드 생성
    function fnInitGrid() {
        let apiUrl = "/rest/admin/emp/selectEmpList.do";
        let params = new Object();

        $("#grdEmpList").jqGrid({
            url: apiUrl,
            postData: params,
            datatype: "json",
            mtype: "GET",
            height: 400,
            jsonReader: {
                page: 'page',
                total: 'total',
                records: 'records',
                root: 'data',
                repeatitems: false,
                id: 'rowId'
            },
            colNames: [
                ''
                , 'rowId'
                , 'domainId'
                , 'companyCd'
                , '<spring:message code="text.company" text="법인"/>'
                , '<spring:message code="text.emp.name" text="성명"/>'
                , 'deptCd'
                , '<spring:message code="text.dept" text="부서"/>'
                , '<spring:message code="text.emp.position" text="직위"/>'
                , '<spring:message code="text.emp.email" text="이메일"/>'
                , '<spring:message code="text.emp.add.yn" text="겸직여부"/>'
                , '<spring:message code="text.emp.no" text="사번"/>'
                , '<spring:message code="text.emp.job.tel" text="사내전화"/>'
                , '<spring:message code="text.emp.mobile.tel" text="휴대폰"/>'
                , '<spring:message code="text.emp.status" text="재직상태"/>'
                , '<spring:message code="text.manual.manage" text="수동관리"/>'
            ],
            colModel: [
                {
                    name: 'button',
                    index: 'button',
                    width: '80',
                    align: 'center',
                    sortable: false,
                    hidden: false,
                    fixed: true,
                    //1/17 수정
                    // formatter: formatButton1
                    formatter: function (cellValue, options, rowObject) {
                        return `<button onclick="selectEmployee('${cellValue}')">${cellValue}</button>`;
                    }
                }
                , {name: 'rowId', index: 'rowId', width: '30%', align: 'center', sortable: false, hidden: true}
                , {name: 'domainId', index: 'domainId', width: '50%', align: 'left', sortable: false, hidden: true}
                , {name: 'companyCd', index: 'companyCd', width: '50%', align: 'left', sortable: false, hidden: true}
                , {name: 'companyNm', index: 'companyNm', width: '50%', align: 'left', sortable: false}
                , {name: 'empNm', index: 'empNm', width: '50%', align: 'center', sortable: false}
                , {name: 'deptCd', index: 'deptCd', width: '55%', align: 'left', sortable: false, hidden: true}
                , {name: 'deptNm', index: 'deptNm', width: '55%', align: 'left', sortable: false}
                , {name: 'posNm', index: 'posNm', width: '30%', align: 'center', sortable: false}
                , {name: 'email', index: 'email', width: '60%', align: 'left', sortable: false}
                , {name: 'addYn', index: 'addYn', width: '30%', align: 'center', sortable: false}
                , {name: 'empNo', index: 'empNo', width: '40%', align: 'left', sortable: false}
                , {name: 'jobTelNo', index: 'jobTelNo', width: '50%', align: 'left', sortable: false}
                , {name: 'mobileTelNo', index: 'mobileTelNo', width: '50%', align: 'left', sortable: false}
                , {name: 'empStatusNm', index: 'empStatusNm', width: '30%', align: 'center', sortable: false}
                , {name: 'manualMngYn', index: 'manualMngYn', width: '30%', align: 'center', sortable: false}
            ],
            rowNum: 10,
            rowList: [5, 10, 100],
            rownumbers: true,
            gridview: true,
            viewrecords: true,
            emptyrecords: '<spring:message code="text.empty.data" text="데이터가 존재하지 않습니다."/>',
            pager: '#grdEmpListPager',
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
    }

    // 1/17일 추가
    function selectEmployee(email) {
        parent.postMessage({ action: 'insertEmail', email: email }, '*');
        alert(`Email '${email}' has been sent to Gmail popup.`);
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
        params.incDeptUseYn = $('input:checkbox[id="chkDeptUseYn"]').is(":checked");
        let deptList = new Array();
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
                    else {
                        if (item.deptUseYn == 'N') deptList[idx] = {
                            id: item.deptCd,
                            parent: item.parentDeptCd,
                            text: item.deptNm + '(N/A)'
                        };
                        else deptList[idx] = {id: item.deptCd, parent: item.parentDeptCd, text: item.deptNm};
                    }
                });
                $('#treeDeptList').jstree(true).settings.core.data = deptList;
                $('#treeDeptList').jstree(true).refresh();
            }
        });
        fnResetSearchCond();
    }

    //트리 생성
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
                    else {
                        if (item.deptUseYn == 'N') deptList[idx] = {
                            id: item.deptCd,
                            parent: item.parentDeptCd,
                            text: item.deptNm + '(N/A)'
                        };
                        else deptList[idx] = {id: item.deptCd, parent: item.parentDeptCd, text: item.deptNm};
                    }
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
                        $("#pDeptNm").val(data.instance.get_node(data.selected).text);
                        fnReselGrid();
                    })
            },
            error: function (data) {
            }
        });
    }

    //엑셀 업로드양식 다운로드
    function fnDownExcelForm() {
        let frm = $("#frmExcelDown");
        frm.attr("action", "/rest/admin/emp/empExcelFormDown.do");
        frm.submit();
    }

    // 엑셀 사용자 목록 다운로드
    function fnDownExcel() {
        let size = "DEFAULT";
        let message = '<spring:message code="text.excel.download.msg" text="다운로드 하시겠습니까?"/>'
        let callback = fnDownExcelImpl;
        gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
    }

    //엑셀 다운로드 버튼 수행
    function fnDownExcelImpl() {
        let frm = $("#frmExcelDown");
        frm.attr("action", "/rest/admin/emp/empListExcelDown.do");
        $("#exDomainId").val(gDomainId);
        $("#exCompanyCd").val($("#selScCompanyCd").val());
        $("#exSearchCond").val($("#selScSearchCond").val());
        $("#exSearchTxt").val($("#txtScSearchTxt").val());
        $("#exCompanyStatusCd").val($("#selScCompanyStatusCd").val());
        $("#exAddYn").val($("#selAddYn").val());
        $("#exManualMngYn").val($("#selManualMngYn").val());
        $("#exDeptCd").val($("#pDeptCd").val());
        $("#exIncDeptUseYn").val($('input:checkbox[id="chkDeptUseYn"]').is(":checked"));
        frm.submit();
    }

    //엑셀업로드 체크
    function fnUploadExcelRegChk() {
        let msg = "";
        let input = event.target;
        let reader = new FileReader();
        reader.onload = function () {
            let fdata = reader.result;
            let read_buffer = XLSX.read(fdata, {type: 'binary'});
            read_buffer.SheetNames.forEach(function (sheetName) {
                let rowdata = XLSX.utils.sheet_to_json(read_buffer.Sheets[sheetName]); // Excel 입력 데이터
                // 행 수 만큼 반복
                for (let i = 0; i < rowdata.length; i++) {
                    // 필수값 체크
                    if (rowdata[i].도메인 == null)
                        msg += '도메인, ';
                    if (rowdata[i].법인코드 == null)
                        msg += '법인코드, ';
                    if (rowdata[i].이메일 == null)
                        msg += '이메일, ';
                    if (rowdata[i].성명 == null)
                        msg += '성명, ';
                    if (rowdata[i].부서코드 == null)
                        msg += '부서코드, ';
                    if (rowdata[i].재직상태코드 == null)
                        msg += '재직상태코드, ';
                    if (msg != '') {
                        msg = msg.substr(0, msg.length - 2);
                        msg += ' ' + '<spring:message code="text.empty.data" text=" 값이 존재하지 않습니다."/>';
                        gfnFailAlert("", msg, gDelay2);
                        return false;
                    }

                    // 정규식 체크
                    let keys = Object.keys(rowdata[i]);
                    let re = /[^ㄱ-ㅎ가-힣a-zA-Z0-9\-\_\.\@\s]/gi;
                    let reNum = /[^0-9]/gi;
                    for (let j = 0; j < keys.length; j++) {
                        let data = rowdata[i][keys[j]];
                        if (keys[j] == '입사일(YYYYMMDD)' || keys[j] == '퇴사일(YYYYMMDD)') {
                            if (reNum.test(data)) {
                                msg = keys[j] + '<spring:message code="text.only.number" text="는(은) 숫자만 입력 가능합니다."/>';
                                gfnFailAlert("", msg, gDelay2);
                                return false;
                            }
                            if (data.toString().length != 8 && data.toString().length != 0) {
                                msg = keys[j] + '<spring:message code="text.input.data.error" text="이(가) 잘못 입력되었습니다."/>';
                                gfnFailAlert("", msg, gDelay2);
                                return false;
                            }
                        } else if (keys[j] == '숨김여부(Y/N)' || keys[j] == '수동관리여부(Y/N)' || keys[j] == '부서장여부(Y/N)') {
                            if ((data != 'Y' && data != 'N') || data.toString().length > 1) {
                                msg = keys[j] + '<spring:message code="text.input.data.error" text="이(가) 잘못 입력되었습니다."/>';
                                gfnFailAlert("", msg, gDelay2);
                                return false;
                            }
                        } else {
                            if (re.test(data)) {
                                msg = keys[j] + '<spring:message code="text.not.accept.text" text="에 허용되지않는 문자가 포함되어있습니다."/>';
                                gfnFailAlert("", msg, gDelay2);
                                return false;
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
        let apiUrl = "/rest/admin/emp/empListExcelUp.do";
        $.ajax({
            url: apiUrl,
            type: "POST",
            data: new FormData($("#frmAttachedFiles")[0]),
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.resultCode == "SUCCESS") {
                    let msg = '<spring:message code="text.excel.upload.success" text="업로드 성공하였습니다."/>';
                    gfnSuccessAlert("", msg, gDelay3);
                    setTimeout(function () {
                        location.href = "/admin/emp/empMasterListView.do";
                    }, 2000);
                } else {
                    let msg = '<spring:message code="text.excel.upload.fail" text="업로드 실패하였습니다."/>' + result.resultMsg;
                    gfnFailAlert("", msg, gDelay2);
                }
            }
        }).always(function (msg) {
            gfnHideLoadingBar();
        });
    }

    //구글 관리콘솔 데이터 업로드 확인 Message
    function fnGoogleUploadChkMsg() {
        let size = "DEFAULT";
        let gMsgCfmTitle = 'Google Upload';
        let message = '<spring:message code="text.google.upload.msg" text="조회 조건에 선택한 법인으로 데이터가 업로드 됩니다. 데이터가 많은 경우 긴 시간이 소요될 수 있습니다. 진행 하시겠습니까?"/>';
        let callback = fnGoogleUpload;
        gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
    }

    //구글 관리콘솔 데이터 업로드 처리
    function fnGoogleUpload() {
        gfnShowLoadingBar();

        let form = new FormData($("#frmAttachedFilesGoogle")[0]);
        form.append("domainId", gDomainId);
        form.append("companyCd", $("#selScCompanyCd").val());

        let apiUrl = "/rest/admin/emp/googleUploadEmpAndDept.do";
        $.ajax({
            url: apiUrl,
            type: "POST",
            data: form,
            dataType: "json",
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            success: function (result) {
                if (result.resultCode == "SUCCESS") {
                    let msg = '<spring:message code="text.excel.upload.success" text="업로드 성공하였습니다."/>';
                    gfnSuccessAlert("", msg, gDelay3);
                    setTimeout(function () {
                        location.href = "/admin/emp/empMasterListView.do";
                    }, 2000);
                } else {
                    let msg = '<spring:message code="text.excel.upload.fail" text="업로드 실패하였습니다."/>' + result.resultMsg;
                    gfnFailAlert("", msg, gDelay2);
                }
            }
        }).always(function (msg) {
            gfnHideLoadingBar();
        });
    }
</script>

</body>
</html>