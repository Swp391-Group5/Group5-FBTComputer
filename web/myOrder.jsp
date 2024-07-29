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
        <title>My Order</title>
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
            <hr>
            <div class="row justify-content-end">
                <div class="col-md-12">
                    <form method="get" action="${pageContext.request.contextPath}/myOrder">
                        <div class="input-group rounded mb-5">
                            <input type="date" class="form-control" placeholder="Start Date" id="startDate" name="startDate" required/>
                            <input type="date" class="form-control" placeholder="End Date" id="endDate" name="endDate" required/>
                            <span class="input-group-text border-0 px-0 py-0" id="search-addon">
                                <button type="submit" style="background: none; border: none; padding: 0;">
                                    <i class="fas fa-search mx-5"></i>
                                </button>
                            </span>
                            <input type="hidden" name="action" value="searchView"/>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-5">
                        <label>Filter by:</label>
                        <select class="form-select" onchange="location = this.value;">
                            <option value="<%=request.getContextPath()%>/myOrder?action=view">None</option>
                            <option value="<%=request.getContextPath()%>/myOrder?action=view&filterBy=Failed">Status: Failed</option>
                            <option value="<%=request.getContextPath()%>/myOrder?action=view&filterBy=Pending">Status: Pending</option>
                            <option value="<%=request.getContextPath()%>/myOrder?action=view&filterBy=Succeed">Status: Succeed</option>
                            <option value="<%= request.getParameter("filter") %>" <%=(request.getParameter("filter") == null) ? "" : "selected" %> hidden><%= request.getParameter("filter") %></option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="mb-5">
                        <label>Sort by:</label>
                        <select class="form-select" onchange="location = this.value;">
                            <option value="<%=request.getContextPath()%>/myOrder?action=view">None</option>
                            <option value="<%=request.getContextPath()%>/myOrder?action=view&sortBy=OrderDate">Date</option>
                            <option value="<%=request.getContextPath()%>/myOrder?action=view&sortBy=Quantity">Quantity</option>
                            <option value="<%=request.getContextPath()%>/myOrder?action=view&sortBy=TotalMoney">Total</option>
                            <option value="<%= request.getParameter("sortBy") %>" <%=(request.getParameter("sortBy") == null) ? "" : "selected" %> hidden><%= request.getParameter("sortBy") %></option>
                        </select>
                    </div>
                </div>
            </div>
            <c:if test="${main != null}">
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
            </c:if>
            <table class="table table-hover">
                <c:if test="${requestScope.listOrder == null || requestScope.listOrder.size() == 0}">
                    <h1 style="text-align: center">List Empty</h1>
                </c:if>
                <c:if test="${requestScope.listOrder != null && requestScope.listOrder.size() > 0}">
                    <thead>
                        <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">Phone Number</th>
                            <th scope="col">Total Items</th>
                            <th scope="col">Total</th>
                            <th scope="col">Status</th>
                            <th scope="col">Payment Method</th>
                            <th scope="col">View</th>
                            <th scope="col">Leave Feedback</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listOrder}" var="i" varStatus="loop">
                            <tr class="fw-normal" >
                                <td>${i.orderDate}</td>
                                <td>${i.name}</td>
                                <td>${i.address}</td>
                                <td>0${i.phone}</td>
                                <td>${i.quantity}</td>
                                <td>${i.getFormattedTotalMoney()}VNĐ</td>
                                <td>${i.isSuccess}</td>
                                <td>${i.paymentMethod}</td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/myOrder?action=viewDetail&id=${i.orderId}" 
                                       class="text-primary" title="View" data-toggle="tooltip">
                                        <button class="btn btn-light text-primary"><i class="fa-solid fa-eye"></i></button>
                                    </a>
                                </td>
                                <td style="text-align: center">
                                    <a href="<%=request.getContextPath()%>/myOrder?action=leaveFeedback0&id=${i.orderId}" 
                                       class="text-primary" title="View" data-toggle="tooltip">
                                        <button class="btn btn-light text-primary"><i class="fa-solid fa-comment"></i></button>
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${main != null}">
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
            </c:if>
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
