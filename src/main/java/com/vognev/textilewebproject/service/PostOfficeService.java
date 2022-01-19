package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.PostOfficeUser;
import com.vognev.textilewebproject.model.dto.PostOfficeUserDto;
import com.vognev.textilewebproject.repository.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.ZipFile;

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

        save(postOfficeUser);
    }


    public boolean updatePostOfficeUser(PostOfficeUserDto postOfficeUserDto) {
        try{
            PostOfficeUser postOfficeUser = postOfficeRepository.getById(postOfficeUserDto.getId());

            postOfficeUser.setPostOffice(postOfficeUserDto.getPostOffice());
            postOfficeUser.setInfo(postOfficeUserDto.getInfo());

            save(postOfficeUser);

            return true;
        }catch (Exception ex){
            ex.printStackTrace();
           return false;
        }
    }


    PostOfficeUser savePostOfficeBasket(MyUser myUser){
        try{
            PostOfficeUser postOfficeUser = new PostOfficeUser(" ",true,myUser," ");

         return   save(postOfficeUser);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    public List<PostOfficeUser> findAll() {
        return postOfficeRepository.findAll();
    }


    public PostOfficeUser save(PostOfficeUser postOfficeUser) {
        return postOfficeRepository.save(postOfficeUser);
    }


    public List<PostOfficeUser> getPostOfficeUserListFromUserId(Long userId){

        return postOfficeRepository.getPostOfficeUserListByUserId(userId);
    }


    public void addPostOfficeFromUser(String postOffice, String info, boolean active, MyUser user) {

        System.out.println(postOffice);
        System.out.println(info);
        System.out.println(active);
        System.out.println(user.getId());
        System.out.println(user.getName());
    }


    public PostOfficeUserDto getPoastOfficeUserDtoByPostOfficeUser(PostOfficeUser postOfficeUser) {

        return new PostOfficeUserDto(
                postOfficeUser.getId(),
                postOfficeUser.getPostOffice(),
                postOfficeUser.getInfo(),
                postOfficeUser.isActive());
    }
}
