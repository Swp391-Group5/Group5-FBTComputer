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
        /* Global reset and custom styles */
        body {
            background-color: #f0f0f0; /* Light gray background */
            color: #333; /* Dark gray text color */
            font-family: Arial, sans-serif; /* Example font */
            margin: 0;
            padding: 0;
        }

        /* Navbar styles */
        .navbar {
            background-color: #007bff; /* Bootstrap primary color */
            color: #fff; /* White text */
            padding: 10px 0;
        }

        .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
        }

        /* Page content styles */
        .container {
            max-width: 800px; /* Adjust as needed */
            margin: 20px auto;
            padding: 20px;
            background-color: #fff; /* White background for content */
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1); /* Shadow effect */
        }

        .form-control {
            border-radius: 5px;
            border-color: #ccc;
        }

        .form-label {
            font-weight: bold;
        }

        /* Image preview and upload styles */
        .preview {
            display: flex;
            flex-wrap: wrap;
            margin-top: 10px;
        }

        .preview img {
            max-width: 150px; /* Adjust as needed */
            max-height: 150px; /* Adjust as needed */
            margin: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .img-wrapper {
            position: relative;
            margin-right: 5px;
        }

        .remove-btn {
            position: absolute;
            top: 5px;
            right: 5px;
            background-color: red;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1em;
            padding: 5px 8px;
            border-radius: 50%;
        }

        /* Footer styles */
        .footer {
            background-color: #f0f0f0;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Your Logo</a>
            <!-- Navbar links, if needed -->
        </div>
    </nav>

    <!-- Page content -->
    <div class="container">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="edit-product" method="post" enctype="multipart/form-data">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Product</h4>
                    </div>
                    <div class="modal-body">
                        <!-- Your form fields -->
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
                                <input name="price" type="text" class="form-control" value="${list.productPrice}" oninput="formatNumber(this)">
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
                                <select name="categoryId" class="form-select">
                                    <option value="" disabled selected>Select Category</option>
                                    <c:forEach var="c" items="${listC}">
                                        <option value="${c.categoryId}">${c.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- Existing image display -->
                        <div class="mb-3">
                            <label for="current_image" class="form-label">Current Image</label>
                            <img class="mt-3 img-fluid" src="${list.productImage}" alt="Current Product Image">
                        </div>

                        <!-- Image upload -->
                        <div class="mb-3">
                            <label for="new_image" class="form-label">New Image</label>
                            <input onchange="checkImageFile()" type="file" id="new_image" name="new_image" accept="image/*" class="form-control">
                            <div class="preview mt-2" id="imagePreview">
                                <!-- Image preview area -->
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

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 Your Company. All rights reserved.</p>
        </div>
    </footer>

    <!-- Bootstrap Bundle JS (Popper included) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Optional JavaScript -->
    <script>
        // Your custom JavaScript, if any
    </script>
</body>
</html>
