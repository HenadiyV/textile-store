package com.vognev.textilewebproject.model;

/**
 * textilewebproject_2  29/09/2021-13:25
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address_user")
public class AddressUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name = "native", strategy = "native")
    private Long id;

    private String region;

    private String district;

    private String city;

    private String address;

    private String postCode;

    private String info;

    private boolean active;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="user_id")
    private MyUser addressUser;

    public AddressUser() {
    }

    public AddressUser( String region,
                       String district, String city,
                       String address, String postCode,
                       String info, boolean active,
                       MyUser addressUser
    ) {

        this.region = region;
        this.district = district;
        this.city = city;
        this.address = address;
        this.postCode = postCode;
        this.info = info;
        this.active = active;
        this.addressUser = addressUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addr) {
        this.address = addr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public MyUser getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(MyUser addressUser) {
        this.addressUser = addressUser;
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
