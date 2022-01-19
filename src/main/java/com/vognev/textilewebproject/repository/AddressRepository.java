package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.AddressUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * textilewebproject_2  30/09/2021-14:03
 */
public interface AddressRepository extends JpaRepository<AddressUser,Long> {

    @Query("from AddressUser adr where adr.addressUser.id =:user_id")
    List<AddressUser> getAddressUserListByUserId(@Param("user_id")Long user_id);

}
