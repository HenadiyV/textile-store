package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.PostOfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * textilewebproject_2  30/09/2021-14:04
 */
public interface PostOfficeRepository extends JpaRepository<PostOfficeUser,Long> {

    @Query("from PostOfficeUser p  where p.postOfficeUser.id =:id ")
    List<PostOfficeUser> getPostOfficeUserListByUserId(@Param("id")Long id);

}
