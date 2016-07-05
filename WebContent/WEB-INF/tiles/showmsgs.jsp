<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	    	
	    		<header id="hdrTitle">Messages:</header>
	    		
	    		<!-- ::::::::::::::TABLE WITH CONTENT:::::::::::::::::: -->
	    		<div id="msgsBlock">
	    			<table>
	    				<thead>
	    					<tr><th class="col1">Date</td><th class="col2">Username</td><th class="col3">Message</td></tr>
	    				</thead>
	    				<tbody id="msgContent"><c:forEach var="msg" items="${msgs}">
	    					<tr>
	    						<td><c:out value="${msg.date}"></c:out></td>
	    						<td><c:out value="${msg.getUsername()}"></c:out></td>
	    						<td><c:out value="${msg.msg}"></c:out></td>
	    					</tr>
	    				</c:forEach></tbody>
	    			</table>
	    		</div>