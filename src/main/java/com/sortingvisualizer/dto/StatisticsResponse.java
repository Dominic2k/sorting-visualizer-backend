package com.sortingvisualizer.dto;

import java.time.LocalDateTime;

public class StatisticsResponse {
    private String algorithmName;
    private Integer totalRuns;
    private Long avgComparisons;
    private Long avgSwaps;
    private LocalDateTime lastUpdated;

    public StatisticsResponse() {}

    public String getAlgorithmName() { return algorithmName; }
    public void setAlgorithmName(String algorithmName) { this.algorithmName = algorithmName; }

    public Integer getTotalRuns() { return totalRuns; }
    public void setTotalRuns(Integer totalRuns) { this.totalRuns = totalRuns; }

    public Long getAvgComparisons() { return avgComparisons; }
    public void setAvgComparisons(Long avgComparisons) { this.avgComparisons = avgComparisons; }

    public Long getAvgSwaps() { return avgSwaps; }
    public void setAvgSwaps(Long avgSwaps) { this.avgSwaps = avgSwaps; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final StatisticsResponse r = new StatisticsResponse();
        public Builder algorithmName(String algorithmName) { r.algorithmName = algorithmName; return this; }
        public Builder totalRuns(Integer totalRuns) { r.totalRuns = totalRuns; return this; }
        public Builder avgComparisons(Long avgComparisons) { r.avgComparisons = avgComparisons; return this; }
        public Builder avgSwaps(Long avgSwaps) { r.avgSwaps = avgSwaps; return this; }
        public Builder lastUpdated(LocalDateTime lastUpdated) { r.lastUpdated = lastUpdated; return this; }
        public StatisticsResponse build() { return r; }
    }
}
