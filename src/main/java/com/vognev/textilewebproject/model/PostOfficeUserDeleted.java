package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  16/10/2021-9:11
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_office_deleted")
public class PostOfficeUserDeleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name = "native", strategy = "native")
    private Long id;
    private Long user_id;
    private String post_office;
    private String info;

    private Date dat_delete;

    public PostOfficeUserDeleted() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPost_office() {
        return post_office;
    }

    public void setPost_office(String post_office) {
        this.post_office = post_office;
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
