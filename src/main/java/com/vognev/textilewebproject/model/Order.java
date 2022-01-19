package com.vognev.textilewebproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * textilewebproject_2  15/10/2021-10:29
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dat_dispatch;

   // @NotNull(message = "status cannot be empty")
    private String status;

    //@NotNull(message = "delivery cannot be empty")
    private String delivery;

    private String info;

    @Temporal(TemporalType.DATE)
    private Date dat_create;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

    @OneToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="user_id")
    private MyUser user;

    @OneToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="address_id")
    private AddressUser addressUser;

    @OneToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="phone_id")
    private PhoneUser phoneUser;

    @OneToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="post_office_id")
    private PostOfficeUser postOfficeUser;

    public Order() {
    }

    public Order(Long id,
                 Date dat_dispatch,
                  String status,//@NotNull(message = "status cannot be empty")
                 String delivery, //@NotNull(message = "delivery cannot be empty")
                 String info_order,
                 Date dat_create,
                 MyUser user,
                 AddressUser addressUser,
                 PhoneUser phoneUser,
                 PostOfficeUser postOfficeUser) {
        this.id = id;
        this.dat_dispatch = dat_dispatch;
        this.status = status;
        this.delivery = delivery;
        this.info = info_order;
        this.dat_create = dat_create;
        this.user = user;
        this.addressUser = addressUser;
        this.phoneUser = phoneUser;
        this.postOfficeUser = postOfficeUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDat_dispatch() {
        return dat_dispatch;
    }

    public void setDat_dispatch(Date dat_dispatch) {
        this.dat_dispatch = dat_dispatch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDat_create() {
        return dat_create;
    }

    public void setDat_create(Date dat_create) {
        this.dat_create = dat_create;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public AddressUser getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(AddressUser addressUser) {
        this.addressUser = addressUser;
    }

    public PhoneUser getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(PhoneUser phoneUser) {
        this.phoneUser = phoneUser;
    }

    public PostOfficeUser getPostOfficeUser() {
        return postOfficeUser;
    }

    public void setPostOfficeUser(PostOfficeUser postOfficeUser) {
        this.postOfficeUser = postOfficeUser;
    }
}