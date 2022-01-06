package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  16/10/2021-9:16
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phone_deleted")
public class PhoneUserDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name = "native", strategy = "native")
    private Long id;
    private Long phone_id;
    private Long user_id;
    private String phone;
    private String info;
    private Date dat_delete;

    public PhoneUserDeleted() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(Long phone_id) {
        this.phone_id = phone_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDat_delete() {
        return dat_delete;
    }

    public void setDat_delete(Date dat_delete) {
        this.dat_delete = dat_delete;
    }
}
