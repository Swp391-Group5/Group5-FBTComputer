<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->


 <nav class="navbar navbar-expand-md navbar-collapse bg-light">
        <div class="container">
            <a class="navbar-brand" href="home">Shop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav m-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="list-cate">Manager Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="category">Manager Category</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                </ul>

                <form action="search" method="post" class="d-flex">
                    <div class="input-group input-group-sm">
                        <input name="txt" type="text" class="form-control" aria-label="Search" placeholder="Search...">
                        <button type="submit" class="btn btn-secondary">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                    <a class="btn btn-success btn-sm ms-3" href="show">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge bg-light text-dark">3</span>
                    </a>
                </form>
            </div>
        </div>
    </nav>
  <section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Siêu thị giày chất lượng cao</h1>
        <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phẩm giày nhập từ Trung Quốc</p>
    </div>
</section>

    <!--end of menu-->
<!--end of menu-->
