<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="molder.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manager Product</title>

        <link rel="icon" href="images/quanly.png" type="images/x-icon"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>


        <style>
            .remove-btn {
                position: absolute;
                top: 0;
                right: 0;
                background-color: red;
                color: white;
                border: none;
                cursor: pointer;
                font-size: 1em;
                padding: 5px 10px;
                border-radius: 15%;
                transform: translate(50%, -50%);
                display: inline;
            }
        </style>
        <script>
            function formatNumber(input) {
                var value = input.value.replace(/\D/g, ''); // Xóa bỏ tất cả các ký tự không phải số
                var formattedValue = addCommas(value); // Sử dụng hàm addCommas để thêm dấu phân cách
                input.value = formattedValue;
            }

            function addCommas(numberString) {
                // Chia nhỏ số theo mỗi 3 chữ số và thêm dấu phân cách
                return numberString.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }
        </script>
    </head>
    <body>
        <jsp:include page="includes/adminNavbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>

            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            <div class="container">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="add" method="post"enctype="multipart/form-data">
                            <input type="hidden" name="pictureOld" value="${list.productImage}"/>
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button class="remove-btn">X</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" value="${product.productName}">
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" id="description" class="form-control" ></textarea>

                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" oninput="formatNumber(this)">
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="number" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Brand</label>
                                <select name="brand" class="form-control">
                                    <c:forEach items="${brands}" var="brand">
                                        <option value="${brand}">${brand}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Photo</label> <br/>
                                <input type="file" id="fileInput" name="fileInput" accept="image/jpeg, image/png" class="form-control">
                                <c:if test="${not empty error}">
                                    <span style="color: red;">${error}</span>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="category">Category:</label>
                                <select name="categoryId" class="form-control">
                                    <option option value="" disabled selected>Category</option>
                                    <c:forEach var="category" items="${listC}">
                                        <option value="${category.categoryId}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>



                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" onclick="window.location.href = 'manager-product'" value="Cancel">
                            <input onClick="alert('Add Product Success')"  type="submit" class="btn btn-success" value="Add">
                            
                    
                        </div>
                        </br>
                        </br>
                        </br>

                    </form>
                    <script>
                        function checkImageFile() {
                            var fileInput = document.getElementById('files');
                            var filePath = fileInput.value;
                            var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
                            if (!allowedExtensions.exec(filePath)) {
                                alert('Chỉ cho phép tải lên các file có định dạng: .jpeg/.jpg/.png/.gif');
                                fileInput.value = '';
                                return false;
                            }
                        }
                    </script>
                    <script>

                        function handleFileSelect(event) {
                            const input = event.target;
                            const preview = input.nextElementSibling;
                            const files = input.files;

                            if (!files || files.length === 0) {
                                return;
                            }

                            const file = files[0];


                            if (!file.type.startsWith('image/')) {
                                return;
                            }


                            const reader = new FileReader();


                            reader.onload = function (e) {
                                const imgSrc = e.target.result;


                                const img = document.createElement('img');
                                img.src = imgSrc;


                                const removeBtn = document.createElement('button');
                                removeBtn.innerHTML = 'X';
                                removeBtn.className = 'remove-btn';
                                removeBtn.onclick = function () {

                                    preview.innerHTML = '';
                                    input.value = '';
                                };


                                const imageWrapper = document.createElement('div');
                                imageWrapper.className = 'img-wrapper';
                                imageWrapper.appendChild(img);
                                imageWrapper.appendChild(removeBtn);


                                while (preview.firstChild) {
                                    preview.removeChild(preview.firstChild);
                                }


                                preview.appendChild(imageWrapper);
                            };


                            reader.readAsDataURL(file);
                        }


                        const fileInputs = document.querySelectorAll('input[type="file"]');
                        fileInputs.forEach(function (input) {
                            input.addEventListener('change', handleFileSelect);
                        });
                    </script>
                    <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>

                    <script>
                        ClassicEditor
                                .create(document.querySelector('#description'), {
                                    ckfinder: {
                                        uploadUrl: 'https://ckeditor.com/apps/ckfinder/3.5.0/core/connector/php/connector.php?command=QuickUpload&type=Files&responseType=json'
                                    },
                                    toolbar: [
                                        'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote', 'insertTable', 'undo', 'redo', 'uploadImage'
                                    ],
                                    image: {
                                        toolbar: [
                                            'imageTextAlternative', 'imageStyle:full', 'imageStyle:side'
                                        ]
                                    }
                                })
                                .then(editor => {
                                    console.log(editor);
                                })
                                .catch(error => {
                                    console.error(error);
                                });
                    </script>

                </div>
            </div>
        </div>             
    </body>
</html>
