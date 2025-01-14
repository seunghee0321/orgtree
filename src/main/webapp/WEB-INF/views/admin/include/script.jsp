<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="/AdminLTE2/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/AdminLTE2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- ChartJS -->
<script src="/AdminLTE2/bower_components/chart.js/Chart.js"></script>

<!-- AdminLTE App -->
<script src="/AdminLTE2/dist/js/adminlte.min.js"></script>

<!-- bootstrap datepicker -->
<script src="/AdminLTE2/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script src="/AdminLTE2/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.ko.min.js"></script>

<!-- JQGrid -->
<script src="/AdminLTE2/etcplugins/jqgrid/i18n/grid.locale-en.js"></script>
<script src="/AdminLTE2/etcplugins/jqgrid/jquery.jqGrid.src.js"></script>

<!-- JSTree -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<!-- Toastr -->
<script src="/AdminLTE2/etcplugins/toastr-master/build/toastr.min.js"></script>

<!-- iCheck -->
<script src="/AdminLTE2/plugins/iCheck/icheck.min.js"></script>

<!-- jquery resize master -->
<script src="/AdminLTE2/etcplugins/jquery.resize-master/jquery.resize.js"></script>

<!-- common JS -->
<script src="/common/common/common.js?ver=<%=System.currentTimeMillis()%>"></script>

<!-- spin JS -->
<script src="/common/common/spin.js"></script>

<!-- Excel -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>

<style>
div.backLayer {display : none; background-color : gray; position : absolute; left : 0px; top : 0px;}
div#loadingDiv {background-color : skyblue; display : none; position : absolute; width : 300px; height : 300px; }
</style>

<div class="backLayer" style=""></div>
<div id="spinner"></div>

<script type ="text/javascript">
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

//필수값 체크 
function gfnCheckRequired(frmObj) {
	var title = "";
	var msg = '<spring:message code="text.required.text" text=" 는(은) 필수 입력값 입니다."/>';
	var f  = frmObj;
	var $t, t;
	var result = true;
	
	f.find("input, select, textarea").each(function() {
		$t = $(this);
		console.log($t);
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

function fnPostMove(url, param, target) { 
	 if(!target) target = "_self";
	 let form = document.createElement('form'); 
	 let objs = new Array();
	 for(let key in param){ 
	 	let value = param[key]; 
		objs = document.createElement('input'); 
	   	objs.setAttribute('type', 'hidden'); 
	   	objs.setAttribute('name', key); 
	   	objs.setAttribute('value', value); 
	   	form.appendChild(objs); 
	 } 
	 form.setAttribute('target', target);
	 form.setAttribute('method', 'post'); 
	 form.setAttribute('action', url); 
	 document.body.appendChild(form); 
	 form.submit(); 
}
//유효성 체크 
function gfnRegExpChk(obj, type) {
	let val = obj.value;
	let re=/[^ㄱ-ㅎ가-힣a-zA-Z0-9\-\_\.\@\s]/gi;
	if(type == 2) {
		re=/[^0-9\-]/gi;
	}
	obj.value=val.replace(re,"");
}
</script>