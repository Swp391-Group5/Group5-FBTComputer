
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang ="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomePage</title>
        <jsp:include page="includes/navbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>
    <br>
    <br>
    <br>
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


                .no-products {
                    text-align: center;
                    margin: 20px 0;
                    font-size: 1.2em;
                    color: red;
                    .carousel-item img {
                        height: 400px; /* Điều chỉnh chiều cao tùy theo nhu cầu */
                        object-fit: cover; /* Đảm bảo ảnh không bị méo */
                    }
                    .card-group {
                        display: flex;
                        justify-content: center; /* Canh giữa các card */
                        gap: 20px; /* Khoảng cách giữa các card */
                    }

                    .card {
                        flex: 1;
                        max-width: 18rem;
                        text-align: center;
                        height: 400px; /* Đặt chiều cao cố định cho card */
                        overflow: hidden; /* Ẩn nội dung dư thừa nếu có */
                    }
                    .card-body {
                        position: relative;
                        padding: 20px;
                        height: 100%; /* Đặt chiều cao của card-body là 100% */
                    }
                    .carousel-item {
                        height: 100%; /* Đặt chiều cao của carousel-item là 100% */
                    }

                    .carousel {
                        height: 100%; /* Đặt chiều cao của carousel là 100% */
                    }




                    .carousel-item img {
                        width: 100%;
                        height: auto;
                        object-fit: contain;
                    }

                </style>
            </head>
            <body>
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
                                 object-fit: contain;
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
                                 object-fit: contain;
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
                                 object-fit: contain;" src="https://bedbugtreatmenthouston.com/wp-content/uploads/2021/03/houston-two-year-bed-bug-treatment-warranty-1-300x240.png" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align:center">LONG-TERM WARRANTY</h5>
                                    <p class="card-text" style="text-align:center">Up to 60 days warranty</p>
                                </div>
                            </div>
                            <div class="card" style="width: 18rem;">
                            <img class="card-img-top" style="height:200px;
                                 width:auto;
                                 margin: auto;
                                 object-fit: contain;" src="https://thumbs.dreamstime.com/b/print-226957006.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title" style="text-align:center">EASY RETURNS</h5>
                                    <p class="card-text" style="text-align:center">Hassle-free returns within 30 days</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <br>
                <br>
                <br>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <li class="breadcrumb-item"><a href="#">Category</a></li>
                                    <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                                </ol>
                            </nav>


                        </div>
                    </div>
                </div>


                <div class="container">
                    <div class="row">

                        <jsp:include page="Left.jsp"></jsp:include>

                            <div class="col-sm-9">

                                <!-- Hiển thị thông báo nếu không có sản phẩm -->
                            <c:if test="${totalSize == 0}">
                                <div class="alert alert-danger" role="alert">
                                    <b>Không có sản phẩm nào được tìm thấy.</b>
                                </div>
                            </c:if>

                            <!-- Hiển thị lựa chọn số sản phẩm trên trang nếu có sản phẩm -->
                            <c:if test="${totalSize != 0}">
                                <div class="row mb-3">
                                    <form id="myform" action="home" class="form-inline">
                                        <label for="page-size-select" class="ps-3">Products per page:</label>
                                        <select name="pageSize" id="page-size-select" class="form-control" onchange="document.getElementById('myform').submit()">
                                            <option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
                                            <option value="10" <c:if test="${pageSize == 10}">selected</c:if>>10</option>
                                            <option value="15" <c:if test="${pageSize == 15}">selected</c:if>>15</option>
                                            <option value="20" <c:if test="${pageSize == 20}">selected</c:if>>20</option>
                                            </select>
                                            <input type="hidden" name="index" value="1" />
                                            <input type="hidden" name="productName" value="${pi}" />
                                        <noscript>
                                        <button type="submit" class="btn btn-primary">Go</button>
                                        </noscript>
                                    </form>

                                </div>

                                <div class="row">
                                    <c:forEach var="o" items="${listP}">
                                        <div class="col-12 col-md-6 col-lg-4">
                                            <div class="card mb-3">
                                                <img src="${o.productImage}" class="card-img-top image-clickable" alt="Card image cap" data-product-id="${o.productId}">
                                                <div class="card-body">
                                                    <h4 class="card-title">
                                                        <a href="detail?productId=${o.productId}" title="View Product" class="text-decoration-none">${o.productName}</a>
                                                    </h4>
                                                    <p class="card-text product-description">${o.productDescription}</p>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <p class="btn btn-danger w-100">
                                                                <fmt:formatNumber value="${o.productPrice}" type="currency" currencySymbol="VND"/>
                                                            </p>
                                                        </div>
                                                        <!-- Thêm margin top để tách biệt giữa hai dòng -->
                                                        <div class="col-md-12">
                                                            <div class="text-center">

                                                                <c:choose>

                                                                    <c:when test="${o.productQuantity == 0}">
                                                                        <p class="btn w-100">
                                                                            <button class="btn btn-secondary w-100" disabled>Sold Out</button>
                                                                        </p>

                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <button class="btn w-100">
                                                                            <a href="cartprocess?productId=${o.productId}" class="btn bg-warning">
                                                                                <i class="fa-solid fa-cart-shopping"></i> <!-- Biểu tượng giỏ hàng từ Bootstrap Icons -->
                                                                                Add to Cart
                                                                            </a>                                                                    
                                                                        </button>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <!-- Hiển thị phạm vi và tổng số sản phẩm -->
                                <div class="row">
                                    <c:if test="${totalSize != 0}">
                                        <p>Showing ${start} to ${end} of ${totalProducts} entries</p>
                                    </c:if>
                                </div>

                                <!-- Hiển thị phân trang nếu có sản phẩm -->
                                <nav aria-label="Page navigation">
                                    <ul class="pagination">
                                        <!-- Check if we are not on the first page, then provide a 'Previous' link -->
                                        <c:if test="${currentPage > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="home?pageSize=${pageSize}&index=${currentPage - 1}&maxRange=${maxRange}&minRange=${minRange}&sortBy=${sortBy}&categoryId=${categoryId}&brand=${brand}&productName=${productName}" aria-label="Previous">
                                                    <span aria-hidden="true">«</span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <!-- Loop through the number of pages and create a page link for each -->
                                        <c:forEach begin="1" end="${num}" var="i">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                <a class="page-link" href="home?pageSize=${pageSize}&index=${i}&minRange=${minPrice}&maxRange=${maxPrice}&sortBy=${sortBy}&categoryId=${categoryId}&brand=${brand}&productName=${productName}">${i}</a>
                                            </li>
                                        </c:forEach>

                                        <!-- Check if we are not on the last page, then provide a 'Next' link -->
                                        <c:if test="${currentPage < num}">
                                            <li class="page-item">
                                                <a class="page-link" href="home?pageSize=${pageSize}&index=${currentPage + 1}&minRange=${minPrice}&maxRange=${maxPrice}&sort=${sort}&categoryId=${categoryId}&brand=${brand}&productName=${productName}" aria-label="Next">
                                                    <span aria-hidden="true">»</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>

                            </c:if>

                        </div>

                    </div>
                </div>


                <div class="container">
                    <div class="row" style="margin-top:50px">            
                        <div class="col-sm-12">
                            <div id="content" class="row">
                                <div class=" col-12 col-md-12 col-lg-6">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt" style="text-align:center;
                                        font-size:18pt;
                                        color:#b57b00;">About Shop</h4>
                                    <h2 class="card-title show_txt" style="text-align:center;
                                        font-size:24pt;">Laptop Family</h2>
                                    <p style="text-align:center;">With years of reputable experience specializing in providing high-quality replica sneakers for both men and women, we have decided to transition our focus to offering laptops. 
                                        Like Auth với chất lượng đảm bảo và giá tốt nhất tại Hà Nội.</p>
                                    <p>Are you in need of a stylish and trendy laptop from top brands but don't have enough budget to afford the genuine ones? Look no further than Laptops4U – where you can fulfill your desires without breaking the bank. With just a fraction of the cost compared to the authentic ones sold in stores, you can still get yourself a quality laptop ranging from 1:1 replicas to super high-end ones..</p>                  
                                </div>  
                            </div>
                            <div class=" col-12 col-md-12 col-lg-6">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29794.87468455396!2d105.79391227910159!3d21.018303400000004!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ac7768d4c1e7%3A0xb583b6d7f4e2a599!2sFPT%20Shop!5e0!3m2!1svi!2sus!4v1716273471328!5m2!1svi!2sus" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade" title="Main content" name="contents"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
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
                document.addEventListener('DOMContentLoaded', (event) => {
                    var myCarousel = document.querySelector('#carouselExampleDark')
                    var carousel = new bootstrap.Carousel(myCarousel, {
                        interval: 2000,
                        wrap: true
                    })
                });

            </script>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    // Add click event listener to product images
                    const images = document.querySelectorAll('.image-clickable');
                    images.forEach(image => {
                        image.addEventListener('click', function () {
                            const productId = image.getAttribute('data-product-id');
                            storeViewedProduct(productId);
                        });
                    });

                    // Store product ID in Local Storage
                    function storeViewedProduct(productId) {
                        let viewedProducts = localStorage.getItem('viewedProducts');
                        if (viewedProducts) {
                            viewedProducts = JSON.parse(viewedProducts);
                            if (!viewedProducts.includes(productId)) {
                                viewedProducts.push(productId);
                            }
                        } else {
                            viewedProducts = [productId];
                        }
                        localStorage.setItem('viewedProducts', JSON.stringify(viewedProducts));
                    }
                });
            </script>
            <%@include  file="includes/footer.jsp" %>
        </body>
    </html>

