package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  29/09/2021-13:25
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "post_office_user")
public class PostOfficeUser implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    private String postOffice;

    private boolean active;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="user_id")
    private MyUser postOfficeUser;

    private String info;

    public PostOfficeUser() {
    }

    public PostOfficeUser(String postOffice, boolean active, MyUser postOfficeUser, String info) {
        this.postOffice = postOffice;
        this.active = active;
        this.postOfficeUser = postOfficeUser;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public MyUser getPostOfficeUser() {
        return postOfficeUser;
    }

    public void setPostOfficeUser(MyUser postOfficeUser) {
        this.postOfficeUser = postOfficeUser;
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

    public void setActive(boolean active) {
        this.active = active;
    }
}
