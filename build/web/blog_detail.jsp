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
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/color.css">
        <link rel="stylesheet" href="css/responsive.css">
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
                        <div class="row">
                            <div id="tg-twocolumns" class="tg-twocolumns">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <figure class="tg-newsdetailimg">
                                        <img src="images/img-03.jpg" alt="image description">
                                        <figcaption class="tg-author">
                                            <div class="tg-authorinfo">
                                                <span class="tg-bookwriter">By: <a href="javascript:void(0);">${o.admin.name}</a></span>

                                            </div>
                                        </figcaption>
                                    </figure>
                                </div>
                                <div class="col-xs-12 ">
                                    <div id="tg-content" class="tg-content">
                                        <div class="tg-newsdetail">
                                            <ul class="tg-bookscategories">
                                                <li><a href="javascript:void(0);">${o.blogCategory.name}</a></li>
                                            </ul>
                                            <div class="tg-themetagbox"><span class="tg-themetag">featured</span></div>
                                            <div class="tg-posttitle">
                                                <h3><a href="javascript:void(0);">${o.title}</a></h3>
                                            </div>
                                            <div class="tg-description">
                                            </div>
                                            <p>
                                                ${o.content}
                                            </p>
                                            <li><a href="javascript:void(0);">${o.updateDate}</a></li>

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

    </body>
</html>
