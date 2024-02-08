<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <label class="title-label">New paste</label>
        <br>
        <br>
        <form id="text" action="/pastebinApp/start" method="POST">
        <textarea class="text" wrap="soft" name="text" id="text" onclick="setCursorPosition()"></textarea>
        <br>
        <label class="title-label">Optional paste settings</label>
        <hr>
        <div class="optional-settings-container">
            <div class="input-params">
                <label class="title-label">Category</label>
                <input autocomplete="off" name="category" id="cat">
            </div>
            <br>
            <br>
            <div class="input-params">
                <label class="title-label">Name</label>
                <input autocomplete="off" name="name" id="name" required>
            </div>
            <br>
            <br>
            <div class="input-params">
                <label class="title-label">Paste expiration</label>
                <select name="expiration" required>
                    <option value="10 minutes">10 minutes</option>
                    <option value="day">1 Day</option>
                    <option value="week">1 Week</option>
                </select>
            </div>
        </div>
        <br>
        <br>
        <button style="width: 15%; background-color: green; color: white; font-weight: 900; font-size: 17px; height: 4%; border-radius: 5px;" type="submit"> + create new paste</button>
        </form>
    </div>
</body>
</html>