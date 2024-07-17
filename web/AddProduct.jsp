<%-- 
    Document   : AddProduct
    Created on : Jul 6, 2024, 9:21:51 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                // Remove any non-numeric characters except dot (.) and limit to one decimal point
                var value = input.value.replace(/[^\d.]/g, '');

                // Split the value into integer and decimal parts
                var parts = value.split('.');
                var integerPart = parts[0];
                var decimalPart = parts.length > 1 ? '.' + parts[1] : '';

                // Format the integer part with commas
                var formattedIntegerPart = new Intl.NumberFormat().format(integerPart);

                // Combine integer and decimal parts
                input.value = formattedIntegerPart + decimalPart;
            }
        </script>
    </head>
    <body>
        <jsp:include page="includes/navbar.jsp"></jsp:include>
        <jsp:include page="includes/head.jsp"></jsp:include>

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
                                <input name="price" type="number" class="form-control" value="${list.productPrice}" oninput="formatNumber(this)">
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="number" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Brand</label>
                                <input name="brand" type="text" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Photo</label> <br/>
                                <input type="file" id="files" name="files" accept="image/" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="category">Category:</label>
                                <select name="categoryId" class="form-control">
                                    <option option value="" disabled selected>Category</option>
                                    <option value="1">Laptop</option>
                                    <option value="2">Mouse</option>
                                    <option value="3">Keyboard</option>
                                    <option value="4">Headphone</option>
                                </select>
                            </div>


                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" onclick="window.location.href = 'manager-product'" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                        </br>
                        </br>
                        </br>

                    </form>

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
