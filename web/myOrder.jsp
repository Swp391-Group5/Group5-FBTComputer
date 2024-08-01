<%-- 
    Document   : myOrder
    Created on : Jun 5, 2024, 9:20:10 PM
    Author     : hungp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="includes/head.jsp" %>

    </head>
    <body>
        <%@include file="includes/navbar.jsp"%>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="home">Home</a></li>
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="profile">My Account</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><strong>My Order</strong></li>
                </ol>
            </nav>
            <form id="myform" action="myOrder">
                <div class="row d-flex align-items-center">
                    <label for="page-size-select" class="ps-3"><strong>Products per page:</strong></label>
                    <div class="col-md-1">
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
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Date</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">View</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:if test="${requestScope.listOrder != null}">
                        <c:forEach items="${requestScope.listOrder}" var="i" varStatus="loop">
                            <tr class="fw-normal" >
                                <td>${i.orderId}</td>
                                <td>${i.orderDate}</td>
                                <td>${i.quantity}</td>
                                <td>${i.getFormattedTotalMoney()}</td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/myOrder?action=viewDetail&id=${i.orderId}" 
                                       class="text-primary" title="View" data-toggle="tooltip">
                                        <button class="btn btn-light text-primary"><i class="fa-solid fa-eye"></i></button>
                                    </a>
                                </td>

                            </tr>
                        </tbody>
                    </c:forEach>
                </c:if>
            </table>
            <div>
                <p>Showing ${start} to ${end} of ${totalProducts} entries</p>
            </div>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="myOrder?pageSize=${pageSize}&index=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${num}" var="i">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="myOrder?pageSize=${pageSize}&index=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < num}">
                    <li class="page-item">
                        <a class="page-link" href="myOrder?pageSize=${pageSize}&index=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">»</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <%@include file="includes/footer.jsp"%>
        <%@include file="includes/finish.jsp"%>
    </body>
</html>
