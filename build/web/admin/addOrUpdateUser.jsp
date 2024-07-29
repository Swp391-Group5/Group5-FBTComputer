<%-- 
    Document   : addOrUpdateEmployee
    Created on : Jun 19, 2024, 4:46:13 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/includes/head.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            .form-add {
                width: 100%;
                margin: 50px auto;
                font-size: 15px;
            }
            .form-add form {
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .form-add h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {
                font-size: 15px;
                font-weight: bolder;
            }
            .form-control{
                width: 120%;
                margin-right: 5px;
            }
            .displayCenter{
                margin-top: 60px;
                display: flex;
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <%@include file="/includes/adminNavbar.jsp"%>
        <br/>
        <div class="displayCenter"></div>
        <c:if test="${requestScope.action == 'add'}">
            <c:set var="acrError" value="${requestScope.acrError}"/>
            <div class="form-add">
                <h1 class="text-center">Add New User</h1>
                <div class="container mt-5">
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <form action="<%=request.getContextPath()%>/sendPasswordEmail" method="post">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <% if (request.getAttribute("errorEmailExist") != null) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= request.getAttribute("errorEmailExist") %>
                                    </div>
                                    <% } %>
                                    <input type="email" class="form-control" id="email" name="email" value="${acrError.email}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="accountName" class="form-label">Account Name:</label>
                                    <input type="text" class="form-control" id="accountName" name="accountName" value="${acrError.name}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="gender" class="form-label">Status:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="male" value="Male"
                                               <c:if test="${acrError.gender != null && acrError.gender == 'false'}">checked</c:if> required>
                                               <label class="form-check-label" for="male">Male</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="female" value="Female"
                                            <c:if test="${acrError.gender != null && acrError.gender == 'true'}">checked</c:if> required>
                                            <label class="form-check-label" for="female">Female</label>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="phone" class="form-label">Phone:</label>
                                    <% if (request.getAttribute("errorMessagePhone") != null) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= request.getAttribute("errorMessagePhone") %>
                                    </div>
                                    <% } %>
                                    <input type="number" class="form-control" id="phone" name="phone" value="${acrError.phoneNumber }" required>
                                </div>
                                <div class="mb-3">
                                    <label for="dateOfBirth" class="form-label">Date of Birth:</label>
                                    <% if (request.getAttribute("errorMessageDateOfBirth") != null) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= request.getAttribute("errorMessageDateOfBirth") %>
                                    </div>
                                    <% } %>
                                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${acrError.dob}" required >
                                </div>
                                <div class="mb-3">
                                    <label for="city" class="form-label">City:</label>
                                    <div >
                                        <select class="form-control input_type" name="city" required>
                                            <c:forEach items="${requestScope.listOfCity}" var="city">
                                                <option value="${city.displayName}">${city.displayName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address:</label>
                                    <input type="text" class="form-control" id="address" name="address" value="${acrError.address}" required >
                                </div>
                                <div class="mb-3">
                                    <label for="role" class="form-label">Role:</label>
                                    <select id="role" name="role" class="form-select" required>
                                        <c:forEach items="${requestScope.listOfRoles}" var="role">
                                            <option value="${role.roleId}">${role.name()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="status" class="form-label">Status:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="active" value="1"
                                               <c:if test="${acrError.gender != null && acrError.gender == 'true'}">checked</c:if> required>
                                               <label class="form-check-label" for="active">Active</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="status" id="inactive" value="0" 
                                            <c:if test="${acrError.gender != null && acrError.gender == 'false'}">checked</c:if> required >
                                            <label class="form-check-label" for="inactive">Inactive</label>
                                        </div>
                                    </div>
                                    <!--<input type="hidden" value="${user.id}" name="id">-->
                                <div class="mb-5 text-center">
                                    <input type="submit" class="btn btn-primary" value="Add User">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.action == 'update' and requestScope.acrUser != null}">
            <c:set var="user" value="${requestScope.acrUser}"/>
            <c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <c:choose>
                <c:when test="${user.avatar != null}">
                    <c:set var="imageUrl" value="${contextPath}/processImageUserList?id=${user.id}&roleId=${user.roleId}" />
                </c:when>
                <c:otherwise>
                    <c:set var="imageUrl" value="${contextPath}/assets/images/avatarMain.jpg" />
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${user.gender}">
                    <c:set var="gender" value="Female" />
                </c:when>
                <c:otherwise>
                    <c:set var="gender" value="Male" />
                </c:otherwise>
            </c:choose>
            <div class="form-add">
                <h1 class="text-center">Update User</h1>
                <div class="container mt-5">
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <form action="<%=request.getContextPath()%>/userManager?action=update" method="POST">
                                <div class="mb-3" style="text-align: center">
                                    <img src="${imageUrl}"
                                         class="avatar img-circle img-thumbnail" 
                                         alt="avatar" id="previewImage" 
                                         style="width: 16vw; height: 16vw; object-fit: cover;" >
                                </div>
                                <div class="mb-3">
                                    <label for="accountId" class="form-label">Account ID:</label>
                                    <input type="text" class="form-control" id="accountId" name="accountId" value="${user.id}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="accountName" class="form-label">Account Name:</label>
                                    <input type="text" class="form-control" id="accountName" name="accountName" value="${user.name}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="gender" class="form-label">Gender:</label>
                                    <input type="text" class="form-control" id="gender" name="gender" value="${gender}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="phone" class="form-label">Phone:</label>
                                    <input type="text" class="form-control" id="phone" name="phone" value="${user.phoneNumber}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="dateOfBirth" class="form-label">Date of Birth:</label>
                                    <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${user.dob}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="city" class="form-label">City:</label>
                                    <input type="text" class="form-control" id="city" name="city" value="${user.city}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address:</label>
                                    <input type="text" class="form-control" id="address" name="address" value="${user.address}" disabled>
                                </div>
                                <div class="mb-3">
                                    <label for="role" class="form-label">Role:</label>
                                    <select id="role" name="role" class="form-select">
                                        <c:forEach items="${requestScope.listOfRoles}" var="role">
                                            <option value="${role.roleId}" ${user.roleId == role.roleId ? "selected" : ""}>${role.name()}</option>
                                        </c:forEach>
                                        <option value="${user.roleId}" ${user.roleId == null ? "" : "selected"} hidden>${user.roleName}</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="status" class="form-label">Status:</label><br>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="active" value="1" 
                                               ${user.status != null && user.status == "true" ? "checked": ""}>
                                        <label class="form-check-label" for="male">Active</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="inactive" value="0" 
                                               ${user.status != null && user.status == "false" ? "checked": ""}>
                                        <label class="form-check-label" for="female">Inactive</label>
                                    </div>
                                </div>
                                <input type="hidden" value="${user.id}" name="id">
                                <input type="hidden" value="${user.roleName}" name="roleName">
                                <div class="mb-5 text-center">
                                    <input type="submit" class="btn btn-primary" value="Update User">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <!-- update password is in another part -->
        <%--<%@include file="/includes/footer.jsp"%>--%>
    </body>
</html>
