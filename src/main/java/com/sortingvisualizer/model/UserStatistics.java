package com.sortingvisualizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_statistics", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "algorithm_name"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Builder.Default
    private Integer totalRuns = 0;
    
    @Column(nullable = false)
    @Builder.Default
    private Long avgComparisons = 0L;
    
    @Column(nullable = false)
    @Builder.Default
    private Long avgSwaps = 0L;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdated;
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
