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
        <%@include file="/includes/head.jsp" %>
    </head>
    <style>
        .product-image {
            width: 50%;
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
        .star {
            font-size: 8rem;
            color: grey;
            cursor: pointer;
        }
        .filled {
            color: gold;
        }
        .description-cell, .image-cell {
            width: 50%;
            height: 100%;
            vertical-align: top;
        }

        .description-cell {
            padding-right: 10px;
        }

        .image-cell {
            text-align: center;
        }
    </style>
    <body>
        <%@include file="/includes/adminNavbar.jsp"%>
        <c:set var="i" value="${requestScope.feedbackOrderProduct}"/>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container"> 
            <h1 style="text-align: center">Feedback Details</h1>
            <br>
            <br>
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body" style="text-align: center">
                            <img src="${i.productImage}" alt="Product Image" class="product-image">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h2 class="product-title">${i.productName}</h2>
                    <p class="product-price">${i.getFormattedProductPrice()}đ</p>
                    <div class="product-description">
                        <p><strong>Description:</strong> ${i.productDescription}</p>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                </div>
            </div>
            <br>
            <br>
            <h2 style="text-align: center">FEEDBACK: #${i.feedbackId}</h2>
            <div class="row">
                <div style="text-align: center" class="col-md-12">
                    <span class="star" id="star1">&#9733;</span>
                    <span class="star" id="star2">&#9733;</span>
                    <span class="star" id="star3">&#9733;</span>
                    <span class="star" id="star4">&#9733;</span>
                    <span class="star" id="star5">&#9733;</span>
                </div>
                <c:set var="contextPath" value="${pageContext.request.contextPath}" />
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Feedback Description</th>
                            <th scope="col">Feedback Image</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="fw-normal">
                            <td class="description-cell">${i.feedbackDescription}</td>
                            <td class="image-cell"> 
                                <c:choose>
                                    <c:when test="${i.feedbackImage != null}">
                                        <img src="${contextPath}/processImageFeedbackList?id=${i.feedbackId}"
                                             class="avatar img-circle img-thumbnail" 
                                             alt="feedback-img" id="previewImage" 
                                             style="width: 100%; object-fit: cover;">
                                    </c:when>
                                    <c:otherwise>
                                        <strong>No Picture Provided</strong>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
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
    <br>
    <br>
    <br>
    <br>
    <br>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var rating = ${requestScope.feedbackOrderProduct.starRate};
            for (let i = 1; i <= rating; i++) {
                document.getElementById("star" + i).classList.add("filled");
            }
        });
    </script>
</html>
