<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomePage</title>
        <jsp:include page="includes/navbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>
            <style>
                .row {
                    display: flex;
                    flex-wrap: wrap;
                    margin: -15px;
                }
                .col-12, .col-md-6, .col-lg-4 {
                    padding: 15px;
                    display: flex;
                }
                .card {
                    display: flex;
                    flex-direction: column;
                    width: 100%;
                }
                .card-body {
                    flex: 1 1 auto;
                    display: flex;
                    flex-direction: column;
                }
                .card-title {
                    white-space: nowrap; /* Ngăn các từ trong tên sản phẩm bị ngắt ra nhiều dòng */
                    overflow: hidden;
                    text-overflow: ellipsis; /* Hiển thị dấu ... nếu tên sản phẩm quá dài */
                }

                .btn-danger {
                    margin-top: auto;
                }
                .product-description {
                    max-height: 3.6em; /* Chiều cao tương đương khoảng 2 dòng văn bản */
                    overflow: hidden;
                    text-overflow: ellipsis; /* Hiển thị dấu ... cho văn bản dài hơn */
                    white-space: normal; /* Đảm bảo văn bản không bị cắt ngang khi trở về hàng mới */
                    line-height: 1.2em; /* Độ cao của mỗi dòng */
                }

                .product-description.expand {
                    max-height: none; /* Hiển thị toàn bộ nội dung */
                }

                .expand-button {
                    cursor: pointer;
                    color: blue;
                }
                .image-clickable {
                    transition: transform 0.3s ease;
                }

                .image-clickable:hover {
                    transform: scale(1.1); /* Thay đổi kích thước hình ảnh khi di chuột vào */
                }
            </style>
        </head>
        <body>
            <br>
            <br>
            <br>
            <!-- 404 Start -->
            <div class="container-fluid py-5">
                <div class="container py-5">
                    <div class="row justify-content-center">
                        <div class="col-lg-6">
                            <i class="bi bi-exclamation-triangle display-1 text-secondary"></i>
                            <div style="text-align: center">
                                <img style="width: 20%" src="<%=request.getContextPath()%>/assets/images/green_tick.png" alt="tick"/>
                        </div>
                        <br>
                        <c:choose>
                            <c:when test="${requestScope.cardType == 'ATM'}">
                                <div style="text-align: center">
                                    <h2 class="mb-4 text-success">Thanks for your order.</h2>
                                    <h2 class="mb-4 text-success">It has been received!</h2>
                                    <ul class="list-unstyled">
                                        <li><strong>Invoice Id:</strong> ${requestScope.orderId}</li>
                                        <li><strong>Date:</strong> <fmt:formatDate value="${requestScope.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                                        <li><strong>Total:</strong> <span class="total-amount">
                                                <fmt:formatNumber value="${requestScope.amount}" type="currency" currencySymbol="VND"/>
                                            </span></li>
                                        <li><strong>Payment method:</strong> <span class="font-weight-bold">${requestScope.cardType}</span></li>
                                        <li><strong></strong> <span class="font-weight-bold text-danger">Order successfully! Please check your email to see your recent order!</span></li>
                                    </ul>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: center">
                                    <h2 class="mb-4 text-success">We've sent the order confirmation and payment.</h2>
                                    <h2 class="mb-4 text-success">Please check your email!</h2>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div style="text-align: center">
                            <a class="btn border-secondary rounded-pill py-1 px-3 text-center" href="home">Go Back To Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 404 End -->
        <br>
        <br>
        <br>
        <%@include  file="includes/finish.jsp" %>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const descriptions = document.querySelectorAll('.product-description');
                descriptions.forEach(description => {
                    if (description.clientHeight < description.scrollHeight) {
                        const expandButton = document.createElement('span');
                        expandButton.classList.add('expand-button');
                        expandButton.textContent = 'show';
                        expandButton.addEventListener('click', function () {
                            description.classList.toggle('expand');
                            expandButton.textContent = description.classList.contains('expand') ? 'hiden' : 'show';
                        });
                        description.parentNode.appendChild(expandButton);
                    }
                });
            });
            document.addEventListener("DOMContentLoaded", function () {
                const images = document.querySelectorAll('.image-clickable');
                images.forEach(image => {
                    image.addEventListener('click', function () {
                        const productId = image.getAttribute('data-product-id');
                        window.location.href = 'detail?productId=' + productId;
                    });
                });
            });

        </script>
        <%@include  file="includes/footer.jsp" %>
    </body>
</html>


