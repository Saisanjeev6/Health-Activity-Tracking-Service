package com.example.trackermicroservice.controller;


import com.example.trackermicroservice.DTO.NutritionLogDTO;
import com.example.trackermicroservice.DTO.NutritionRecommendationDTO;
import com.example.trackermicroservice.service.NutritionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
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
    public ResponseEntity<?> getNutritionLogs(@PathVariable("petId") String petId) {
        try{
            return ResponseEntity.ok(nutritionLogService.getNutritionLogs(petId));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no logs with the given petId");
        }
    }

    @GetMapping("/{petId}/nutrition/recommendations")
    public ResponseEntity<?> getNutritionRecommendations(@PathVariable("petId") String petId) {
        try {
            return ResponseEntity.ok(nutritionLogService.getNutritionRecommendations(petId));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no logs with the given petId, so we can't give any recommendations");
        }
    }

    @PostMapping("/{petId}/nutrition/log")
    public ResponseEntity<?> addNutritionLog(@PathVariable("petId") String petId, @RequestBody NutritionLogDTO nutritionLogDTO) {
        try {
            return ResponseEntity.ok(nutritionLogService.addNutritionLog(petId, nutritionLogDTO));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The request is invalid");
        }
    }

    @PutMapping("/{petId}/nutrition/log/{logId}")
    public ResponseEntity<?> updateNutritionLog(@PathVariable("petId") String petId, @PathVariable("logId") UUID logId, @RequestBody NutritionLogDTO nutritionLogDTO) {
        try {
            return ResponseEntity.ok(nutritionLogService.updateNutritionLog(petId, logId, nutritionLogDTO));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no logs with the given petId and logId so we can't update the log");
        }
    }

    @DeleteMapping("/{petId}/nutrition/log/{logId}")
    public ResponseEntity<?> deleteNutritionLog(@PathVariable("petId") String petId, @PathVariable("logId") UUID logId) {
        try {
            nutritionLogService.deleteNutritionLog(petId, logId);
            return ResponseEntity.ok("Log deleted successfully");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no logs with the given petId and logId so we can't delete the log");
        }

    }
}
