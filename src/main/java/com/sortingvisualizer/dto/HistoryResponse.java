package com.sortingvisualizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class HistoryResponse {
    private Long id;
    private String algorithmName;
    private Integer arraySize;
    private Integer comparisonCount;
    private Integer swapCount;
    private Long executionTimeMs;
    private LocalDateTime createdAt;
}
