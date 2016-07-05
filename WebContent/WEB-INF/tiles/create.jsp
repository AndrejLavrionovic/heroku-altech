<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	    	
	    		<header id="pageTitle">New user registration form.</header>

				<header id="hdrParagraph">
				AMS (Altech Management System) providing the "New user registration form." This page is
				available only for permitted users such as admin and HR.<br />
				Form consist of several text inputs and dropdown lists. All fields must be completed to proceed
				the registration. 
				<p>There is some rules must be followed while registration:</p>
				<ol style="margin-left:16px;">
					<li>Firstname and lastname must not be longer than 45 characters.</li>
					<li>User must be older then 16 and younger than 80.</li>
					<li>Phone number must be with Irish provider (+353)</li>
					<li>Username must be unique.</li>
					<li>Username must begin with any letter (NOT a number or symbol).</li>
					<li>Username must NOT contain any symbols.</li>
					<li>Username must be 4 - 20 characters long.</li>
					<li>Password must be unique.</li>
					<li>Password must contain atleast one number, uppercase letter, lowercase letter, non-letter symbol.</li>
					<li>Password must be 8-32 characters long.</li>
				</ol>
				</header>
	    		
	    		
	    		<!-- :::::::::::::::::::::: FORM NEW USER ::::::::::::::::::::: -->
	    		<div id="newUserSection">
	    			<sf:form method="post" action="${pageContext.request.contextPath}/docreate" id="frmNewUser" commandName="user">
	    			
	    			<sf:errors path="*" cssClass="error" element="div"></sf:errors>
	    			
	    			<div id="frmUser">
	    				<div id="nameBlock">
	    					<div id="lblNameBlock">
	    						<label class="lblNew" id="lblName">User name:</label>
	    					</div>
	    					<div id="txtFirstnameBlock">
	    						<label class="lblTops" id="lblFirstname" for="txtFirstname">Firstname:<span class="spanModal" id="modalFirstname">(?)</span></label>
	    						<sf:input path="firstname" cssClass="txtNew" cssErrorClass="txtNew txtError" type="text" id="txtFirstname" />
	    					</div>
	    					<div id="txtLastnameBlock">
	    						<label class="lblTops" id="lblLastname" for="lblLastname">Lastname:<span class="spanModal" id="modalLastname">(?)</span></label>
	    						<sf:input path="lastname" cssClass="txtNew" cssErrorClass="txtNew txtError" type="text" id="txtLastname" />
	    					</div>
	    				</div>
	    				
	    				<div id="blockDob">
							<label class="lblNew" id="lblDatePicker" for="inDatePicker">Date of birth:</label>
							<div id="inDatePicker">
								<sf:select path="month" cssClass="selNew" cssErrorClass="selNew selError" id="pickMonth"></sf:select>
								<sf:select path="day" cssClass="selNew" cssErrorClass="selNew selError" id="pickDay"></sf:select>
								<sf:select path="year" cssClass="selNew" cssErrorClass="selNew selError" id="pickYear"></sf:select>
							</div>
	    				</div>
	    				
	    				
	    				<div id="contactsBlock">
	    					<div id="lblContactsBlock">
	    						<label class="lblNew" id="lblContacts" for="txtTelBlock">User Contacts:</label>
	    					</div>
	    					<div id="txtTelBlock">
	    						<label class="lblTops" id="lblTel" for="txtTel">Tel. number<span class="spanModal" id="modalTel">(?)</span></label>
	    						<div>
	    							<sf:select path="telPrefix" cssClass="selNew" cssErrorClass="selNew selError" id="selTelPrefix"></sf:select>
	    							<sf:input path="telNumber" cssClass="txtNew" cssErrorClass="txtNew txtError" type="text" id="txtTel" style="display: inline-block;" />
	    						</div>
	    						
	    					</div>
	    					<div id="txtEmailBlock">
	    						<label class="lblTops" id="lblEmail" for="txtEmail">Email<span class="spanModal" id="modalEmail">(?)</span></label>
	    						<sf:input path="email" cssClass="txtNew" cssErrorClass="txtNew txtError" type="text" id="txtEmail" />
	    					</div>
	    				</div>
	    				
	    				<label class="lblNew" id="lblStatus" for="selAuthority">Authority:</label>
	    				
	    				<sf:select path="authority" id="selAuthority" cssClass="selNew" cssErrorClass="selNew selError" >
	    					<option selected="selected" value=""></option>
							<option value="user" >Employee</option>
						 	<option value="man">Office Manager</option>
						 	<option value="head">Head Manager</option>
						 	<option value="hr">HR Admin</option>
					 	</sf:select>
	    				<br />
	    			</div>
	    			
	    			<div id="login">
	    				<label class="lblNew" id="lblUsername" for="txtUsername">Username:<span class="spanModal" id="modalUsername">(?)</span></label>
	    				<sf:input path="username" cssClass="txtNew" cssErrorClass="txtNew txtError" type="text" id="txtUsername" /><br />
	    				<label class="lblNew" id="lblPassword" for="txtPassword">Password:<span class="spanModal" id="modalPassword">(?)</span></label>
	    				<sf:input path="password" cssClass="txtNew" cssErrorClass="txtNew txtError" type="text" id="txtPassword" /><br />
	    				<label class="lblNew" id="lblConfirm">Confirm password:</label>
	    				<input class="txtNew" type="text" id="txtConfirm" name="confirm" /><br />
	    			</div>

	    			
	    			<div id="frmBtns">
	    				<input class="buttons" type="submit" id="btnNewUser" name="newUser" value="Create" />
	    				<input class="buttons" type="button" id="btnClear" name="clear" value="Clear" />
	    				<input class="buttons" type="button" id="btnBack" name="back" value="Back" />
	    			</div>
	    			
	    			</sf:form>
	    		</div>
	    		
	    		<div id="myModal" class="modal">
		            <div id="modalwindow" class="modalwindow">
		
		                <header id="modalHeader"><img id="imgClose" src="${pageContext.request.contextPath}/src/img/close_32.png" /></header>
		                <section id="modalContent">
		                    <h3 id="fieldName"></h3>
		                    <ul id="conditions">
		                    </ul>
		                </section>
		
		            </div>
		        </div>
	    			
    			<script src="${pageContext.request.contextPath}/src/js/dataPicker.js"></script>
    			<script src="${pageContext.request.contextPath}/src/js/modal.js"></script>