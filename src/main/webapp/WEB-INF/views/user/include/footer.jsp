<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<footer class="main-footer">
<!-- To the right -->
<div id="divBuildLabel" class="pull-right hidden-xs">
  <b>Build No.</b> YYYYMMDD
</div>
<!-- Default to the left -->
<strong>Copyright &copy; 2021 <a href="#">DB Inc</a>.</strong> All rights reserved.
</footer>

<script type ="text/javascript">
$(function() {
	let buildNoHtml = "<b>Build No.</b> " + gBuildNo;
	$("#divBuildLabel").html(buildNoHtml);
}) // $(function()
</script>