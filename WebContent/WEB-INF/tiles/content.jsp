<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

	    
	    <div id="body">
	        <div id="mainsection">
	        
	        	<div id="hdrImg">
	        		<img alt="altech_logo" src="${pageContext.request.contextPath}/src/img/altech_logo.png">
	        	</div>
	        	
	        	<div id="homePageText">
	        		<p>Welcome to the AMS (Altech Management System)</p><br>
	        		<p>AMS performs as a readily available source of information between the organisation
	        		and the employee. Contact information, salary information, posts, work schedule,
	        		holidays booking and other features are available for employees and management.
	        		AMS makes it easy to check and track human resources department at the press of a button.</p><br>
	        		<p>A member of the organisation can easily retrieve information such us
	        		employee's details, memos, notifications and be in touch with his/her colleagues.</p><br>
	        		<p>Administrators would be very pleased if users of AMS will leave a notes or featback
	        		about AMS. Problems and issues also will help to improve system performance.</p>
	        	</div>
	        	
	        	<div id="frmDiv">
	        		<sf:form method="post" action="${pageContext.request.contextPath}/createamsmsg" commandName="amsMessage">
	        			<textarea id="notes" name="msg" rows="5" cols="50"></textarea><br>
	        			<input type="submit" name="amsMessage" value="Send message.">
	        		</sf:form>
	        	</div>
	        	
	        	<div id="botomDiv">
	        		<span>Thanks for using AMS.</span>
	        	</div>
	        </div>
	    </div>