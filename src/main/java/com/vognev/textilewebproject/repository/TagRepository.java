package com.vognev.textilewebproject.repository;

import com.vognev.textilewebproject.dto.TagDto;
import com.vognev.textilewebproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * textile-store  15/02/2022-18:08
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("select new com.vognev.textilewebproject.dto.TagDto(tg.id,tg.name,false) from Tag tg")
    List<TagDto> getListTagDto();
}
