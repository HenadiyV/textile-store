package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.AddressUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * textilewebproject_2  30/09/2021-14:03
 */
public interface AddressRepository extends JpaRepository<AddressUser,Long> {
}
