package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.PostOfficeUser;
import com.vognev.textilewebproject.dto.PostOfficeUserDto;
import com.vognev.textilewebproject.repository.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        PostOfficeUser postOfficeUser = new PostOfficeUser(postOffice,active,user,info);

        if(active){

            List<PostOfficeUser> postOfficeUserSet = postOfficeRepository.getPostOfficeUserListByUserId(user.getId());

            for(PostOfficeUser po: postOfficeUserSet){

                po.setActive(false);

                save(po);
            }
        }
        save(postOfficeUser);
    }


    public PostOfficeUserDto getPoastOfficeUserDtoByPostOfficeUser(PostOfficeUser postOfficeUser) {

        return new PostOfficeUserDto(
                postOfficeUser.getId(),
                postOfficeUser.getPostOffice(),
                postOfficeUser.getInfo(),
                postOfficeUser.isActive());
    }

    public void updatePostOffice(Long id, String postOffice, String info, boolean active) {

        PostOfficeUser postOfficeUser = getPostOfficeById(id);

        postOfficeUser.setPostOffice(postOffice);
        postOfficeUser.setInfo(info);
        postOfficeUser.setActive(active);

        save(postOfficeUser);
    }

    public List<PostOfficeUserDto> getListPostOfficeDtoByListPostOffice(Set<PostOfficeUser> postOfficeUsers) {
        List<PostOfficeUserDto> postOfficeUserDtos = new ArrayList<>();
        for(PostOfficeUser po:postOfficeUsers){
            PostOfficeUserDto pf =  new PostOfficeUserDto(po.getId(),po.getPostOffice(),po.getInfo(),po.isActive());
            postOfficeUserDtos.add(pf);
        }
        return postOfficeUserDtos;
    }
}
