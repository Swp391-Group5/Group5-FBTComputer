/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quanb
 */
public class CategoryBlog {
 private int categoryBlogId;
    private String categoryBlogName;
    private boolean categoryBlogStatus;

    public CategoryBlog() {
    }

    public CategoryBlog(int categoryBlogId, String categoryBlogName, boolean categoryBlogStatus) {
        this.categoryBlogId = categoryBlogId;
        this.categoryBlogName = categoryBlogName;
        this.categoryBlogStatus = categoryBlogStatus;
    }

    public int getCategoryBlogId() {
        return categoryBlogId;
    }

    public void setCategoryBlogId(int categoryBlogId) {
        this.categoryBlogId = categoryBlogId;
    }

    public String getCategoryBlogName() {
        return categoryBlogName;
    }

    public void setCategoryBlogName(String categoryBlogName) {
        this.categoryBlogName = categoryBlogName;
    }

    public boolean isCategoryBlogStatus() {
        return categoryBlogStatus;
    }

    public void setCategoryBlogStatus(boolean categoryBlogStatus) {
        this.categoryBlogStatus = categoryBlogStatus;
    }

   


  
}
