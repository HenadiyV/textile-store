package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.PostOfficeUser;
import com.vognev.textilewebproject.model.dto.PostOfficeUserDto;
import com.vognev.textilewebproject.repository.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * textilewebproject_2  23/10/2021-7:04
 */
@Service
public class PostOfficeService {
    @Autowired
    private PostOfficeRepository postOfficeRepository;

    PostOfficeUser getPostOfficeById(Long id){
        return postOfficeRepository.getById(id);
    }

    void saveUserPostOffice(String postOffice, MyUser newUser) {

        PostOfficeUser postOfficeUser= new PostOfficeUser();

        postOfficeUser.setActive(true);
        postOfficeUser.setPostOffice(postOffice);
        postOfficeUser.setPostOfficeUser(newUser);
        postOfficeUser.setInfo(" ");

        postOfficeRepository.save(postOfficeUser);
    }


    public boolean updatePostOfficeUser(PostOfficeUserDto postOfficeUserDto) {
        try{
            PostOfficeUser postOfficeUser = postOfficeRepository.getById(postOfficeUserDto.getId());

            postOfficeUser.setPostOffice(postOfficeUserDto.getPostOffice());
            postOfficeUser.setInfo(postOfficeUserDto.getInfo());

            postOfficeRepository.save(postOfficeUser);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
           return false;
        }
    }


    void savePostOfficeBasket(MyUser myUser){
        try{
            PostOfficeUser postOfficeUser = new PostOfficeUser(" ",true,myUser," ");

            postOfficeRepository.save(postOfficeUser);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
