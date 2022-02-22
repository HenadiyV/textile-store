package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.AddressUser;
import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.dto.AddressUserDto;
import com.vognev.textilewebproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * textilewebproject_2  23/10/2021-6:55
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    AddressUser getAddressById(Long id){
        return addressRepository.getById(id);
    }


   public AddressUserDto getAddressUserDtoByAddressUser(AddressUser addressUser){

        return new AddressUserDto(
                addressUser.getId(),
                addressUser.getRegion(),
                addressUser.getDistrict(),
                addressUser.getCity(),
                addressUser.getAddress(),
                addressUser.getPostCode(),
                addressUser.isActive());
    }


    void saveUserAddress(String region,String district,String city,
                         String address, String postCode, MyUser newUser
    ) {
        AddressUser addressUser=new AddressUser();

        addressUser.setRegion(region);
        addressUser.setDistrict(district);
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

            addressUser.setRegion(addressUserDto.getRegion());
            addressUser.setDistrict(addressUserDto.getDistrict());
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
            AddressUser addressUser = new AddressUser("region", "district","city",
                    "address","postCode"," ",true,myUser);

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


    public void addAddressFromUser( String region,String district,String city, String address,
                                    String postCode, String info, boolean active, MyUser user) {

        AddressUser addressUser = new AddressUser(region,district,city,address,postCode,info,active,user);

        if(active) {

            List<AddressUser> addressUserList= getAddressByUserId(user.getId());

            for (AddressUser adr : addressUserList) {

                adr.setActive(false);
                save(adr);
            }
        }
        save(addressUser);
    }


    public void updateAddress(Long id, String region,String district,String city, String address, String postCode, String info, boolean active) {

        AddressUser addressUser = getAddressById(id);

        addressUser.setActive(active);
        addressUser.setRegion(region);
        addressUser.setDistrict(district);
        addressUser.setCity(city);
        addressUser.setAddress(address);
        addressUser.setPostCode(postCode);
        addressUser.setInfo(info);

        save(addressUser);
    }

    public List<AddressUserDto> getListAddressDtoByListAddress(Set<AddressUser> addresses) {
        List<AddressUserDto> addressUserDtos = new ArrayList<>();
        for(AddressUser adr:addresses){
            AddressUserDto addressUserDto= new AddressUserDto(adr.getId(),
                    adr.getRegion(),
                    adr.getDistrict(),
                    adr.getCity(),
                    adr.getAddress(),
                    adr.getPostCode(),
                    adr.isActive());
            addressUserDtos.add(addressUserDto);
        }
        return addressUserDtos;
    }
}
