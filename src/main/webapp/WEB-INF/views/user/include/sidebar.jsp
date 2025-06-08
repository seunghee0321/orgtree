<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Left side column. contains the logo and sidebar -->
<aside class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled">

	<!-- sidebar: style can be found in sidebar.less -->
	<li class="nav-item active">

		<!-- Sidebar user panel (optional) -->
		<div class="sidebar-brand d-flex align-items-center justify-content-center">
			<div class="image">
				<img id="imgMyProfile" src="<c:out value='${sessionUser.picture}'/>" class="img-profile rounded-circle" alt="User Image">
			</div>
			<%--            <p><i class="fas fa-user"></i> <c:out value="${sessionUser.empLangCdNm}"/></p>--%>
			<%--            <i class="fas fa-building"></i> <c:out value="${sessionUser.deptLangCdNm}"/>--%>
			<div class="sidebar-brand-text mx-3 text-left">
				<i class="fas fa-user text-white"></i> <c:out value="${sessionUser.empLangCdNm}"/><br>
				<i class="fas fa-building text-white"></i> <c:out value="${sessionUser.deptLangCdNm}"/>
			</div>

		</div>

		<!-- Sidebar Menu -->
		<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- 사용자조회 -->
			<li class="nav-item" id="mgEmpMasterList">
				<a href="/user/emp/empMasterListView.do" class="nav-link">
					<i class="fas fa-fw fa-tachometer-alt"></i>
					<span><spring:message code="text.emp.search" text="default text"/></span>
				</a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- 개인설정 -->
			<li class="nav-item" id="mgEmpConfig">
				<a href="/user/config/empConfigView.do" class="nav-link">
					<i class="nav-icon fas fa-wrench"></i>
					<span><spring:message code="text.my.config" text="default text"/></span>
				</a>
			</li>
		</ul>
		<!-- /.sidebar-menu -->
	</li>
	<!-- /.sidebar -->
</aside>

<script type="text/javascript">
</script>
