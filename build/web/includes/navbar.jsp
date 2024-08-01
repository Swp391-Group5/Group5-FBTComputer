<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    #includes-topnav {
        position: fixed;
        width: 100%;
        z-index: 100;
        background-color: #0000FF; /* Changed to blue */
    }
    ::-webkit-scrollbar {
        width: 10px;
    }
    ::-webkit-scrollbar-track {
        background: #0056b3;
    }
    ::-webkit-scrollbar-thumb {
        background: #5385B4;
    }
    ::-webkit-scrollbar-thumb:hover {
        background: #205F9C;
    }

    .cart-btn {
        position: relative; /* Đặt thuộc tính position: relative cho thẻ <a> */
    }

    .cart-item-count {
        top: -10px;
        right: -5px; /* Đặt vị trí theo nhu cầu của bạn */
        height: 25px;
        min-width: 20px;
        background-color: #6c757d; /* Màu nền */
        border-radius: 50%; /* Làm tròn góc */
        display: flex;
        align-items: center;
        justify-content: center;
        color: #343a40; /* Màu chữ */
        padding: 0 5px;
    }
</style>
<nav class="navbar navbar-expand-md navbar-light" id="includes-topnav">      
    <!-- Left side of the navbar -->
    <a class="navbar-brand" href="home">
        <img src="<%=request.getContextPath()%>/assets/images/FBTComputerLogo2.png" alt="alt" style="width:300px; vertical-align: top; margin-left: 1vh;"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Right side of the navbar -->
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav m-auto">
            <li class="nav-item m-auto">
                <a class="nav-link text-white" href="home">
                    <strong>Home</strong>
                </a>
            </li>
            <li class="nav-item m-auto">
                <a class="nav-link text-white" href="blog">
                    <strong>Blog</strong>
                </a>
            </li>
              <li class="nav-item m-auto">
                <a class="nav-link text-white" href="contact.jsp">
                    <strong>Contact</strong>
                </a>
            </li>
            <li class="nav-item m-auto">
                <a class="nav-link text-white" href="blog">
                    <strong>Warranty</strong>
                </a>
            </li>
        </ul>
        <form action="search" method="post" class="my-2 my-lg-0 ms-3 d-flex justify-content-center">
            <div class="input-group input-group-sm">
                <input oninput="searchByName(this)" value="" name="txt" type="text" class="form-control py-1 px-3 h-100 rounded" placeholder="Search...">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-outline-light btn-number mr-3 px-3">
                        <i class="fas fa-search" aria-hidden="true"></i>
                    </button>
                </div>
            </div>


        <a class="btn btn-light btn-sm py-2 px-0 w-50 h-25 ms-3 me-3 cart-btn position-relative" href="cart.jsp">
                <i class="fa-solid fa-cart-plus" style="margin-right: 5px;"></i>Cart
                <span id="content" class="position-absolute bg-secondary rounded-pill d-flex align-items-center justify-content-center text-dark px-1 cart-item-count"> 
                    ${sessionScope.cart == null ? "0" : sessionScope.cart.items.size()}
                </span>
            </a>


            <ul class="navbar-nav m-2">
                <li class="nav-item dropdown m-auto">
                    <a class="nav-link dropdown-toggle text-white p-0 me-3" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:choose>
                            <c:when test="${sessionScope.customer != null}">
                                <strong>Hello ${sessionScope.customer.customerName}</strong>
                            </c:when>
                            <c:otherwise>
                                <strong>Hello ${sessionScope.admin.adminName}</strong>
                            </c:otherwise>
                        </c:choose>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test="${sessionScope.customer != null}">
                            <li><a class="dropdown-item" href="profile"><i class="fas fa-user-circle"></i> Profile</a></li>
                            </c:if>
                        <li><a class="dropdown-item" href="login.jsp"><i class="fa-solid fa-right-to-bracket"></i> Login</a></li>
                        <li><a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </form>  
    </div>
</nav>
<br><br><br>

