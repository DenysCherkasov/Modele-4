<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Created product</title>
</head>
<body>
<section>
    <h1>Created product</h3>
    	 <p>ID: ${product.id}</p>
    	 <p>Date of production: ${product.dateCreation}</p>
    	 <p>Term of production: ${product.productionTime}</p>
    	 <p>Count of broken microcircuits: ${product.brokenMicrocircuits}</p>
    	 <p>Produced fuel: ${product.producedFuel}</p>
    	 <p>Used fuel: ${product.usedFuel}</p>
    	 </form>
         <form method="post">
            <button type="submit">CREATE NEW PRODUCT AGAIN</button>
         </form>
    	 </form>
         <form method="GET" action="index.html">
         <button type="submit" class="button">BACK TO MAIN PAGE</button>
         </form>
</section>
</body>
</html>
