<%-- 
    Document   : blogs
    Created on : Jun 15, 2024, 10:59:27 PM
    Author     : HP
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

        <link rel="stylesheet" href="./blogCss/bootstrap.min.css">
        <link rel="stylesheet" href="./blogCss/normalize.css">
        <link rel="stylesheet" href="./blogCss/font-awesome.min.css">
        <!--        <link rel="stylesheet" href="./css/icomoon.css">-->
        <link rel="stylesheet" href="./blogCss/owl.carousel.css">
        <link rel="stylesheet" href="./blogCss/transitions.css">
        <link rel="stylesheet" href="./blogCss/main_1.css">
        <link rel="stylesheet" href="./blogCss/color.css">
        <link rel="stylesheet" href="./blogCss/responsive.css
              <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



    </head>
    <body>

        <div id="tg-wrapper" class="tg-wrapper tg-haslayout">

            <div class="tg-innerbanner tg-haslayout tg-parallax tg-bginnerbanner" data-z-index="-100" data-appear-top-offset="600" data-parallax="scroll" data-image-src="images/parallax/bgparallax-07.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="tg-innerbannercontent">
                                <h1>Blogs &amp; Articles</h1>
                                <ol class="tg-breadcrumb">
                                    <li><a href="home">Home</a></li>
                                    <li class="tg-active">Blog</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

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
                                                <h2><span>Latest Blog &amp; Articles</span>Technologies for 2024</h2>

                                            </div>
                                            <!--                                            <div style="display: flex; justify-content: center; margin-top: 10px;">
                                                                                            <form id="pageForm" action="blogs" method="GET">
                                                                                          <div style="display: flex; justify-content: center; margin-top: 10px;">
                                                                                                  <input type="hidden" id="search" name="search" value="${param['search']}">
                                                                                                <select id="pageSelect" name="index" onchange="this.form.submit()">
                                            
                                                                                                </select>
                                                                                            </form>
                                                                                        </div>-->
                                            <div style="display: flex; justify-content: flex-end; align-items: center; margin-top: 20px; margin-bottom: 20px;">
                                                <form id="pageForm" action="blogs" method="GET" style="display: flex; align-items: center;">
                                                    <input type="hidden" id="search" name="search" value="${param.search}">
                                                    <span style="margin-right: 10px;"></span>
                                                    <select id="pageSelect" name="index" onchange="this.form.submit()" style="padding: 10px; border-radius: 25px; border: 1px solid #ccc; background-color: #f9f9f9;">
                                                        <c:forEach var="i" begin="1" end="${numberPage}">
                                                            <option value="${i}" ${param.index == i ? 'selected' : ''}>Page ${i}</option>
                                                        </c:forEach>
                                                    </select>

                                                </form>
                                            </div>

                                            <!--                                            Nội dung bài viết-->
                                            <div class="row">
                                                <c:forEach var="blog" items="${blogs}">
                                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                        <article class="tg-post">
                                                            <figure><a href="blogs?action=view&id=${blog.blogId}"><img src="${blog.blogThumbnail}" alt="image description"></a></figure>

                                                            <div class="tg-postcontent">
                                                                <ul class="tg-bookscategories">
                                                                    <li><a href="javascript:void(0);">${categoryNames[blog.categoryBlogId]}</a></li>
                                                                </ul>
                                                                <div class="tg-themetagbox"><span class="tg-themetag">featured</span></div>
                                                                <div class="tg-posttitle">
                                                                    <h3><a href="blogs?action=view&id=${blog.blogId}">${blog.blogTitle}</a></h3>
                                                                </div>
                                                                <div class="tg-description">
                                                                    <p>${blog.shortDesc} ... <a href="blogs?action=view&id=${blog.blogId}">More</a></p>
                                                                </div>
                                                                <span class="tg-bookwriter">By: <a href="javascript:void(0);">${adminNames[blog.adminId]}</a></span>
                                                                <ul class="tg-postmetadata">
                                                                    <li><a href="javascript:void(0);">${blog.blogUpdateDate}</a></li>
                                                                </ul>
                                                            </div>
                                                        </article>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <!--                                            Phân trang-->
                                            <div style="display: flex; justify-content: center;">
                                                <ul class="pagination">
                                                    <li class="page-item ${index == 1 ? 'disabled' : ''}">
                                                        <a class="page-link" href="blogs?index=${index == 1 ? 1 : index - 1}&search=${param['search']}">Previous</a>
                                                    </li>
                                                    <c:forEach var="i" begin="1" end="${numberPage}">
                                                        <li class="page-item ${index == i ? 'active' : ''}">
                                                            <a class="page-link" href="blogs?index=${i}&search=${param['search']}">Trang ${i}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item ${index == numberPage ? 'disabled' : ''}">
                                                        <a class="page-link" href="blogs?index=${index == (numberPage) ? numberPage : index + 1}&search=${param['search']}">Next</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--                                           Chức năng tìm kiếm-->
                                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-3 pull-left">
                                    <aside id="tg-sidebar" class="tg-sidebar">
                                        <div class="tg-widget tg-widgetsearch">
                                            <form action="blogs" method="GET" class="tg-formtheme tg-formsearch">
                                                <div class="form-group">
                                                    <button type="submit"><i class="fa fa-search"></i></button>
                                                    <input type="search" name="search" class="form-group" placeholder="Search Here" value="${param.search}">
                                                </div>

                                            </form>
                                        </div>


                                        <!--hien thi top trending-->
                                        <div class="tg-widget tg-widgettrending">
                                            <div class="tg-widgettitle">
                                                <h3>Trending Posts</h3>
                                            </div>
                                            <div class="tg-widgetcontent">
                                                <ul>
                                                    <c:forEach var="o" items="${top3}">
                                                        <li>
                                                            <article class="tg-post">
                                                                <figure><a href="blogs?action=view&id=${o.blogId}"><img src="${o.blogThumbnail}" alt="image description"></a></figure>
                                                                <div class="tg-postcontent">
                                                                    <div class="tg-posttitle">
                                                                        <h3><a href="blogs?action=view&id=${o.blogId}">${o.blogTitle}</a></h3>
                                                                    </div>
                                                                    <span class="tg-bookwriter"> <a href="javascript:void(0);">${o.blogUpdateDate}</a></span>
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
            </main>
            <footer id="tg-footer" class="tg-footer tg-haslayout">

            </footer>
        </div>
    </body>
</html>
