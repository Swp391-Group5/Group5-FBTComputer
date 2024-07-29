<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Find Warranty</title>
    <%@include file="includes/head.jsp" %>
    <link rel="stylesheet" href="css/main.css"/>
    <style>
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        .container form {
            width: 500px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }
    </style>
</head>
<body>
    <div class="container">
        <h3 class="text-center">Find Warranty</h3>
        <c:if test="${not empty error}">
            <span style="color: red;">${error}</span>
        </c:if>
        <hr>
        <form action="CheckWarranty" method="post">
            <div class="form-group">
                <label for="orderHistoryDetailId">Order History Detail ID:</label>
                <input type="number" id="orderHistoryDetailId" name="orderHistoryDetailId" class="form-control">   
            </div>
            <div class="form-group">
                <label for="serialnumber">Serial Number:</label>
                <input type="text" id="serialnumber" name="serialnumber" class="form-control">   
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
