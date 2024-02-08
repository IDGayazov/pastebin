<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<style>
    <%@include file="/WEB-INF/css/styles.css"%>
    <%@include file="/WEB-INF/js/text.js"%>
</style>

<html>
<head>
  <meta charset="utf-8">
  <title>Pastebin</title>
</head>
<body>
    <div class="header">
        <p class="header-text">PASTEBIN</p>
        <div class="header-right">
                <button type="button" class="button">LOGIN</button>
                <div class="spacer"></div>
                <button type="button" class="button">SIGN UP</button>
        </div>
    </div>

    <div class="main">
        <div class="paste-info">
            <label class="title-label">${requestScope.username}</label>
            <label class="title-info">Create date ${requestScope.createDate}</label>
        </div>
        <br>
        <br>
        <textarea class="text" wrap="soft" name="text" id="text" onclick="setCursorPosition()" readonly="true">${requestScope.text}</textarea>
    </div>
</body>
</html>