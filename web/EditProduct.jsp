<%-- 
    Document   : EditProduct
    Created on : Jul 6, 2024, 9:33:07 PM
    Author     : admin
--%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">



        <!-- Font Awesome for icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <!-- CKEditor -->
        <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>

        <!-- Your custom styles -->
        <style>
            /* Custom styles */
            .remove-btn {
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

            .preview {
                display: flex;
                flex-wrap: wrap;
            }

            .preview img {
                border-radius: 15px;
                border-color: black;
                height: auto;
                max-width: 350px;
                max-height: 284px;
                margin: 10px;
                object-fit: contain;
                background: black;
            }

            .img-wrapper {
                position: relative;
                display: inline-block;
            }
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
    </head>
    <body>
        <jsp:include page="includes/navbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>

            <div class="container mt-5">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="edit-product" method="post" enctype="multipart/form-data">
                            <div class="modal-header">
                                <h4 class="modal-title">Edit Product</h4>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label class="form-label">Product ID</label>
                                    <input name="pid" type="number" class="form-control" value="${list.productId}" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Name</label>
                                <input name="name" type="text" class="form-control" value="${list.productName}">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Description</label>
                                <textarea name="description" id="description" class="form-control">${list.productDescription}</textarea>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Price</label>
                                    <input name="price" type="number" class="form-control" value="${list.productPrice}" oninput="formatNumber(this)">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Quantity</label>
                                    <input name="quantity" type="number" class="form-control" value="${list.productQuantity}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Brand</label>
                                    <input name="brand" type="text" class="form-control" value="${list.productBrand}">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label class="form-label">Category:</label>
                                    <select  name="cateforyId"  class="form-select">
                                        <option value="" disabled selected>Select Category</option>
                                        <c:forEach var="c" items="${listC}">
                                            <option value="${c.categoryId}">${c.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>

                            <div  class=" mb-3">
                                <span for="fit" class="form-label">Old image</span>

                                <img class="mt-5 img-fluid"  src="${list.productImage}" alt="Slider Image" />  
                            </div>

                            <br>
                            <br>
                            <br>
                            <div class=" mb-3">
                                <label for="slider_image" class="form-label">Hình thu nhỏ</label>
                                <input onchange="checkImageFile()" type="file" id="files" name="files" accept="image/" class="form-control">
                                <div class="preview" id="preview">
                                    <button class="remove-btn">X</button> <!-- Nút bỏ chọn -->
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" onclick="window.location.href = 'manager-product'">Cancel</button>
                            <button type="submit" class="btn btn-success">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap Bundle JS (Popper included) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Optional JavaScript -->
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

    </body>
</html>
