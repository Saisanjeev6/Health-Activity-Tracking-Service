package com.example.trackermicroservice.entity;


import com.example.trackermicroservice.DTO.NutritionLogDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NutrionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;
    private long petId;
    private Date date;
    private String food;
    private double calories;
    private double protein;
    private double fat;
    private double fiber;
    private double carbohydrates;
    private double weight;
    public NutrionLog(long petId, NutritionLogDTO nutritionLogDTO){
        this.petId = petId;
        this.date = nutritionLogDTO.getDate();
        this.food = nutritionLogDTO.getFood();
        this.calories = nutritionLogDTO.getCalories();
        this.protein = nutritionLogDTO.getProtein();
        this.fat = nutritionLogDTO.getFat();
        this.fiber = nutritionLogDTO.getFiber();
        this.carbohydrates = nutritionLogDTO.getCarbohydrates();
        this.weight = nutritionLogDTO.getWeight();
    }
}
