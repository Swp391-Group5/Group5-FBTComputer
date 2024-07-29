/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Blog {
       private int blogId;
    private String blogTitle;
    private String blogUpdateDate;
    private String blogContent;
    private String blogThumbnail;
    private int adminId;
    private int categoryBlogId;
    private int blogStatus;
    private String img;
     private String adminName;
    private String url;
     private String shortDesc;
    private CategoryBlog categoryBlog;
    public Blog() {
    }

    public Blog(int blogId, String blogTitle, String blogContent, int adminId, int blogStatus, String img, String url, CategoryBlog categoryBlog) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.adminId = adminId;
        this.blogStatus = blogStatus;
        this.img = img;
        this.url = url;
        this.categoryBlog = categoryBlog;
    }

    public Blog(int blogId, String blogTitle, String blogUpdateDate, String blogContent, String blogThumbnail, int adminId, int categoryBlogId, int blogStatus, String img, String adminName, String url, String shortDesc, CategoryBlog categoryBlog) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogUpdateDate = blogUpdateDate;
        this.blogContent = blogContent;
        this.blogThumbnail = blogThumbnail;
        this.adminId = adminId;
        this.categoryBlogId = categoryBlogId;
        this.blogStatus = blogStatus;
        this.img = img;
        this.adminName = adminName;
        this.url = url;
        this.shortDesc = shortDesc;
        this.categoryBlog = categoryBlog;
    }

    public Blog(int blogId, String blogTitle, String blogUpdateDate, String blogContent, String blogThumbnail, int adminId, int categoryBlogId, int blogStatus, String img, String url, String shortDesc, CategoryBlog categoryBlog) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogUpdateDate = blogUpdateDate;
        this.blogContent = blogContent;
        this.blogThumbnail = blogThumbnail;
        this.adminId = adminId;
        this.categoryBlogId = categoryBlogId;
        this.blogStatus = blogStatus;
        this.img = img;
        this.url = url;
        this.shortDesc = shortDesc;
        this.categoryBlog = categoryBlog;
    }

    public Blog(int blogId, String blogTitle, String blogUpdateDate, String blogContent, String blogThumbnail, int adminId, int blogStatus, String img, String url, String shortDesc, CategoryBlog categoryBlog) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogUpdateDate = blogUpdateDate;
        this.blogContent = blogContent;
        this.blogThumbnail = blogThumbnail;
        this.adminId = adminId;
        this.blogStatus = blogStatus;
        this.img = img;
        this.url = url;
        this.shortDesc = shortDesc;
        this.categoryBlog = categoryBlog;
    }

    public Blog(int blogId, String blogTitle, String blogUpdateDate, String blogContent, String blogThumbnail, int adminId, int blogStatus, String img, String url, CategoryBlog categoryBlog) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogUpdateDate = blogUpdateDate;
        this.blogContent = blogContent;
        this.blogThumbnail = blogThumbnail;
        this.adminId = adminId;
        this.blogStatus = blogStatus;
        this.img = img;
        this.url = url;
        this.categoryBlog = categoryBlog;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public int getCategoryBlogId() {
        return categoryBlogId;
    }

    public void setCategoryBlogId(int categoryBlogId) {
        this.categoryBlogId = categoryBlogId;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogUpdateDate() {
        return blogUpdateDate;
    }

    public void setBlogUpdateDate(String blogUpdateDate) {
        this.blogUpdateDate = blogUpdateDate;
    }


    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogThumbnail() {
        return blogThumbnail;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setBlogThumbnail(String blogThumbnail) {
        this.blogThumbnail = blogThumbnail;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getBlogStatus() {
        return blogStatus;
    }

    public String getShortDesc() {
        return shortDesc;
    }



    public void setBlogStatus(int blogStatus) {
        this.blogStatus = blogStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CategoryBlog getCategoryBlog() {
        return categoryBlog;
    }

    public void setCategoryBlog(CategoryBlog categoryBlog) {
        this.categoryBlog = categoryBlog;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogUpdateDate=" + blogUpdateDate + ", blogContent=" + blogContent + ", blogThumbnail=" + blogThumbnail + ", adminId=" + adminId + ", blogStatus=" + blogStatus + ", img=" + img + ", url=" + url + ", shortDesc=" + shortDesc + ", categoryBlog=" + categoryBlog + '}';
    }

    
    
    
}
