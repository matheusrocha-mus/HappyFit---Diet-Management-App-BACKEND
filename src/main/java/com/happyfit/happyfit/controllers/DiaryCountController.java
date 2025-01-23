package com.happyfit.happyfit.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.happyfit.happyfit.services.DiaryService;

@RestController
@RequestMapping("/diary")
public class DiaryCountController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/count")
    public Long countDiary() {
        long countDiarys = diaryService.countDiary();
        return countDiarys;
    }

    @GetMapping("/countDate")
    public long countDistinctDates() {
        long countDate = diaryService.countDistinctDates();
        return countDate;
    }
    
}
