package com.utsav.college.service;

import com.utsav.college.model.College;
import com.utsav.college.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository repo;

    public List<Map<String, Object>> recommend(int rank, int budget) {

        return repo.findAll().stream()
                .filter(c -> rank >= c.getMinRank() && rank <= c.getMaxRank())
                .filter(c -> c.getFees() <= budget)
                .map(c -> {
                    int rankScore = 100 - Math.abs(rank - c.getMinRank()) / 10;
                    int feeScore = 100 - (c.getFees() / 2000);

                    int totalScore = Math.max(0, (rankScore + feeScore) / 2);

                    Map<String, Object> result = new HashMap<>();
                    result.put("college", c);
                    result.put("score", totalScore);

                    return result;
                })
                .sorted((a, b) -> (int)b.get("score") - (int)a.get("score"))
                .toList();
    }
}