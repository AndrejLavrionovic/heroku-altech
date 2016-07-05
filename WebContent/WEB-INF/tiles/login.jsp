<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	    
	    <div id="body">
	        <div id="mainsection">
	    	
	    		<header id="hdrTitle">Login to the system here:</header>
	    		
	    		<div id="login">
	    			<header id="hLog">Login</header>
	    			<div id="logNotes">
	    				<p>Password must contain both numbers and letters and must be between
	    				8 and 16 characters.</p>
	    			</div>
	    			<section id="sLog">
	    				<form name='f' method="post" action='${pageContext.request.contextPath}/j_spring_security_check'>
		    				<label class="lblLog" id="email">Username:</label>
		    				<input class="txtLog" type="text" name="j_username"/></br>
		    				<label class="lblLog" id="password">Password:</label>
		    				<input class="txtLog" type="password" name="j_password""/></br>
		    				
		    				<!-- ::::::ERROR (USERNAME OR PASSWORD IS NOT CORRECT):::::: -->
		    				<c:if test="${param.error != null }">
		    					<div id="loginError">
		    						<img alt="error" src="${pageContext.request.contextPath}/src/img/error_20.png">
		    						<span>Username or password is not correct.</span>
		    					</div>
		    				</c:if>
		    				
		    				<div id="logBtns">
		    					<input type="submit" name="submit" value="Login" />
		    				</div>
	    				</form>
	    			</section>
	    		</div>
	        	
	        </div>
	    </div>