<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery  -->
<script src="/sbadmin2/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap 4 -->
<script src="/sbadmin2/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- ChartJS -->
<script src="/sbadmin2/vendor/chart.js/Chart.min.js"></script>

<!-- AdminLTE App -->
<script src="/sbadmin2/js/sb-admin-2.min.js"></script>

<!-- JSTree -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/jstree.min.js"></script>

<!-- Toastr -->
<script src="/sbadmin2/plugins/etcplugins/toastr-master/build/toastr.min.js"></script>

<!-- jqGrid Locale -->
<script src="/sbadmin2/plugins/etcplugins/jqgrid/i18n/grid.locale-en.js"></script>

<!--jqGrid JS-->
<script src="/sbadmin2/plugins/etcplugins/jqgrid/jquery.jqGrid.src.js"></script>

<!-- Common JS -->
<script src="/common/common/common.js?ver=<%=System.currentTimeMillis()%>"></script>

<!-- Spin JS -->
<script src="/common/common/spin.js"></script>

<style>
    div.backLayer {display : none; background-color : gray; position : absolute; left : 0px; top : 0px;}
    div#spinner {background-color : skyblue; display : none; position : absolute; width : 300px; height : 300px; }
</style>

<div class="backLayer" style=""></div>
<div id="spinner"></div>

<script type="text/javascript">
    var gSpinner = null;    // 로딩바 객체 변수

    function gfnShowLoadingBar() {
        let width = $(window).width();
        let height = $(window).height();

        $('.backLayer').width(width);
        $('.backLayer').height(height);
        $('.backLayer').fadeTo(300, 0.4);

        let opts = {
            lines: 13                // The number of lines to draw
            , length: 18             // The length of each line
            , width: 12             // The line thickness
            , radius: 42             // The radius of the inner circle
            , scale: 1                 // Scales overall size of the spinner
            , corners: 1             // Corner roundness (0..1)
            , color: '#0088cc'         // #rgb or #rrggbb or array of colors
            , opacity: 0.25         // Opacity of the lines
            , rotate: 0             // The rotation offset
            , direction: 1             // 1: clockwise, -1: counterclockwise
            , speed: 1                 // Rounds per second
            , trail: 60             // Afterglow percentage
            , fps: 20                 // Frames per second when using setTimeout() as a fallback for CSS
            , zIndex: 2e9             // The z-index (defaults to 2000000000)
            , className: 'spinner'     // The CSS class to assign to the spinner
            , top: '46%'             // Top position relative to parent
            , left: '41%'             // Left position relative to parent
            , shadow: false         // Whether to render a shadow
            , hwaccel: false         // Whether to use hardware acceleration
            , position: 'absolute'     // Element positioning
        }

        let target = document.getElementById("spinner");

        if (gSpinner == null) {
            gSpinner = new Spinner().spin(target);
        }
    }

    function gfnHideLoadingBar() {
        if (gSpinner != null) {
            gSpinner.stop();
            gSpinner = null;
        }
        $(".backLayer").css("display", "none");
    }

    // 필수값 체크
    function gfnCheckRequired(frmObj) {
        var title = "";
        var msg = '<spring:message code="text.required.text" text=" 는(은) 필수 입력값 입니다."/>';
        var f  = frmObj;
        var $t, t;
        var result = true;

        f.find("input, select, textarea").each(function() {
            $t = $(this);

            if ($t.prop("required")) {
                if (!$.trim($t.val())) {
                    t = $("label[for='"+$t.attr("id")+"']").text();
                    result = false;
                    $t.focus();
                    gfnFailAlert(title, t + " " + msg, 2500);
                    return false;
                }
            }
        });

        if (!result)
            return false;
        else
            return true;
    }

    function gfnSuccessAlert(pTitle, pMsg, pDelay) {
        if (typeof pTitle == "undefined" || pTitle == "" || pTitle == null) {
            pTitle = "System Info";
        }

        if (typeof pDelay == "undefined" || pDelay == "" || pDelay == null) {
            pDelay = 1500;
        }

        toastr.options.escapeHtml = true;
        toastr.options.closeButton = true;
        toastr.options.newestOnTop = false;
        toastr.options.progressBar = true;
        toastr.success(pMsg, pTitle, {timeOut: pDelay});
    }

    function gfnFailAlert(pTitle, pMsg, pDelay) {
        if (typeof pTitle == "undefined" || pTitle == "" || pTitle == null) {
            pTitle = "System Info";
        }

        if (typeof pDelay == "undefined" || pDelay == "" || pDelay == null) {
            pDelay = 2000;
        }

        toastr.options.escapeHtml = true;
        toastr.options.closeButton = true;
        toastr.options.newestOnTop = false;
        toastr.options.progressBar = true;
        toastr.error(pMsg, pTitle, {timeOut: pDelay});
    }
</script>
