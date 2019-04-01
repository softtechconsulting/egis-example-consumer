package com.softtech.cloud.egisexampleconsumer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.ResultActions.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class EgisExampleConsumerApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Rule
	public StubRunnerRule rule = new StubRunnerRule()
			.downloadStub("com.softtech","egis-visa-applicant-stubs", "0.0.1-BUILD-SNAPSHOT", "")
			.repoRoot("C:\\Users\\david\\.m2\\repository")
			.stubsMode(StubRunnerProperties.StubsMode.LOCAL)
			.maxPort(8083)
			.minPort(8083);
	
	@Test
	public void testingVisaSummary() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8083/visa/anumber?alienNumber=A123456789")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("THERE YOU GO"));
	}

}
