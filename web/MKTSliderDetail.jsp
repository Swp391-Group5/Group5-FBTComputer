<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="Dashboard for managing sliders" />
        <meta name="author" content="Veetu" />
        <title>Dashboard - Slider Management</title>

        <!-- Latest Bootstrap CSS and JS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Font Awesome -->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Custom CSS -->
        <style>
            body {
                background-color: #e0f7fa; /* Soft blue background */
            }
            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .text-center {
                margin-bottom: 20px;
            }
            .btn-outline-dark {
                border-color: #0275d8;
                color: #0275d8;
            }
            .btn-outline-dark:hover {
                background-color: #0275d8;
                color: #fff;
            }
            .btn-dark {
                background-color: #0275d8;
                border-color: #0275d8;
            }
            .btn-dark:hover {
                background-color: #025aa5;
                border-color: #025aa5;
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

            .main-container {
                width: 795px;
                margin-left: auto;
                margin-right: auto;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <div class="groundy" id="layoutSidenav_content">
                <main>
                    <div class="container rounded bg-white mt-5 mb-5">
                        <form action="update-slider" method="post" enctype="multipart/form-data">
                            <div class="row" style="margin-top: 8%;">
                                <div class="p-4">
                                    <h4 class="text-center" >Chỉnh sửa Slider</h4>
                                </div>

                                <div class="col-md-8 ">
                                    <div class="p-3 py-5 ">
                                        <div class="col-md-12 mb-3">
                                            <input type="hidden" class="form-control" name="blogId" value="${Slider_Detail.blogId}">
                                        </div>
                                        <div class="col-md-12 mb-3">
                                            <label for="slider_title" class="form-label">Tiêu đề</label>
                                            <input type="text" id="blog_title" class="form-control" name="blog_title" value="${Slider_Detail.blogTitle}">
                                        </div>
                                        <div class="col-md-12  mb-3">
                                            <label for="slider_c" class="form-label">Content</label>

                                            <textarea class="form-control" id="blog_c" name="blog_c" rows="2">${Slider_Detail.blogContent}</textarea>


                                        </div>
                                        <div class="col-md-12 mb-3">
                                            <label for="backlink" class="form-label">link URL</label>
                                            <textarea class="form-control" id="url" name="url" rows="2">${Slider_Detail.url}</textarea>
                                        </div>
                                        <div  class="col-md-12 mb-3">
                                            <span for="fit" class="form-label">Old image</span>

                                            <img class="mt-5 img-fluid"  src="UPLOAD_IMAGE/${Slider_Detail.img}" alt="Slider Image" />  
                                        </div>

                                        <br>
                                        <br>
                                        <br>
                                        <div class="col-md-12 mb-3">
                                            <label for="slider_image" class="form-label">Hình thu nhỏ</label>
                                            <input onchange="checkImageFile()" type="file" id="files" name="files" accept="image/" class="form-control">
                                            <div class="preview" id="preview">
                                                <button class="remove-btn">X</button> <!-- Nút bỏ chọn -->
                                            </div>
                                        </div>

                                    </div>
                                </div>

                                <div class="mt-5 p-4 text-center">
                                    <a href="slider-list" class="btn btn-outline-dark">Quay lại</a>
                                    <button type="submit" class="btn btn-dark">Lưu</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </main>
            </div>
        </div>

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

        <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
           ClassicEditor
                   .create(document.querySelector('#blog_c'), {
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



    </body>
</html>
