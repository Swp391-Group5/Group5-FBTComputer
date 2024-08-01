<%-- 
    Document   : viewBlog
    Created on : Jun 15, 2024, 8:57:14 PM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>View Blog</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .blog-content {
                white-space: pre-line; /* Hiển thị nội dung blog có định dạng */
            }
            .thumbnail {
                max-width: 100%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Blog Details</h2>

            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <td>${blog.blogid}</td>
                </tr>
                <tr>
                    <th>Title</th>
                    <td>${blog.blogtitle}</td>
                </tr>
                <tr>
                    <th>Date</th>
                    <td>${blog.blogdate}</td>
                </tr>
                <tr>
                    <th>Thumbnail</th>
                    <td><img src="${blog.blogthumnail}" alt="Thumbnail" class="thumbnail"></td>
                </tr>
                <tr>
                    <th>Admin ID</th>
                    <td>${admin.adminName}</td>
                </tr>
                <tr>
                    <th>Category ID</th>
                    <td>${category.categoryName}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>${blog.blogstatus == 1 ? 'Active' : 'Hidden'}</td>
                </tr>
                <tr>
                    <th>Image</th>
                    <td><img src="${blog.img}" alt="Image" class="thumbnail"></td>
                </tr>
                <tr>
                    <th>URL</th>
                    <td>${blog.url}</td>
                </tr>
                <tr>
                    <th>Short desc</th>
                    <td>${blog.shortDesc}</td>
                </tr>
            </table>
            <div style="background: gray; padding: 10px; color: #fff" class="mb-2">
                <h3>Detail blog</h3>
                ${blog.blogcontent}
            </div>
            <a href="managerBlog" class="btn btn-primary">Back to Blog List</a>
        </div>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
