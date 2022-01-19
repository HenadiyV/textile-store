package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.PhoneUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * textilewebproject_2  30/09/2021-14:01
 */
public interface PhoneRepository extends JpaRepository<PhoneUser,Long> {

    @Query("from PhoneUser ph where ph.phone like :phone")
    PhoneUser getPhoneUserByPhone(@Param("phone")String phone);

    @Query("from PhoneUser ph where ph.phoneUser.id =:id")
    List<PhoneUser> getPhoneListByUserId(@Param("id")Long id);
}
