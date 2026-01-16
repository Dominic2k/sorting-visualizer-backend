package com.sortingvisualizer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sorting_history")
public class SortingHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String algorithmName;
    
    @Column(nullable = false)
    private Integer arraySize;
    
    @Column(nullable = false)
    private Integer comparisonCount;
    
    @Column(nullable = false)
    private Integer swapCount;
    
    @Column(nullable = false)
    private Long executionTimeMs;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    public SortingHistory() {}
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
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
        private final SortingHistory h = new SortingHistory();
        public Builder user(User user) { h.user = user; return this; }
        public Builder algorithmName(String algorithmName) { h.algorithmName = algorithmName; return this; }
        public Builder arraySize(Integer arraySize) { h.arraySize = arraySize; return this; }
        public Builder comparisonCount(Integer comparisonCount) { h.comparisonCount = comparisonCount; return this; }
        public Builder swapCount(Integer swapCount) { h.swapCount = swapCount; return this; }
        public Builder executionTimeMs(Long executionTimeMs) { h.executionTimeMs = executionTimeMs; return this; }
        public SortingHistory build() { return h; }
    }
}
