package com.example.trackermicroservice.DTO;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDashboardDTO {
    private long userId;
    private List<HealthInsightsDTO> healthInsightsList;
}
