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

    void saveUserPhone(String phone, MyUser newUser) {
        PhoneUser phoneUser= new PhoneUser();
        phoneUser.setPhone(phone);
        phoneUser.setPhoneUser(newUser);
        phoneUser.setActive(true);
        phoneUser.setInfo(" ");
        phoneRepository.save(phoneUser);
    }


    public boolean updatePhoneUser(PhoneUserDto ph) {

        PhoneUser phoneUser = phoneRepository.getById(ph.getId());

        Map<String,String> errorMap= new HashMap<>();

        PhoneUser phoneUserDb=getNumberPhone(ph.getPhone());

            if(phoneUserDb==null){

                phoneUser.setInfo(ph.getInfo());
                phoneUser.setPhone(ph.getPhone());

                phoneRepository.save(phoneUser);
            }else if(phoneUserDb.getPhoneUser().getId().equals(ph.getUserId())) {

                phoneUser.setInfo(ph.getInfo());
                phoneUser.setPhone(ph.getPhone());

                phoneRepository.save(phoneUser);
            }else{

            return false;
        }
       return true;
    }
}
