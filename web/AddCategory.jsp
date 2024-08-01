<%-- 
    Document   : AddCategory
    Created on : May 19, 2024, 10:33:55 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
        <%@include file="includes/head.jsp" %>
        <style>
            .container {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h3 {
                font-size: 1.5rem;
                margin-top: 20px;
            }

            p {
                margin-bottom: 10px;
            }

            form {
                text-align: center;
            }

            input[type="number"],
            input[type="text"],
            button,
            select {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                border-radius: 5px;
                box-sizing: border-box;
            }

            button {
                font-size: 1rem;
            }

            .btn-info {
                margin-top: 20px;
            }


        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <a class="navbar-brand" href="home">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link" href="list-cate">List Category</a>
                    <a class="nav-item nav-link" href="#">Add Category</a>
                </div>
            </div>
        </nav>
        <br>
        <div class="container">
            <h3 class="mt-3" style="text-align: center;">Add Category</h3>
            <form action="add-cate" class="text-center">
                <div class="form-group">
                    <label for="cname">Category Name</label>
                    <input type="text" id="cname" name="cname" class="form-control" placeholder="Enter Category Name">
                </div>
                <div class="form-group">
                    <label for="cstatus">Status</label>
                    <select id="cstatus" name="cs" class="form-control">
                        <option value="0">Inactive</option>
                        <option value="1">Active</option>
                    </select>
                </div>
                <button class="btn btn-info" type="submit">Submit</button>
            </form>
            <h4 style="color: red; text-align: center">${requestScope.error}</h4>
        </div>
    </body>
</html>
