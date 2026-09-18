package com.example.demo.service;

import java.time.Instant;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Job;
import com.example.demo.repository.JobRepository;

@Service
@RequiredArgsConstructor
public class JobRetryService {

    private static final int MAX_RETRY = 3;

    private final JobRepository jobRepository;
    private final TaskScheduler taskScheduler;

    public void retryJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Job not found: " + jobId
                        )
                );

        // 이미 끝난 Job이라면 다시 실행하지 않음
        if ("SUCCESS".equals(job.getStatus())
                || "FAILED".equals(job.getStatus())) {
            return;
        }

        try {

            executeJob(job);

            job.setStatus("SUCCESS");
            jobRepository.save(job);

        } catch (Exception e) {

            recordFailure(job);
        }
    }


    private void executeJob(Job job) {

        /*
         * 여기에 실제 Job 작업이 들어감.
         *
         * 예:
         * 외부 API 호출
         * 파일 처리
         * 이메일 전송
         * 특정 데이터 처리
         *
         * 지금은 Retry 테스트를 위해
         * 일부러 예외를 발생시켜도 됨.
         */

        throw new RuntimeException("작업 실패 테스트");
    }


    private void recordFailure(Job job) {

        int count = job.getRetryCount() + 1;

        job.setRetryCount(count);

        if (count >= MAX_RETRY) {

            job.setStatus("FAILED");
            jobRepository.save(job);

            return;
        }

        jobRepository.save(job);

        scheduleRetry(
                job.getId(),
                count
        );
    }


    private void scheduleRetry(
            Long jobId,
            int retryCount
    ) {

        long delay =
                (long) Math.pow(
                        2,
                        retryCount - 1
                );

        Instant executeAt =
                Instant.now()
                        .plusSeconds(delay);

        taskScheduler.schedule(
                () -> retryJob(jobId),
                executeAt
        );
    }
}