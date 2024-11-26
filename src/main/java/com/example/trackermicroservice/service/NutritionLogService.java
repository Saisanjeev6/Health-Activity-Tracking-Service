package com.example.trackermicroservice.service;


import com.example.trackermicroservice.DTO.NutritionLogDTO;
import com.example.trackermicroservice.DTO.NutritionRecommendationDTO;
import com.example.trackermicroservice.entity.NutrionLog;
import com.example.trackermicroservice.repository.NutritionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NutritionLogService {
    private final NutritionLogRepository nutritionLogRepository;
    @Autowired
    public NutritionLogService(NutritionLogRepository nutritionLogRepository) {
        this.nutritionLogRepository = nutritionLogRepository;
    }
    public List<NutritionLogDTO> getNutritionLogs(String petId) {
        if(nutritionLogRepository.getNutritionLogs(petId).size() != 0) {
            List<NutrionLog> nutrionLogs = nutritionLogRepository.getNutritionLogs(petId);
            List<NutritionLogDTO> nutritionLogDTOS = new ArrayList<>();
            for(NutrionLog nutrionLog : nutrionLogs) {
                NutritionLogDTO nutritionLogDTO = new NutritionLogDTO(nutrionLog);
                nutritionLogDTOS.add(nutritionLogDTO);
            }
            return nutritionLogDTOS;
        }
        else
            throw new IllegalArgumentException("Pet not found");
    }

    public NutritionLogDTO addNutritionLog(String  petId, NutritionLogDTO nutritionLogDTO) {
        NutrionLog nutrionLog = new NutrionLog(petId, nutritionLogDTO);

        if (nutritionLogRepository.save(nutrionLog) != null) {
            nutritionLogDTO.setLogId(nutrionLog.getLogId());
            nutritionLogDTO.setPetId(petId);
            return nutritionLogDTO;
        } else
            throw new IllegalArgumentException("Unable to add nutrition log");
    }

    public NutritionLogDTO updateNutritionLog(String petId, UUID logId, NutritionLogDTO nutritionLogDTO) {
        NutrionLog nutrionLog = new NutrionLog(petId, nutritionLogDTO);
        nutrionLog.setLogId(logId);
        if(nutritionLogRepository.getReferenceByLogAndPetId(logId, petId) != 0 && nutritionLogRepository.save(nutrionLog) != null) {
            nutritionLogDTO.setPetId(petId);
            nutritionLogDTO.setLogId(logId);
            return nutritionLogDTO;
        }
        else
            throw new IllegalArgumentException("Pet not found, if you want to add a new log use POST method");
    }

    public void deleteNutritionLog(String petId, UUID logId) {
        if(nutritionLogRepository.getReferenceById(logId) != null) {
            nutritionLogRepository.deleteById(logId);
        }
        else
            throw new IllegalArgumentException("Pet not found");
    }

    public List<NutritionRecommendationDTO> getNutritionRecommendations(String petId) {
        List<NutrionLog> nutrionLogs = nutritionLogRepository.getNutritionLogs(petId);
        if(nutrionLogs.size() == 0) {
            throw new IllegalArgumentException("Pet not found");
        }
        List<NutritionRecommendationDTO> recommendations = new ArrayList<>();

        for (NutrionLog log : nutrionLogs) {
            NutritionRecommendationDTO recommendation = new NutritionRecommendationDTO();
            recommendation.setPetId(log.getPetId());
            if (log.getProtein() < 50) {
                recommendation.setRecommendation("Increase protein intake with lean meats or legumes.");
            } else if (log.getFat() > 30) {
                recommendation.setRecommendation("Reduce fat intake by avoiding fried foods.");
            } else if (log.getFiber() < 25) {
                recommendation.setRecommendation("Increase fiber intake with fruits and vegetables.");
            } else if (log.getCarbohydrates() > 60) {
                recommendation.setRecommendation("Reduce carbohydrate intake by cutting down on sugars and starches.");
            } else {
                recommendation.setRecommendation("Maintain current diet but monitor portion sizes.");
            }
            recommendations.add(recommendation);
        }
        return recommendations;
    }
}