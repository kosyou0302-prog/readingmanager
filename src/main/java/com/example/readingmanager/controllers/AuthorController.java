package com.example.readingmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.readingmanager.entities.Author;
import com.example.readingmanager.entities.ReadingLog;
import com.example.readingmanager.repositories.AuthorRepository;
import com.example.readingmanager.repositories.ReadingLogRepository;
import com.example.readingmanager.repositories.WorkRepository;

import jakarta.transaction.Transactional;

@Controller
public class AuthorController {

    @Autowired AuthorRepository authorRepository;
    @Autowired ReadingLogRepository readingLogRepository;
    @Autowired WorkRepository workrRepository;

    @GetMapping("/readingMng")
    public ModelAndView readingMng(@ModelAttribute("formModel") ReadingLog readingLog, ModelAndView mav) {
        mav.setViewName("/redinghtml/index");
        mav.addObject("title", "読書記録");
        List<Author> list = authorRepository.findAll();
        mav.addObject("data", list);
        return mav;
    }
    @PostMapping("/readingMng")
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") @Validated ReadingLog readingLog, BindingResult result, ModelAndView mav) {
        ModelAndView res = null;
        System.out.println(result.getFieldErrors());
        if (!result.hasErrors()) {
            readingLogRepository.saveAndFlush(readingLog);
            res = mav;
        } else {
            
        }
        return res;
    }
}
