<%-- 
    Document   : sidebar
    Created on : Jun 2, 2024, 5:55:37 PM
    Author     : hungp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    /* Custom styles */
    .sidebar {
        background-color: #333;
        color: white;
        padding-top: 20px;
    }

    .sidebar a {
        color: white;
        padding: 15px 10px;
        display: block;
        text-decoration: none;
    }

    .sidebar a:hover {
        background-color: #575757;
    }

    .content {
        padding: 20px;
    }

    /* Responsive adjustments */
    @media (min-width: 768px) {
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
        }

        .content {
            margin-left: 250px;
        }

        .offcanvas {
            display: none;
        }
    }
</style>
</head>
<body>
    <button class="btn btn-primary d-md-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#sidebar" aria-controls="sidebar">
        Toggle Sidebar
    </button>
    <div class="offcanvas offcanvas-start sidebar d-md-none" tabindex="-1" id="sidebar">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasExampleLabel">Sidebar Menu</h5>
            <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <a href="home.jsp">Home</a>
            <a href="profile.jsp">Profile</a>
            <a href="settings.jsp">Settings</a>
            <a href="contact.jsp">Contact</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <nav class="col-md-3 col-lg-2 d-none d-md-block sidebar">
                <div class="sidebar-sticky">
                    <h2>Sidebar Menu</h2>
                    <a href="home.jsp">Home</a>
                    <a href="profile.jsp">Profile</a>
                    <a href="settings.jsp">Settings</a>
                    <a href="contact.jsp">Contact</a>
                </div>
            </nav>
        </div>
    </div>

