<%-- 
    Document   : Warranty
    Created on : Jul 14, 2024, 9:03:20 PM
    Author     : quanb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="includes/head.jsp" %>
        <link rel="stylesheet" href="css/main.css"/>
        <style>
            .container{
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
            }
            .container form{
                width: 500px;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19)
            }
            .invalid-feedback {
                display: none;
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h3 class="text-center">Warranty</h3>
            <hr>
            <form action="SaveWarranty" id="myForm" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="orderHistoryDetailId">OrderHistoryDetailId: </label>
                    <input type="text" name="orderHistoryDetailId" value="${orderHistoryDetailId}" class="form-control" readonly>   
                </div>
                <div class="form-group">
                    <label for="serialNumber">SerialNumber: </label>
                    <input type="text" name="serialNumber" value="${serialNumber}" class="form-control" readonly>   
                </div>
                <div class="form-group">
                    <label for="name">Product Name:</label>      
                    <input type="text" id="productname" name="productname" class="form-control">   
                </div> 
                <div class="form-group">
                    <label for="name">Gmail:</label>      
                    <input type="text" id="email" name="email" class="form-control">   
                </div> 
                
                    <div class="form-group">
                        <label for="phoneNumber">Phone Number:</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" pattern="\d{10}" required>
                        <div class="invalid-feedback" id="phoneNumberError" style="display:none; color: red;">
                            Please enter a valid 10-digit phone number.
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="textInput">CauseError:</label>
                        <input type="text" id="textInput" name="textInput" class="form-control" maxlength="36" required>
                        <div class="invalid-feedback" id="textInputError" style="display:none; color: red;">
                            Please enter a text with a maximum of 36 characters.
                        </div>
                    </div>

                  
                                        
                <div class="form-group">                                                                                           
                    <label>Photo</label> <br/>
                    <input type="file" id="fileInput" name="fileInput" accept="image/jpeg, image/png" class="form-control">
                    <c:if test="${not empty error}">
                        <span style="color: red;">${error}</span>
                    </c:if>
                </div>

                <button onClick="alert('Received information. Please check the process in Mail')" type="submit" class="btn btn-primary">
                    Submit
                </button>
            </form>
        </div>
        <script>
            document.querySelector('form').addEventListener('submit', function (event) {
                const phoneNumberInput = document.getElementById('phoneNumber');
                const invalidFeedback = phoneNumberInput.nextElementSibling;
                const phoneNumber = phoneNumberInput.value;

                const phoneNumberPattern = /^\d{10}$/;

                if (!phoneNumberPattern.test(phoneNumber)) {
                    event.preventDefault(); // Prevent form submission
                    invalidFeedback.style.display = 'block'; // Show error message
                    phoneNumberInput.classList.add('is-invalid'); // Add invalid class
                } else {
                    invalidFeedback.style.display = 'none'; // Hide error message
                    phoneNumberInput.classList.remove('is-invalid'); // Remove invalid class
                }
            });
        </script>
        <script>
            document.getElementById('myForm').addEventListener('submit', function (event) {
                event.preventDefault(); // Ngăn chặn hành động gửi form mặc định

                var phoneNumber = document.getElementById('phoneNumber');
                var textInput = document.getElementById('textInput');
                var phoneNumberError = document.getElementById('phoneNumberError');
                var textInputError = document.getElementById('textInputError');

                var isValid = true;

                // Kiểm tra số điện thoại
                if (!/^\d{10}$/.test(phoneNumber.value)) {
                    phoneNumberError.style.display = 'block'; // Hiển thị thông báo lỗi số điện thoại
                    isValid = false;
                } else {
                    phoneNumberError.style.display = 'none'; // Ẩn thông báo lỗi số điện thoại
                }

                // Kiểm tra văn bản
                if (textInput.value.length > 36) {
                    textInputError.style.display = 'block'; // Hiển thị thông báo lỗi văn bản
                    isValid = false;
                } else {
                    textInputError.style.display = 'none'; // Ẩn thông báo lỗi văn bản
                }

                // Nếu không có lỗi, gửi form
                if (isValid) {
                    this.submit();
                }
            });
        </script>
    </body>
</html>
