<%-- 
    Document   : OrderDetail
    Created on : Jun 5, 2024, 11:40:07 PM
    Author     : hungp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="includes/head.jsp" %>
    </head>
    <style>
        .product-image {
            width: 100%;
            height: auto;
            background-color: #f8f9fa;
        }
        .product-details {
            margin-top: 20px;
        }
        .product-price {
            font-size: 24px;
            color: #ff0000;
        }
        .product-description {
            margin-top: 10px;
        }
        .star-rating {
            direction: rtl;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5rem;
        }

        .star-rating input[type="radio"] {
            display: none;
        }

        .star-rating label {
            color: #ddd;
            font-size: 2em;
            cursor: pointer;
            padding: 0 5px;
        }

        .star-rating input[type="radio"]:checked ~ label {
            color: #f5b301;
        }

        .star-rating label:hover,
        .star-rating label:hover ~ label {
            color: #f5b301;
        }

        .modal-body img {
            max-width: 100px;
            height: auto;
            margin: auto;
        }

        #emojiContainer {
            font-size: 3em;
            margin-top: 10px;
        }
    </style>
    <body>
        <%@include file="includes/navbar.jsp"%>
        <c:set var="i" value="${requestScope.orderDetail}"/>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="home">Home</a></li>
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="profile">My Account</a></li>
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="myOrder">My Order</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><strong>Order Detail</strong></li>
                </ol>
            </nav>
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
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Serial Number</th>
                            <th scope="col">Category</th>
                            <th scope="col">Product Name</th>
                            <th scope="col">Brand</th>
                            <th scope="col">Price</th>
                            <th scope="col">View</th>
                            <th scope="col">Feedback</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:if test="${requestScope.orderDetail != null}">
                        <c:forEach items="${requestScope.orderDetail}" var="i" varStatus="loop">
                            <tr class="fw-normal" >
                                <td>${i.serialNumber}</td>
                                <td>${i.categoryName}</td>
                                <td>${i.productName}</td>
                                <td>${i.productBrand}</td>
                                <td>${i.getFormattedProductPrice()}</td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/detail?productId=${i.productId}" 
                                       class="text-primary" title="View" data-toggle="tooltip">
                                        <button class="btn btn-light text-primary"><i class="fa-solid fa-eye"></i></button>
                                    </a>
                                </td>

                                <td>
                                    <!-- Add feedback button here -->
                                    <button type="button" class="btn btn-light text-primary feedback-btn" data-toggle="modal" data-target="#feedbackModal" data-order-id="${i.serialNumber}" data-product-name="${i.productName}" data-product-image="${i.getProductImage()}">
                                        Feedback
                                    </button>
                                    <!-- Feedback Modal -->
                                    <div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <form action="feed-back" method="post" enctype="multipart/form-data">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="feedbackModalLabel">Product Review</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body text-center">
                                                        <!-- Hidden fields to include OrderId and ProductId -->
                                                        <input type="hidden" id="orderId" name="orderId" value="${orderId}">
                                                        <input type="hidden" id="productId" name="productId" value="${productId}">

                                                        <h5 class="mt-2" id="productName">${productName}</h5>

                                                        <!-- Star Rating Input -->
                                                        <div class="star-rating">
                                                            <input type="radio" id="5-stars" name="StarRate" value="5"  />
                                                            <label for="5-stars" class="star">&#9733;</label>
                                                            <input type="radio" id="4-stars" name="StarRate" value="4"  />
                                                            <label for="4-stars" class="star">&#9733;</label>
                                                            <input type="radio" id="3-stars" name="StarRate" value="3" />
                                                            <label for="3-stars" class="star">&#9733;</label>
                                                            <input type="radio" id="2-stars" name="StarRate" value="2"  />
                                                            <label for="2-stars" class="star">&#9733;</label>
                                                            <input type="radio" id="1-star" name="StarRate" value="1" />
                                                            <label for="1-star" class="star">&#9733;</label>
                                                        </div>
                                                        <div id="emojiContainer"></div>
                                                        <textarea class="form-control" id="feedbackText" name="feedbackText" placeholder="Share your thoughts about the product..." rows="3" required></textarea>
                                                        <input type="file" class="form-control-file mt-2" id="feedbackImage" name="feedbackImage">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        <button type="submit" class="btn btn-danger" id="submitFeedback">Submit</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>


                                </td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </c:if>
            </table>
            <div>
                <p>Showing ${start} to ${end} of ${totalProducts} entries</p>
            </div>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="myOrder?pageSize=${pageSize}&index=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">«</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${num}" var="i">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="myOrder?pageSize=${pageSize}&index=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < num}">
                    <li class="page-item">
                        <a class="page-link" href="myOrder?pageSize=${pageSize}&index=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">»</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
        <script>
                                                                $(document).ready(function () {
                                                                    $('#feedbackModal').on('show.bs.modal', function (event) {
                                                                        var button = $(event.relatedTarget); // Button that triggered the modal
                                                                        var orderId = button.data('order-id');
                                                                        var productName = button.data('product-name');
                                                                        var productImage = button.data('product-image');

                                                                        // Update the modal's content with product information
                                                                        var modal = $(this);
                                                                        modal.find('.modal-body #productImage').attr('src', productImage);
                                                                        modal.find('.modal-body #productName').text(productName);
                                                                        modal.find('form').attr('action', 'feed-back?orderId=' + ${requestScope.orderId}});
                                                                    });
                                                                });

        </script>
    </body>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <%@include file="includes/footer.jsp"%>
    <%@include file="includes/finish.jsp"%>
</html>
