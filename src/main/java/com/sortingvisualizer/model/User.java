package com.sortingvisualizer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    // Password có thể null cho OAuth users
    @Column(nullable = true)
    private String passwordHash;
    
    @Column(nullable = false)
    private String displayName;
    
    // Avatar URL từ Google
    @Column
    private String avatarUrl;
    
    // Provider (LOCAL, GOOGLE)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.LOCAL;
    
    // Provider ID (sub từ Google)
    @Column
    private String providerId;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SortingHistory> sortingHistory = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserStatistics> statistics = new ArrayList<>();
    
    public User() {}
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    
    public AuthProvider getProvider() { return provider; }
    public void setProvider(AuthProvider provider) { this.provider = provider; }
    
    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<SortingHistory> getSortingHistory() { return sortingHistory; }
    public void setSortingHistory(List<SortingHistory> sortingHistory) { this.sortingHistory = sortingHistory; }
    
    public List<UserStatistics> getStatistics() { return statistics; }
    public void setStatistics(List<UserStatistics> statistics) { this.statistics = statistics; }
    
    // Builder
    public static Builder builder() { return new Builder(); }
    
    public static class Builder {
        private final User user = new User();
        public Builder email(String email) { user.email = email; return this; }
        public Builder passwordHash(String passwordHash) { user.passwordHash = passwordHash; return this; }
        public Builder displayName(String displayName) { user.displayName = displayName; return this; }
        public Builder avatarUrl(String avatarUrl) { user.avatarUrl = avatarUrl; return this; }
        public Builder provider(AuthProvider provider) { user.provider = provider; return this; }
        public Builder providerId(String providerId) { user.providerId = providerId; return this; }
        public User build() { return user; }
    }
}

