
<%-- 
    Document   : blog_detail
    Created on : May 20, 2024, 9:48:34 PM
    Author     : chi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Blog Detail</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="blogCss/bootstrap.min.css">
        <link rel="stylesheet" href="blogCss/normalize.css">
        <link rel="stylesheet" href="blogCss/font-awesome.min.css">
        <link rel="stylesheet" href="blogCss/owl.carousel.css">
        <link rel="stylesheet" href="blogCss/transitions.css">
        <link rel="stylesheet" href="blogCss/main_1.css">
        <link rel="stylesheet" href="blogCss/color.css">
        <link rel="stylesheet" href="blogCss/responsive_1.css">
        <style>
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                padding-top: 60px;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.9);
            }
            .modal-content {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
            }
            #caption {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
                text-align: center;
                color: #ccc;
                padding: 10px 0;
                height: 150px;
            }
            .modal-content, #caption {
                -webkit-animation-name: zoom;
                -webkit-animation-duration: 0.6s;
                animation-name: zoom;
                animation-duration: 0.6s;
            }
            @-webkit-keyframes zoom {
                from {
                    transform: scale(0)
                }
                to {
                    transform: scale(1)
                }
            }
            @keyframes zoom {
                from {
                    transform: scale(0.1)
                }
                to {
                    transform: scale(1)
                }
            }
            .close {
                position: absolute;
                top: 15px;
                right: 35px;
                color: #f1f1f1;
                font-size: 40px;
                font-weight: bold;
                transition: 0.3s;
            }
            .close:hover,
            .close:focus {
                color: #bbb;
                text-decoration: none;
                cursor: pointer;
            }
            .modal-navigation {
                position: absolute;
                top: 50%;
                width: auto;
                padding: 16px;
                margin-top: -50px;
                color: white;
                font-weight: bold;
                font-size: 20px;
                transition: 0.3s;
                user-select: none;
                -webkit-user-select: none;
            }
            .modal-navigation:hover {
                background-color: rgba(0,0,0,0.8);
            }
            .prev {
                left: 0;
            }
            .next {
                right: 0;
            }
        /* Breadcrumb container */
.tg-breadcrumb {
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 10px 0; /* Adjust margin as needed */
    background-color: #3CB371; /* Green background color for the entire breadcrumb */
    padding: 10px 0; /* Adjust vertical padding */
    border-radius: 5px; /* Rounded corners for the entire breadcrumb */
}

/* Individual breadcrumb items */
.tg-breadcrumb div {
    margin-right: 10px; /* Adjust spacing between items */
    font-size: 16px; /* Adjust font size */
}

/* Styling for active/current page */
.tg-breadcrumb div.tg-active {
    font-weight: bold; /* Make active item bold */
    color: #fff; /* Active item text color */
}

/* Styling for the first breadcrumb item (home) */
.tg-breadcrumb div:first-child {
    background-color: transparent; /* Remove background color for the home item */
    padding: 0; /* Remove padding for the home item */
}

/* Breadcrumb link styles */
.tg-breadcrumb div a {
    color: #333; /* Link color */
    text-decoration: none;
}

/* Hover effect for links */
.tg-breadcrumb div a:hover {
    text-decoration: underline;
}



        </style>
    </head>

    <body>

        <div id="tg-wrapper" class="tg-wrapper tg-haslayout">
            <div class="tg-innerbanner tg-haslayout tg-parallax tg-bginnerbanner" data-z-index="-100" data-appear-top-offset="600" data-parallax="scroll" data-image-src="images/parallax/bgparallax-07.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="tg-innerbannercontent">
                                <h1>Blog &amp; Articles</h1>
                                <div class="tg-breadcrumb">
                                    <div><a href="javascript:void(0);">home</a></div>
                                    <div><a href="javascript:void(0);">blog</a></div>
                                    <div class="tg-active">${o.title}</div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <main id="tg-main" class="tg-main tg-haslayout">
                <div class="tg-sectionspace tg-haslayout">
                    <div class="container">
                        <a href="blogs" class="btn btn-success">Back to list</a>
                        <div class="row">
                            <div id="imageModal" class="modal">
                                <span class="close">&times;</span>
                                <img class="modal-content" id="modalImage">
                                <a class="modal-navigation prev">&#10094;</a>
                                <a class="modal-navigation next">&#10095;</a>
                            </div>
                            <div id="tg-twocolumns" class="tg-twocolumns">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <figure class="tg-newsdetailimg">
                                        <img src="${blog.blogThumbnail}" alt="image description" class="clickable-image">
                                        <figcaption class="tg-author">
                                            <div class="tg-authorinfo">
                                                <span class="tg-bookwriter">By: <a href="javascript:void(0);">${admin.adminName}</a></span>
                                            </div>
                                        </figcaption>
                                    </figure>
                                </div>
                                <div class="col-xs-12 ">
                                    <div id="tg-content" class="tg-content">
                                        <div class="tg-newsdetail">
                                            <ul class="tg-bookscategories">
                                                <li><a href="javascript:void(0);">${category.categoryName}</a></li>
                                            </ul>
                                            <div class="tg-themetagbox"><span class="tg-themetag">featured</span></div>
                                            <div class="tg-posttitle">
                                                <h3><a href="javascript:void(0);">${blog.blogTitle}</a></h3>
                                            </div>
                                            <div class="tg-description"></div>
                                            <p>${blog.blogContent}</p>
                                            <li><a href="javascript:void(0);">${blog.blogUpdateDate}</a></li>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <footer id="tg-footer" class="tg-footer tg-haslayout"></footer>
        </div>
        <script>
            var modal = document.getElementById("imageModal");
            var setClassImage = document.querySelectorAll("img");
            for (var i = 0; i < setClassImage.length; i++) {
                setClassImage[i].classList.add("clickable-image");
            }

            var images = document.getElementsByClassName('clickable-image');
            var modalImg = document.getElementById("modalImage");
            var currentIndex = 0;

            function showImage(index) {
                if (index >= 0 && index < images.length) {
                    modalImg.src = images[index].src;
                    currentIndex = index;
                    modal.style.display = "block";
                }
            }

            for (var i = 0; i < images.length; i++) {
                images[i].onclick = (function (index) {
                    return function () {
                        showImage(index);
                    }
                })(i);
            }

            var span = document.getElementsByClassName("close")[0];
            span.onclick = function () {
                modal.style.display = "none";
            }

            modal.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            var prev = document.getElementsByClassName("prev")[0];
            var next = document.getElementsByClassName("next")[0];

            prev.onclick = function () {
                showImage(currentIndex - 1);
            }
            next.onclick = function () {
                showImage(currentIndex + 1);
            }
        </script>

    </body>
</html>