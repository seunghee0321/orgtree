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
		<li class="header">ORG TREE ADMIN MENU</li>
	
		<!-- 사용자관리 -->
		<li id="mgEmpMasterList"><a href="/admin/emp/empMasterListView.do"><i class="fa fa-users"></i> <span><spring:message code="text.emp.mng" text="사용자관리"/></span></a></li>
			
		<!-- 부서관리 -->
		<li id="mgDeptMasterList"><a href="/admin/dept/deptMasterListView.do"><i class="fa fa-sitemap"></i> <span><spring:message code="text.dept.mng" text="부서관리"/></span></a></li>	
		
		<!-- 관리자할당 -->
		<li id="mgEmpRoleMng"><a href="/admin/auth/empRoleMngView.do"><i class="fa fa-user"></i> <spring:message code="text.admin.assign" text="관리자할당"/></span></a></li>	
		
		<!-- 사용자별법인할당 -->
		<li id="mgEmpCompanyMng"><a href="/admin/auth/empCompanyMngView.do"><i class="fa fa-building"></i> <span><spring:message code="text.admin.company.mng" text="관리자별법인할당"/></span></a></li>

		<!-- 인증키관리 -->
		<li id="mgAuthKeyMng"><a href="/admin/auth/authKeyMngView.do"><i class="fa fa-key"></i> <span><spring:message code="text.authkey.mng" text="인증키관리"/></span></a></li>

	</ul>
	<!-- /.sidebar-menu -->
</section>
<!-- /.sidebar -->
</aside>

<script type="text/javascript">
</script>