package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.PhoneUser;
import com.vognev.textilewebproject.model.dto.PhoneUserDto;
import com.vognev.textilewebproject.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

/**
 * textilewebproject_2  23/10/2021-6:59
 */
@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;


    PhoneUser getPhoneById(Long id){
        return phoneRepository.getById(id);
    }


    PhoneUser getNumberPhone(String phone) {
        return phoneRepository.getPhoneUserByPhone(phone);
    }


    PhoneUser saveUserPhone(String phone, MyUser newUser) {
        try{
        PhoneUser phoneUser= new PhoneUser();

        phoneUser.setPhone(phone);
        phoneUser.setPhoneUser(newUser);
        phoneUser.setActive(true);
        phoneUser.setInfo(" ");

      return  phoneRepository.save(phoneUser);
        }catch(Exception ex){
            System.out.println("saveUserPhone 42");
            ex.printStackTrace();
        }
        return null;
    }


    public boolean updatePhoneUser(PhoneUserDto ph) {

        PhoneUser phoneUser = phoneRepository.getById(ph.getId());

        Map<String,String> errorMap= new HashMap<>();

        PhoneUser phoneUserDb=getNumberPhone(ph.getPhone());

            if(phoneUserDb==null){

                phoneUser.setInfo(ph.getInfo());
                phoneUser.setPhone(ph.getPhone());

                save(phoneUser);
            }else if(phoneUserDb.getPhoneUser().getId().equals(ph.getUserId())) {

                phoneUser.setInfo(ph.getInfo());
                phoneUser.setPhone(ph.getPhone());

                save(phoneUser);
            }else{

            return false;
        }
       return true;
    }


    public List<PhoneUser> findAll() {
        return phoneRepository.findAll();
    }


    public PhoneUser save(PhoneUser phoneUser) {
        return phoneRepository.save(phoneUser);
    }


    public List<PhoneUser> getPhoneUserListFromUserId(Long userId){

        return phoneRepository.getPhoneListByUserId(userId);
    }

    public void addPhoneFromUser(String phone, String info, boolean active, MyUser user) {

        System.out.println(phone);
        System.out.println(info);
        System.out.println(active);
        System.out.println(user.getId());
        System.out.println(user.getName());

    }

    public PhoneUserDto getPhoneUserDtoByPhoneUser(PhoneUser phoneUser) {

        return new PhoneUserDto(
                phoneUser.getId(),
                phoneUser.getPhone(),
                phoneUser.getInfo(),
                phoneUser.isActive()
        );
    }
}
