package com.sortingvisualizer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_statistics", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "algorithm_name"}))
public class UserStatistics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "algorithm_name", nullable = false)
    private String algorithmName;
    
    @Column(nullable = false)
    private Integer totalRuns = 0;
    
    @Column(nullable = false)
    private Long avgComparisons = 0L;
    
    @Column(nullable = false)
    private Long avgSwaps = 0L;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdated;
    
    public UserStatistics() {}
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
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
        private final UserStatistics s = new UserStatistics();
        public Builder user(User user) { s.user = user; return this; }
        public Builder algorithmName(String algorithmName) { s.algorithmName = algorithmName; return this; }
        public Builder totalRuns(Integer totalRuns) { s.totalRuns = totalRuns; return this; }
        public Builder avgComparisons(Long avgComparisons) { s.avgComparisons = avgComparisons; return this; }
        public Builder avgSwaps(Long avgSwaps) { s.avgSwaps = avgSwaps; return this; }
        public UserStatistics build() { return s; }
    }
}
