<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    <%@include file="/WEB-INF/css/header.css"%>
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="header">
        <p class="header-text">PASTEBIN</p>
        <div class="header-right">
        		<c:choose>
        			<c:when test="${requestScope.username eq 'Anonymous'}">
        				<a href="/pastebinApp/login" type="button" class="button-info">LOGIN</a>
                		<div class="spacer"></div>
                		<a href="/pastebinApp/registration" type="button" class="button-info">SIGNUP</a>
        			</c:when>
        			<c:when test="${requestScope.username ne 'Anonymous'}">
        				<div class="dropdown">
	        				<span style="color: white;">${requestScope.username}</span>
	                  		<div class="dropdown-content">
	                  			<a href="/pastebinApp/user">My pastebin</a>
	                    		<a href="/pastebinApp/comments">My comments</a>
	                  		</div>
                  		</div>
                  		<div class="spacer"></div>
				        <form action="/pastebinApp/logout" method="POST">
				        	<input class="button-info" type="submit" value="logout"/>
				        </form>
                  	</c:when>
        		</c:choose>
        </div>
    </div>
</body>
</html>