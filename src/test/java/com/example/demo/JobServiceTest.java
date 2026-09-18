package com.example.demo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.repository.JobRepository;
import com.example.demo.service.JobService;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import com.example.demo.entity.Job;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    public void testFindById() {
        // 테스트 코드 작성
        Job job = new Job("Test Job", "Pending",0);
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        Job result = jobService.findById(1L);
        assertEquals(job, result);

        
    }
    @Test
    public void testNotFound() {
        // 테스트 코드 작성
        when(jobRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> jobService.findById(1L));
    }
}
