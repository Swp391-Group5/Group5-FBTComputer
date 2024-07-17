<%-- 
    Document   : MKTDashboard
    Created on : Jun 23, 2022, 7:56:23 AM
    Author     : Veetu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/42.0.0/ckeditor5.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            /* Navbar */
            .navbar-custom {
                background-color: #0275d8; /* Màu nền */
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .navbar-custom .navbar-brand,
            .navbar-custom .navbar-nav .nav-link {
                color: #ffffff; /* Màu chữ */
            }

            .navbar-custom .navbar-nav .nav-link {
                padding: 8px 15px;
            }

            .navbar-custom .navbar-nav .nav-link:hover {
                color: #f8f9fa; /* Màu chữ khi hover */
            }

            /* Body và nội dung chính */
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(110deg, #f8f9fa 60%,  60%);
                padding-top: 56px; /* Để navbar không che phần đầu nội dung */
            }

            .container {
                margin-top: 20px;
                margin-bottom: 20px;
            }

            /* Form */
            .form-control {
                margin-bottom: 10px;
            }

            .btn-outline-dark {
                margin-right: 10px;
            }

            /* Responsive */
            @media (max-width: 768px) {
                .navbar-custom .navbar-nav .nav-link {
                    padding: 8px 10px;
                }
            }
        </style>
    </head>
    <body>
        <!-- Navbar -->
        <!--        <nav class="navbar navbar-expand-lg navbar-custom fixed-top">
                    <div class="container">
                        <a class="navbar-brand" href="#">SB Admin</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Dashboard</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Users</a>
                                </li>
                                 Thêm các mục menu khác tại đây 
                            </ul>
                        </div>
                    </div>
                </nav>-->

        <!-- Nội dung chính của trang -->
        <div class="container rounded bg-white mt-5 mb-5">
            <form action="add-slider" method="post" enctype="multipart/form-data">
                <div class="row" style="margin-top: 8%;">
                    <div class="col-md-8 mx-auto">
                        <div class="p-3 py-5">
                            <div class="mb-3">
                                <label for="slider_title" class="form-label">Tiêu đề</label>
                                <input type="text" class="form-control" id="slider_title" name="slider_title">
                            </div>
                            <div class="mb-3">
                                <label for="slider_c" class="form-label">Content</label>
                                <textarea class="form-control" id="sliderc" name="sliderc" rows="2"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="backlink" class="form-label">Link URL</label>
                                <textarea class="form-control" id="backlink" name="backlink" rows="2"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label><br>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="status" id="status1" value="0">
                                    <label class="form-check-label" for="status1">Ẩn</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="status" id="status2" value="1" checked>
                                    <label class="form-check-label" for="status2">Hiện</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label for="slider_image" class="form-label">Hình thu nhỏ</label>
                                <input type="file" id="files" name="files" accept=".jpg, .jpeg, .png, .gif" class="form-control">
                                <div class="preview" id="preview">
                                    <button class="remove-btn">X</button> <!-- Nút bỏ chọn -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mt-5 p-4 text-center">
                        <a href="slider-list" class="btn btn-outline-dark">Quay lại</a>
                        <input type="submit" class="btn btn-dark" value="Lưu">
                    </div>
                </div>
            </form>
        </div>
        <script>
            function handleFileSelect(event) {
                const input = event.target;
                const preview = input.nextElementSibling;
                const files = input.files;

                if (!files || files.length === 0) {
                    return;
                }

                const file = files[0];

                // Kiểm tra nếu không phải là file ảnh
                if (!file.type.startsWith('image/')) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi!',
                        text: 'Vui lòng chọn một tệp ảnh.',
                    });
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

        </script>
        <!-- Các tập tin JavaScript và các script đã có -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>

        <script>
            ClassicEditor
                    .create(document.querySelector('#sliderc'), {
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
    </body>
</html>
