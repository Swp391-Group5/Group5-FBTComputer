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
                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><strong>My Account</strong></li>
                </ol>
            </nav>

            <hr>
            <div class="row">   
                <!-- left column -->
                <div class="col-md-3">
                    <div class="text-center">
                        <img src="<%= request.getContextPath()%>/assets/images/avatarMain.jpg" class="avatar img-circle img-thumbnail" alt="avatar" id="previewImage" >
                        <div class="contact">
                            <!--                            <label for="avatarInput" class="custom-file-upload btn btn-block btn-primary">
                                                            <i class="fa-regular fa-image"></i>
                                                            <input type="file" id="avatarInput" accept="image/*" name="Avatar" />
                                                            Change Avatar
                                                        </label>-->
                            <!-- Change password button-->
                            <form action="<%=request.getContextPath()%>/sendOtp" method="post" class="mt-3 mb-2">
                                <input type="hidden" name="email" value="${customer.customerEmail}">
                                <button type="submit" class="btn btn-block btn-light">
                                    <i class="fa-solid fa-key"></i> Change Password
                                </button>
                            </form>
                                
                            <a href="orders.jsp" class="btn btn-block btn-light"><i class="fa fa-book"></i> My Order</a>
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
                                    <input class="form-control input_type" type="text" name="accountId" value="${customer.customerId}"readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="email" value="${customer.customerEmail}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">Account Name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountName" value="${customer.customerName}" readonly>
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
                                    <input class="form-control input_type" type="text"  name="phone" value="${customer.customerPhoneNumber}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Date of Birth</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="dateOfBirth" value="${customer.customerDOB}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">City:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text"  name="city" value="${customer.customerCity}" readonly>
                                </div>
                            </div>
                            <!--                            <div class="form-group">
                                                            <label class="col-lg-3 control-label">District:</label>
                                                            <div class="col-lg-8">
                                                                <input class="form-control input_type" type="text"  name="District" value="${requestScope.District}" readonly>
                                                            </div>
                                                        </div>-->
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Address:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text"  name="address" value="${customer.customerAddress}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"></label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text"  name="action" value="editInfo0" hidden>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-3 col-lg-8">
                                    <input type="submit" class="btn btn-info p-3" value="Edit Infomation">
                                </div>
                            </div> 
                        </form>
                    </c:if>
                    <c:if test="${requestScope.action == 'editInfo'}">
                        <c:set var="customer" value="${requestScope.customer}"/>
                        <form id="form-1" action="profile" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">AccountID:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountId" value="${customer.customerId}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="email" value="${customer.customerEmail}" readonly >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label input_type">Account Name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text" name="accountName" value="${customer.customerName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Gender:</label>
                                <div class="col-lg-8">
                                    <label><input type="radio" name="gender" value="Male" <%= (request.getParameter("gender") != null && request.getParameter("gender").equals("Male")) ? "checked" : "" %> > Male</label>
                                    <label><input type="radio" name="gender" value="Female" <%= (request.getParameter("gender") != null && request.getParameter("gender").equals("Female")) ? "checked" : "" %> > Female</label>
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
                                    <input class="form-control input_type" type="number" name="phone" value="${customer.customerPhoneNumber}" required >
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
                                    <input class="form-control input_type" type="date" name="dateOfBirth" value="${customer.customerDOB}" required >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">City:</label>
                                <div class="col-lg-8">
                                    <select class="form-control input_type" name="city" required>
                                        <option value="">Select your City/Province...</option>
                                        <option value="Hanoi City">Hanoi City</option>
                                        <option value="Da Nang City">Da Nang City</option>
                                        <option value="Ho Chi Minh City">Ho Chi Minh City</option>
                                        <option value="Hai Phong City">Hai Phong City</option>
                                        <option value="Can Tho City">Can Tho City</option>
                                        <option value="An Giang Province">An Giang Province</option>
                                        <option value="Bac Giang Province">Bac Giang Province</option>
                                        <option value="Bac Kan Province">Bac Kan Province</option>
                                        <option value="Bac Lieu Province">Bac Lieu Province</option>
                                        <option value="Bac Ninh Province">Bac Ninh Province</option>
                                        <option value="Ba Ria-Vung Tau Province">Ba Ria-Vung Tau Province</option>
                                        <option value="Ben Tre Province">Ben Tre Province</option>
                                        <option value="Binh Dinh Province">Binh Dinh Province</option>
                                        <option value="Binh Duong Province">Binh Duong Province</option>
                                        <option value="Binh Phuoc Province">Binh Phuoc Province</option>
                                        <option value="Binh Thuan Province">Binh Thuan Province</option>
                                        <option value="Ca Mau Province">Ca Mau Province</option>
                                        <option value="Cao Bang Province">Cao Bang Province</option>
                                        <option value="Dak Lak Province">Dak Lak Province</option>
                                        <option value="Dak Nong Province">Dak Nong Province</option>
                                        <option value="Dien Bien Province">Dien Bien Province</option>
                                        <option value="Dong Nai Province">Dong Nai Province</option>
                                        <option value="Dong Thap Province">Dong Thap Province</option>
                                        <option value="Gia Lai Province">Gia Lai Province</option>
                                        <option value="Ha Giang Province">Ha Giang Province</option>
                                        <option value="Ha Nam Province">Ha Nam Province</option>
                                        <option value="Ha Tinh Province">Ha Tinh Province</option>
                                        <option value="Hai Duong Province">Hai Duong Province</option>
                                        <option value="Hau Giang Province">Hau Giang Province</option>
                                        <option value="Hoa Binh Province">Hoa Binh Province</option>
                                        <option value="Hung Yen Province">Hung Yen Province</option>
                                        <option value="Khanh Hoa Province">Khanh Hoa Province</option>
                                        <option value="Kien Giang Province">Kien Giang Province</option>
                                        <option value="Kon Tum Province">Kon Tum Province</option>
                                        <option value="Lai Chau Province">Lai Chau Province</option>
                                        <option value="Lam Dong Province">Lam Dong Province</option>
                                        <option value="Lang Son Province">Lang Son Province</option>
                                        <option value="Lao Cai Province">Lao Cai Province</option>
                                        <option value="Long An Province">Long An Province</option>
                                        <option value="Nam Dinh Province">Nam Dinh Province</option>
                                        <option value="Nghe An Province">Nghe An Province</option>
                                        <option value="Ninh Binh Province">Ninh Binh Province</option>
                                        <option value="Ninh Thuan Province">Ninh Thuan Province</option>
                                        <option value="Phu Tho Province">Phu Tho Province</option>
                                        <option value="Quang Binh Province">Quang Binh Province</option>
                                        <option value="Quang Nam Province">Quang Nam Province</option>
                                        <option value="Quang Ngai Province">Quang Ngai Province</option>
                                        <option value="Quang Ninh Province">Quang Ninh Province</option>
                                        <option value="Quang Tri Province">Quang Tri Province</option>
                                        <option value="Soc Trang Province">Soc Trang Province</option>
                                        <option value="Son La Province">Son La Province</option>
                                        <option value="Tay Ninh Province">Tay Ninh Province</option>
                                        <option value="Thai Binh Province">Thai Binh Province</option>
                                        <option value="Thai Nguyen Province">Thai Nguyen Province</option>
                                        <option value="Thanh Hoa Province">Thanh Hoa Province</option>
                                        <option value="Thua Thien-Hue Province">Thua Thien-Hue Province</option>
                                        <option value="Tien Giang Province">Tien Giang Province</option>
                                        <option value="Tra Vinh Province">Tra Vinh Province</option>
                                        <option value="Tuyen Quang Province">Tuyen Quang Province</option>
                                        <option value="Vinh Long Province">Vinh Long Province</option>
                                        <option value="Vinh Phuc Province">Vinh Phuc Province</option>
                                        <option value="Yen Bai Province">Yen Bai Province</option>
                                        <option value="<%= request.getParameter("city") %>" <%=(request.getParameter("city") == null) ? "" : "selected" %> hidden><%= request.getParameter("city") %></option>
                                    </select>
                                </div>
                            </div>
                            <!--
                        <div class="form-group">
                            <label class="col-lg-3 control-label">District:</label>
                            <div class="col-lg-8">
                                <select class="form-control input_type" name="District" required>
                                    <option value="">Select your District...</option>
                                    <option value="Ba Dinh">Ba Dinh</option>
                                    <option value="Bac Tu Liem">Bac Tu Liem</option>
                                    <option value="Cau Giay">Cau Giay</option>
                                    <option value="Dong Da">Dong Da</option>
                                    <option value="Ha Dong">Ha Dong</option>
                                    <option value="Hai Ba Trung">Hai Ba Trung</option>
                                    <option value="Hoan Kiem">Hoan Kiem</option>
                                    <option value="Hoang Mai">Hoang Mai</option>
                                    <option value="Long Bien">Long Bien</option>
                                    <option value="Nam Tu Liem">Nam Tu Liem</option>
                                    <option value="Tay Ho">Tay Ho</option>
                                    <option value="Thanh Xuan">Thanh Xuan</option>

                                </select>
                            </div>
                        </div>-->
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Address:</label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text"  name="address" value="${customer.customerAddress}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"></label>
                                <div class="col-lg-8">
                                    <input class="form-control input_type" type="text"  name="action" value="editInfo" hidden>
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
    <!--    <script>
            var fileInput = document.getElementById("avatarInput");
            var previewImage = document.getElementById("previewImage");
            fileInput.addEventListener("change", handlePreviewAvatar);
            function handlePreviewAvatar(e) {
                var file = e.target.files[0];
                var previewURL = URL.createObjectURL(file);
                previewImage.src = previewURL;
            }
        </script>-->
</html>
