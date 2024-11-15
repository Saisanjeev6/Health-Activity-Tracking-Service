package com.example.trackermicroservice.repository;



import com.example.trackermicroservice.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {
    @Query("SELECT a FROM ActivityLog a WHERE a.petId = :petId")
    List<ActivityLog> getActivityLogs(Long petId);
}
