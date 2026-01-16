package com.sortingvisualizer.service;

import com.sortingvisualizer.dto.HistoryRequest;
import com.sortingvisualizer.dto.HistoryResponse;
import com.sortingvisualizer.model.SortingHistory;
import com.sortingvisualizer.model.User;
import com.sortingvisualizer.model.UserStatistics;
import com.sortingvisualizer.repository.HistoryRepository;
import com.sortingvisualizer.repository.StatisticsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoryService {
    
    private final HistoryRepository historyRepository;
    private final StatisticsRepository statisticsRepository;
    
    public HistoryService(HistoryRepository historyRepository, 
                          StatisticsRepository statisticsRepository) {
        this.historyRepository = historyRepository;
        this.statisticsRepository = statisticsRepository;
    }
    
    @Transactional
    public HistoryResponse saveHistory(User user, HistoryRequest request) {
        SortingHistory history = SortingHistory.builder()
                .user(user)
                .algorithmName(request.getAlgorithmName())
                .arraySize(request.getArraySize())
                .comparisonCount(request.getComparisonCount())
                .swapCount(request.getSwapCount())
                .executionTimeMs(request.getExecutionTimeMs())
                .build();
        
        historyRepository.save(history);
        
        // Update statistics
        updateStatistics(user, request);
        
        return toResponse(history);
    }
    
    public Page<HistoryResponse> getHistory(User user, int page, int size) {
        Page<SortingHistory> historyPage = historyRepository
                .findByUserOrderByCreatedAtDesc(user, PageRequest.of(page, size));
        
        return historyPage.map(this::toResponse);
    }
    
    private void updateStatistics(User user, HistoryRequest request) {
        UserStatistics stats = statisticsRepository
                .findByUserAndAlgorithmName(user, request.getAlgorithmName())
                .orElse(UserStatistics.builder()
                        .user(user)
                        .algorithmName(request.getAlgorithmName())
                        .totalRuns(0)
                        .avgComparisons(0L)
                        .avgSwaps(0L)
                        .build());
        
        int newTotalRuns = stats.getTotalRuns() + 1;
        long newAvgComparisons = ((stats.getAvgComparisons() * stats.getTotalRuns()) 
                + request.getComparisonCount()) / newTotalRuns;
        long newAvgSwaps = ((stats.getAvgSwaps() * stats.getTotalRuns()) 
                + request.getSwapCount()) / newTotalRuns;
        
        stats.setTotalRuns(newTotalRuns);
        stats.setAvgComparisons(newAvgComparisons);
        stats.setAvgSwaps(newAvgSwaps);
        
        statisticsRepository.save(stats);
    }
    
    private HistoryResponse toResponse(SortingHistory history) {
        return HistoryResponse.builder()
                .id(history.getId())
                .algorithmName(history.getAlgorithmName())
                .arraySize(history.getArraySize())
                .comparisonCount(history.getComparisonCount())
                .swapCount(history.getSwapCount())
                .executionTimeMs(history.getExecutionTimeMs())
                .createdAt(history.getCreatedAt())
                .build();
    }
}
