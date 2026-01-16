package com.sortingvisualizer.repository;

import com.sortingvisualizer.model.SortingHistory;
import com.sortingvisualizer.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<SortingHistory, Long> {
    Page<SortingHistory> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
    List<SortingHistory> findByUserAndAlgorithmName(User user, String algorithmName);
}
