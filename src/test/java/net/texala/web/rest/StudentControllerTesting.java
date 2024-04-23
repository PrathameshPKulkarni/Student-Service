package net.texala.web.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.texala.enums.Status;
import net.texala.model.Student;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTesting {

	private Student student;
	private Student student1;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {

		student = new Student();
		student.setId(120);
		student.setName("Prathamesh");
		student.setAddress("Alandi");
		student.setSalary(20000);
		student.setStatus(Status.Activated);

		student1 = new Student();
		student1.setId(121);
		student1.setName("Sourabh");
		student1.setAddress("pune");
		student1.setSalary(22000);
		student1.setStatus(Status.DEACTIVATED);
	}

	/**
	 * test_FetchAll
	 * 
	 * @throws Exception
	 */
	@DisplayName("It should show all records")
	@Test
	void test_FetchAll_getMapping_ShouldShowAllRecords() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v0/students").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	/**
	 * test_FetchById
	 * 
	 * @throws Exception
	 */
	@Test
	void test_FetchById_getMapping_ShouldShowOneRecords() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v0/students/121").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * test_AddStudent
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@DisplayName("It should add new record of student")
	@Test
	void test_AddStudent_postMapping_shouldCreateNewStudentRecord() throws JsonProcessingException, Exception {
		objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(student);

		mockMvc.perform(MockMvcRequestBuilders.post("/v0/students").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isCreated());
	}

	/**
	 * test_Update
	 * 
	 * @throws Exception
	 */
	@DisplayName("It Should update one record")
	@Test
	void test_Update_PutMapping_ShouldUpdateRecords() throws Exception {
		objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(student1);

		mockMvc.perform(MockMvcRequestBuilders.put("/v0/students/120").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk());
	}

	/**
	 * test_UpdateStatus
	 * 
	 * @throws Exception
	 */
	@DisplayName("It should update Status of student")
	@Test
	void test_UpdateStatus_PutMapping_ShouldUpdateRecords() throws Exception {

		Student newStudent = new Student();
		newStudent.setStatus(Status.Activated);
		objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(newStudent);

		mockMvc.perform(MockMvcRequestBuilders.patch("/v0/students/120?status=Activated")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * test_Delete
	 * 
	 * @throws Exception
	 */
	@DisplayName("it should delete record from record")
	@Test
	void test_Delete_DeleteMapping_ShouldUpdateRecords() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/v0/students/120").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}
