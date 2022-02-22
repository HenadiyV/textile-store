package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.PhoneUser;
import com.vognev.textilewebproject.dto.PhoneUserDto;
import com.vognev.textilewebproject.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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


    public PhoneUser getNumberPhone(String phone) {
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

            if (getNumberPhone(phone)== null) {

                PhoneUser phoneUser = new PhoneUser(phone, active, info, user);

                if (active) {

                    deactivationPhone(user);
                }
                save(phoneUser);
            }
    }


    private void deactivationPhone(MyUser user) {
        List<PhoneUser> phoneUserSet= getPhoneUserListFromUserId(user.getId());

        for(PhoneUser ph:phoneUserSet){
            ph.setActive(false);
            save(ph);
        }
    }


    public PhoneUserDto getPhoneUserDtoByPhoneUser(PhoneUser phoneUser) {

        return new PhoneUserDto(
                phoneUser.getId(),
                phoneUser.getPhone(),
                phoneUser.getInfo(),
                phoneUser.isActive()
        );
    }


    public void updateProne(Long id, String phone, String info, boolean active) {

        if(getNumberPhone(phone)==null){

                PhoneUser phoneUser = getPhoneById(id);

                phoneUser.setPhone(phone);
                phoneUser.setInfo(info);
                phoneUser.setActive(active);

                save(phoneUser);
        }else{
            System.out.println(phone+" phone exist");
        }
    }

    public List<PhoneUserDto> getListPhoneDtoByListPhone(Set<PhoneUser> phones) {
        List<PhoneUserDto> phoneUserDtos =new ArrayList<>();
        for(PhoneUser ph:phones){
            PhoneUserDto phDto= new PhoneUserDto(ph.getId(),ph.getPhone(),ph.isActive());
            phoneUserDtos.add(phDto);
        }
        return phoneUserDtos;
    }
}
