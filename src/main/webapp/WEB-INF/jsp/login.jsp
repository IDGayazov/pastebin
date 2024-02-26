<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <%@include file="/WEB-INF/css/reg.css"%>
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<div class="header">
        <p class="header-text">PASTEBIN</p>
        <div class="header-right">
            <a href="/pastebinApp/registration" type="button" class="button">SIGN UP</a>
        </div>
    </div>

    <div class="main">
        <div class="entering-info">
            <label class="reg-label">Авторизация</label>
            <form id="enter" action="/pastebinApp/login" method="POST">
                <div class="user-info">
                	<c:if test="${param.error != null}">
                		<label style="color:red;">Login or password is incorrect</label><br>
                	</c:if>
                    <input class="user-info-input" name="login" placeholder="Login">
                        <br>
                      <c:if test="${not empty requestScope.passwordError}">
                		<label style="color:red;">${passwordError}</label><br>
                	</c:if>
                    <input class="user-info-input" name="password" type="password" placeholder="Password">
                </div>
                <button class="reg-button" type="submit">Войти</button>
            </form>
        </div>
    </div>
</body>
</html>