package com.example.readingmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.readingmanager.entities.Author;
import com.example.readingmanager.entities.ReadingLog;
import com.example.readingmanager.entities.Work;
import com.example.readingmanager.repositories.AuthorRepository;
import com.example.readingmanager.repositories.ReadingLogRepository;
import com.example.readingmanager.repositories.WorkRepository;

import jakarta.transaction.Transactional;

@Controller
public class ReadingLogController {

    @Autowired AuthorRepository authorRepository;
    @Autowired ReadingLogRepository readingLogRepository;
    @Autowired WorkRepository workRepository;

    // ホーム画面
    @GetMapping("/readingMng")
    public ModelAndView readingMng(@ModelAttribute("formModel") ReadingLog readingLog, ModelAndView mav) {
        // 読書記録
        mav.setViewName("redinghtml/index");
        mav.addObject("title", "読書記録");
        List<ReadingLog> recentLogs = readingLogRepository.findTop5ByOrderByFinishedDateDesc();
        mav.addObject("recentLogs", recentLogs);
        // 読了登録
        mav.addObject("add","新しい読書記録を追加");
        List<Work> worklist = workRepository.findAll();
        mav.addObject("wklist",worklist);
        // 補助機能
        mav.addObject("Assistance", "補助機能");
        return mav;
    }
    @PostMapping("/readingMng")
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") @Validated ReadingLog readingLog, BindingResult result, ModelAndView mav) {
        System.out.println(result.getFieldError());

        if (result.hasErrors()) {
            // エラー
            mav.setViewName("redinghtml/index");
            mav.addObject("title", "読書記録");
            mav.addObject("data", readingLogRepository.findAll());
            mav.addObject("add", "新しい読書記録を追加");
            mav.addObject("wklist", workRepository.findAll());
            mav.addObject("Assistance", "補助機能");
            return mav;
        }
        // エラーじゃない
        readingLogRepository.saveAndFlush(readingLog);
        return new ModelAndView("redirect:/readingMng");
    }
    // 更新---------------------------------------------------------------------------------------------------------
    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@ModelAttribute("formModel") ReadingLog readingLog, @PathVariable Long id, ModelAndView mav) {
        mav.setViewName("redinghtml/rdedit");
        mav.addObject("title", "読了記録更新");
        List<Work> worklist = workRepository.findAll();
        mav.addObject("wklist",worklist);
        Optional<ReadingLog> data = readingLogRepository.findById(id);
        mav.addObject("formModel", data.get());
        return mav;
    }
    @PostMapping("/edit")
    @Transactional
    public ModelAndView edit(@ModelAttribute("formModel") @Validated ReadingLog readingLog, BindingResult result, ModelAndView mav) {
        System.out.println(result.getFieldError());

        if(result.hasErrors()) {
            // エラー
            mav.setViewName("redinghtml/rdedit");
            mav.addObject("title", "読了記録更新");
            mav.addObject("wklist", workRepository.findAll());
            return mav;
        }
        // エラーじゃない
        readingLogRepository.saveAndFlush(readingLog);
        return new ModelAndView("redirect:/readingMng");
    }

    // 削除---------------------------------------------------------------------------------------------------------
    @GetMapping("/deletelog/{id}")
    public ModelAndView deleteLogForm(@ModelAttribute("formModel") Author author , @PathVariable Long id, ModelAndView mav) {
        mav.setViewName("redinghtml/rddelete");
        mav.addObject("title", "読了記録削除");
        Optional<ReadingLog> data = readingLogRepository.findById(id);
        mav.addObject("formModel", data.get());
        return mav;
    }
    @PostMapping("/deletelog")
    @Transactional
    public ModelAndView deleteLog(@RequestParam Long id) {
        readingLogRepository.deleteById(id);
        return new ModelAndView("redirect:/readingMng");
    }
}
