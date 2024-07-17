<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-vpZl2lJD5zzOykKkLrBbEPv9Wp0yqDgqQ5m9vJkzQJqJpzz/3ZvVoKHyN1p+qLiX" crossorigin="anonymous">
<style>
    #includes-topnav{
        position: fixed;
        width: 100%;
        z-index: 100;
    }
    ::-webkit-scrollbar {
        width: 10px;
    }

    ::-webkit-scrollbar-track {
        background: #f1f1f1;
    }

    ::-webkit-scrollbar-thumb {
        background: #5385B4;
    }

    ::-webkit-scrollbar-thumb:hover {
        background: #205F9C;
    }
</style>
<nav class="navbar navbar-expand-md navbar-light bg-primary" id="includes-topnav">      
    <!-- Left side of the navbar -->
    <a class="navbar-brand" href="home">
        <!--<i class="fa fa-home" style="font-size:40px; color:#ADD8E6;"></i>-->
        <img src="<%=request.getContextPath()%>/assets/images/FBTComputerLogo2.png" alt="alt" style="width:300px; vertical-align: top; margin-left: 1vh; "/>
    </a>
    <button class="navbar-toggler me-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Right side of the navbar -->
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav m-auto">
            <li class="nav-item m-auto"><a class="nav-link text-white" href="home"><i class="fas fa-house"></i> <strong>Home page </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="blog"><i class="fas fa-newspaper"></i> <strong>Blog </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="blog"><i class="fas fa-shield-halved"></i> <strong>Warranty </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="contact.jsp"><i class="fas fa-phone"></i> <strong>Contact Us </strong></a></li>
        </ul>

        <form action="search"  class="my-2 my-lg-0 ms-3 d-flex justify-content-center">
            <div class="input-group input-group-sm">
                <input name="productName" type="text" value="${pi}" class="form-control py-1 ps-2 pe-5 h-auto rounded"
                       placeholder="Search...">
                <div class="input-group-append">

                    <button type="submit" class="btn btn-outline-light btn-number mr-3 px-3">
                        <i class="fas fa-search" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
            <a class="btn btn-light btn-sm py-2 px-0 w-50 h-25 ms-3 me-3" href="cart.jsp">
                <i class="fas fa-cart-plus" style="margin-right: 5px;"></i>Cart
                <span class="badge bg-light">${cart_list.size()}</span>
            </a>
            <ul class="navbar-nav m-2">
                <li class="nav-item dropdown m-auto">
                    <a class="nav-link dropdown-toggle text-white p-0 me-5" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user"></i> <strong>Hello ${sessionScope.customer.customerName}${sessionScope.admin.adminName}</strong>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <c:if test="${sessionScope.customer == null && sessionScope.admin == null}">
                            <li><a class="dropdown-item" href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a></li>
                            </c:if>
                        <!-- Display logout link if customer session attribute is not null -->
                        <c:if test="${sessionScope.customer != null}">
                            <li><a class="dropdown-item" href="profile"><i class="fas fa-user-circle"></i> Profile</a></li>
                            <li><a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                            </c:if>
                            <c:if test="${sessionScope.admin != null}">
                            <li><a class="dropdown-item" href="adminDashboard"><i class="fa-solid fa-table-columns"></i> Admin Dashboard</a></li>
                            <li><a class="dropdown-item" href="profile"><i class="fas fa-user-circle"></i> Profile</a></li>
                            <li><a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                            </c:if>
                    </ul>
                </li>
            </ul>
        </form>  
    </div>
</nav>
<br>

<script>
//    var dropdownToggle = document.querySelector('.nav-link.dropdown-toggle');
//    var dropdownMenu = document.querySelector('.dropdown-menu');
//
//    dropdownToggle.addEventListener('click', function (event) {
//        event.preventDefault();
//        if (!dropdownMenu.classList.contains('show')) {
//            dropdownMenu.classList.add('show');
//        } else {
//            dropdownMenu.classList.remove('show');
//        }
//    });
</script>


