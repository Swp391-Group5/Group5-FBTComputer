/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.restfb.Facebook;

/**
 *
 * @author DELL DN
 */
public class UserFacebookDto {

    @Facebook
    private String name;
    
    @Facebook
    private String id;
    
    @Facebook
    private String email;

    public UserFacebookDto() {
    }

    public UserFacebookDto(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserFacebookDto{" + "name=" + name + ", id=" + id + ", email=" + email + '}';
    }

}
