package com.sortingvisualizer.controller;

import com.sortingvisualizer.dto.StatisticsResponse;
import com.sortingvisualizer.model.User;
import com.sortingvisualizer.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    
    private final StatisticsService statisticsService;
    
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    
    @GetMapping
    public ResponseEntity<List<StatisticsResponse>> getAllStatistics(
            @AuthenticationPrincipal User user) {
        List<StatisticsResponse> statistics = statisticsService.getAllStatistics(user);
        return ResponseEntity.ok(statistics);
    }
    
    @GetMapping("/{algorithmName}")
    public ResponseEntity<StatisticsResponse> getStatisticsByAlgorithm(
            @AuthenticationPrincipal User user,
            @PathVariable String algorithmName) {
        StatisticsResponse statistics = statisticsService.getStatisticsByAlgorithm(user, algorithmName);
        return ResponseEntity.ok(statistics);
    }
}
