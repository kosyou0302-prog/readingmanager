package com.example.readingmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.readingmanager.entities.Author;
import com.example.readingmanager.repositories.AuthorRepository;
import com.example.readingmanager.repositories.ReadingLogRepository;
import com.example.readingmanager.repositories.WorkRepository;

import jakarta.transaction.Transactional;

@Controller
public class AuthorController {

    @Autowired AuthorRepository authorRepository;
    @Autowired ReadingLogRepository readingLogRepository;
    @Autowired WorkRepository workrRepository;

    // 著者追加---------------------------------------------------------------------------------------------------------
    @GetMapping("/addAuthor")
    public ModelAndView addAuthor(@ModelAttribute("formModel") Author author ,ModelAndView mav) {
        mav.setViewName("redinghtml/rdauthor");
        mav.addObject("title", "著者追加");
        return mav;
    }
    @PostMapping("/addAuthor")
    @Transactional
    public ModelAndView add(@ModelAttribute Author author, ModelAndView mav) {
        authorRepository.saveAndFlush(author);
        return new ModelAndView("redirect:/readingMng");
    }
}
