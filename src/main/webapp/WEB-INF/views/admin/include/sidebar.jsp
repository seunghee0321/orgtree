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

			<!-- 사용자관리 -->
			<li class="nav-item" id="mgEmpMasterList">
				<a href="/admin/emp/empMasterListView.do" class="nav-link">
					<i class="fa fa-users"></i>
					<span><spring:message code="text.emp.mng" text="사용자관리"/></span>
				</a>
			</li>
			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- 부서관리 -->
			<li class="nav-item" id="mgDeptMasterList"><a href="/admin/dept/deptMasterListView.do" class="nav-link"><i class="fa fa-sitemap"></i> <span><spring:message code="text.dept.mng" text="부서관리"/></span></a></li>

			<!-- 관리자할당 -->
			<li class="nav-item" id="mgEmpRoleMng"><a href="/admin/auth/empRoleMngView.do" class="nav-link"><i class="fa fa-user"></i> <span><spring:message code="text.admin.assign" text="관리자할당"/></span></a></li>

			<!-- 사용자별법인할당 -->
			<li class="nav-item" id="mgEmpCompanyMng"><a href="/admin/auth/empCompanyMngView.do" class="nav-link"><i class="fa fa-building"></i> <span><spring:message code="text.admin.company.mng" text="관리자별법인할당"/></span></a></li>

			<!-- 인증키관리 -->
			<li class="nav-item" id="mgAuthKeyMng"><a href="/admin/auth/authKeyMngView.do" class="nav-link"><i class="fa fa-key"></i> <span><spring:message code="text.authkey.mng" text="인증키관리"/></span></a></li>

		</ul>
		<!-- /.sidebar-menu -->
	</li>
<!-- /.sidebar -->
</aside>

<script type="text/javascript">
</script>