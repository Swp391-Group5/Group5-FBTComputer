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
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/transitions.css">
        <link rel="stylesheet" href="css/main_1.css">
        <link rel="stylesheet" href="css/color.css">
        <link rel="stylesheet" href="css/responsive.css">
        <style>
            /* The Modal (background) */
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

            /* Modal Content (image) */
            .modal-content {
                margin: auto;
                display: block;
                width: 80%;
                max-width: 700px;
            }

            /* Caption of Modal Image (Image Text) - You can add a caption if you want */
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

            /* Add Animation */
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

            /* The Close Button */
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
        </style>
    </head>
    <body>
        <!--[if lt IE 8]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        <!--************************************
                        Wrapper Start
        *************************************-->
        <div id="tg-wrapper" class="tg-wrapper tg-haslayout">
            <!--************************************
                            Header Start
            *************************************-->

            <!--************************************
                            Header End
            *************************************-->
            <!--************************************
                            Inner Banner Start
            *************************************-->
            <div class="tg-innerbanner tg-haslayout tg-parallax tg-bginnerbanner" data-z-index="-100" data-appear-top-offset="600" data-parallax="scroll" data-image-src="images/parallax/bgparallax-07.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="tg-innerbannercontent">
                                <h1>Blog &amp; Articles</h1>
                                <ol class="tg-breadcrumb">
                                    <li><a href="javascript:void(0);">home</a></li>
                                    <li><a href="javascript:void(0);">blog</a></li>
                                    <li class="tg-active">${o.title}</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--************************************
                            Inner Banner End
            *************************************-->
            <!--************************************
                            Main Start
            *************************************-->
            <main id="tg-main" class="tg-main tg-haslayout">
                <!--************************************
                                News Grid Start
                *************************************-->
                <div class="tg-sectionspace tg-haslayout">
                    <div class="container">
                        <a href="blogs" class="btn btn-success">Back to list</a>
                        <div class="row">
                            <div id="imageModal" class="modal">
                                <span class="close">&times;</span>
                                <img class="modal-content" id="modalImage">
                            </div>
                            <div id="tg-twocolumns" class="tg-twocolumns">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <figure class="tg-newsdetailimg">
                                        <img src="${blog.blogthumnail}" alt="image description" class="clickable-image">
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
                                                <h3><a href="javascript:void(0);">${blog.blogtitle}</a></h3>
                                            </div>
                                            <div class="tg-description">
                                            </div>
                                            <p>
                                                ${blog.blogcontent}
                                            </p>
                                            <li><a href="javascript:void(0);">${blog.blogdate}</a></li>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!--************************************
                                News Grid End
                *************************************-->
            </main>
            <!--************************************
                            Main End
            *************************************-->
            <!--************************************
                            Footer Start
            *************************************-->
            <footer id="tg-footer" class="tg-footer tg-haslayout">

            </footer>
            <!--************************************
                            Footer End
            *************************************-->
        </div>
        <!--************************************
                        Wrapper End
        *************************************-->
        <script>
            // Get the modal
            var modal = document.getElementById("imageModal");
            var setClassImage = document.querySelectorAll("img");
            for(var i = 0; i < setClassImage.length; i++) {
                setClassImage[i].classList.add("clickable-image");
            }

            var images = document.getElementsByClassName('clickable-image');
            var modalImg = document.getElementById("modalImage");

            for (var i = 0; i < images.length; i++) {
                images[i].onclick = function () {
                    modal.style.display = "block";
                    modalImg.src = this.src;
                }
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
        </script>
    </body>
</html>
