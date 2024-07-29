<%-- 
    Document   : addNewBlog
    Created on : Jun 15, 2024, 8:01:54 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create Blog</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Create New Blog</h2>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            <form action="managerBlog?action=add" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="blogtitle">Title</label>
                    <input type="text" class="form-control" id="blogtitle" name="blogtitle" required>
                </div>
                <div class="form-group">
                    <label for="shortDesc">Short description</label>
                    <textarea class="form-control" id="shortDesc" name="shortDesc" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    <label for="blogcontent">Content</label>
                    <textarea class="form-control" id="blogcontent" name="blogcontent" rows="5" required></textarea>
                </div>
                <!--                <div class="form-group">
                                    <label for="blogthumbnail">Thumbnail</label>
                                    <input type="file" class="form-control" id="blogthumbnail" name="blogthumbnail" required>
                                </div>-->
                <div class="form-group">
                    <label for="blogthumbnail">Thumbnail</label>
                    <input type="file" class="form-control" id="blogthumbnail" name="blogthumbnail" onchange="previewThumbnail(event)" accept="image/*" required>
                </div>
                <div class="form-group">
                    <label>Preview:</label>
                    <img id="thumbnailPreview" src="#" alt="Thumbnail Preview" style="max-width: 200px; max-height: 200px;">
                </div>

                <div class="form-group">
                    <label for="category">Category ID</label>
                    <select class="form-control" id="category" name="category" required>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.categoryId}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="blogstatus">Status</label>
                    <select class="form-control" id="blogstatus" name="blogstatus" required>
                        <option value="true">Active</option>
                        <option value="false">Hidden</option>
                    </select>
                </div>
                <!--                <div class="form-group">
                                    <label for="img">Image URL</label>
                                    <input type="file" class="form-control" id="img" name="img" required>
                                </div>-->
                <div class="form-group">
                    <label for="img">Image URL</label>
                    <input type="file" class="form-control" id="img" name="img" onchange="previewImage(event)" accept="image/*" required>
                </div>
                <div class="form-group">
                    <label>Preview:</label>
                    <img id="imagePreview" src="#" alt="Image Preview" style="max-width: 200px; max-height: 200px;">
                </div>

                <div class="form-group">
                    <label for="url">URL</label>
                    <input type="text" class="form-control" id="url" name="url" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
        <script>
                        CKEDITOR.replace('blogcontent');
        </script>
        <script>
            function previewThumbnail(event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('thumbnailPreview');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            }
            function previewImage(event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('imagePreview');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            }


        </script>
    </body>
</html>
