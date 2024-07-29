<%-- 
    Document   : leaveFeedback
    Created on : Jul 10, 2024, 11:28:25 PM
    Author     : hungp
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Order</title>
        <%@include file="includes/head.jsp" %>
        <script src="ckeditor2/ckeditor.js"></script>
    </head>
    <style>
        .star {
            font-size: 5rem;
            color: grey;
            cursor: pointer;
        }
        .star.selected {
            color: gold;
        }
        .error-message {
            color: red;
            display: none;
        }
    </style>
    <body>
        <%@include file="includes/navbar.jsp"%>
        <br>
        <br>
        <br>
        <br>
        <br>
        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <div class="container">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="home">Home</a></li>
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="profile">My Account</a></li>
                    <li class="breadcrumb-item"><a style="text-decoration: none" href="myOrder">My Order</a></li>
                    <li class="breadcrumb-item active" aria-current="page"><strong>Leave Feedback</strong></li>
                </ol>
            </nav>
            <hr>
            <br>
            <br>
            <div style="text-align: center">
                <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getParameter("error") %>
                </div>
                <% } %>
                <h3>How satisfied are you with our product?<span style="color: red">*</span></h3>
                <form id="ratingForm" action="myOrder" method="get">
                    <span class="star" onclick="rate(1)">&#9733;</span>
                    <span class="star" onclick="rate(2)">&#9733;</span>
                    <span class="star" onclick="rate(3)">&#9733;</span>
                    <span class="star" onclick="rate(4)">&#9733;</span>
                    <span class="star" onclick="rate(5)">&#9733;</span>
                    <br>
                    <span id="ratingError" class="error-message">Please select a star rating.</span>
                    <br>
                    <h3>Please leave a feedback<span style="color: red">*</span></h3>
                    <br>
                    <textarea name="comment" id="comment" rows="10" cols="80" required>${feedbackOrderProduct.feedbackDescription}</textarea>
                    <script>
                        CKEDITOR.replace('comment');
                    </script>
                    <br>
                    <span id="commentError" class="error-message">Please leave a feedback.</span>
                    <br>
                    <h3>Please upload a picture of your product<span style="color: red">*</span></h3>
                    <br>
                    <input type="hidden" id="ratingValue" name="rating" value="0">
                    <input type="hidden" name="id" value="${requestScope.orderId}">
                    <input type="hidden" name="action" value="leaveFeedback">
                </form>

                <form action="uploadImageFeedback" id="frmUploadPhoto" class="mt-2" method="post" enctype="multipart/form-data">
                    <label for="avatarInput" class="custom-file-upload btn btn-block btn-light">
                        <i class="fa-regular fa-image"></i>
                        <input type="file" id="feedbackInput" accept="image/*" name="feedbackInput" />
                    </label>
                    <input type="hidden" name="id" value="${requestScope.orderId}">
                    <button type="submit" id="uploadAvatarButton" class="btn btn-primary">Upload Image</button>
                </form>
                <span id="avatarError" class="error-message">Please upload a picture of your product.</span>
                <c:choose>
                    <c:when test="${feedbackOrderProduct.feedbackImage != null}">
                        <img src="${contextPath}/processImageFeedbackList?id=${feedbackOrderProduct.feedbackId}"
                             class="avatar img-circle img-thumbnail" 
                             alt="feedback-img" id="previewImage" 
                             style="width: 45%; object-fit: cover;" >
                    </c:when>
                    <c:when test="${feedbackOrderProduct.feedbackImage == null}">
                        <strong>No Picture Provided</strong>
                    </c:when>
                </c:choose>

                <br>
                <br>
                <br>

                <button type="button" onclick="submitRating()" class="btn btn-primary p-5">Submit</button>

            </div>
        </div>
        <br>
        <br>
        <br>
        <%@include file="includes/footer.jsp"%>
        <%@include file="includes/finish.jsp"%>
    </body>
    <script>
        function rate(rating) {
            var stars = document.getElementsByClassName('star');
            for (var i = 0; i < stars.length; i++) {
                if (i < rating) {
                    stars[i].classList.add('selected');
                } else {
                    stars[i].classList.remove('selected');
                }
            }
            document.getElementById('ratingValue').value = rating;
        }

        function submitRating() {
            document.getElementById('ratingForm').submit();
//            document.getElementById('frmUploadPhoto').submit();
        }
    </script>
</html>
