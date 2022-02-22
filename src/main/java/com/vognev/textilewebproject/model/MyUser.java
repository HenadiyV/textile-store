package com.vognev.textilewebproject.model;

/**
 * textilewebproject  17/09/2021-18:02
 */

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "my_user")
public class MyUser implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;


    private String username;

    private String name;

    private String password;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    private String email;

    private String activationCode;

    private String info;

    private int rating;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }


    @OneToMany(mappedBy = "phoneUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PhoneUser> phones;
    @OneToMany(mappedBy = "addressUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<AddressUser> addresses;
    @OneToMany(mappedBy = "postOfficeUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PostOfficeUser> postOfficeUsers;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> userOrders;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return Objects.equals(id, myUser.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public MyUser() {
    }

    public MyUser(String name, String email,String password,int rating, boolean active) {
        this.name = name;
        this.email = email;
        this.active = active;
        this.rating = rating;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Set<PhoneUser> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneUser> phones) {
        this.phones = phones;
    }

    public Set<AddressUser> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressUser> addresses) {
        this.addresses = addresses;
    }

    public Set<PostOfficeUser> getPostOfficeUsers() {
        return postOfficeUsers;
    }

    public void setPostOfficeUsers(Set<PostOfficeUser> postOfficeUsers) {
        this.postOfficeUsers = postOfficeUsers;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<Order> userOrders) {
        this.userOrders = userOrders;
    }
}