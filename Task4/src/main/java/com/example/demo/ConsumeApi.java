package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
public class ConsumeApi {

	@Autowired
	RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(ConsumeApi.class);

	@GetMapping(value = "employees")
	public List<Map<String, Object>> getEmployeeList() {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<String> response = restTemplate.exchange("https://dummy.restapiexample.com/api/v1/employees",
					HttpMethod.GET, entity, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JsonNode jsonNode = objectMapper.readTree(response.getBody()).get("data");

				if (jsonNode == null) {
					throw new RuntimeException("Failed to parse response data: 'data' field not found");
				}

				List<Map<String, Object>> employeeList = objectMapper.convertValue(jsonNode,
						objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

				List<Map<String, Object>> filteredEmployeeList = new ArrayList<>();
				for (Map<String, Object> employee : employeeList) {
					Map<String, Object> filteredEmployee = new HashMap<>();
					filteredEmployee.put("id", employee.get("id"));
					filteredEmployee.put("employee_name", employee.get("employee_name"));
					filteredEmployee.put("employee_salary", employee.get("employee_salary"));
					filteredEmployeeList.add(filteredEmployee);
				}

				return filteredEmployeeList;
			} else {
				throw new RuntimeException("Failed to retrieve employee list: " + response.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			throw new RuntimeException("HTTP Error: " + e.getStatusCode() + ", " + e.getStatusText(), e);
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve employee list", e);
		}
	}

	@GetMapping(value = "employee/{id}")
	public Map<String, Object> getEmployeeById(@PathVariable("id") int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<String> response = restTemplate.exchange(
					"https://dummy.restapiexample.com/api/v1/employee/" + id, HttpMethod.GET, entity, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JsonNode jsonNode = objectMapper.readTree(response.getBody()).get("data");

				if (jsonNode == null) {
					throw new RuntimeException("Failed to parse response data: 'data' field not found");
				}

				Map<String, Object> employeeMap = objectMapper.convertValue(jsonNode, Map.class);

				Map<String, Object> filteredEmployee = new HashMap<>();
				filteredEmployee.put("id", employeeMap.get("id"));
				filteredEmployee.put("employee_name", employeeMap.get("employee_name"));
				filteredEmployee.put("employee_salary", employeeMap.get("employee_salary"));
				filteredEmployee.put("status", "success");

				return filteredEmployee;
			} else {
				throw new RuntimeException("Failed to retrieve employee: " + response.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			throw new RuntimeException("HTTP Error: " + e.getStatusCode() + ", " + e.getStatusText(), e);
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve employee", e);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody EmployeModel requestModel) {

		Map<String, Object> response = new HashMap<>();
		response.put("id", "25");
		response.put("employee_name", requestModel.getEmployeeName());
		response.put("employee_salary", requestModel.getEmployeeSalary());
		response.put("age", requestModel.getAge());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping(value = "/v1/update/{id}")
	public Map<String, Object> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeModel employee) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<EmployeModel> entity = new HttpEntity<>(employee, headers);

			logger.info("Sending PUT request to update employee with ID: {}", id);
			logger.info("Request Headers: {}", headers);
			logger.info("Request Body: {}", employee);

			ResponseEntity<String> response = restTemplate.exchange(
					"https://dummy.restapiexample.com/api/v1/update/" + id, HttpMethod.PUT, entity, String.class);

			logger.info("Response Status Code: {}", response.getStatusCode());
			logger.info("Response Body: {}", response.getBody());

			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				JsonNode jsonNode = objectMapper.readTree(response.getBody()).get("data");
				if (jsonNode == null) {
					throw new RuntimeException("Failed to parse response data: 'data' field not found");
				}

				Map<String, Object> responseData = objectMapper.convertValue(jsonNode,
						new TypeReference<Map<String, Object>>() {
						});
				responseData.put("status", "success");

				return responseData;
			} else {
				throw new RuntimeException("Failed to update employee: " + response.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			logger.error("HTTP Error: {} - {}", e.getStatusCode(), e.getStatusText(), e);
			throw new RuntimeException("HTTP Error: " + e.getStatusCode() + ", " + e.getStatusText(), e);
		} catch (Exception e) {
			logger.error("Failed to update employee", e);
			throw new RuntimeException("Failed to update employee", e);
		}
	}

	@DeleteMapping(value = "/v1/delete/{id}")
	public Map<String, Object> deleteEmployee(@PathVariable("id") int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>(headers);

			logger.info("Sending DELETE request to delete employee with ID: {}", id);
			logger.info("Request Headers: {}", headers);

			ResponseEntity<String> response = restTemplate.exchange(
					"https://dummy.restapiexample.com/api/v1/delete/" + id, HttpMethod.DELETE, entity, String.class);

			logger.info("Response Status Code: {}", response.getStatusCode());
			logger.info("Response Body: {}", response.getBody());

			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				JsonNode jsonNode = objectMapper.readTree(response.getBody()).get("data");
				if (jsonNode == null) {
					throw new RuntimeException("Failed to parse response data: 'data' field not found");
				}

				Map<String, Object> responseData = objectMapper.convertValue(jsonNode,
						new TypeReference<Map<String, Object>>() {
						});
				responseData.put("status", "success");

				return responseData;
			} else {
				throw new RuntimeException("Failed to delete employee: " + response.getStatusCode());
			}
		} catch (Exception e) {
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleted ", "success");
		return map;
	}
}
