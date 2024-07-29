<%-- 
    Document   : cart
    Created on : Jul 3, 2024, 9:15:06 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="Cart.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
        <jsp:include page="includes/navbar.jsp"></jsp:include>
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

                /*Modal Content (image)*/
                .modal-content {
                    margin: auto;
                    display: block;
                    width: 80%;
                    max-width: 700px;

                }
                /*Close Button*/
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
                    transform: scale(1.2);
                    transition: transform 0.3s ease;
                }

                .custom-link {
                    color: black;
                    text-decoration: none;
                }

                .custom-link:hover {
                    color: #0056b3;
                    text-decoration: none
                }


            </style>
        </head>
        <body>


            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

            <script>
                window.onload = function () {
                    var message = "${requestScope.message}";
                    if (message) {
                        window.alert(message);
                    }

                    var checkoutButton = document.getElementById('checkoutButton');
                    checkoutButton.addEventListener('click', function (event) {
                        event.preventDefault(); // Ngăn chặn chuyển hướng mặc định

                        var inputQuantities = document.querySelectorAll('.inputQuantity');
                        var inputIds = document.querySelectorAll('.inputId');

                        var isValid = true;

                        inputQuantities.forEach((inputQuantity, index) => {
                            var quantity = inputQuantity.value.trim();
                            var productId = inputIds[index].value;

                            if (!validateQuantity(quantity)) {
                                isValid = false;
                                inputQuantity.classList.add('is-invalid');
                                alert("Invalid quantity for product ID = " + productId);
                            } else {
                                inputQuantity.classList.remove('is-invalid');
                            }
                        });

                        if (isValid) {
                            inputQuantities.forEach((inputQuantity, index) => {
                                var quantity = inputQuantity.value.trim();
                                var productId = inputIds[index].value;
                                updateQuantity(quantity, productId);
                            });
                        }
                    });

                    function validateQuantity(quantity) {
                        var quantityPattern = /^[1-9]\d*$/; // Chỉ cho phép số nguyên dương
                        return quantityPattern.test(quantity);
                    }

                    // Hàm cập nhật số lượng sản phẩm
                    function updateQuantity(quantity, productId) {
                        // Thực hiện cập nhật giỏ hàng với số lượng mới
                        window.location.href = "cart?productId=" + productId + "&productQuantity=" + quantity + "&action=update";
                    }
                };
        </script>




    </head>
<body>

    <div class="container-fluid py-5">
        <div class="container py-5">
            <c:if test="${sessionScope.cart.items == null || sessionScope.cart.items.size() == 0}">
                <h1 style="text-align: center">Your cart is empty</h1>
                <div style="text-align: center">
                    <button class="btn btn-primary" onclick="window.location.href = 'home'">Return to Home</button>
                </div>
            </c:if>

            <c:if test="${sessionScope.cart.items != null && sessionScope.cart.items.size() > 0}">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th class="text-center" scope="col">ID</th>
                                <th class="text-center" scope="col">Name</th>
                                <th class="text-center" scope="col">Image</th>
                                <th class="text-center" scope="col">Quantity</th>
                                <th class="text-center" scope="col">Price</th>
                                <th class="text-center" scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.cart.items}" var="c">
                                <tr class="align-middle">
                                    <td class="text-center align-middle"><a class="custom-link" href="detail?productId=${c.productId}">${c.productId}</a></td> <!-- ID -->
                                    <td class="text-center align-middle"><a class="custom-link" href="detail?productId=${c.productId}">${c.productName}</a></td> <!-- Name -->
                                    <td class="text-center align-middle">
                                        <div class="d-flex align-items-center justify-content-center">
                                            <a href="detail?productId=${c.productId}">
                                                <img src="UPLOAD_IMAGE1/${c.productImage}" class="img-fluid rounded" style="width: 80px; height: 80px; flex-shrink: 0;" alt="">
                                            </a>
                                        </div>
                                    </td> <!-- Image -->
                                    <td class="text-center align-middle">
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="input-group-btn">
                                                <a href="cart?productId=${c.productId}&action=minus">
                                                    <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                        <i style="width: 100%" class="fa fa-minus"></i>
                                                    </button>
                                                </a>
                                            </div>
                                            <div class="mx-2" style="padding: 0px 1px;">
                                                <input type="hidden" class="inputId" name="productId" value="${c.productId}" />
                                                <input type="hidden" name="action" value="update" />
                                                <input style="width: 60px; text-align: center" class="inputQuantity" name="productQuantity" type="text" class="form-control" value="${c.numberInCart}">
                                            </div>

                                            <div class="input-group-btn">
                                                <a href="cart?productId=${c.productId}&action=add">
                                                    <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                        <i style="width: 100%" class="fa fa-plus"></i>
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-center align-middle">
                                        <fmt:formatNumber value="${c.productPrice * c.numberInCart}" type="currency" var="formattedPrice" currencySymbol="" />
                                        ${formattedPrice} VND

                                    </td> <!-- Price -->
                                    <td class="text-center align-middle">
                                        <div class="d-flex align-items-center justify-content-center">
                                            <a href="cart?productId=${c.productId}&action=delete">
                                                <button type="button" class="btn btn-outline-danger me-2">
                                                    Delete
                                                </button>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>

                    </table>

                </div>
                <div class="row g-4 justify-content-end">
                    <div class="col-8"></div>
                    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                        <div class="bg-light rounded">
                            <%
                               Cart c = (Cart) session.getAttribute("cart");
                               double total = 0;
                               if(c != null){
                                    total = c.getAmount();
                               }
                               request.setAttribute("total", total);
                            %>
                            <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                <h5 class="mb-0 ps-4 me-4">Total</h5>
                                <p class="mb-0 pe-4">
                                    <fmt:formatNumber value="${total}" type="currency" var="formattedPrice" currencySymbol="" />
                                    ${formattedPrice} VND

                                </p>
                            </div>
                            <!--                                <a href="cartcheck"><button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="button" onclick="myFunction()">Proceed Checkout</button></a>-->
                            <a href="#" id="checkoutButton">
                                <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="button">Proceed Checkout</button>
                            </a>

                        </div>
                    </div>
                </div>
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
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <%@include  file="includes/finish.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>
