<%-- 
    Document   : orderManager
    Created on : Jun 11, 2024, 9:59:41 PM
    Author     : hungp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Manager</title>
        <%@include file="/includes/head.jsp" %>

    </head>
    <style>
    </style>
    <body>
        <%@include file="/includes/adminNavbar.jsp"%>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="table-wrapper">
                <h1 style="text-align: center">Order Manager</h1>
                <br>
                <div class="row justify-content-end">
                    <div class="col-md-12">
                        <form method="get" action="${pageContext.request.contextPath}/orderManager">
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
                <div class="col-sm-12">
                    <div class="mb-5">
                        <label>Sort by:</label>
                        <select class="form-select" onchange="location = this.value;">
                            <option value="<%=request.getContextPath()%>/orderManager?action=view">None</option>
                            <option value="<%=request.getContextPath()%>/orderManager?action=view&sortBy=OrderDate">Date</option>
                            <option value="<%=request.getContextPath()%>/orderManager?action=view&sortBy=Quantity">Quantity</option>
                            <option value="<%=request.getContextPath()%>/orderManager?action=view&sortBy=TotalMoney">Total</option>
                            <option value="<%= request.getParameter("sortBy") %>" <%=(request.getParameter("sortBy") == null) ? "" : "selected" %> hidden><%= request.getParameter("sortBy") %></option>
                        </select>
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
                <br>
                <table class="table table-hover table-bordered">
                    <c:if test="${requestScope.listOrder == null || requestScope.listOrder.size() == 0}">
                        <h1 style="text-align: center">List Empty</h1>
                    </c:if>
                    <c:if test="${requestScope.listOrder != null && requestScope.listOrder.size() > 0}">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Date</th>
                                <th scope="col">Name</th>
                                <th scope="col">Address</th>
                                <th scope="col">Phone Number</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                                <th scope="col">Success Status</th>
                                <th scope="col">View</th>
                                <th scope="col">Order Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listOrder}" var="i" varStatus="loop">
                                <tr class="fw-normal" >
                                    <td>${i.orderId}</td>
                                    <td>${i.orderDate}</td>
                                    <td>${i.name}</td>
                                    <td>${i.address}</td>
                                    <td>0${i.phone}</td>
                                    <td>${i.quantity}</td>
                                    <td>${i.getFormattedTotalMoney()}VNĐ</td>
                                    <td style="text-align: center" class="">
                                        <div class="dropdown">
                                            <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton${i.orderId}" data-bs-toggle="dropdown" aria-expanded="false" ${i.isSuccess == 'Succeed' ? 'disabled' : ''} ${i.isSuccess == 'Failed' ? 'disabled' : ''}>
                                                ${i.isSuccess}
                                            </button>
                                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton${i.orderId}">
                                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/orderManager?action=editStatus&type=successStatus&id=${i.orderHistoryId}&value=Failed&sortBy=${requestScope.sortBy == null ? 'none' : requestScope.sortBy}&startDateStr=${requestScope.startDateStr == null ? 'none' : requestScope.startDateStr}&endDateStr=${requestScope.endDateStr == null ? 'none' : requestScope.endDateStr}">Failed</a></li>
                                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/orderManager?action=editStatus&type=successStatus&id=${i.orderHistoryId}&value=Pending&sortBy=${requestScope.sortBy == null ? 'none' : requestScope.sortBy}&startDateStr=${requestScope.startDateStr == null ? 'none' : requestScope.startDateStr}&endDateStr=${requestScope.endDateStr == null ? 'none' : requestScope.endDateStr}">Pending</a></li>
                                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/orderManager?action=editStatus&type=successStatus&id=${i.orderHistoryId}&value=Succeed&sortBy=${requestScope.sortBy == null ? 'none' : requestScope.sortBy}&startDateStr=${requestScope.startDateStr == null ? 'none' : requestScope.startDateStr}&endDateStr=${requestScope.endDateStr == null ? 'none' : requestScope.endDateStr}">Succeed</a></li>
                                            </ul>
                                        </div>
                                    </td>

                                    <td>
                                        <a href="<%=request.getContextPath()%>/orderManager?action=viewDetail&id=${i.orderId}" 
                                           class="text-primary" title="View" data-toggle="tooltip">
                                            <button class="btn btn-light text-primary"><i class="fa-solid fa-eye"></i></button>
                                        </a>
                                    </td>
                                    <td style="text-align: center">
                                        <button class="btn ${i.orderHistoryStatus ? "btn-success" : "btn-danger"}">
                                            <a class="" style="text-decoration: none; color: white" href="<%=request.getContextPath()%>/orderManager?action=editStatus&type=orderStatus&id=${i.orderHistoryId}&sortBy=${requestScope.sortBy == null ? "none" : requestScope.sortBy }&startDateStr=${requestScope.startDateStr == null ? "none" : requestScope.startDateStr }&endDateStr=${requestScope.endDateStr == null ? "none" : requestScope.endDateStr }">${i.orderHistoryStatus ? "True" : "False"}
                                            </a>
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                        <tr class="border-0">
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0">TOTAL: ${requestScope.totalSale}VNĐ</th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                            <th scope="col" class="border-0"></th>
                        </tr>
                    </c:if>
                </table>
                <c:if test="${main != null}">
                    <div>
                        <p>Showing ${start} to ${end} of ${totalProducts} entries</p>
                    </div>
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="orderManager?pageSize=${pageSize}&index=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">«</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${num}" var="i">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="orderManager?pageSize=${pageSize}&index=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < num}">
                            <li class="page-item">
                                <a class="page-link" href="orderManager?pageSize=${pageSize}&index=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">»</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </c:if>
            </div>
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
    </body>
</html>
