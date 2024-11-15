package com.example.trackermicroservice.DTO;


import com.example.trackermicroservice.entity.NutrionLog;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class NutritionLogDTO {
    private long petId;
    private long logId;
    private Date date;
    private String food;
    private double calories;
    private double protein;
    private double fat;
    private double fiber;
    private double carbohydrates;
    private double weight;
    public NutritionLogDTO(NutrionLog nutrionLog){
        this.petId = nutrionLog.getPetId();
        this.logId = nutrionLog.getLogId();
        this.date = nutrionLog.getDate();
        this.food = nutrionLog.getFood();
        this.calories = nutrionLog.getCalories();
        this.protein = nutrionLog.getProtein();
        this.fat = nutrionLog.getFat();
        this.fiber = nutrionLog.getFiber();
        this.carbohydrates = nutrionLog.getCarbohydrates();
    }
}

