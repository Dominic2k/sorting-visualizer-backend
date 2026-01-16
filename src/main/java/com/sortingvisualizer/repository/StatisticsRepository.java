package com.sortingvisualizer.repository;

import com.sortingvisualizer.model.User;
import com.sortingvisualizer.model.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<UserStatistics, Long> {
    List<UserStatistics> findByUser(User user);
    Optional<UserStatistics> findByUserAndAlgorithmName(User user, String algorithmName);
}
