package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.dto.TagDto;
import com.vognev.textilewebproject.model.Tag;
import com.vognev.textilewebproject.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * textile-store  15/02/2022-18:09
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll(){
      return tagRepository.findAll();
    }

    public List<TagDto> getListTagDto(){
      return tagRepository.getListTagDto();
    }


    public void save(Tag tag){
        tagRepository.save(tag);
    }

    public Tag getById(int id){
        return tagRepository.getById(id);
    }

    public List<TagDto> getProductTag(Set<Tag> productSet){
        List<Tag> tags=tagRepository.findAll();
        List<TagDto> tagDtos = new ArrayList<>();

        for(Tag tg:tags){
            TagDto tagDto=new TagDto();
            tagDto.setActive(false);
            tagDto.setId(tg.getId());
            tagDto.setName(tg.getName());
            for(Tag tgS:productSet){
                if(tg.equals(tgS)){
                    System.out.println(tg.getName());
                    tagDto.setActive(true);
                }
            }
            tagDtos.add(tagDto);
        }
        return tagDtos;
    }
}
