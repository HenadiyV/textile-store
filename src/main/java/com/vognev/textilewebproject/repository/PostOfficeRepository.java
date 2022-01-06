package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.model.PostOfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * textilewebproject_2  30/09/2021-14:04
 */
public interface PostOfficeRepository extends JpaRepository<PostOfficeUser,Long> {
}
