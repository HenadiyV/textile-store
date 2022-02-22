package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.Tag;
import com.vognev.textilewebproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

/**
 * textilewebproject_3  10/11/2021-21:45
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/tag")
    public String allTag(Model model){

        model.addAttribute("tagList", tagService.findAll());

        return "admin-tag";
    }


    @PostMapping("/tag")
    public String addTag(
            @Valid Tag tag,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("tag", tag);
        } else {

            model.addAttribute("tag", null);

            tagService.save(tag);
        }
        model.addAttribute("tagList", tagService.findAll());

        return "admin-tag";
    }


    @GetMapping("/tag/{tag}")
    public String editTag(
            @PathVariable Tag tag,
            Model model
    ){
        model.addAttribute("tag", tag);
        model.addAttribute("tagList", tagService.findAll());

        return "admin-tag";
    }


    @PostMapping("/tag/{tag}")
    public String updateTag(
            @Valid Tag tag,
            @RequestParam String name,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("tag", tag);
        } else {
            if(!name.isEmpty()) {
                tag.setName(name);
                tagService.save(tag);
            }
            model.addAttribute("tag", null);


        }
        model.addAttribute("tagList", tagService.findAll());

        return "admin-tag";
    }

}
