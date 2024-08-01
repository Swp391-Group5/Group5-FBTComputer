<%-- 
    Document   : editBlog
    Created on : Jun 15, 2024, 8:23:24 PM
    Author     : HP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Blog</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Edit Blog</h2>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            <form action="managerBlog?action=edit" method="post" enctype="multipart/form-data">
                <input type="hidden" name="blogid" value="${blog.blogid}">
                <div class="form-group">
                    <label for="blogtitle">Title</label>
                    <input type="text" class="form-control" id="blogtitle" name="blogtitle" value="${blog.blogtitle}" required>
                </div>
                <div class="form-group">
                    <label for="shortDesc">Short description</label>
                    <textarea class="form-control" id="shortDesc" name="shortDesc" rows="5" required>${blog.shortDesc}</textarea>
                </div>
                <div class="form-group">
                    <label for="blogcontent">Content</label>
                    <textarea class="form-control" id="blogcontent" name="blogcontent" rows="5" required>${blog.blogcontent}</textarea>
                </div>
                <div class="form-group">
                    <label for="blogthumbnail">Thumbnail URL</label>
                    <input type="hidden" class="form-control" name="oldThumbnail" value="${blog.blogthumnail}" required>
                    <input type="file" class="form-control" id="blogthumbnail" name="blogthumbnail" value="${blog.blogthumnail}>
                           </div>
                           <div class="form-group">
                    <label for="category">Category</label>
                    <select class="form-control" id="category" name="category" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}" ${category.categoryId == blog.categoryblogid ? "selected" : ""}>${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="blogstatus">Status</label>
                    <select class="form-control" id="blogstatus" name="blogstatus" required>
                        <option value="1" ${blog.blogstatus == 1 ? "selected" : ""}>Active</option>
                        <option value="0" ${blog.blogstatus == 0 ? "selected" : ""}>Hidden</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="img">Image URL</label>
                    <input type="hidden" class="form-control" name="oldImg" value="${blog.img}" required>
                    <input type="file" class="form-control" id="img" name="img" onchange="previewImage(event)" accept="image/*" value="${blog.img}">
                </div>
                <div class="form-group">
                    <label>Preview:</label>
                    <img id="imagePreview" src="#" alt="Image Preview" style="max-width: 200px; max-height: 200px;">
                </div>
                
                <div class="form-group">
                    <label for="url">URL</label>
                    <input type="text" class="form-control" id="url" name="url" value="${blog.url}" required>
                </div>
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>

        <script>
            ClassicEditor
                    .create(document.querySelector('#blogcontent'), {
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
