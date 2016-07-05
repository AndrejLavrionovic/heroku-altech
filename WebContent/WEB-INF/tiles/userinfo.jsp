<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="userInfoBlock">

	<div id="blcUserPersonalInformation">
		<header id="hdrPersonalInfo">User's Personal Info</header>
		
		<section id="secPersonalInfo">
			<table>
				<tr><td class="infoname">Name:</td><td class="infovalue"><c:out value="${user.firstname} ${user.lastname}" /></td></tr>
				<tr><td class="infoname">Username:</td><td class="infovalue"><c:out value="${user.username}" /></td></tr>
				<tr><td class="infoname">Telephone number:</td><td class="infovalue"><c:out value="${user.tel}" /></td></tr>
				<tr><td class="infoname">Email address:</td><td class="infovalue"><c:out value="${user.email}" /></td></tr>
				<tr><td class="infoname">Age:</td><td class="infovalue">37</td></tr>
			</table>
		</section>
		<footer id="ftrPersonalInfo">
			<a href="<c:url value='/edituser?u=${user.username}' />"><div class="btmMngUsers" id="btnEditUser">Edit User</div></a>
			<a href="<c:url value='/deleteuser?u=${user.username}' />"><div class="btmMngUsers" id="btnDeleteUser">Delete user</div></a>
			<a href="${pageContext.request.contextPath}/users"><div class="btmMngUsers" id="btnBackToUsers">Back to Search Users</div></a>
		</footer>
	</div>
</div>