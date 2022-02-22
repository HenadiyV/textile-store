package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.dto.UserDto;
import com.vognev.textilewebproject.model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
//import com.vognev.textilewebproject.dto.UserDto;
import java.util.List;

/**
 * textilewebproject  17/09/2021-18:05new  com.vognev.textilewebproject.dto.UserDto
 */
public interface MyUserRepository extends JpaRepository<MyUser,Long> {

    MyUser findByUsername(String username);

    MyUser findByName(String nick);

    MyUser findByEmail(String email);

    MyUser findByActivationCode(String code);

    @Query("select new com.vognev.textilewebproject.dto.UserDto(u.id,u.name,u.username,u.email,u.active) from MyUser u where u.username like :us% or u.name like :us%")
    List<UserDto> searchUserNickName(@Param("us")String us);

    @Query("from MyUser u where u.username like :login and u.name like :name")
    MyUser getUserNickName(@Param("login")String login,@Param("name")String name);

    @Query("from MyUser u where u.email like :email")
    Boolean getUserEmail(@Param("email")String email);

    @Query("select new com.vognev.textilewebproject.dto.UserDto(u.id,u.name,u.username,u.email,u.active) from MyUser u")
    List<UserDto> listUserDto();

    @Query("SELECT new com.vognev.textilewebproject.dto.UserDto(u.id,u.name,u.username,u.email,u.active) FROM MyUser u WHERE u.active=1 ORDER BY u.id DESC")
    List<UserDto> listUserLimit(Pageable pageable);

    @Query("select count(us)from MyUser us")
    Integer countUser();

    @Query("from MyUser u where u.username like :us% or u.name like :us%")
    List<MyUser> findByNameAndUsername(@Param("us")String us);

//    @Query("from com.vognev.textilewebproject.model.Role r where r.roles =:rol")
//    List<Role> getUserRole(@Param("rol")String rol);
}
