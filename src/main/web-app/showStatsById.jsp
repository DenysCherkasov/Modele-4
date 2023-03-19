<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Statistic of product</title>
</head>
<body>
<section>
    <h1>Statistic of product</h3>
    <p>Date of production: ${product.dateCreation}</p>
    <p>Term of production: ${product.productionTime}</p>
    <p>Count of broken microcircuits: ${product.brokenMicrocircuits}</p>
    <p>Produced fuel: ${product.producedFuel}</p>
    <p>Produced fuel: ${product.usedFuel}</p>

    </form>
    <form method="GET" action="index.html">
    <button type="submit" class="button">BACK TO MAIN PAGE</button>
    </form>
</section>
</body>
</html>
