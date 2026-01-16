package com.sortingvisualizer.service;

import com.sortingvisualizer.dto.StatisticsResponse;
import com.sortingvisualizer.model.User;
import com.sortingvisualizer.model.UserStatistics;
import com.sortingvisualizer.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    
    private final StatisticsRepository statisticsRepository;
    
    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }
    
    public List<StatisticsResponse> getAllStatistics(User user) {
        return statisticsRepository.findByUser(user).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public StatisticsResponse getStatisticsByAlgorithm(User user, String algorithmName) {
        UserStatistics stats = statisticsRepository
                .findByUserAndAlgorithmName(user, algorithmName)
                .orElseThrow(() -> new RuntimeException("No statistics found for algorithm: " + algorithmName));
        
        return toResponse(stats);
    }
    
    private StatisticsResponse toResponse(UserStatistics stats) {
        return StatisticsResponse.builder()
                .algorithmName(stats.getAlgorithmName())
                .totalRuns(stats.getTotalRuns())
                .avgComparisons(stats.getAvgComparisons())
                .avgSwaps(stats.getAvgSwaps())
                .lastUpdated(stats.getLastUpdated())
                .build();
    }
}
