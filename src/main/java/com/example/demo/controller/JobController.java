package com.example.demo.controller;

import com.example.demo.dto.JobResponseDto;
import com.example.demo.dto.JobStatusPostRequestDto;
import com.example.demo.dto.JobStatusUpdateRequestDto;
import com.example.demo.entity.Job;
import com.example.demo.service.JobService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<JobResponseDto> create(
        @Valid
        @RequestBody JobStatusPostRequestDto requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobService.saveJob(requestDto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
        @PathVariable Long id,
        @RequestBody JobStatusUpdateRequestDto request
    ) {
        jobService.updateStatus(id, request);
        return ResponseEntity.status(HttpStatus.OK).body("Job status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
