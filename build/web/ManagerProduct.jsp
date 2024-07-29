<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manager Product</title>

        <link rel="icon" href="images/quanly.png" type="images/x-icon"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-vpZl2lJD5zzOykKkLrBbEPv9Wp0yqDgqQ5m9vJkzQJqJpzz/3ZvVoKHyN1p+qLiX" crossorigin="anonymous">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>

        <style>
            body {
                font-family: 'Varela Round', sans-serif;
                background-color: #f5f5f5;
            }
            .table-wrapper {
                background: #fff;
                padding: 50px 10px;
                margin: 40px auto;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
                max-width: 100%;
            }
            .table-title {
                padding-bottom: 15px;
                background: #ccc;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #e9e9e9;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #e9e9e9;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child {
                width: 100px;
            }
            table.table td a {
                color: #a0a5b1;
                display: inline-block;
                margin: 0 5px;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #E34724;
            }
            table.table td i {
                font-size: 19px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                color: #999;
                margin: 0 2px;
                min-width: 30px;
                min-height: 30px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            .img {
                width: 60px;
                height: 60px;
                object-fit: cover;
            }
            .bg-pink {
                background-color: #a0a5b1; /* Define pink color */
            }
            .status.on {
                color: green;
                font-weight: bold;
            }

            .status.off {
                color: red;
                font-weight: bold;
            }
            .form-inline #page-size-select {
                margin-left: 10px; /* Adjust margin as needed */
                border: 1px solid #ccc; /* Example border style */
                padding: 5px; /* Example padding */
            }

            .form-inline #page-size-select:focus {
                outline: none; /* Remove focus outline if needed */
                border-color: #66afe9; /* Example focus border color */
            }

        </style>
    </head>
    <body>
        <jsp:include page="includes/adminNavbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>
            </br>
            </br>
            </br>

            <div class="container">
                <div class="table-wrapper">
                    <div class="table-title bg-white text-center">
                        <div class="row">
                            <div class="col-12 text-black">
                                <h1>Manage Product</h1>
                            </div>
                            <div class="col-md-12">
                                <a href="add" class="btn btn-primary mb-2" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            </div>

                            <section class="mb-4">
                                <div class="d-flex justify-content-between align-items-center mt-0">
                                    <form action="searchmanage" method="post" class="d-flex">
                                        <div class="input-group">
                                            <input name="productName" type="text" value="${pi}" class="form-control" aria-label="Search" placeholder="Search...">
                                        <button type="submit" class="btn btn-secondary">
                                            <i class="fa fa-search"></i>
                                        </button>                
                                    </div>
                                </form>
                                <div class="text-black row mb-3">
                                    <form id="myform" action="manager-product" class="form-inline">
                                        <label for="page-size-select" class="ps-3">Products per page:</label>
                                        <select name="pageSize" id="page-size-select" class="form-control" onchange="document.getElementById('myform').submit()">
                                            <option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
                                            <option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
                                            <option value="15" <c:if test="${pageSize == 15}">selected</c:if>>15</option>
                                            <option value="20" <c:if test="${pageSize == 20}">selected</c:if>>20</option>
                                            </select>
                                            <input type="hidden" name="index" value="1" />
                                            <input type="hidden" name="productName" value="${pi}" />
                                        <noscript>
                                        <button type="submit" class="btn btn-primary">Go</button>
                                        </noscript>
                                    </form>

                                </div>    
                            </div>
                        </section>
                        <c:if test="${not empty message}">
                            <div class="alert alert-danger" role="alert">
                                ${message}
                            </div>
                        </c:if>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Brand</th>
                            <th>Image</th>
                            <th>Status</th>
                            <th>Created By</th>
                            <th>Created Date</th>
                            <th>Modified By</th>
                            <th>Modified Date</th>
                            <th>Category</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listP}" var="o">
                            <tr>
                                <td>${o.productId}</td>
                                <td>${o.productName}</td>
                                <td><fmt:formatNumber value="${o.productPrice}" type="currency" currencySymbol="VND"/></td>   

                                <td>${o.productBrand}</td>
                                <td><img class="img" src="UPLOAD_IMAGE1/${o.productImage}" alt="${o.productName}"></td>
                                <td>${o.productStatus ? 'Active' : 'Inactive'}</td>
                                <td>${o.createBy}</td>
                                <td>${o.createDate}</td>
                                <td>${o.modifyBy}</td>
                                <td>${o.modifyDate}</td>
                                <td>${o.category.categoryName}</td>
                                <td>
                                    <a href="toggleProductStatus?pid=${o.productId}&status=${o.productStatus}" class="edit">
                                        <button class="btn ${o.productStatus ? 'btn-success' : 'btn-danger'}">${o.productStatus ? 'Show' : 'Hide'}</button>
                                    </a>
                                    <a href="editproduct?pid=${o.productId}" class="edit">
                                        <button class="btn edit">Edit</button>
                                    </a>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <!--<a href="home" class="btn btn-primary mt-3">Back to home</a>-->
        </div>


    </body>
</html>