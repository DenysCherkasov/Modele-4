<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Statistics</title>
</head>
<body>
	<h1>Total Statistics</h1>
	<p>Total count of products: ${countProducts}</p>
	<p>Total count of broken microcircuits: ${brokenMicrocircuits}</p>
	<p>Total produced fuel: ${producedFuel}</p>
	<p>Total used fuel: ${usedFuel}</p>

	<form method="GET">
	<button type="submit" class="button">SHOW STATISTIC BY ID</button>
	<select name="id">
      <c:forEach items="${allId}" var="id" varStatus="allId">
        <option value="${id}"><a href="${pageContext.request.contextPath}/stats?id=${id}">${id}</a></option>
            ${id}
        </option>
      </c:forEach>
    </select>
</body>
</html>
