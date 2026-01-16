package com.sortingvisualizer.controller;

import com.sortingvisualizer.dto.HistoryRequest;
import com.sortingvisualizer.dto.HistoryResponse;
import com.sortingvisualizer.model.User;
import com.sortingvisualizer.service.HistoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    
    private final HistoryService historyService;
    
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }
    
    @PostMapping
    public ResponseEntity<HistoryResponse> saveHistory(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody HistoryRequest request) {
        HistoryResponse response = historyService.saveHistory(user, request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<HistoryResponse>> getHistory(
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<HistoryResponse> history = historyService.getHistory(user, page, size);
        return ResponseEntity.ok(history);
    }
}
