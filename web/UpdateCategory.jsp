<%-- 
    Document   : UpdateCategory
    Created on : May 19, 2024, 9:16:03 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includes/head.jsp" %>
        <title>UpdateCategory</title>
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
        button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            font-size: 1rem;
        }
    </style>
</head>
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
        <h3 class="mt-3" style="text-align: center;">Update Category</h3>
        <form action="update-cate" class="text-center" method="post">
            <div class="form-group">
                <!--<label for="cid">ID</label>-->
                <input type="hidden" id="cid" name="cid" value="${cute.categoryId}" readonly class="form-control">
            </div>
            <div class="form-group">
                <label for="cstatus">Status</label>
                <select  name="cstatus" id="cstatus" class="form-control">
                    <option  value="0" ${cute.categoryStatus == 0 ? 'selected' : ''} >Inactive</option>
                    <option value="1" ${cute.categoryStatus == 1 ? 'selected' : ''} >Active</option>
                </select>
            </div>
            <div class="form-group">
                <label for="cname">Category Name</label>
                <input type="text" id="cname" name="cname" value="${cute.categoryName}" class="form-control" placeholder="Enter Category Name">
            </div>
            <button class="btn btn-info" type="submit">Submit</button>     
        </form>
    </div>
    </body>
</html>
