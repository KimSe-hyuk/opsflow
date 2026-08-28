package com.example.demo.controller;

import com.example.demo.dto.JobStatusUpdateRequestDto;
import com.example.demo.entity.Job;
import com.example.demo.service.JobService;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.JobStatusPostRequestDto;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return ResponseEntity.ok(job);
    }

    @PostMapping
    public ResponseEntity<@Nullable Object> create(@RequestBody JobStatusPostRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.saveJob(request));
    }

 
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
        @PathVariable Long id,
        @RequestBody JobStatusUpdateRequestDto request
    ) {
        jobService.updateStatus(request);
        return ResponseEntity.ok("Job status updated successfully");
    }

 
 
}
