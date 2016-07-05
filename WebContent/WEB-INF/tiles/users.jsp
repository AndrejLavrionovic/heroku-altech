<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    	
    <c:if test="${message != null}">	
    	<div id="msg"><c:out value="${message}" /></div>
    </c:if>
    <c:if test="${error != null}">	
    	<div id="error"><c:out value="${error}" /></div>
    </c:if>

	<header id="hdrParagraph">
	Here you can search for user by providing one or more of his details such as username, employee number,
	fersname, lastname, email.<br />
	Using firstname, lastname and email fields you can insert
	just part of appropriate values to get results that consists of that values or
	'%' symbol to get all existing users.<br />
	When user is found you can look his details, change user's details, delete user.
	<p>Note, that if you have provided more then one parameter, then user will be searched in following order:</p>
	<ol style="margin-left:16px;">
		<li>Employee Number (full number)</li>
		<li>Username (full username)</li>
		<li>Firstname (one or more characters)</li>
		<li>Lastname (one or more characters)</li>
		<li>Email (full email)</li>
	</ol>
	</header>
	
	<div id="searchUserForm">
		<sf:form action="${pageContext.request.contextPath}/getusers" method="post" commandName="search" >
		
		<!-- 
			<label id="lblFindUserById">Emp No:</label>
			<input type="text" id="txtUserid" name="empnum" />
			<input type="submit" id="btnfindUser" name="findUserById" value="Find" />
		 -->
			
			
			<div id="divSearchParams">
			
				<header id="hdrSearchParams">Search user /-s</header>
				
				<section id="sctSearchParams">
			
					<table class="tblSearchParams">
						<tr>
							<td id="colempnum"><label for="txtEmpnum">Emp No.:</label></td>
							<td id="colusername"><label for="txtUsername">Username:</label></td>
							<td id="colfirstname"><label for="txtFirstname">Firstname:</label></td>
							<td id="collastname"><label for="txtLastname">Lastname:</label></td>
						</tr>
						<tr id="rowSearchInputs">
							<td><sf:input class="txtSearch" path="empnum" type="text" id="txtEmpnum" name="empnum" autocomplete="off" value="" /></td>
							<td><sf:input class="txtSearch" path="username" type="text" id="txtUsername" name="username" autocomplete="off" /></td>
							<td><sf:input class="txtSearch" path="firstname" type="text" id="txtFirstname" name="firstname" autocomplete="off" /></td>
							<td><sf:input class="txtSearch" path="lastname" type="text" id="txtLastname" name="lastname" autocomplete="off" /></td>
						</tr>
					</table>
					
					<hr />
					
					<label id="lblEmail" for="txtEmail">User's email:</label>
					<sf:input type="text" path="email" name="email" id="txtEmail" autocomplete="off" /><br />
					
					<input type="submit" id="btnfindUser" name="findUserById" value="Find" />
				
				</section>
				
			</div>
			
		</sf:form>
	</div>
	
		<!-- 
	
		<div id="userbyidBlock">
			<c:if test="${user != null}">
				<table id="tblGetUsers">
		
 				<thead class="tblHeader">
 					<tr><td class="col1">Emp No.</td><td class="col2">Firstname</td><td class="col3">Lastname</td><td class="col4">Email</td><td class="col5">Tel</td></tr>
 				</thead>
 				<tbody class="tblBody">
 					<tr>
 						<td><c:out value="${user.empnum}"></c:out></td>
 						<td><c:out value="${user.firstname}"></c:out></td>
 						<td><c:out value="${user.lastname}"></c:out></td>
 						<td><c:out value="${user.email}"></c:out></td>
 						<td><c:out value="${user.tel}"></c:out></td>
 					</tr>
 				</tbody>
				</table>
				
				
				<div id="divoptions">
					<a class="btnlink" href="<c:url value='/edituser?u=${user.username}' />" ><div id="btnUser">Edit User</div></a>
					<a class="btnlink" href="<c:url value='/deleteuser?u=${user.username}' />"><div id="btnUser">Delete User</div></a>
				</div>
			</c:if>
		</div>
		
		-->
	
	<c:if test="${users != null && users.size() > 0}">
	<div id="getUsers">
		<table id="tblGetUsers">
		
			<thead class="tblHeader">
				<tr><td class="col1">Emp No.</td><td class="col2">Firstname</td><td class="col3">Lastname</td><td class="col4">Email</td><td class="col5" colspan="3"></td></tr>
			</thead>
			<tbody class="tblBody"><c:forEach var="user" items="${users}">
				<tr id="rowUsersContent">
					<td><c:out value="${user.empnum }"></c:out></td>
					<td><c:out value="${user.firstname }"></c:out></td>
					<td><c:out value="${user.lastname }"></c:out></td>
					<td><c:out value="${user.email }"></c:out></td>
					<td class="userControl"><a href="<c:url value='/userinfo?u=${user.username}' />"><img class="imgusermanagement" alt="User info" src="${pageContext.request.contextPath}/src/img/user_info_16.png"></a></td>
					<td class="userControl"><a href="<c:url value='/edituser?u=${user.username}' />"><img class="imgusermanagement" alt="Edit user" src="${pageContext.request.contextPath}/src/img/user_edit_16.png"></a></td>
					<td class="userControl"><a href="<c:url value='/deleteuser?u=${user.username}' />"><img class="imgusermanagement" alt="Delete user" src="${pageContext.request.contextPath}/src/img/user_delete_16.png"></a></td>
				</tr>
			</c:forEach></tbody>
		
		</table>
	</div>
	</c:if>