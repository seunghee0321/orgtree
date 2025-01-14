<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">

	<!-- Sidebar user panel (optional) -->
	<div class="user-panel">
		<div class="pull-left image">
			<img id="imgMyProfile" src="<c:out value='${sessionUser.picture}'/>" class="img-circle" alt="User Image">
		</div>
		<div class="pull-left info">
			<p><i class="fa fa-user"></i> <c:out value="${sessionUser.empLangCdNm}"/></p>
			<i class="fa fa-building"></i> <c:out value="${sessionUser.deptLangCdNm}"/>
		</div>
	</div>

	<!-- Sidebar Menu -->
	<ul class="sidebar-menu" data-widget="tree">
		<li class="header">ORG TREE MENU</li>
	
		<!-- 사용자조회 -->
		<li id="mgEmpMasterList"><a href="/user/emp/empMasterListView.do"><i class="fa fa-user"></i> <span><spring:message code="text.emp.search" text="default text"/></span></a></li>
			
		<!-- 개인설정 -->
		<li id="mgEmpConfig"><a href="/user/config/empConfigView.do"><i class="fa fa-wrench"></i> <span><spring:message code="text.my.config" text="default text"/></span></a></li>	
	
	</ul>
	<!-- /.sidebar-menu -->
</section>
<!-- /.sidebar -->
</aside>

<script type="text/javascript">
</script>