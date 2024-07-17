<%-- 
    Document   : listBlog
    Created on : Jun 15, 2024, 7:44:17 PM
    Author     : HP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Blog List</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>
    <body>
        <div class="container mt-5">
            <h2>Blog List</h2>
            <a href="managerBlog?action=add" class="btn btn-primary mb-3">Create New Blog</a>

            <div style="display: flex; justify-content: center; margin-top: 20px; margin-bottom: 20px;">
                <form action="managerBlog" method="GET" class="" style="width: 100%; max-width: 600px; display: flex; align-items: center; border: 1px solid #ccc; border-radius: 25px; padding: 5px; background-color: #f9f9f9;">
                    <input type="search" name="searchTitle" class="" placeholder="Search Here" value="${param.search}" style="flex: 1; border: none; outline: none; padding: 10px; border-radius: 25px 0 0 25px;">
<!--                        <input type="text" name="adminName" placeholder="Search by admin name" value="${param.adminName}" style="flex: 1; border: none; outline: none; padding: 10px; border-radius: 25px 0 0 25px;">-->
                    <select name="adminName">
                        <option value="">All Admins</option>
                        <c:forEach var="admin" items="${adminList}">
                            <option value="${admin.adminName}" <c:if test="${param.adminName == admin.adminName}">selected</c:if>>
                                ${admin.adminName}
                            </option>
                        </c:forEach>
                    </select>
                    <button type="submit" style="border: none; background-color: transparent; cursor: pointer; padding: 10px; border-radius: 0 25px 25px 0;">
                        <i class="fa fa-search" style="font-size: 20px; color: #333;"></i>
                    </button>
                </form>
            </div>


            <!--            <div style="display: flex; justify-content: start; margin-top: 10px;margin-bottom: 10px">
                            <form action="managerBlog" method="GET" class="tg-formtheme tg-formsearch">
                                <div class="form-group">
                                    <input type="search" name="search" class="form-group" placeholder="Search Here" value="${param.search}">
                                    <button type="submit"><i class="fa fa-search"></i></button>
            
                                </div>
            
                            </form>
            
                        </div>-->
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    ${param.error}
                </div>
            </c:if>
            <c:if test="${param.success != null}">
                <div class="alert alert-success">
                    ${param.success}
                </div>
            </c:if>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Date</th>
<!--                        <th>Thumbnail</th>-->
<!--                        <th>Admin</th>-->
                        <th>Category</th>
                        <th>Status</th>
                        <th>Image</th>
                        <th>Actions</th>
                        <!--                        <th>Actions</th>-->
                    </tr>
                </thead>
                <tbody>


                    <!--                <div style="display: flex; justify-content: center;">
                                        <ul class="pagination">
                                            <li class="page-item ${index == 1 ? 'disabled' : ''}">
                                                <a class="page-link" href="managerBlog?index=${index == 1 ? 1 : index - 1}&search=${param['search']}">Previous</a>
                                            </li>
                    <c:forEach var="i" begin="1" end="${numberPage}">
                        <li class="page-item ${index == i ? 'active' : ''}">
                            <a class="page-link" href="managerBlog?index=${i}&search=${param['search']}">Trang ${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${index == numberPage ? 'disabled' : ''}">
                        <a class="page-link" href="managerBlog?index=${index == (numberPage) ? numberPage : index + 1}&search=${param['search']}">Next</a>
                    </li>
                </ul>
            </div>-->




                <div style="display: flex; justify-content: flex-end; align-items: center; margin-top: 20px; margin-bottom: 20px;">
                    <form id="pageForm" action="managerBlog" method="GET" style="display: flex; align-items: center;">
                        <input type="hidden" id="search" name="search" value="${param.search}">
                        <span style="margin-right: 10px;"></span>
                        <select id="pageSelect" name="index" onchange="this.form.submit()" style="padding: 10px; border-radius: 25px; border: 1px solid #ccc; background-color: #f9f9f9;">
                            <c:forEach var="i" begin="1" end="${numberPage}">
                                <option value="${i}" ${param.index == i ? 'selected' : ''}>Page ${i}</option>
                            </c:forEach>
                        </select>

                    </form>
                </div>



                <c:forEach var="blog" items="${blogList}">
                    <tr>
                        <td>${blog.blogid}</td>
                        <td>${blog.blogtitle}</td>
                        <td>${blog.blogdate}</td>
<!--                        <td><img src="${blog.blogthumnail}" alt="Thumbnail" class="img-thumbnail" width="100"></td>-->
<!--                        <td>${adminNames[blog.adminid]}</td>-->
                        <td>${categoryNames[blog.categoryblogid]}</td>
                        <td>

                            <c:choose>
                                <c:when test="${blog.blogstatus == true}">
                                    <span class="badge badge-success">
                                        Active
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger">
                                        Inactive
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><img src="${blog.img}" alt="Image" class="img-thumbnail" width="100"></td>
    <!--                            <td><a href="${blog.url}" target="_blank">${blog.url}</a></td>-->
                        <td width =200>
                            <a href="managerBlog?action=edit&id=${blog.blogid}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="managerBlog?action=view&id=${blog.blogid}" class="btn btn-info btn-sm">View</a>
                            <a onclick="return confirm('Are you sure to delete?')" href="managerBlog?action=delete&id=${blog.blogid}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <a href="home" class="btn btn-primary mb-3">Back To Home</a>

        </div>
    </body>
</html>
