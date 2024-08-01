<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Manager</title>
        <%@include file="/includes/head.jsp" %>


        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <style>
            .table,td,th{
                border: 1px solid black;
            }
            .body {
                color: #566787;
                background: #f5f5f5;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
                min-width: 100%;
            }
            .table-title h2 {
                margin: 8px 0 0;
                font-size: 22px;
            }
            .search-box {
                position: absolute;
                float: right;
            }
            .search-box input {
                height: 34px;
                border-radius: 20px;
                padding-left: 35px;
                border-color: #ddd;
                box-shadow: none;
            }
            .search-box input:focus {
                border-color: #3FBAE4;
            }

            .table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            .table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            .table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            .table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            .table.table td:last-child {
                width: 130px;
            }
            .table.table td a {
                color: #a0a5b1;
                display: inline-block;
                margin: 0 5px;
            }
            .table.table td a.view {
                color: #03A9F4;
            }
            .table.table td a.edit {
            }
            .table.table td a.delete {
                color: #E34724;
            }
            .table.table td i {
                font-size: 19px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 95%;
                width: 30px;
                height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 30px !important;
                text-align: center;
                padding: 0;
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
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 6px;
                font-size: 95%;
            }
            .displayCenter{
                margin-top: 120px;
                display: flex;
                justify-content: center;
            }

        </style>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    </head>
    <%--<%@ include file="/includes/sidebar.jsp" %>--%>
    <body>
        <%--<%@include file="headerMenuAdmin.jsp" %>--%>
        <jsp:include page="includes/adminNavbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>
            <br/>
            <div class="displayCenter"></div>
            <h1 style="text-align: center">Manager Warranty</h1>
            <div class="container-fluid">
                <div class="table-responsive">
                    <div class="table-wrapper"> 
                        <form id="myform" action="ManageWarranty">
                            <div class="row d-flex align-items-center">
                                <label for="page-size-select" class="ps-3"><strong>Warranty per page:</strong></label>
                                <div class="col-md-1 mb-3">
                                    <select class="form-select" name="pageSize" id="page-size-select" onchange="document.getElementById('myform').submit()">
                                        <option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
                                    <option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
                                    <option value="15" <c:if test="${pageSize == 15}">selected</c:if>>15</option>
                                    <option value="20" <c:if test="${pageSize == 20}">selected</c:if>>20</option>
                                    </select>
                                    <noscript>
                                    <button type="submit"  value="Go" >
                                        Go
                                    </button>
                                    </noscript>
                                </div>
                            </div>
                        </form>

                        <table class="table table-striped table-hover table-bordered">
                        <c:if test="${requestScope.listW == null || requestScope.listW.size() == 0}">
                            <h1 style="text-align: center">List Empty</h1>
                        </c:if>
                        <c:if test="${requestScope.listW != null && requestScope.listW.size() > 0}">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>WarrantyID</th>
                                    <th>SerialNumber</th>
                                    <th>UserName</th>
                                    <th>PhoneNumber</th>
                                    <th>Email</th>
                                    <th>ProductName </th>
                                    <th>WarrantyIMG </th>
                                    <th>WarrantyPeriod</th>
                                    <th>CauseError</th>
                                    <th>AdminName</th>
                                    <th>AdminID</th>
                                    <th>isSucsess</th>
                                    <th>ExportWarranty<th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listW}" var="i" varStatus="loop">
                                    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

                                    <tr>
                                        <td>${loop.index +1}</td>


                                        <td>${i.warrantyId}</td>
                                        <td>${i.serialNumber}</td>
                                        <td>${i.userName}</td>
                                        <td>${i.phoneNumber}</td>
                                        <td>${i.email}</td>
                                        <td>${i.productName}</td>
                                        <td><img style="width: 100%" src="UPLOAD_IMAGE2/${i.warrantyImage}" alt="${i.productName}"></td>

                                        <td>${i.warrantyPeriod}</td>
                                        <td>${i.causeError}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${i.adminName == null}">
                                                    Not Assigned
                                                </c:when>
                                                <c:otherwise>
                                                    ${i.adminName}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${i.adminID == 0}">
                                                    Not Assigned
                                                </c:when>
                                                <c:otherwise>
                                                    ${i.adminID}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="text-align: center" class="">
                                            <div class="dropdown">
                                                <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton${i.warrantyId}" data-bs-toggle="dropdown" aria-expanded="false">
                                                    ${i.isSucsess}
                                                </button>
                                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton${i.warrantyId}">
                                                    <li><a onclick="alert('Change status successful')" class="dropdown-item" href="${pageContext.request.contextPath}/ManageWarranty?action=editStatus&type=successStatus&id=${i.warrantyId}&value=Pass">Pass</a></li>
                                                    <li><a onclick="alert('Change status successful')" class="dropdown-item" href="${pageContext.request.contextPath}/ManageWarranty?action=editStatus&type=successStatus&id=${i.warrantyId}&value=Fail">Fail</a></li>
                                                    <li><a onclick="alert('Change status successful')" class="dropdown-item" href="${pageContext.request.contextPath}/ManageWarranty?action=editStatus&type=successStatus&id=${i.warrantyId}&value=Wait">Wait</a></li>
                                                </ul>
                                            </div>
                                        </td>

                                        <td style="text-align: center" class="">
                                            <button class="btn btn-light" type="button">
                                                <a href="<%=request.getContextPath()%>/WarrantyPDFServlet?warrantyId=${i.warrantyId}" target="_blank">Export to PDF</a>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>

                </div>  
            </div> 
        </div>
        <script>
        </script>
        
    </body>
</html>


