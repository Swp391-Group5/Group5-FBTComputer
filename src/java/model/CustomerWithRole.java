/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hungp
 */
public class CustomerWithRole {

    private Customer customer;
    private Role role;

    public CustomerWithRole(Customer customer, Role role) {
        this.customer = customer;
        this.role = role;
    }

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CustomerWithRole{" + "customer=" + customer + ", role=" + role + '}';
    }
    
}
