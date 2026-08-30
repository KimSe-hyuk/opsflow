// package com.example.demo;

// import com.example.demo.entity.Job;
// import com.example.demo.repository.JobRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest
// @AutoConfigureMockMvc
// class DemoApplicationTests {

// 	@Autowired
// 	private MockMvc mockMvc;

// 	@Autowired
// 	private JobRepository jobRepository;

// 	@BeforeEach
// 	void setUp() {
// 		jobRepository.deleteAll();
// 	}

// 	@Test
// 	void contextLoads() {
// 	}

// 	@Test
// 	void apiHealthReturnsUp() throws Exception {
// 		mockMvc.perform(get("/api/health"))
// 			.andExpect(status().isOk())
// 			.andExpect(content().json("{\"status\":\"UP\"}"));
// 	}

// 	@Test
// 	void deleteJobReturnsNoContentAndGetAfterDeleteReturnsNotFound() throws Exception {
// 		Job job = jobRepository.save(new Job("test-job", "PENDING"));

// 		mockMvc.perform(delete("/api/jobs/{id}", job.getId()))
// 			.andExpect(status().isNoContent());

// 		mockMvc.perform(get("/api/jobs/{id}", job.getId()))
// 			.andExpect(status().isNotFound());
// 	}

// 	@Test
// 	void deleteNonExistentJobReturnsNotFound() throws Exception {
// 		mockMvc.perform(delete("/api/jobs/{id}", 999999L))
// 			.andExpect(status().isNotFound());
// 	}

// }
