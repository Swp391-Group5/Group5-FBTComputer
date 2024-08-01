

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/path/to/jquery-ui.css">
<script src="/path/to/jquery.min.js"></script>
<script src="/path/to/jquery-ui.min.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .list-group-item.active a {
        color: #fff;

    }
    .list-group-item a {
        text-decoration: none;
    }


    .filter-container {
        max-width: 300px;
        border: 1px solid #ccc;
        padding: 20px;
        border-radius: 5px;
    }
    .filter-container h2 {
        margin-top: 0;
    }
    .filter-container .filter-button {
        display: inline-block;
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .filter-container .filter-button:hover {
        background-color: #0056b3;
    }
    .filter-container .brand-checkbox {
        display: flex;
        align-items: center;
        margin: 10px 0;
    }
    .filter-container .brand-checkbox input {
        margin-right: 10px;
    }
    input[type="checkbox"]#asus:checked + label,
    input[type="checkbox"]#apple:checked + label {
        color: red;
    }



    /* Thay đổi style cho các checkbox và label */
    .brand-checkbox {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }

    .brand-checkbox input[type="checkbox"] {
        margin-right: 10px;
    }

    .brand-checkbox label {
        font-weight: bold;
        cursor: pointer;
    }

    /* Thay đổi style cho price range slider */
    .price-input-container {
        margin-bottom: 20px;
    }

    .price-field {
        margin-bottom: 10px;
    }

    /* CSS cho các thanh slider */
    .price-slider {
        width: 100%; /* chiều rộng 100% của phần tử cha */
        height: 10px; /* độ cao của thanh slider */
        background-color: #f0f0f0; /* màu nền của thanh slider */
        border-radius: 5px; /* bo tròn viền của thanh slider */
        position: relative; /* vị trí tương đối */
        overflow: hidden; /* ẩn phần dư thừa nếu có */
    }

    .price-slider::before {
        content: ''; /* tạo một phần tử trước */
        position: absolute; /* vị trí tuyệt đối so với phần tử cha */
        top: 0; /* đặt top ở vị trí đầu tiên */
        left: 0; /* đặt left ở vị trí đầu tiên */
        width: 50%; /* chiều rộng ban đầu của thanh slider */
        height: 100%; /* chiều cao của thanh slider */
        background-color: #f64876; /* màu nền cho phần slider đã được chọn */
    }

    /* CSS cho các đường ngang (hr) */
    hr {
        border: none; /* loại bỏ đường viền */
        height: 1px; /* độ cao của đường ngang */
        background-color: #ccc; /* màu sắc của đường ngang */
        margin: 20px 0; /* khoảng cách trên và dưới đường ngang */
    }

    /* Hiệu ứng đường ngang */
    .hr-dashed {
        border-top: 1px dashed #999; /* đường ngang nét đứt */
    }

    .hr-dotted {
        border-top: 1px dotted #999; /* đường ngang nét chấm */
    }

    .hr-double {
        border-top: 3px double #999; /* đường ngang nét đôi */
    }
    .custom-wrapper {
        /* các thuộc tính CSS khác cho phần tử của thanh trượt */
        /* Ví dụ: */
        margin-top: 20px; /* Khoảng cách giữa slider wrapper và phần tử custom wrapper */
    }

</style>

<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase">
            <i class="fa fa-list"></i> Categories
        </div>
        <ul class="list-group">
            <li class="list-group-item text-white <c:if test='${view == "0"}'>active</c:if>'">
                    <a href="home?categoryId=0">All</a>
                </li>
            <c:forEach items="${listC}" var="category">
                <c:if test="${category.categoryStatus == 1}">
                    <li class="list-group-item text-white <c:if test='${category.categoryId == view}'>active</c:if>'">
                        <a style="<c:if test='${requestScope.categoryId == category.categoryId}'>color:red;font-style:italic;"</c:if>" href="home?categoryId=${category.categoryId}" <c:if test='${category.categoryId == view}'>class="text-danger"</c:if>>${category.categoryName}</a>
                        </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>



    <h2 class="pt-2 mb-4">Filters</h2>
    <section class="mb-4">
        <div class="d-flex justify-content-between align-items-center mt-0">
            <form action="home"  class="d-flex">
                <div class="input-group">
                    <input name="productName" type="text" value="${productName}" class="form-control" aria-label="Search" placeholder="Search...">
                    <button type="submit" class="btn btn-secondary">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>
        </div>
    </section>

    <label for="sortBy" style=" font-family: inherit"><h3>Sort by (Price)</h3></label>
    <form action="home" class="mb-3" style="border: none; margin: 0; padding: 0;">
        <div class="d-flex align-items-center">
            <select id="sort" name="sortBy" class="form-select text-success" style="max-width: 200px; border: 1px solid green; border-radius: 5px;">
                <option value="0" ${sortBy == 0 ? 'selected' : ''}>Default</option>
                <option value="1" ${sortBy == 1 ? 'selected' : ''}>Low to High</option>
                <option value="2" ${sortBy == 2 ? 'selected' : ''}>High to Low</option>
                <option value="3" ${sortBy == 3 ? 'selected' : ''}>A to Z</option>
                <option value="4" ${sortBy == 4 ? 'selected' : ''}>Z to A</option>
            </select>
            <button type="submit" class="btn text-white ms-2" style="background-color: #f64876; border-radius: 5px;">
                Sort
            </button>
        </div>
    </form>

    <span class="hr"><hr></span>    
    <section class="mb-4">

        <h2>Brands</h2>
        <form action="home"  id="searchForm" >
            <div class="brand-checkbox">
                <input type="checkbox" id="asus" name="brand" value="Asus">
                <label for="asus">Asus</label>
            </div>
            <div class="brand-checkbox">
                <input type="checkbox" id="apple" name="brand" value="Apple">
                <label for="apple">Apple</label>
            </div>
            <input type="submit" class="btn-success" value="Search">
        </form>
<!--        <span class="hr"><hr></span> 
        <br>-->
<!--        <div class="custom-wrapper">
            <div class="header">
                <h2 class="projtitle">Price Range Slider</h2>
            </div>

            <div class="price-input-container">
                <div class="price-input">
                    <div class="price-field">
                        <span>Minimum Price</span> <br>
                        <input type="number" class="min-input" value="0">
                    </div>
                    <br>
                    <div class="price-field">
                        <span>Maximum Price</span>
                        <input type="number" class="max-input" value="100000000">
                    </div>
                </div>
                <div class="slider-container">
                    <div class="price-slider"></div>
                </div>
            </div>

            <br>

             Slider 
            <div class="range-input">
                <form id="priceRangeForm" action="home" method="GET">
                    <input type="range" class="min-range" name="minRange" min="0" max="100000000" value="0" step="1000000">
                    <input type="range" class="max-range" name="maxRange" min="0" max="100000000" value="100000000" step="1000000">
                    <input type="submit" value="Submit">
                </form>
            </div>
        </div>-->


    </section>
    <script>
        window.addEventListener('DOMContentLoaded', (event) => {
            let checkboxes = document.querySelectorAll('input[name="brand"]');
            checkboxes.forEach((checkbox) => {
                let brand = checkbox.value;
                if (window.location.search.includes(brand)) {
                    checkbox.checked = true;
                }
            });
        });
        const minInput = document.querySelector('.min-input');
        const maxInput = document.querySelector('.max-input');
        const minRange = document.querySelector('.min-range');
        const maxRange = document.querySelector('.max-range');

        minInput.addEventListener('input', () => {
            minRange.value = minInput.value;
        });

        maxInput.addEventListener('input', () => {
            maxRange.value = maxInput.value;
        });

        minRange.addEventListener('input', () => {
            minInput.value = minRange.value;
        });

        maxRange.addEventListener('input', () => {
            maxInput.value = maxRange.value;
        });

        minInput.addEventListener('input', () => {
            if (parseInt(minInput.value) > parseInt(maxInput.value)) {
                minInput.value = maxInput.value;
            }
            minRange.value = minInput.value;
        });

        maxInput.addEventListener('input', () => {
            if (parseInt(maxInput.value) < parseInt(minInput.value)) {
                maxInput.value = minInput.value;
            }
            maxRange.value = maxInput.value;
        });

        minRange.addEventListener('input', () => {
            if (parseInt(minRange.value) > parseInt(maxRange.value)) {
                minRange.value = maxRange.value;
            }
            minInput.value = minRange.value;
        });

        maxRange.addEventListener('input', () => {
            if (parseInt(maxRange.value) < parseInt(minRange.value)) {
                maxRange.value = minRange.value;
            }
            maxInput.value = maxRange.value;
        });


        function syncInputs() {
            document.querySelector('.min-range').value = document.querySelector('.min-input').value;
            document.querySelector('.max-range').value = document.querySelector('.max-input').value;
        }
    </script>

</div>


