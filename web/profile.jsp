<%-- 
    Document   : profile
    Created on : Feb 21, 2024, 4:05:51 PM
    Author     : admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Details</title>
        <link rel="icon" href="images/person.png"
              type="images/x-icon"/>
        <%@include file="includes/head.jsp" %>
        <style>
            .custom-file-upload input[type="file"] {
                display: none;
            }
            .hidden{
                display: none !important;
            }
        </style>
    </head>
    <body>
        <%@include file="includes/navbar.jsp"%>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="container bootstrap snippets bootdey">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><strong>My Account</strong></li>
                </ol>
            </nav>
            <hr>
            <div class="row">   
                <!-- left column -->
                <div class="col-md-3">
                    <div class="text-center">
                        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
                        <c:choose>
                            <c:when test="${customerAvatar != null || adminAvatar != null}">
                                <c:choose>
                                    <c:when test="${sessionScope.admin != null}">
                                        <c:set var="imageUrl" value="${contextPath}/processImage?id=${sessionScope.admin.adminId}" />
                                    </c:when>
                                    <c:when test="${sessionScope.customer != null}">
                                        <c:set var="imageUrl" value="${contextPath}/processImage?id=${sessionScope.customer.customerId}" />
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <c:set var="imageUrl" value="${contextPath}/assets/images/avatarMain.jpg" />
                            </c:otherwise>
                        </c:choose>

                        <img src="${imageUrl}"
                             class="avatar img-circle img-thumbnail" 
                             alt="avatar" id="previewImage" 
                             style="width: 16vw; height: 16vw; object-fit: cover;" >
                        <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("error") %>
                        </div>
                        <% } %>
                        <div class="contact">
                            <form action="uploadImage" id="frmUploadPhoto" class="mt-2" method="post" enctype="multipart/form-data">
                                <!-- enctype="multipart/form-data": Used to send binary data such as images -->
                                <label for="avatarInput" class="custom-file-upload btn btn-block btn-light">
                                    <i class="fa-regular fa-image"></i>
                                    <input type="file" id="avatarInput" accept="image/*" name="avatarInput" />
                                    <!--<input type="file" id="avatarInput" accept="image/*" name="avatarInput" onchange="this.form.submit()" />-->
                                    <!--<input type="file" id="avatarInput" accept="image/*" name="avatarInput" onchange="document.querySelector('#uploadAvatarButton').classList.remove('hidden')" />-->
                                    Change Avatar
                                </label>
                                <button type="submit" id="uploadAvatarButton" class="btn btn-primary">Upload Avatar</button>
                            </form>

                            <form action="<%=request.getContextPath()%>/sendOtp" method="post" class="mt-2 mb-2">
                                <input type="hidden" name="email" value="${customer.customerEmail}">
                                <input type="hidden" name="action" value="change">
                                <button type="submit" class="btn btn-block btn-light">
                                    <i class="fa-solid fa-key"></i> Change Password
                                </button>
                            </form>
                            <a href="myOrder" class="btn btn-block btn-light"><i class="fa fa-book"></i> My Order</a>
                            <a href="logout" class="btn btn-block btn-light"><i class="fa-solid fa-power-off"></i> <strong>Logout</strong></a>
                        </div>
                    </div>
                </div>

                <!-- edit form column -->
                <div class="col-md-9 personal-info">




                    <h3 class="card-text"><strong>Account</strong> Customer</h3>



                    <c:if test="${requestScope.action == 'view'}">
                        <c:set var="customer" value="${requestScope.customer}"/>
                        <form id="form-1" action="profile" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">AccountID:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountId" 
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerId}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminId}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="email"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerEmail}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminEmail}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">Account Name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountName"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerName}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminName.trim()}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Gender</label>
                                <div class="col-lg-8">
                                    <c:choose>
                                        <c:when test="${customer.customerGender}">
                                            <input class="form-control input_type" type="text" name="gender" value="Female" readonly>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="form-control input_type" type="text" name="gender" value="Male" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Phone:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="phone"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerPhoneNumber}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminPhoneNumber}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Date of Birth:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="dateOfBirth"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerDOB}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminDOB}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">City:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="city"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerCity}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminCity.trim()}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Address:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="address"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerAddress}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminAddress.trim()}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"></label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="action" value="editInfo0" hidden>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <input type="submit" class="btn btn-dark p-3" value="Edit Information">
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${requestScope.action == 'editInfo'}">
                        <c:set var="customer" value="${requestScope.customer}"/>
                        <form id="form-1" action="profile" method="get" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">AccountID:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountId"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerId}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminId}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="email"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerEmail}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminEmail}"
                                               </c:when>
                                           </c:choose> readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">Account Name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountName"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerName}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminName.trim()}"
                                               </c:when>
                                           </c:choose> required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Gender:</label>
                                <div class="col-lg-8">
                                    <label>
                                        <input type="radio" name="gender" value="Male" <%= (request.getParameter("gender") != null && request.getParameter("gender").equals("Male")) ? "checked" : "" %>> Male
                                    </label>
                                    <label>
                                        <input type="radio" name="gender" value="Female" <%= (request.getParameter("gender") != null && request.getParameter("gender").equals("Female")) ? "checked" : "" %>> Female
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Phone:</label>
                                <div class="col-lg-8">
                                    <% if (request.getAttribute("errorMessagePhone") != null) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= request.getAttribute("errorMessagePhone") %>
                                    </div>
                                    <% } %>
                                    <input class="form-control input_type" type="text" name="phone"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerPhoneNumber}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminPhoneNumber}"
                                               </c:when>
                                           </c:choose> required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Date of Birth:</label>
                                <div class="col-lg-8">
                                    <% if (request.getAttribute("errorMessageDateOfBirth") != null) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= request.getAttribute("errorMessageDateOfBirth") %>
                                    </div>
                                    <% } %>
                                    <input class="form-control input_type" type="date" name="dateOfBirth"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerDOB}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminDOB}"
                                               </c:when>
                                           </c:choose> required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">City:</label>
                                <div class="col-lg-8">
                                    <select class="form-control input_type" name="city" required>
                                        <option value="">Select your City/Province...</option>
                                        <option value="Hanoi">Ha Noi</option>
                                        <option value="Da Nang">Da Nang</option>
                                        <option value="Ho Chi Minh">Ho Chi Minh</option>
                                        <option value="Hai Phong">Hai Phong</option>
                                        <option value="Can Tho">Can Tho</option>
                                        <option value="An Giang">An Giang</option>
                                        <option value="Bac Giang">Bac Giang</option>
                                        <option value="Bac Kan">Bac Kan</option>
                                        <option value="Bac Lieu">Bac Lieu</option>
                                        <option value="Bac Ninh">Bac Ninh</option>
                                        <option value="Ba Ria-Vung Tau">Ba Ria-Vung Tau</option>
                                        <option value="Ben Tre">Ben Tre</option>
                                        <option value="Binh Dinh">Binh Dinh</option>
                                        <option value="Binh Duong">Binh Duong</option>
                                        <option value="Binh Phuoc">Binh Phuoc</option>
                                        <option value="Binh Thuan">Binh Thuan</option>
                                        <option value="Ca Mau">Ca Mau</option>
                                        <option value="Cao Bang">Cao Bang</option>
                                        <option value="Dak Lak">Dak Lak</option>
                                        <option value="Dak Nong">Dak Nong</option>
                                        <option value="Dien Bien">Dien Bien</option>
                                        <option value="Dong Nai">Dong Nai</option>
                                        <option value="Dong Thap">Dong Thap</option>
                                        <option value="Gia Lai">Gia Lai</option>
                                        <option value="Ha Giang">Ha Giang</option>
                                        <option value="Ha Nam">Ha Nam</option>
                                        <option value="Ha Tinh">Ha Tinh</option>
                                        <option value="Hai Duong">Hai Duong</option>
                                        <option value="Hau Giang">Hau Giang</option>
                                        <option value="Hoa Binh">Hoa Binh</option>
                                        <option value="Hung Yen">Hung Yen</option>
                                        <option value="Khanh Hoa">Khanh Hoa</option>
                                        <option value="Kien Giang">Kien Giang</option>
                                        <option value="Kon Tum">Kon Tum</option>
                                        <option value="Lai Chau">Lai Chau</option>
                                        <option value="Lam Dong">Lam Dong</option>
                                        <option value="Lang Son">Lang Son</option>
                                        <option value="Lao Cai">Lao Cai</option>
                                        <option value="Long An">Long An</option>
                                        <option value="Nam Dinh">Nam Dinh</option>
                                        <option value="Nghe An">Nghe An</option>
                                        <option value="Ninh Binh">Ninh Binh</option>
                                        <option value="Ninh Thuan">Ninh Thuan</option>
                                        <option value="Phu Tho">Phu Tho</option>
                                        <option value="Quang Binh">Quang Binh</option>
                                        <option value="Quang Nam">Quang Nam</option>
                                        <option value="Quang Ngai">Quang Ngai</option>
                                        <option value="Quang Ninh">Quang Ninh</option>
                                        <option value="Quang Tri">Quang Tri</option>
                                        <option value="Soc Trang">Soc Trang</option>
                                        <option value="Son La">Son La</option>
                                        <option value="Tay Ninh">Tay Ninh</option>
                                        <option value="Thai Binh">Thai Binh</option>
                                        <option value="Thai Nguyen">Thai Nguyen</option>
                                        <option value="Thanh Hoa">Thanh Hoa</option>
                                        <option value="Thua Thien-Hue">Thua Thien-Hue</option>
                                        <option value="Tien Giang">Tien Giang</option>
                                        <option value="Tra Vinh">Tra Vinh</option>
                                        <option value="Tuyen Quang">Tuyen Quang</option>
                                        <option value="Vinh Long">Vinh Long</option>
                                        <option value="Vinh Phuc">Vinh Phuc</option>
                                        <option value="Yen Bai">Yen Bai</option>
                                        <option value="<%= request.getParameter("city") %>" <%=(request.getParameter("city") == null) ? "" : "selected" %> hidden><%= request.getParameter("city") %></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Address:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="address"
                                           <c:choose>
                                               <c:when test="${not empty customer}">
                                                   value="${customer.customerAddress}"
                                               </c:when>
                                               <c:when test="${not empty admin}">
                                                   value="${admin.adminAddress.trim()}"
                                               </c:when>
                                           </c:choose>>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"></label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="action" value="editInfo" hidden>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <input type="submit" class="btn btn-success px-4 py-3" value="Save">
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="includes/finish.jsp"%>                    
        <%@include file="includes/footer.jsp"%>

    </body> 
</html>
