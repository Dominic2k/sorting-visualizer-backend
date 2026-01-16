package com.sortingvisualizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class StatisticsResponse {
    private String algorithmName;
    private Integer totalRuns;
    private Long avgComparisons;
    private Long avgSwaps;
    private LocalDateTime lastUpdated;
}
