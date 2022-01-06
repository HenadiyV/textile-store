package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  29/09/2021-13:26
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "phone_user")
public class PhoneUser implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    private String info;

    private boolean active;

    private String phone;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="user_id")
    private MyUser phoneUser;

    public PhoneUser() {
    }

    public PhoneUser( String phone,boolean active, MyUser phoneUser) {
        this.active = active;
        this.phone = phone;
        this.phoneUser = phoneUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MyUser getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(MyUser phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
