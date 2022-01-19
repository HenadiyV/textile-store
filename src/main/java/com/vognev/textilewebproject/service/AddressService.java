package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.AddressUser;
import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.dto.AddressUserDto;
import com.vognev.textilewebproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.ZipFile;

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


   public AddressUserDto getAddressUserDtoByAddressUser(AddressUser addressUser){

        return new AddressUserDto(addressUser.getId(),
                addressUser.getCity(),
                addressUser.getAddress(),
                addressUser.getPostCode(),
                addressUser.isActive());
    }


    void saveUserAddress(String city, String address, String postCode, MyUser newUser) {
        AddressUser addressUser=new AddressUser();
        addressUser.setCity(city);
        addressUser.setAddress(address);
        addressUser.setPostCode(postCode);
        addressUser.setAddressUser(newUser);
        addressUser.setInfo(" ");

        save(addressUser);
    }


    public boolean updateAddressUser(AddressUserDto addressUserDto) {
        try{
            AddressUser addressUser= addressRepository.getById(addressUserDto.getId());

            addressUser.setCity(addressUserDto.getCity());
            addressUser.setAddress(addressUserDto.getAddress());
            addressUser.setPostCode(addressUserDto.getPostCode());
            addressUser.setInfo(addressUserDto.getInfo());

            save(addressUser);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    AddressUser saveBasketAdddress(MyUser myUser){
        try{
            AddressUser addressUser = new AddressUser("city","address","postCode"," ",true,myUser);

         return save(addressUser);
        }catch(Exception ex){
            ex.printStackTrace();

        }
        return null;
    }


    public List<AddressUser> findAll() {
        return addressRepository.findAll();
    }


    public AddressUser save(AddressUser address) {
        return addressRepository.save(address);
    }


    public List<AddressUser> getAddressByUserId(Long userId) {
        return addressRepository.getAddressUserListByUserId(userId);
    }


    public List<AddressUser> getAll() {

        return addressRepository.findAll();
    }


    public void addAddressFromUser(String city, String address, String postCode, String info, boolean active, MyUser user) {
        System.out.println(city);
        System.out.println(address);
        System.out.println(postCode);
        System.out.println(info);
        System.out.println(active);
        System.out.println(user.getId());
        System.out.println(user.getName());
    }
}
