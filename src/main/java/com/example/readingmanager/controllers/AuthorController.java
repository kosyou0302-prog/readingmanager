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
import com.example.readingmanager.entities.Work;
import com.example.readingmanager.repositories.AuthorRepository;
import com.example.readingmanager.repositories.ReadingLogRepository;
import com.example.readingmanager.repositories.WorkRepository;

import jakarta.transaction.Transactional;

@Controller
public class AuthorController {

    @Autowired AuthorRepository authorRepository;
    @Autowired ReadingLogRepository readingLogRepository;
    @Autowired WorkRepository workrRepository;

    // ホーム画面
    @GetMapping("/readingMng")
    public ModelAndView readingMng(@ModelAttribute("formModel") ReadingLog readingLog, ModelAndView mav) {
        // 読書記録
        mav.setViewName("redinghtml/index");
        mav.addObject("title", "読書記録");
        List<ReadingLog> list = readingLogRepository.findAll();
        mav.addObject("data", list);
        // 読了登録
        mav.addObject("add","新しい読書記録を追加");
        List<Work> worklist = workrRepository.findAll();
        mav.addObject("wklist",worklist);
        // 補助機能
        mav.addObject("Assistance", "補助機能");
        return mav;
    }
    @PostMapping("/readingMng")
    @Transactional
    public ModelAndView form(@ModelAttribute @Validated ReadingLog readingLog, BindingResult result, ModelAndView mav) {
        ModelAndView res = null;
        System.out.println(result.getFieldError());
        if (result.hasErrors()) {
            readingLogRepository.saveAndFlush(readingLog);
            res = new ModelAndView("redirect:/readingMng");
        } else {
            mav.setViewName("redinghtml/index");
            Iterable<ReadingLog> list = readingLogRepository.findAll();
            mav.addObject("datalist", list);
            res = mav;
        }
        return res;
    }
    // 更新---------------------------------------------------------------------------------------------------------


    // 削除---------------------------------------------------------------------------------------------------------


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
        return mav;
    }

    // 作品検索---------------------------------------------------------------------------------------------------------

}
