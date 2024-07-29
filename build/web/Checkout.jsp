<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="Cart.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Page</title>
        <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        -->        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
        <link href="css/detail.css" rel="stylesheet">
        <jsp:include page="includes/navbar.jsp"></jsp:include>
            <!-- Google Web Fonts -->
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

            <!-- Icon Font Stylesheet -->
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

            <!-- Libraries Stylesheet -->
            <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
            <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


            <!-- Customized Bootstrap Stylesheet -->
            <link href="css/bootstrap.min.css" rel="stylesheet">

            <!-- Template Stylesheet -->
            <link href="css/style.css" rel="stylesheet">        
            <style>
                .product-info {
                    padding: 20px;
                }
                .attr {
                    width: 25px;
                    background: #5a5a5a;
                }
                .attr2 {
                    background: #5a5a5a;
                    color: white;
                    padding: 5px;
                    margin-right: 5px;
                }


                .modal {
                    display: none;
                    position: fixed;
                    z-index: 1;
                    padding-top: 50px;
                    left: 0;
                    top: 0;
                    width: 100%;
                    height: 100%;
                    overflow: auto;
                    background-color: rgb(0,0,0);
                    background-color: rgba(0,0,0,0.9);
                }

                /* Modal Content (image) */
                .modal-content {
                    margin: auto;
                    display: block;
                    width: 80%;
                    max-width: 700px;
                }

                /* Close Button */
                .close {
                    position: absolute;
                    top: 15px;
                    right: 35px;
                    color: #f1f1f1;
                    font-size: 40px;
                    font-weight: bold;
                    transition: 0.3s;
                }

                .close:hover,
                .close:focus {
                    color: #bbb;
                    text-decoration: none;
                    cursor: pointer;
                }
                .item-photo img:hover {
                    transform: scale(1.2); /* Phóng to ảnh khi di chuột vào */
                    transition: transform 0.3s ease; /* Tạo hiệu ứng mượt mà */
                }
            </style>

            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                function updateQuantity(param, productId) {
                    var quantity = param.value;
                    if (isNaN(quantity) || quantity <= 0) {
                        alert("Quantity must be a non-negative number!");
                        param.value = "1";
                        param.focus();
                        return;
                    }
                    window.location.href = "cart?productId=" + productId + "&productQuantity=" + quantity + "&action=update";
                }
            </script>            
        </head>
        <body>

            <!-- Checkout Page Start -->
            <div class="container-fluid py-5">
                <div class="container py-5">
                    <h1 class="mb-4">Billing details</h1>
                <c:if test="${message != null}">
                    <h3 style="color: green">${message}</h3>
                </c:if>
                <form action="/FPTLaptopWeb/checkout" method="post">
                    <div class="row g-5">
                        <div class="col-md-12 col-lg-6 col-xl-7">
                            <div class="form-item">
                                <label class="form-label my-3">Full name <sup>*</sup></label>
                                <input type="text" class="form-control" name="name" placeholder="Full name" value="${name}" required="" readonly="">
                            </div>        

                            <div class="form-item">
                                <label class="form-label my-3">Address <sup>*</sup></label>
                                <input type="text" class="form-control" name="address" placeholder="Address" value="${requestScope.address}" required="">
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Phone<sup>*</sup></label>
                                <input type="tel" class="form-control" name="phone" placeholder="Phone number" value="${phone}" required="">
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-6 col-xl-5">
                            <div class="">
                                <div class="table">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr class="text-center">
                                                <th scope="col">Products</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Quantity</th>
                                                <th scope="col">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.cart.items}" var="c">
                                                <tr>
                                                    <th scope="row">
                                                        <div class="d-flex align-items-center mt-2">
                                                            <img src="${c.productImage}" class="img-fluid" style="width: 100%" alt="">
                                                        </div>
                                                    </th>
                                                    <td class="py-5 text-center align-middle">${c.productName}</td>
                                                    <td class="py-5 text-center align-middle">
                                                        <fmt:formatNumber value="${c.productPrice}" type="currency" var="formattedPrice" currencySymbol="" />
                                                        ${formattedPrice} VND
                                                    </td>
                                                    <td class="py-5 text-center align-middle">${c.numberInCart}</td>
                                                    <td class="py-5 text-center align-middle">
                                                        <fmt:formatNumber value="${c.productPrice * c.numberInCart}" type="currency" var="formattedPrice" currencySymbol="" />
                                                        ${formattedPrice} VND
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <th scope="row">
                                                </th>
                                                <td class="py-5">
                                                    <p class="mb-0 text-dark text-uppercase py-3"><strong>TOTAL</strong></p>
                                                </td>
                                                <td class="py-5"></td>
                                                <td class="py-5"></td>
                                                <td class="py-5">
                                                    <%
                                   Cart c = (Cart) session.getAttribute("cart");
                                   double total = 0;
                                   if(c != null){
                                        total = c.getAmount();
                                   }
                                   request.setAttribute("total", total);
                                                    %>
                                                    <div class="py-3 border-bottom border-top">
                                                        <p class="mb-0 text-dark">
                                                            <strong style="text-align: center">                                                       
                                                                <fmt:formatNumber value="${total}" type="currency" var="formattedPrice" currencySymbol="" />
                                                                ${formattedPrice} VND
                                                            </strong>
                                                        </p>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <!-- Starting Payment -->
                            <div class="p-3 py-5">
                                <div class="radio-buttons">
                                    <div>
                                        <label class="payment-method__item mb-3">
                                            <span class="payment-method__item-custom-checkbox custom-radio">
                                                <input type="radio" id="payment-QR" name="bankCode" value="QR" checked>
                                                <span class="checkmark">
                                                </span>
                                            </span>
                                            <span class="payment-method__item-name font-weight-bold h5" class="">
                                                Pay by QR's shop
                                            </span>
                                        </label>
                                        <br>
                                        <label class="payment-method__item">
                                            <span class="payment-method__item-custom-checkbox custom-radio">
                                                <input type="radio" id="payment-vnpay" name="bankCode" value="VNBANK">
                                                <span class="checkmark"> </span>
                                            </span>
                                            <span class="payment-method__item-name font-weight-bold h5">
                                                Pay by domestic card and bank account
                                            </span>
<!--                                                <input type="hidden" name="order_id" value="${order_id}"/>-->
<!--                                                <input type="hidden" name="amount" value="${total}"/>-->
                                        </label>
                                    </div>
                                </div>

                            </div>                                       
                            <!-- Ending payment here! -->

                            <div class="row g-4 text-center align-items-center justify-content-center pt-4">
                                <button type="submit" class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">Checkout Now</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Checkout Page End -->

        <input type="hidden" id="msg" value="${requestScope.message}">                                
        <%@include  file="includes/finish.jsp" %>
    </body>
</html>
