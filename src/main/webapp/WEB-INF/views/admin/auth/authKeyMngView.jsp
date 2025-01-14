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
                    <h3 id="contentTitle" style="margin-top:0px; margin-bottom:0px;"><spring:message code="text.authkey.mng" text="인증키관리" /></h3>
                </div>
                <div class="col-sm-6" style="text-align:right;">
		            <span class="pull-right" id="btnAdmins">
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
                <!-- 인증키 테이블 -->
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title"><i class="fa fa-folder-open"></i><spring:message code="text.authkey.info" text="인증키정보" /></h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div id="table1" style="padding:0;">
                                <table id="simple-table" class="table">
                                    <colgroup>
                                        <col width="10%" />
                                        <col width="20%" />
                                        <col width="70%" />
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td class="table_padding font-bold confer_title_color"><label for="auteKeyInfo"><spring:message code="text.authkey" text="인증키" /></label></td>
                                        <td><input type="text" class="form-control" id="auteKeyInfo" name="auteKeyInfo" style="" readonly/></td>
                                        <td>
                                            <button class="btn btn-primary float-right" id="btnCreateAuthKey"> <spring:message code="text.authkey.issue" text="발급" /></button>
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
    gfnSelectMenu("mgAuthKeyMng", "");
  }

  //화면 컴포넌트 초기화
  function fnSetComponent() {
    fnSetAuthKey();
  }

  //이벤트
  function fnSetEvent() {
    // 인증키발급 버튼 클릭
    $("#btnCreateAuthKey").off("click").on("click", function (e) {
      e.preventDefault();
      let size = "DEFAULT";
      let gMsgCfmTitle = '<spring:message code="text.authkey.issue" text="인증키발급" />';
      let message = '<spring:message code="text.authkey.chk" text="데이터 동기화를 위한 인증키를 발급받으시겠습니까? 발급 시 기존 인증키는 무효화 됩니다." />';
      let callback = fnCreateAuthKey;
      gfnInitCfmMdlDialog(size, gMsgCfmTitle, message, callback);
    });
  }

  function fnSetAuthKey() {
    let apiUrl = "/rest/admin/api/sync/apiKey";
    $.ajax({
      type:'get',
      url:apiUrl,
      dataType:'json',
      success: function(result) {
        if (result.resultCode == "SUCCESS") {
          let data = result.dataOne;
          $("#auteKeyInfo").val(data);
        }
        else {
          let msg = '<spring:message code="text.search.fail" text="조회 실패하였습니다."/>' + result.resultMsg;
          gfnFailAlert("", msg, gDelay2);
        }

      }
    });
  }

  //인증키발급
  function fnCreateAuthKey() {
    let apiUrl = "/rest/admin/api/sync/apiKey";
    let params = new Object();
    $.ajax({
      type:'post',
      url:apiUrl,
      dataType:'json',
      success: function(result) {
        if (result.resultCode == "SUCCESS") {
          let data = result.dataOne;
          $("#auteKeyInfo").val(data);
          let msg = '<spring:message code="text.issue.success" text="발급 성공하였습니다."/>';
          gfnSuccessAlert("", msg, gDelay3);
        }
        else {
          let msg = '<spring:message code="text.issue.fail" text="발급 실패하였습니다."/>' + result.resultMsg;
          gfnFailAlert("", msg, gDelay2);
        }

      }
    });
  }
</script>

</body>
</html>