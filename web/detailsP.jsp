<%-- 
    Document   : test
    Created on : Jun 1, 2024, 9:21:39 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />

        <link href="css/detail2.css" rel="stylesheet">

        <style>
            /* Hiệu ứng cho hình ảnh khi hover */
            .image_list li:hover img {
                transform: scale(1.1); /* Phóng to hình ảnh khi hover */
                transition: transform 0.3s ease; /* Thời gian chuyển đổi hiệu ứng */
            }

            /* Hiệu ứng màu khi hover qua chữ */
            .product_name:hover {
                color: red; /* Màu chữ thay đổi khi hover */
                transition: color 0.3s ease; /* Thời gian chuyển đổi hiệu ứng */
            }
            /* Hiệu ứng phóng to cho hình ảnh khi hover */
            .image_selected img:hover {
                transform: scale(1.1); /* Phóng to hình ảnh khi hover */
                transition: transform 0.3s ease; /* Thời gian chuyển đổi hiệu ứng */
                cursor: pointer; /* Thay đổi con trỏ chuột thành dấu nhấp khi hover */
            }

            /* Hiển thị overlay khi hover vào hình ảnh */
            .image_selected:hover::before {
                content: ''; /* Tạo một lớp trống trước hình ảnh */
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5); /* Màu nền đen với độ mờ 50% */
                opacity: 0;
                transition: opacity 0.3s ease; /* Thời gian chuyển đổi hiệu ứng */
            }

            /* Hiển thị nút đóng khi hover vào hình ảnh */
            .image_selected:hover::after {
                content: 'Close'; /* Nội dung của nút đóng */
                position: absolute;
                top: 20px; /* Điều chỉnh vị trí nút đóng */
                right: 20px; /* Điều chỉnh vị trí nút đóng */
                color: #fff; /* Màu chữ của nút đóng */
                font-size: 16px; /* Kích thước chữ của nút đóng */
                padding: 10px 15px; /* Kích thước của nút đóng */
                background-color: rgba(0, 0, 0, 0.8); /* Màu nền của nút đóng */
                border-radius: 5px; /* Bo tròn góc của nút đóng */
                opacity: 0; /* Mặc định ẩn nút đóng */
                transition: opacity 0.3s ease; /* Thời gian chuyển đổi hiệu ứng */
            }

            /* Hiển thị nút đóng khi hover vào hình ảnh */
            .image_selected:hover::after:hover {
                opacity: 1; /* Hiển thị nút đóng khi hover */
                cursor: pointer; /* Thay đổi con trỏ chuột thành dấu nhấp khi hover */
            }
            .similar_product:hover {
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
                transform: scale(1.05);
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }
            body{
                margin-top:20px;
                background:#eee;
            }
            .review-list ul li .left span {
                width: 32px;
                height: 32px;
                display: inline-block;
            }
            .review-list ul li .left {
                flex: none;
                max-width: none;
                margin: 0 10px 0 0;
            }
            .review-list ul li .left span img {
                border-radius: 50%;
            }
            .review-list ul li .right h4 {
                font-size: 16px;
                margin: 0;
                display: flex;
            }
            .review-list ul li .right h4 .gig-rating {
                display: flex;
                align-items: center;
                margin-left: 10px;
                color: #ffbf00;
            }
            .review-list ul li .right h4 .gig-rating svg {
                margin: 0 4px 0 0px;
            }
            .country .country-flag {
                width: 16px;
                height: 16px;
                vertical-align: text-bottom;
                margin: 0 7px 0 0px;
                border: 1px solid #fff;
                border-radius: 50px;
                box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
            }
            .country .country-name {
                color: #95979d;
                font-size: 13px;
                font-weight: 600;
            }
            .review-list ul li {
                border-bottom: 1px solid #dadbdd;
                padding: 0 0 30px;
                margin: 0 0 30px;
            }
            .review-list ul li .right {
                flex: auto;
            }
            .review-list ul li .review-description {
                margin: 20px 0 0;
            }
            .review-list ul li .review-description p {
                font-size: 14px;
                margin: 0;
            }
            .review-list ul li .publish {
                font-size: 13px;
                color: #95979d;
            }

            .review-section h4 {
                font-size: 20px;
                color: #222325;
                font-weight: 700;
            }
            .review-section .stars-counters tr .stars-filter.fit-button {
                padding: 6px;
                border: none;
                color: #4a73e8;
                text-align: left;
            }
            .review-section .fit-progressbar-bar .fit-progressbar-background {
                position: relative;
                height: 8px;
                background: #efeff0;
                -webkit-box-flex: 1;
                -ms-flex-positive: 1;
                flex-grow: 1;
                box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
                background-color: #ffffff;
                ;
                border-radius: 999px;
            }
            .review-section .stars-counters tr .star-progress-bar .progress-fill {
                background-color: #ffb33e;
            }
            .review-section .fit-progressbar-bar .progress-fill {
                background: #2cdd9b;
                background-color: rgb(29, 191, 115);
                height: 100%;
                position: absolute;
                left: 0;
                z-index: 1;
                border-radius: 999px;
            }
            .review-section .fit-progressbar-bar {
                display: flex;
                align-items: center;
            }
            .review-section .stars-counters td {
                white-space: nowrap;
            }
            .review-section .stars-counters tr .progress-bar-container {
                width: 100%;
                padding: 0 10px 0 6px;
                margin: auto;
            }
            .ranking h6 {
                font-weight: 600;
                padding-bottom: 16px;
            }
            .ranking li {
                display: flex;
                justify-content: space-between;
                color: #95979d;
                padding-bottom: 8px;
            }
            .review-section .stars-counters td.star-num {
                color: #4a73e8;
            }
            .ranking li>span {
                color: #62646a;
                white-space: nowrap;
                margin-left: 12px;
            }
            .review-section {
                border-bottom: 1px solid #dadbdd;
                padding-bottom: 24px;
                margin-bottom: 34px;
                padding-top: 64px;
            }
            .review-section select, .review-section .select2-container {
                width: 188px !important;
                border-radius: 3px;
            }
            ul, ul li {
                list-style: none;
                margin: 0px;
            }
            .helpful-thumbs, .helpful-thumb {
                display: flex;
                align-items: center;
                font-weight: 700;
            </style>
        </head>
        <body>
            <jsp:include page="includes/navbar.jsp"></jsp:include>
            <jsp:include page="includes/head.jsp"></jsp:include>
                <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
                    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                        <c:forEach items="${sessionScope.listSlider_HomePageAll}" var="s">
                            <div class="carousel-item ${s.blogId == sessionScope.sliderFirst.blogId ? 'active' : ''}">
                                <a href="#">
                                    <img src="UPLOAD_IMAGE/${s.img}" style="object-fit: contain;"  class="d-block w-100" alt="${s.blogTitle}">
                                </a>
                            </div>
                        </c:forEach>

                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>

                    <div class="card-group" style="margin-top: 10px">
                            <div class="card" style="width: 18rem;">
                            <img style="height:200px;
                                 width:auto;
                                 margin: auto;
                                 object-fit: cover;
                                 " src="https://emergencydeliveryservice.com/wp-content/uploads/2018/07/nationwide-delivery.jpg">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align:center">NATIONWIDE DELIVERY</h5>
                                    <p class="card-text" style="text-align:center">Shipping across Vietnam</p>
                                </div>
                            </div>
                            <div class="card" style="width: 18rem;">
                            <img class="card-img-top" style="height:200px;
                                 width:auto;
                                 margin: auto;
                                 object-fit: cover;
                                 " src="https://io.meeymedia.com/meeyland-cms/uploads/images/2022/07/30/cod3-1659191255.jpeg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align:center">CASH ON DELIVERY</h5>
                                    <p class="card-text" style="text-align:center">Pay upon receiving</p>
                                </div>
                            </div>
                            <div class="card" style="width: 18rem;">
                            <img class="card-img-top" style="height:200px;
                                 width:auto;
                                 margin: auto;
                                 object-fit: cover;" src="https://bedbugtreatmenthouston.com/wp-content/uploads/2021/03/houston-two-year-bed-bug-treatment-warranty-1-300x240.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align:center">LONG-TERM WARRANTY</h5>
                                    <p class="card-text" style="text-align:center">Up to 60 days warranty</p>
                                </div>
                            </div>
                            <div class="card" style="width: 18rem;">
                            <img class="card-img-top" style="height:200px;
                                 width:auto;
                                 margin: auto;
                                 object-fit: cover;" src="https://thumbs.dreamstime.com/b/print-226957006.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align:center">EASY RETURNS</h5>
                                    <p class="card-text" style="text-align:center">Hassle-free returns within 30 days</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="container-fluid">
                    <header class="header" style="display: none;">
                    <div class="header_main">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
                                    <div class="header_search">
                                        <div class="header_search_content">
                                            <div class="header_search_form_container">
                                                <form action="#" class="header_search_form clearfix">
                                                    <div class="custom_dropdown">
                                                        <div class="custom_dropdown_list"> <span class="custom_dropdown_placeholder clc">All Categories</span> <i class="fas fa-chevron-down"></i>
                                                            <ul class="custom_list clc">
                                                                <li><a class="clc" href="#">All Categories</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>

                <div class="single_product">
                    <div class="container-fluid" style=" background-color: #fff;
                         padding: 11px;">
                        <div class="row">
                            <div class="col-lg-2 order-lg-1 order-2">
                                <ul class="image_list">
                                    <li data-image="${detail.image1}"> <img src="${detail.image1}" alt=""></li>
                                    <li data-image="${detail.image2}" > <img src="${detail.image2}" alt=""></li>
                                    <li data-image="${detail.image3}"> <img src="${detail.image3}" alt=""></li>
                                </ul>
                            </div>
                            <div class="col-lg-4 order-lg-2 order-1">
                                <div class="image_selected"><img src="${detail.getProductImage()}" alt=""></div>
                            </div>
                            <div class="col-lg-6 order-3">
                                <div class="product_description">
                                    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">Product Details</li>
                                        </ol>
                                    </nav>
                                    <div class="product_name">${detail.productName}</div>
                                    <div class="product-brand">
                                        <span class="brand-name">Brand  : ${detail.productBrand} </span>
                                    </div>

                                    <div> <span class="product_price"> <h3 style="color: red"> Price: <fmt:formatNumber value="${detail.productPrice}" type="number" maxFractionDigits="2"/> <span>&#8363;</span> </h3></span> </div>

                                        <hr class="singleline">
                                        <div class="product-description">

                                            <span class="product_info">
                                                <h3>Description:</h3>
                                                ${detail.productDescription}
                                            </span>
                                        </div>

                                        <hr class="singleline">
                                        <div class="order_info d-flex flex-row">
                                            <form action="#">
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6" style="margin-left: 13px;">

                                        </div>
                                        <div class="section" style="padding-bottom:20px;">
                                            <c:choose>
                                                <c:when test="${detail.productQuantity == 0}">
                                                    <button class="btn btn-secondary" disabled>Sold Out</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="cartprocess?productId=${detail.productId}" class="btn btn-danger">
                                                        <i class="fa-solid fa-cart-shopping"></i> <!-- Biểu tượng giỏ hàng từ Bootstrap Icons -->
                                                        Add to Cart
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-underline">
                            <div class="col-md-6 d-flex justify-content-start"> <span class=" deal-text">Similar Product</span> </div>
                            <div class="col-md-6"> <a href="#" data-abc="true"> <span class="ml-auto view-all"></span> </a> </div>
                        </div>
                        <div class="super_container">
                            <!-- Your content here -->

                            <div class="row d-flex justify-content-center">
                                <c:forEach var="similarProduct" items="${similarProducts}">
                                    <div class="col-md-4">
                                        <div class="card mb-3 similar_product">
                                            <a href="detail?productId=${similarProduct.getProductId()}" >   <img  src="${similarProduct.getProductImage()}" class="card-img-top" alt="${similarProduct.getProductName()}"></a>
                                            <div class="card-body">
                                                <h5 class="card-title">
                                                    <a href="detail?productId=${similarProduct.getProductId()}" class="text-decoration-none text-dark">${similarProduct.getProductName()}</a>
                                                </h5>
                                                <p class="card-text">
                                                    <fmt:formatNumber value="${similarProduct.getProductPrice()}" type="currency" currencySymbol="VND"/>
                                                </p>
                                                <p class="btn btn-success w-100">
                                                    <a href="detail?productId=${similarProduct.getProductId()}" class="text-decoration-none text-white">View Details</a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="row row-underline">
                            <div class="col-md-6"> <span class=" deal-text">Specifications</span> </div>
                            <div class="col-md-6"> <a href="#" data-abc="true"> <span class="ml-auto view-all"></span> </a> </div>
                        </div>
                        <div class="row">   
                            <div class="col-md-12">
                                <table class="col-md-12">
                                    <tbody>
                                        <tr class="row mt-10">
                                            <td class="col-md-4"><span class="p_specification">Model  :</span> </td>
                                            <td class="col-md-8">
                                                <ul>
                                                    <li>${detail.productName}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr class="row mt-10">
                                            <td class="col-md-4"><span class="p_specification">Number Size  :</span> </td>
                                            <td class="col-md-8">
                                                <ul>
                                                    <li>${detail.specification.size} Inches</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr class="row mt-10">
                                            <td class="col-md-4"><span class="p_specification"> Weight  :</span> </td>
                                            <td class="col-md-8">
                                                <ul>
                                                    <li>${detail.specification.weight} g</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr class="row mt-10">
                                            <td class="col-md-4"><span class="p_specification">Color :</span> </td>
                                            <td class="col-md-8">
                                                <ul>
                                                    <li>${detail.specification.color}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr class="row mt-10">
                                            <td class="col-md-4"><span class="p_specification">Suitable for :</span> </td>
                                            <td class="col-md-8">
                                                <ul>
                                                    <li>Processing & Multitasking</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr class="row mt-10">
                                            <td class="col-md-4"><span class="p_specification">Processor Brand :</span> </td>
                                            <td class="col-md-8">
                                                <ul>
                                                    <li>${detail.productBrand} </li>
                                                </ul>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                        <div class="row row-underline">
                            <div class="col-md-6 d-flex justify-content-start"> <span class=" deal-text">Recent Product</span> </div>
                            <div class="col-md-6"> <a href="#" data-abc="true"> <span class="ml-auto view-all"></span> </a> </div>
                        </div>
                        <!-- Hiển thị danh sách sản phẩm đã xem gần đây -->
                        <div class="super_container">
                            <!-- Your content here -->

                            <div class="row">
                                <c:forEach var="viewedProduct" items="${viewedProductDetails}">
                                    <div class="col-md-4">
                                        <div class="card mb-3 similar_product">
                                            <div class="row g-0">
                                                <div class="col-md-6">
                                                    <a href="detail?productId=${viewedProduct.productId}">
                                                        <img src="${viewedProduct.productImage}" class="card-img-top" alt="${viewedProduct.productName}">
                                                    </a>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="card-body">
                                                        <h5 class="card-title">
                                                            <a href="detail?productId=${viewedProduct.productId}" class="text-decoration-none text-dark">${viewedProduct.productName}</a>
                                                        </h5>
                                                        <p class="card-text">
                                                            <fmt:formatNumber value="${viewedProduct.productPrice}" type="currency" currencySymbol="VND"/>
                                                        </p>
                                                        <p class="btn btn-success w-100">
                                                            <a href="detail?productId=${viewedProduct.productId}" class="text-decoration-none text-white">View Details</a>
                                                        </p>
                                                        <!-- Add other product details here if needed -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                    </div>
                </div>
                <br>
                <div class="card text-center">
                    <div class="card-header">
                        <h2>Feedback Product</h2>
                    </div>
                    <c:if test="${requestScope.totalFeedback == 0}">
                        <p class="card-text"><strong>Be the first to review this product.</strong></p>
                    </c:if>
                </div>

                <div class="container">
                    <c:if test="${requestScope.totalFeedback > 0}">
                        <div id="reviews" class="review-section">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <h4 class="m-0">Total Feedback: ${requestScope.totalFeedback}</h4>
                            </div>
                        </div>
                    </c:if>

                    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
                    <c:forEach items="${requestScope.feedback}" var="i" varStatus="loop">
                        <div class="review-list">
                            <ul>
                                <li>
                                    <div class="d-flex">
                                        <div class="right">
                                            <div class="row">
                                                <div class="col-6">
                                                    <h4>
                                                        ${i.userName}
                                                        <span class="gig-rating text-body-2">
                                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1792 1792" width="15" height="15">
                                                            <path
                                                                fill="currentColor"
                                                                d="M1728 647q0 22-26 48l-363 354 86 500q1 7 1 20 0 21-10.5 35.5t-30.5 14.5q-19 0-40-12l-449-236-449 236q-22 12-40 12-21 0-31.5-14.5t-10.5-35.5q0-6 2-20l86-500-364-354q-25-27-25-48 0-37 56-46l502-73 225-455q19-41 49-41t49 41l225 455 502 73q56 9 56 46z"
                                                                ></path>
                                                            </svg>
                                                            ${i.starRate}
                                                        </span>
                                                    </h4>
                                                    <div class="review-description">
                                                        <p>
                                                            ${i.feedbackDescription}
                                                        </p>
                                                    </div>
                                                    <span class="publish py-3 d-inline-block w-100">${i.feedbackDate}</span>
                                                </div>
                                                <div style="text-align: right" class="col-6">
                                                        <c:choose>
                                                            <c:when test="${i.feedbackImage != null}">
                                                                <img src="${contextPath}/processImageFeedbackList?id=${i.feedbackId}"
                                                                     class="avatar img-circle img-thumbnail" 
                                                                     alt="feedback-img" id="previewImage" 
                                                                     style="width: 50%;
                                                                object-fit: cover;" >
                                                        </c:when>
                                                        <c:when test="${i.feedbackImage == null}">
                                                            <strong>No Picture Provided</strong>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
                    <br>
                    <br>
                    <br>
                    <br>
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        // Retrieve viewed products from Local Storage
                        const viewedProducts = JSON.parse(localStorage.getItem('viewedProducts')) || [];
                        const products = document.querySelectorAll('.similar_product');

                        products.forEach(product => {
                            const productId = product.querySelector('a').getAttribute('href').split('=')[1];
                            if (viewedProducts.includes(productId)) {
                                product.classList.add('viewed');
                            }
                        });
                    });

                </script>
        </body>                                                                                       
    </html>
