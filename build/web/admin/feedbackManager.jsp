<%-- 
    Document   : feedbackManager
    Created on : Jul 3, 2024, 12:21:44 PM
    Author     : hungp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Manager</title>
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
            <h1 style="text-align: center">Feedback Manager</h1>
            <br>
            <div class="row justify-content-end">
                <div class="col-md-12">
                    <form method="post" action="${pageContext.request.contextPath}/adminFeedbackManager">
                        <div class="input-group rounded mb-5">
                            <div class="input-group-prepend">
                                <select class="form-select" id="searchCriteria" name="criteria">
                                    <option value="Username">Username</option>
                                    <option value="ProductName">Product Name</option>
                                    <option value="<%= request.getParameter("criteria") %>" <%=(request.getParameter("criteria") == null) ? "" : "selected" %> hidden><%= request.getParameter("criteria") %></option>
                                </select>
                            </div>
                            <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" id="searchInput" name="query"/>
                            <span class="input-group-text border-0 px-0 py-0" id="search-addon">
                                <button type="submit" style="background: none; border: none; padding: 0;">
                                    <i class="fas fa-search mx-5"></i>
                                </button>
                            </span>
                            <input type="hidden" name="action" value="searchKeyword"/>
                        </div>
                    </form>
                </div>
                <div class="col-md-12">
                    <form method="get" action="${pageContext.request.contextPath}/adminFeedbackManager">
                        <div class="input-group rounded mb-5">
                            <input type="date" class="form-control" placeholder="Start Date" id="startDate" name="startDate" required/>
                            <input type="date" class="form-control" placeholder="End Date" id="endDate" name="endDate" required/>
                            <span class="input-group-text border-0 px-0 py-0" id="search-addon">
                                <button type="submit" style="background: none; border: none; padding: 0;">
                                    <i class="fas fa-search mx-5"></i>
                                </button>
                            </span>
                            <input type="hidden" name="action" value="searchDate"/>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-5">
                        <label>Sort by:</label>
                        <select class="form-select" onchange="location = this.value;">
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view">None</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&sortBy=FeedbackDate">Date</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&sortBy=UserName">User Name</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&sortBy=ProductName">Product Name</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&sortBy=StarRate">Star</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&sortBy=FeedbackStatus">Status</option>
                            <option value="<%= request.getParameter("sortBy") %>" <%=(request.getParameter("sortBy") == null) ? "" : "selected" %> hidden><%= request.getParameter("sortBy") %></option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="mb-5">
                        <label>Filter by:</label>
                        <select class="form-select" onchange="location = this.value;">
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view">None</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=True">Status: True</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=False">Status: False</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=1">1 Star</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=2">2 Stars</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=3">3 Stars</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=4">4 Stars</option>
                            <option value="<%=request.getContextPath()%>/adminFeedbackManager?action=view&filterBy=5">5 Stars</option>
                            <option value="<%= request.getParameter("filterBy") %>" <%=(request.getParameter("filterBy") == null) ? "" : "selected" %> hidden><%= request.getParameter("filterBy") %></option>
                        </select>
                    </div>
                </div>
            </div>
            <c:if test="${main != null}">
                <form id="myform" action="adminFeedbackManager">
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
            <c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <table class="table table-hover table-bordered">
                <c:if test="${requestScope.listFeedback == null || requestScope.listFeedback.size() == 0}">
                    <h1 style="text-align: center">List Empty</h1>
                </c:if>
                <c:if test="${requestScope.listFeedback != null && requestScope.listFeedback.size() > 0}">
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Date</th> 
                            <th scope="col">Feedback ID</th>
                            <th scope="col">User Name</th> 
                            <th scope="col">Product</th> 
                            <th scope="col">Star</th>
                            <th scope="col">Image</th> 
                            <th scope="col">Status</th>
                            <th scope="col">View Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listFeedback}" var="i" varStatus="loop">
                            <tr class="fw-normal" >
                                <td>${loop.index +1}</td>
                                <td>${i.feedbackDate}</td>
                                <td>${i.feedbackId}</td>
                                <td>${i.userName}</td>
                                <td>${i.productName}</td>
                                <td style="font-size: 20px" class="stars-cell">${i.starRate}</td>
                                <td style="text-align: center">
                                    <c:choose>
                                        <c:when test="${i.feedbackImage != null}">
                                            <img src="${contextPath}/processImageFeedbackList?id=${i.feedbackId}"
                                                 class="avatar img-circle img-thumbnail" 
                                                 alt="feedback-img" id="previewImage" 
                                                 style="width: 6vw; height: 6vw; object-fit: cover;" >
                                        </c:when>
                                        <c:when test="${i.feedbackImage == null}">
                                            <strong>No Picture Provided</strong>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td style="text-align: center">
                                    <button class="btn ${i.feedbackStatus ? "btn-success" : "btn-danger"}">
                                        <a class="" style="text-decoration: none; color: white" href="<%=request.getContextPath()%>/adminFeedbackManager?action=editStatus&type=feedbackStatus&id=${i.orderId}&sortBy=${requestScope.sortBy == null ? "none" : requestScope.sortBy }&filterBy=${requestScope.filterBy == null ? "none" : requestScope.filterBy }&startDateStr=${requestScope.startDateStr == null ? "none" : requestScope.startDateStr }&endDateStr=${requestScope.endDateStr == null ? "none" : requestScope.endDateStr}&query=${requestScope.query == null ? "none" : requestScope.query}&criteria=${requestScope.criteria == null ? "none" : requestScope.criteria}">${i.feedbackStatus ? "True" : "False"}
                                        </a>
                                    </button>
                                </td>
                                <td style="text-align: center">
                                    <a href="<%=request.getContextPath()%>/adminFeedbackManager?action=viewDetail&id=${i.feedbackId}" 
                                       class="text-primary" title="View" data-toggle="tooltip">
                                        <button class="btn btn-light text-primary"><i class="fa-solid fa-eye"></i></button>
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
                            <a class="page-link" href="adminFeedbackManager?pageSize=${pageSize}&index=${currentPage - 1}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${num}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="adminFeedbackManager?pageSize=${pageSize}&index=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${currentPage < num}">
                        <li class="page-item">
                            <a class="page-link" href="adminFeedbackManager?pageSize=${pageSize}&index=${currentPage + 1}" aria-label="Next">
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
        <script>
            function generateStars(num) {
                return '★'.repeat(num);
            }

            document.addEventListener('DOMContentLoaded', () => {
                const starCells = document.querySelectorAll('.stars-cell');
                starCells.forEach(cell => {
                    const numStars = parseInt(cell.textContent, 10);
                    cell.textContent = generateStars(numStars);
                });
            });
        </script>
    </body>
</html>
