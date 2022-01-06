package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject  17/09/2021-18:05new  com.vognev.textilewebproject.model.dto.UserDto
 */
public interface MyUserRepository extends JpaRepository<MyUser,Long> {

    MyUser findByUsername(String username);

    MyUser findByName(String nick);

    MyUser findByEmail(String email);

    MyUser findByActivationCode(String code);

    @Query("select new com.vognev.textilewebproject.model.dto.UserDto(u.id,u.name,u.username,u.email,u.active) from MyUser u where u.username like :us% or u.name like :us%")
    List<UserDto> searchUserNickName(@Param("us")String us);

    @Query("from MyUser u where u.username like :login and u.name like :name")
    MyUser getUserNickName(@Param("login")String login,@Param("name")String name);

    @Query("from MyUser u where u.email like :email")
    Boolean getUserEmail(@Param("email")String email);

    @Query("select new com.vognev.textilewebproject.model.dto.UserDto(u.id,u.name,u.username,u.email,u.active) from MyUser u ")
    List<UserDto> listUserDto();
}
