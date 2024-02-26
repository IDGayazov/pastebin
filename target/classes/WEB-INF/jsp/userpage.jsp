<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<style>
    <%@include file="/WEB-INF/css/userpage.css"%>
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pastebin</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <div class="main">
        <div class="left-side">
        <div class="username">
            <label class="username-label">${requestScope.username}'s pastebin</label>
        </div>
        
        <br><br>
        
        <div class="create-paste">
            <a href="/pastebinApp/create_paste">Create new paste</a>
        </div>
        
        <br><br>
        
        <div class="stats">
            <label class="stats-label">Your stats: </label>
            
            <br><br>
            
            <label>Number of pastes: 0</label>
            
            <br><br>
            
            <label>Number of public pastes: 0</label>
        </div>
        </div>
        
        <div class="right-side">
            <label style="font-size: 20px;">My pastes:</label>
            
            <br><br>
            
            <c:forEach var="paste" items="${pastes}">
            	<hr> <a href="/pastebinApp/paste/${paste.hash}" style="font-size: 18px;">${paste.name}</a>
            </c:forEach>
            <hr>
        </div>
    </div>
    
</body>
</html>