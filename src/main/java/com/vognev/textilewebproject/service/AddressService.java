package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.AddressUser;
import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.dto.AddressUserDto;
import com.vognev.textilewebproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * textilewebproject_2  23/10/2021-6:55
 */
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


    AddressUser getAddressById(Long id){
        return addressRepository.getById(id);
    }


    void saveUserAddress(String city, String address, String postCode, MyUser newUser) {
        AddressUser addressUser=new AddressUser();
        addressUser.setCity(city);
        addressUser.setAddress(address);
        addressUser.setPostCode(postCode);
        addressUser.setAddressUser(newUser);
        addressUser.setInfo(" ");
        addressRepository.save(addressUser);
    }


    public boolean updateAddressUser(AddressUserDto addressUserDto) {
        try{
            AddressUser addressUser= addressRepository.getById(addressUserDto.getId());

            addressUser.setCity(addressUserDto.getCity());
            addressUser.setAddress(addressUserDto.getAddress());
            addressUser.setPostCode(addressUserDto.getPostCode());
            addressUser.setInfo(addressUserDto.getInfo());

            addressRepository.save(addressUser);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    void saveBasketAdddress(MyUser myUser){
        try{
            AddressUser addressUser = new AddressUser();

            addressRepository.save(addressUser);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
