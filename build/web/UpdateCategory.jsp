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
            /* Rounded switch button */
            .switch {
                position: relative;
                display: inline-block;
                width: 40px; /* Đảm bảo switch có đủ không gian cho nút tròn */
                height: 20px; /* Chiều cao của switch button */
            }

            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }

            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                transition: .4s;
                border-radius: 20px; /* Kích thước góc bo tròn */
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 16px; /* Chiều cao của nút tròn */
                width: 16px; /* Chiều rộng của nút tròn */
                left: 2px; /* Khoảng cách từ nút tròn đến viền của switch */
                bottom: 2px; /* Khoảng cách từ nút tròn đến viền dưới của switch */
                background-color: white;
                transition: .4s;
                border-radius: 50%; /* Tạo hình tròn */
            }

            /* Định dạng cho trạng thái checked */
            input:checked + .slider {
                background-color: green;
            }

            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }

            input:checked + .slider:before {
                transform: translateX(20px); /* Dịch chuyển nút tròn sang phải */
            }

            /* CSS cho toggle switch */
            .toggle-input {
                display: none;
            }

            .toggle-label {
                cursor: pointer;
                display: inline-block;
                width: 60px;
                height: 34px;
                background-color: #ccc;
                border-radius: 20px;
                position: relative;
                transition: background-color 0.4s;
                line-height: 34px;
                text-align: center;
                color: #fff;
            }

            .toggle-label::before {
                content: "";
                position: absolute;
                width: 30px;
                height: 30px;
                border-radius: 50%;
                background-color: #fff;
                top: 2px;
                left: 2px;
                transition: transform 0.4s;
            }

            .toggle-input:checked + .toggle-label {
                background-color: green;
            }

            .toggle-input:checked + .toggle-label::before {
                transform: translateX(26px);
            }

            .search-toggle {
                margin-bottom: 20px;
            }
            /* CSS cho chế độ tối */
            .dark-mode {
                background-color: blanchedalmond;
                color: #0277b7
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
                <br>
                <button class="btn btn-primary" type="submit">Submit</button>     
            </form>
        </div>
    </body>
</html>
