/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author hungp
 */
public class AdminCustomerRoleView {

    private int id;
    private String type;
    private String name;
    private int age;
    private String email;
    private String password;
    private boolean gender;
    private String address;
    private String city;
    private byte [] avatar;
    private String phoneNumber;
    private Date dob;
    private boolean status;
    private int roleId;
    private String roleName;
    private boolean roleStatus;

    public AdminCustomerRoleView() {
    }

    public AdminCustomerRoleView(String name, String email, String password, boolean gender, String address, String city, String phoneNumber, Date dob, boolean status, int roleId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.status = status;
        this.roleId = roleId;
    }
    
    
    
    public AdminCustomerRoleView(int id, String type, String name, int age, String email, String password, boolean gender, String address, String city, byte [] avatar, String phoneNumber, Date dob, boolean status, int roleId, String roleName, boolean roleStatus) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.status = status;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleStatus = roleStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte [] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte [] avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(boolean roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Override
    public String toString() {
        return "AdminCustomerRoleView{" + "id=" + id + ", type=" + type + ", name=" + name + ", age=" + age + ", email=" + email + ", password=" + password + ", gender=" + gender + ", address=" + address + ", city=" + city + ", avatar=" + avatar + ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", status=" + status + ", roleId=" + roleId + ", roleName=" + roleName + ", roleStatus=" + roleStatus + '}';
    }

    
}
