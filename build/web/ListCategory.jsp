

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includes/head.jsp" %>
        <title>List-Cate</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
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
        <jsp:include page="includes/adminNavbar.jsp"></jsp:include>
        <br>
        <br>
        <br>
        <br>

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-primary">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            
        </nav>
        <br>
        <br>
        <h1 class="text-center mt-10">Category Manager</h1>
        <div class="container">


            <div class="d-flex justify-content-between mb-3">
                <div id="categoryTable_length" class="dataTables_length"></div>
                <div id="categoryTable_filter" class="dataTables_filter"></div>
                <div class="search-toggle">
                    <!--<input type="checkbox" id="searchToggle" class="toggle-input">-->
                    <!--<label style="width: 100px" for="searchToggle" class="toggle-label">Toggle Search</label>-->
                    <form action="excel-cate" method="get"> 
                        <button type="submit" class="mb-0 text-center btn btn-primary">Xuất file Excel</button> 
                    </form> 
                    <br>
                    <button class="me-2 text-center btn btn-primary"><a style="text-decoration: none" class="text-white" href="AddCategory.jsp">Add Category</a></button>
                </div>
            </div>
        <c:if test="${mess!=null }">
            <div class="alert alert-success" role="alert">
                ${mess}
            </div>
        </c:if>
        <table id="categoryTable" class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Toggle Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cate}" var="o">
                    <tr>
                        <td>${o.categoryId}</td>
                        <td>${o.categoryName}</td>
                        <td>
                            ${o.categoryStatus == 1 ? '<span class="text-success"><b>Show</b></span>' : '<span class="text-danger"><b>Hidden</b></span>'}
                        </td>
                        <td>
                            <a href="update-cate?categoryId=${o.categoryId}" class="btn btn-success mr-2">Update</a>
                        </td>
                        <td>
                            <a href="delete-cate?categoryId=${o.categoryId}" class="btn btn-danger mr-2">Delete</a>
                        </td>
                        <td>
                            <form action="toggle-cate" method="post">
                                <input type="hidden" name="categoryId" value="${o.categoryId}">
                                <input type="hidden" name="status" value="${o.categoryStatus == 1 ? 'true' : 'false'}">
                                <label class="switch">
                                    <input type="checkbox" onchange="this.form.submit()" ${o.categoryStatus == 1 ? 'checked' : ''}>
                                    <span class="slider ${o.categoryStatus == 1 ? 'round-primary' : 'round-danger'}"></span>
                                </label>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script>
                                        $(document).ready(function () {
                                            $('#categoryTable').DataTable({
                                                "language": {
                                                    "decimal": "",
                                                    "emptyTable": "No data available in table",
                                                    "info": "_START_ to _END_ of _TOTAL_ entries",
                                                    "infoEmpty": "Showing 0 to 0 of 0 entries",
                                                    "infoFiltered": "(filtered from _MAX_ total entries)",
                                                    "infoPostFix": "",
                                                    "thousands": ",",
                                                    "lengthMenu": "Show _MENU_ entries",
                                                    "loadingRecords": "Loading...",
                                                    "processing": "Processing...",
                                                    "search": "Search:",
                                                    "zeroRecords": "No matching records found",
                                                    "paginate": {
                                                        "first": "First",
                                                        "last": "Last",
                                                        "next": "Next",
                                                        "previous": "Previous"
                                                    },
                                                    "aria": {
                                                        "sortAscending": ": activate to sort column ascending",
                                                        "sortDescending": ": activate to sort column descending"
                                                    }
                                                },
                                                "lengthMenu": [5, 10, 15, 20] 
                                            });
                                        });
    </script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Lắng nghe sự kiện thay đổi của toggle input
            const toggleInput = document.querySelector('.toggle-input');
            toggleInput.addEventListener('change', function () {
                // Lấy trạng thái của toggle (checked hoặc không)
                const isChecked = toggleInput.checked;

                // Thay đổi giao diện của trang dựa trên trạng thái của toggle
                if (isChecked) {
                    document.body.classList.add('dark-mode'); // Thêm lớp dark-mode khi bật toggle
                } else {
                    document.body.classList.remove('dark-mode'); // Loại bỏ lớp dark-mode khi tắt toggle
                }
            });
        });
    </script>
</body>
</html>
