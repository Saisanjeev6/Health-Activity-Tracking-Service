package com.example.trackermicroservice.controller;


import com.example.trackermicroservice.DTO.NutritionLogDTO;
import com.example.trackermicroservice.DTO.NutritionRecommendationDTO;
import com.example.trackermicroservice.service.NutritionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health/pet")
public class DietNutritionTrackingController {
    private final NutritionLogService nutritionLogService;
    @Autowired
    public DietNutritionTrackingController(NutritionLogService nutritionLogService) {
        this.nutritionLogService = nutritionLogService;
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
    @GetMapping("/{petId}/nutrition/logs")
    public ResponseEntity<List<NutritionLogDTO>> getNutritionLogs(@PathVariable("petId") long petId) {
        try{
            return ResponseEntity.ok(nutritionLogService.getNutritionLogs(petId));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{petId}/nutrition/recommendations")
    public ResponseEntity<List<NutritionRecommendationDTO>> getNutritionRecommendations(@PathVariable("petId") long petId) {
        try {
            return ResponseEntity.ok(nutritionLogService.getNutritionRecommendations(petId));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{petId}/nutrition/log")
    public ResponseEntity<NutritionLogDTO> addNutritionLog(@PathVariable("petId") long petId, @RequestBody NutritionLogDTO nutritionLogDTO) {
        try {
            return ResponseEntity.ok(nutritionLogService.addNutritionLog(petId, nutritionLogDTO));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{petId}/nutrition/log/{logId}")
    public ResponseEntity<NutritionLogDTO> updateNutritionLog(@PathVariable("petId") long petId, @PathVariable("logId") long logId, @RequestBody NutritionLogDTO nutritionLogDTO) {
        try {
            return ResponseEntity.ok(nutritionLogService.updateNutritionLog(petId, logId, nutritionLogDTO));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{petId}/nutrition/log/{logId}")
    public ResponseEntity<String> deleteNutritionLog(@PathVariable("petId") long petId, @PathVariable("logId") long logId) {
        try {
            nutritionLogService.deleteNutritionLog(petId, logId);
            return ResponseEntity.ok("Log deleted successfully");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
