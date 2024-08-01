<%-- 
    Document   : bloglist
    Created on : May 20, 2024, 9:40:34 PM
    Author     : chi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Blog</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/transitions.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/color.css">
        <link rel="stylesheet" href="css/responsive.css">
    </head>
    <body>

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
                                <h1>Blogs &amp; Articles</h1>
                                <ol class="tg-breadcrumb">
                                    <li><a href="javascript:void(0);">home</a></li>
                                    <li class="tg-active">Blog</li>
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
                                <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9 pull-right">
                                    <div id="tg-content" class="tg-content">
                                        <div class="tg-newslist">
                                            <div class="tg-sectionhead">
                                                <h2><span>Latest Blog &amp; Articles</span>Asus laptops for 2023</h2>
                                                
                                            </div>
                                            <img src="images/a1.png" alt="LAPTOP ASUS" width="800" height="100">
                                            <p>It’s quite confusing walking into a shop to look at the laptops. Unless it’s a branded store that looks more like a design studio, has about three products out on the shop floor and five employees to each of them, chances are that you’ve at some point become lost trying to navigate the brands and set-ups of the laptop world.</p>
                                            <p>One of the brands that always seems to be in eyeshot is Asus. Started in 1989 from a coffee shop in Taipei, the company is now a leading global brand for motherboards, processors and all manner of computer hardware. Not just producing these for the market, Asus also crams as much technological knowhow into its own laptops as possible, becoming a top-three global brand for consumer notebooks. To paraphrase, when you’re in Currys, you’re never more than three feet away from an Asus.</p>
                                            <p>Asus laptops, while offering fantastic specs and, on the whole, attractive design, can themselves be pretty confusing, with rafts of zenbook 14s and battalions of ROG strix set-ups for you to wade through. Below are some of the best laptops Asus produces, but remember that you can power up or down each product (processing, memory etc), depending on your needs.</p>
                                            
                                            
                                            <div class="row">
                                                <c:forEach var="o" items="${plist}">
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                        <article class="tg-post">
                                                            <figure><a href="javascript:void(0);"><img src="${o.thumnail}" alt="image description"></a></figure>
                                                            <div class="tg-postcontent">
                                                                <ul class="tg-bookscategories">
                                                                    <li><a href="javascript:void(0);">${o.blogCategory.name}</a></li>
                                                                </ul>
                                                                <div class="tg-themetagbox"><span class="tg-themetag">featured</span></div>
                                                                <div class="tg-posttitle">
                                                                    <h3><a href="javascript:void(0);">${o.title}</a></h3>
                                                                </div>
                                                                <div class="tg-description">
                                                                    <p> ${o.content.substring(100)} ... <a href="BlogDetail?id=${o.id}">More</a></p>
                                                                </div>
                                                                <span class="tg-bookwriter">By: <a href="javascript:void(0);">${o.admin.name}</a></span>
                                                                <ul class="tg-postmetadata">
                                                                    <li><a href="javascript:void(0);">${o.updateDate}</a></li>
                                                                </ul>
                                                            </div>
                                                        </article>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div style="display: flex; justify-content: center;">
                                                <ul class="pagination" >
                                                    <li  class="page-item next"><a href="ListBlog?index=1&search=${param['search']}"><i class="fa fa-angle-left" class="page-link" aria-hidden="true"></i></a></li>
                                                            <c:forEach var = "i" begin = "1" end = "${numberPage}">
                                                        <li class="${param['index']==i?'page-item active':'page-item'}"><a href="ProductList?index=${i}&search=${param['search']}"   class="page-link">${i}</a></li>
                                                        </c:forEach>
                                                    <li  class="page-item next"><a href="ListBlog?index=${numberPage}&search=${param['search']}"><i class="fa fa-angle-right" class="page-link" aria-hidden="true"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-3 pull-left">
                                    <aside id="tg-sidebar" class="tg-sidebar">
                                        <div class="tg-widget tg-widgetsearch">
                                            <form action="ListBlog" method="GET" class="tg-formtheme tg-formsearch">
                                                <div class="form-group">
                                                    <button type="submit"><i class="icon-magnifier"></i></button>
                                                    <input type="search" name="search" class="form-group" placeholder="Search Here">
                                                </div>
                                            </form>
                                        </div>

                                        <div class="tg-widget tg-widgettrending">
                                            <div class="tg-widgettitle">
                                                <h3>Trending Posts</h3>
                                            </div>
                                            <div class="tg-widgetcontent">
                                                <ul>
                                                    <c:forEach var="o" items="${top3}">
                                                        <li>
                                                            <article class="tg-post">
                                                                <figure><a href="BlogDetail?id=${o.id}"><img src="${o.thumnail}" alt="image description"></a></figure>
                                                                <div class="tg-postcontent">
                                                                    <div class="tg-posttitle">
                                                                        <h3><a href="BlogDetail?id=${o.id}">${o.title}</a></h3>
                                                                    </div>
                                                                    <span class="tg-bookwriter"> <a href="javascript:void(0);">${o.updateDate}</a></span>
                                                                </div>
                                                            </article>
                                                        </li>
                                                    </c:forEach>


                                                </ul>
                                            </div>
                                        </div>


                                    </aside>
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

    </body>
</html>
