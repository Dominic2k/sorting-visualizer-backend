package com.sortingvisualizer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HistoryRequest {
    @NotBlank(message = "Algorithm name is required")
    private String algorithmName;
    
    @NotNull(message = "Array size is required")
    @Min(value = 1, message = "Array size must be at least 1")
    private Integer arraySize;
    
    @NotNull(message = "Comparison count is required")
    @Min(value = 0, message = "Comparison count cannot be negative")
    private Integer comparisonCount;
    
    @NotNull(message = "Swap count is required")
    @Min(value = 0, message = "Swap count cannot be negative")
    private Integer swapCount;
    
    @NotNull(message = "Execution time is required")
    @Min(value = 0, message = "Execution time cannot be negative")
    private Long executionTimeMs;

    public HistoryRequest() {}

    public String getAlgorithmName() { return algorithmName; }
    public void setAlgorithmName(String algorithmName) { this.algorithmName = algorithmName; }

    public Integer getArraySize() { return arraySize; }
    public void setArraySize(Integer arraySize) { this.arraySize = arraySize; }

    public Integer getComparisonCount() { return comparisonCount; }
    public void setComparisonCount(Integer comparisonCount) { this.comparisonCount = comparisonCount; }

    public Integer getSwapCount() { return swapCount; }
    public void setSwapCount(Integer swapCount) { this.swapCount = swapCount; }

    public Long getExecutionTimeMs() { return executionTimeMs; }
    public void setExecutionTimeMs(Long executionTimeMs) { this.executionTimeMs = executionTimeMs; }
}
