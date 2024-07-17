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
        <%@include file="/includes/adminNavbar.jsp"%>
        <br/>
        <div class="displayCenter"></div>
        <h1 style="text-align: center">User Manager</h1>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper"> 
                    <div class="row justify-content-end">
                        <div class="col-md-12">
                            <form method="post" action="${pageContext.request.contextPath}/userManager">
                                <div class="input-group rounded mb-5">
                                    <div class="input-group-prepend">
                                        <select class="form-select" id="searchCriteria" name="criteria">
                                            <option value="Username">Username</option>
                                            <option value="Email">Email</option>
                                            <option value="Phone Number">Phone Number</option>
                                            <option value="<%= request.getParameter("criteria") %>" <%=(request.getParameter("criteria") == null) ? "" : "selected" %> hidden><%= request.getParameter("criteria") %></option>
                                        </select>
                                    </div>
                                    <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" id="searchInput" name="query"/>
                                    <span class="input-group-text border-0 px-0 py-0" id="search-addon">
                                        <button type="submit" style="background: none; border: none; padding: 0;">
                                            <i class="fas fa-search mx-5"></i>
                                        </button>
                                    </span>
                                    <input type="hidden" name="action" value="search"/>
                                </div>
                            </form>
                        </div>
                    </div>


                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-9"></div>
                            <div class="col-sm-3">

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="mb-5">
                                <label>Filter by:</label>
                                <select class="form-select" onchange="location = this.value;">
                                    <option value="<%=request.getContextPath()%>/userManager">None</option>
                                    <c:forEach items="${requestScope.listOfFilter}" var="i">
                                        <option value="<%=request.getContextPath()%>/userManager?filter=${i}">${i}</option>
                                    </c:forEach>
                                    <option value="<%= request.getParameter("filter") %>" <%=(request.getParameter("filter") == null) ? "" : "selected" %> hidden><%= request.getParameter("filter") %></option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="mb-5">
                                <label>Sort by:</label>
                                <select class="form-select" onchange="location = this.value;">
                                    <option value="<%=request.getContextPath()%>/userManager">None</option>
                                    <c:forEach items="${requestScope.listOfSortBy}" var="i">
                                        <option value="<%=request.getContextPath()%>/userManager?sortBy=${i}">${i}</option>
                                    </c:forEach>
                                    <option value="<%= request.getParameter("sortBy") %>" <%=(request.getParameter("sortBy") == null) ? "" : "selected" %> hidden><%= request.getParameter("sortBy") %></option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row flex-row-reverse">
                        <div class="col-sm-1">
                            <div  >
                                <a href="<%=request.getContextPath()%>/userManager?action=add0"><button type="button" class="btn btn-primary mb-3">Add new user</button></a>
                            </div>
                        </div>
                    </div>
                    <c:set var="loggedInAdminId" value="${sessionScope.loggedInAdminId}" />
                    <c:if test="${main != null}">
                        <form id="myform" action="userManager">
                            <div class="row d-flex align-items-center">
                                <label for="page-size-select" class="ps-3"><strong>Products per page:</strong></label>
                                <div class="col-md-1 mb-3">
                                    <select class="form-select" name="pageSize" id="page-size-select" onchange="document.getElementById('myform').submit()">>
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
                    </c:if>
                    <table class="table table-striped table-hover table-bordered">
                        <c:if test="${requestScope.listOfACR == null || requestScope.listOfACR.size() == 0}">
                            <h1 style="text-align: center">List Empty</h1>
                        </c:if>
                        <c:if test="${requestScope.listOfACR != null && requestScope.listOfACR.size() > 0}">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ID</th>
                                    <th>Avatar</th>
                                    <th>Name </th>
                                    <th>Gender </th>
                                    <th>Phone Number</th>
                                    <th>Email</th>
                                    <th>Position</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listOfACR}" var="i" varStatus="loop">
                                    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
                                    <c:choose>
                                        <c:when test="${i.avatar != null}">
                                            <c:set var="imageUrl" value="${contextPath}/processImageUserList?id=${i.id}&roleId=${i.roleId}" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="imageUrl" value="${contextPath}/assets/images/avatarMain.jpg" />
                                        </c:otherwise>
                                    </c:choose>
                                    <tr>
                                        <td>${loop.index +1}</td>
                                        <td>${i.id}</td>
                                        <td style="text-align: center">
                                            <img src="${imageUrl}"
                                                 class="avatar img-circle img-thumbnail" 
                                                 alt="avatar" id="previewImage" 
                                                 style="width: 6vw; height: 6vw; object-fit: cover;" >
                                        </td>
                                        <td>${i.name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${i.gender}">
                                                    Female
                                                </c:when>
                                                <c:otherwise>
                                                    Male
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${i.phoneNumber}</td>
                                        <td>${i.email}</td>
                                        <td>${i.roleName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${i.status}">
                                                    Active
                                                </c:when>
                                                <c:otherwise>
                                                    Inactive
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="text-align: center">
                                            <c:choose>
                                                <c:when test="${i.roleName == 'Admin' && i.id == loggedInAdminId}">
                                                    Cannot take action
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="<%=request.getContextPath()%>/userManager?action=update0&id=${i.id}&roleId=${i.roleId}" 
                                                       class="text-primary " title="Edit" data-toggle="tooltip">
                                                        <button class="btn btn-light text-primary"><i class="fa-solid fa-pen"></i></button>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                    <c:if test="${main != null}">
                        <div>
                            <p>Showing ${start} to ${end} of ${totalProducts} entries</p>
                        </div>
                        <ul class="pagination">
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="userManager?pageSize=${pageSize}&index=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">«</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${num}" var="i">
                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                    <a class="page-link" href="userManager?pageSize=${pageSize}&index=${i}">${i}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${currentPage < num}">
                                <li class="page-item">
                                    <a class="page-link" href="userManager?pageSize=${pageSize}&index=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </c:if>
                </div>  
            </div> 
        </div>
        <script>
        </script>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
