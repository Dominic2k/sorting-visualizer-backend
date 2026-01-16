package com.sortingvisualizer.dto;

import java.time.LocalDateTime;

public class HistoryResponse {
    private Long id;
    private String algorithmName;
    private Integer arraySize;
    private Integer comparisonCount;
    private Integer swapCount;
    private Long executionTimeMs;
    private LocalDateTime createdAt;

    public HistoryResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final HistoryResponse r = new HistoryResponse();
        public Builder id(Long id) { r.id = id; return this; }
        public Builder algorithmName(String algorithmName) { r.algorithmName = algorithmName; return this; }
        public Builder arraySize(Integer arraySize) { r.arraySize = arraySize; return this; }
        public Builder comparisonCount(Integer comparisonCount) { r.comparisonCount = comparisonCount; return this; }
        public Builder swapCount(Integer swapCount) { r.swapCount = swapCount; return this; }
        public Builder executionTimeMs(Long executionTimeMs) { r.executionTimeMs = executionTimeMs; return this; }
        public Builder createdAt(LocalDateTime createdAt) { r.createdAt = createdAt; return this; }
        public HistoryResponse build() { return r; }
    }
}
