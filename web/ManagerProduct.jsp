<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                font-family: 'Varela Round', sans-serif;
                background-color: #f5f5f5;
            }
            .table-wrapper {
                background: #fff;
                padding: 50px 70px;
                margin: 40px auto;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
                max-width: 100%;
            }
            .table-title {
                padding-bottom: 15px;
                background: #435d7d;
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
                color: #fff;
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
                background: #f5f5f5;
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
            img {
                width: 100px;
                height: 120px;
                object-fit: cover;
            }
            .bg-pink {
                background-color: #FFCCFF !important; /* Define pink color */
            }
            .status.on {
                color: green;
                font-weight: bold;
            }

            .status.off {
                color: red;
                font-weight: bold;
            }

        </style>
    </head>
    <body>
        <%--<jsp:include page="includes/navbar.jsp"></jsp:include>
            <jsp:include page="includes/head.jsp"></jsp:include>--%>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title bg-primary text-center">
                    <div class="row">
                        <div class="col-12">
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-md-12">
                            <a href="AddProduct.jsp" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                        </div>

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
                            <th>Status</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listP}" var="o">
                            <tr>
                                
                                <td><a href="edit-product?pid=${o.productId}">
                                    ${o.productId}
                                    </a></td>
                                <td>${o.productName}</td>
                                <td>${o.productPrice}</td>

                                <td>${o.productBrand}</td>
                                <td><img src="${o.productImage}" alt="${o.productName}"></td>
                                <td>${o.productStatus ? 'Active' : 'Inactive'}</td>
                                <td>${o.createBy}</td>
                                <td>${o.createDate}</td>
                                <td>${o.modifyBy}</td>
                                <td>${o.modifyDate}</td>
                                <td>${o.category.categoryName}</td>
                                <td>
                                    <a href="toggleProductStatus?pid=${o.productId}&status=${o.productStatus}"  class="edit" data-toggle="modal">
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                        <button class="toggle-status btn ${o.productStatus ? 'btn-success' : 'btn-danger'}" 
                                                >
                                            ${o.productStatus ? 'On' : 'Off'}
                                        </button>
                                    </a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:set var="page" value="${requestScope.page}"/>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li class="page-item"><a href="manager-product?page=${page-1}" class="page-link">Previous</a></li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.num}" var="i">
                            <li class="page-item ${page == i ? 'active' : ''}"><a href="manager-product?page=${i}" class="page-link">${i}</a></li>
                            </c:forEach>
                            <c:if test="${page < requestScope.num}">
                            <li class="page-item"><a href="manager-product?page=${page+1}" class="page-link">Next</a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
            <a href="home" class="btn btn-primary mt-3">Back to home</a>
        </div>
        <!--         Edit Modal HTML 
                <div id="addProductModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="add" method="post">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Add Product</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input name="productName" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                        <textarea name="productDescription" class="form-control" required></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Price</label>
                                        <input name="productPrice" type="number" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Quantity</label>
                                        <input name="productQuantity" type="number" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Brand</label>
                                        <input name="productBrand" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Image</label>
                                        <input name="productImage" type="file" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Category</label>
                                        <select name="category" class="form-control">
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
</div>
<div class="form-group">
    <label>Specification</label>
    <textarea name="specification" class="form-control" required></textarea>
</div>
</div>
<div class="modal-footer">
<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
<input type="submit" class="btn btn-success" value="Add">
</div>
</form>
</div>
</div>
</div>-->
        <script>
            function toggleStatus(productId, currentStatus) {
                var newStatus = !currentStatus;
                fetch('toggleProductStatus', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({productId: productId, productStatus: newStatus})
                })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                location.reload(); // Reload the page to update the status
                            } else {
                                alert('Failed to update status');
                            }
                        })
                        .catch(error => console.error('Error:', error));
            }
        </script>
    </body>
</html>