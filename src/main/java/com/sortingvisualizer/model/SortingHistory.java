package com.sortingvisualizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sorting_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
