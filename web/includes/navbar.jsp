<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-vpZl2lJD5zzOykKkLrBbEPv9Wp0yqDgqQ5m9vJkzQJqJpzz/3ZvVoKHyN1p+qLiX" crossorigin="anonymous">
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #8ba0b6">      
    <!-- Left side of the navbar -->
    <a class="navbar-brand text-white fw-bold" href="home">
        <i class="fa fa-home" style="font-size:30px; color:#ADD8E6;"></i>
        Diamond Galaxy
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Right side of the navbar -->
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav m-auto">
            <c:if test="${sessionScope.auth.isAdmin == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="list-user"><i class="fas fa-user"></i> Manager Account</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.auth.isSeller == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="manager-product"><i class="fas fa-cog"></i> Manager Product</a>
                </li> 
                 <li class="nav-item">
                     <a class="nav-link" href="list-cate"><i class="fa-solid fa-icons"></i>Category</a>
                </li>
                 <li class="nav-item">
                     <a class="nav-link" href="chart.jsp"><i class="fas fa-chart-line"></i>Chart</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.auth !=null }">
                <li class="nav-item"><a class="nav-link" href="#"><i class="fa-regular fa-user"></i>Hello ${sessionScope.auth.name}</a></li>
                <li class="nav-item"><a class="nav-link" href="profile.jsp"><i class="fas fa-user-circle"></i>Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="changePassword.jsp"><i class="fas fa-key"></i> Change Password</a></li>
                <%--  <li class="nav-item"><a class="nav-link" href="orders.jsp"><i class="fa-solid fa-truck-fast"></i>Orders</a></li> --%>
                <li class="nav-item"><a class="nav-link" href="log-out"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                </c:if>

            <c:if test="${sessionScope.auth ==null}" >
                <li class="nav-item px-3 py-1" style="border: 1px solid #fff; border-radius: 5px;">
                    <a class="nav-link text-white" href="user-login">
                        <i class='bx bx-door-open' style="font-size: 16px" ></i>
                        Login
                    </a>
                </li>
                </c:if>   

        </ul>
        <form action="search" method="post" class="form-inline my-2 my-lg-0 ml-3">
            <div class="input-group input-group-sm">
                <input oninput="searchByName(this)" value="${pi}" name="txt"
                       type="text" class="form-control px-3 py-2" 
                       aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-secondary btn-number px-3">
                        <i class="fas fa-search" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
            <a class="btn btn-success btn-sm ml-3 py-2" href="cart.jsp">
                <i class="fa-solid fa-cart-plus" style="margin-right: 5px;"></i>Cart
                <span class="badge badge-light">${cart_list.size()}</span>
            </a>
        </form>     
    </div>


</nav>


