package com.utsav.college.controller;

import com.utsav.college.model.*;
import com.utsav.college.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepo;

    @Autowired
    private CollegeRepository collegeRepo;

    // ✅ SAVE FAVORITE
    @PostMapping("/save")
    public String saveFavorite(@RequestBody Favorite fav) {

        favoriteRepo.save(fav);
        return "Saved";
    }

    // ✅ GET FAVORITES FOR USER
    @GetMapping("/get")
    public List<College> getFavorites(@RequestParam String email) {

        List<Favorite> favs = favoriteRepo.findByEmail(email);

        List<College> result = new ArrayList<>();

        for (Favorite f : favs) {
            collegeRepo.findById(f.getCollegeId()).ifPresent(result::add);
        }

        return result;
    }
}