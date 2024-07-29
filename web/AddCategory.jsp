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
                text-align: left;
            }

/*            input[type="number"],
            input[type="text"],
            button,
            select {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                border-radius: 5px;
                box-sizing: border-box;
            }*/

            button {
                font-size: 1rem;
            }

            .btn-info {
                margin-top: 20px;
            }


        </style>
    </head>
    <body>
        <jsp:include page="/includes/adminNavbar.jsp"></jsp:include>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
            <!--        <nav class="navbar navbar-expand-lg navbar-light bg-primary">
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
                    </nav>-->
            <br>
            <div class="container">
                <h3 class="mt-3" style="text-align: center;">Add Category</h3>
                <form action="add-cate" method="get" class="text-center">
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
                    <br>
                    <button class="btn btn-primary" type="submit">Submit</button>
                </form>
                <div id="error-message" style="display: none; color: red; text-align: center;">
                    <!-- Thông báo lỗi sẽ được hiển thị ở đây -->
                </div>
            </div>
            <script>
                // Kiểm tra xem có lỗi nào từ requestScope không
                var error1 = "${requestScope.error}";
                var error2 = "${requestScope.error1}";

                // Nếu có lỗi thì hiển thị thông báo
                if (error1 || error2) {
                    var errorMessage = document.getElementById('error-message');
                    errorMessage.innerHTML = "Error: " + error1 + " " + error2;
                    errorMessage.style.display = 'block';
                }
        </script>

    </body>
</html>
