<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<style>
    <%@include file="/WEB-INF/css/styles.css"%>
    <%@include file="/WEB-INF/js/text.js"%>
</style>

<html>
<body>
 	<jsp:include page="header.jsp"/>
 	
    <div class="main">
        <div class="paste-info">
        	<c:choose>
        		<c:when test="${typeError eq '1'}">
            		<label class="title-label">This paste is no longer available</label>
        		</c:when>
        		<c:when test="${typeError eq '2'}">
        			<label class="title-label">This paste is not exist</label>
        		</c:when>
        		<c:when test="${typeError eq '3'}">
        			<label class="title-label">This paste has private access</label>
        		</c:when>
        	</c:choose>
        </div>
    </div>
</body>
</html>