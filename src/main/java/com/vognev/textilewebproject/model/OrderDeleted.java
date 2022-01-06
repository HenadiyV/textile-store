package com.vognev.textilewebproject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * textilewebproject_2  15/10/2021-11:06
 */
@Entity
@Table(name="order_deleted")
public class OrderDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Long order_id;
    private Long user_id;
    private String nick ;
    private String  name;
    private String phone;
    private String address;
    private String  post_office;
    private double summ ;
    @Temporal(TemporalType.DATE)
    private Date dat_create ;
    @Temporal(TemporalType.DATE)
    private Date dat_delete;
    private String status ;
    private String  info ;
    private boolean canceled;

    public OrderDeleted() {
    }

    public OrderDeleted(Long id, Long order_id, Long user_id, String nick, String name, String phone, String address,
                        String post_office, double summ, Date dat_create, Date dat_delete, String status, String info,
                        boolean canceled) {
        this.id = id;
        this.order_id = order_id;
        this.user_id = user_id;
        this.nick = nick;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.post_office = post_office;
        this.summ = summ;
        this.dat_create = dat_create;
        this.dat_delete = dat_delete;
        this.status = status;
        this.info = info;
        this.canceled = canceled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost_office() {
        return post_office;
    }

    public void setPost_office(String post_office) {
        this.post_office = post_office;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public Date getDat_create() {
        return dat_create;
    }

    public void setDat_create(Date dat_create) {
        this.dat_create = dat_create;
    }

    public Date getDat_delete() {
        return dat_delete;
    }

    public void setDat_delete(Date dat_delete) {
        this.dat_delete = dat_delete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
