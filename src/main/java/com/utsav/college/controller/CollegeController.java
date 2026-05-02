package com.utsav.college.controller;

import com.utsav.college.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/college")
@CrossOrigin("*")
public class CollegeController {

    @Autowired
    private CollegeService service;

    @GetMapping("/recommend")
    public List<Map<String, Object>> recommend(
            @RequestParam int rank,
            @RequestParam int budget
    ) {
        return service.recommend(rank, budget);
    }
}