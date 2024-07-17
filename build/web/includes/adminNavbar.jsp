<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
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
            <li class="nav-item m-auto"><a class="nav-link text-white" href="adminDashboard"><i class="fa-solid fa-gauge-high"></i> <strong>Main dashboard </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="productManager"><i class="fa-solid fa-cart-shopping"></i> <strong>Product Manager </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="userManager"><i class="fa-solid fa-user-gear"></i> <strong>User Manager </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="orderManager"><i class="fa-solid fa-user-gear"></i> <strong>Order Manager </strong></a></li>
            <li class="nav-item m-auto"><a class="nav-link text-white" href="adminFeedbackManager"><i class="fa-solid fa-comments"></i> <strong>Feedback Manager</strong></a></li>
        </ul>
        <form action="search" method="post" class="my-2 my-lg-0 ms-3 d-flex justify-content-center">
            <ul class="navbar-nav m-2">
                <li class="nav-item dropdown m-auto">
                    <a class="nav-link dropdown-toggle text-white p-0 me-5  " href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user"></i> <strong>Hello ${sessionScope.customer.customerName} ${sessionScope.admin.adminName}</strong>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test="${sessionScope.customer == null && sessionScope.admin == null}">
                            <li><a class="dropdown-item" href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a></li>
                            </c:if>
                            <c:if test="${sessionScope.customer != null}">
                            <li><a class="dropdown-item" href="profile"><i class="fas fa-user-circle"></i> Profile</a></li>
                            <li><a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                            </c:if>
                            <c:if test="${sessionScope.admin != null}">
                            <li><a class="dropdown-item" href="home"><i class="fa-solid fa-house"></i> Main menu</a></li>
                            <li><a class="dropdown-item" href="profile"><i class="fas fa-user-circle"></i> Profile</a></li>
                            <li><a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                            </c:if>
                    </ul>
                </li>
            </ul>
        </form>  
    </div>
</nav>

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


