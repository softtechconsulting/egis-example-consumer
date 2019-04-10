package com.softtech.cloud.egisexampleconsumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureStubRunner(repositoryRoot = "http://nexus-ci.apps.softtechdemos.com/repository/maven-releases", classifier = "stubs", stubsMode = StubsMode.REMOTE, ids = "com.softtech:egis-visa-applicant-stubs:test-upload-rel-6", minPort = 12000, maxPort = 12000)
public class EgisExampleConsumerApplicationTests {

	@Autowired
	private RestOperations restOperations;

	@Test
	public void testingVisaSummaryGetByAlienNumber() throws Exception {
		assertNotNull(restOperations);
		final String uriString = UriComponentsBuilder.fromUriString("http://localhost:12000/visa/v1/anumber")
				.queryParam("alienNumber", "A123456789").build().toUriString();

		@SuppressWarnings("unchecked")
		Map<String, Object> returnedVal = restOperations.getForEntity(uriString, Map.class).getBody();
		assertNotNull(returnedVal);
		final String jsonForm = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(returnedVal);
		System.out.println(jsonForm);
	}
	
	@Test
	public void testingVisaSummaryGetByBirthdateBioData() throws Exception {
		assertNotNull(restOperations);
		final String uriString = UriComponentsBuilder.fromUriString("http://localhost:12000/visa/v1/bioData")
				.queryParam("birthDate", "1985-01-01").build().toUriString();

		@SuppressWarnings("unchecked")
		ResponseEntity<Map> response = restOperations.getForEntity(uriString, Map.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		final String jsonForm = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody());
		System.out.println(jsonForm);
	}
	
	@Test
	public void testingVisaSummaryGetByBirthdateAndNameBioData() throws Exception {
		assertNotNull(restOperations);
		final String uriString = UriComponentsBuilder.fromUriString("http://localhost:12000/visa/v1/bioData")
				.queryParam("birthDate", "1985-01-01")
				.queryParam("lastName", "Tracer").build().toUriString();

		@SuppressWarnings("unchecked")
		ResponseEntity<Map> response = restOperations.getForEntity(uriString, Map.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		final String jsonForm = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody());
		System.out.println(jsonForm);
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testingVisaSummaryGetByBirthdateAndNameBioData_noDataFound() throws Exception {
		assertNotNull(restOperations);
		final String uriString = UriComponentsBuilder.fromUriString("http://localhost:12000/visa/v1/bioData")
				.queryParam("birthDate", "1985-01-01")
				.queryParam("lastName", "Tracker").build().toUriString();

		@SuppressWarnings("unchecked")
		ResponseEntity<Map> response = restOperations.getForEntity(uriString, Map.class);
	}
}
