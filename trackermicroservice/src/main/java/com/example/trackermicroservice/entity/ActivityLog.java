package com.example.trackermicroservice.entity;

import com.example.trackermicroservice.DTO.ActivityLogDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;
    private long petId;
    private String activity;
    private String duration;
    private String distance;
    private String date;
    public ActivityLog(long petid, ActivityLogDTO activityLogDTO){
        this.petId = petid;
        this.activity = activityLogDTO.getActivity();
        this.duration = activityLogDTO.getDuration();
        this.distance = activityLogDTO.getDistance();
        this.date = activityLogDTO.getDate();
    }
}
