package com.example.readingmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.readingmanager.dto.ReadingLogSearchDto;
import com.example.readingmanager.entities.Author;
import com.example.readingmanager.entities.ReadingLog;
import com.example.readingmanager.entities.Work;
import com.example.readingmanager.repositories.AuthorRepository;
import com.example.readingmanager.repositories.ReadingLogRepository;
import com.example.readingmanager.repositories.WorkRepository;
import com.example.readingmanager.specifications.WorkSpecifications;

import jakarta.transaction.Transactional;

@Controller
public class WorkController {
    // 作品検索
    @Autowired AuthorRepository authorRepository;
    @Autowired ReadingLogRepository readingLogRepository;
    @Autowired WorkRepository workRepository;

    @GetMapping("/searchWork")
    public ModelAndView searchWork(ModelAndView mav) {
        mav.setViewName("redinghtml/rdsearch");
        mav.addObject("title", "作品検索");
        mav.addObject("formModel", new ReadingLogSearchDto());
        List<Author> ath = authorRepository.findAll();
        mav.addObject("authorList", ath);
        return mav;
    }
    @PostMapping("/searchWork")
    @Transactional
    public ModelAndView search(@ModelAttribute("formModel") @Validated ReadingLogSearchDto dto, BindingResult result, ModelAndView mav) {
        System.out.println(result.getFieldError());

        if(result.hasErrors()) {
            // エラーがある場合
            mav.setViewName("redinghtml/rdsearch");
            mav.addObject("title", "作品検索");
            return mav;
        }
        Specification<Work> spec = Specification
            .where(WorkSpecifications.titleContains(dto.getTitle()))
            .and(WorkSpecifications.authorIdEquals(dto.getAuthorId()))
            .and(WorkSpecifications.isReadStatus(dto.getReadStatus()));
        List<Work> list = workRepository.findAll(spec);
        mav.setViewName("redinghtml/rdsearch");
        mav.addObject("title", "作品検索");
        mav.addObject("search", "検索結果");
        List<Author> ath = authorRepository.findAll();
        mav.addObject("authorList", ath);
        mav.addObject("data", list);
        return mav;
    }
}
