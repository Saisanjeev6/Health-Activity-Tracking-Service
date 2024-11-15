package com.example.trackermicroservice.DTO;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class HealthInsightsDTO {
    private long petId;
    private String insights;
    private int totalActivityDuration;
    private int totalActivityDistance;
    private int totalCaloriesConsumed;
    private String nutritionRecommendation;
    private String activityRecommendation;

}
