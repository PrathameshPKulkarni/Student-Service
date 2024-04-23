package net.texala.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.texala.enums.Status;
import net.texala.model.Student;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudentRepositoryJunitTesting {

	@Autowired
	private StudentRepository studentRepository;

	private Student student;

	private Student student1;

	/**
	 * 
	 */
	@BeforeEach
	void setup() {
		student = new Student();

		student.setId(120);
		student.setName("Prathamesh");
		student.setAddress("Alandi");
		student.setSalary(200000);
		student.setStatus(Status.Activated);

		student1 = new Student();

		student1.setId(121);
		student1.setName("Sourabh");
		student1.setAddress("Buldhana");
		student1.setSalary(250000);
		student1.setStatus(Status.Activated);
	}

	/**
	 * test_add
	 */
	@Test
	@DisplayName("It will Save the Student record ")
	void test_add_shouldAddOneRecord() {
		Student saveStudent = studentRepository.save(student);

		assertNotNull(saveStudent);
		assertThat(saveStudent.getId()).isNotEqualTo(null);

	}

	/**
	 * test_getFetchAl
	 */
	@Test
	@DisplayName("It Should return  the Students ")
	void test_getFetchAll_showAllRecords() {

		studentRepository.save(student);
		studentRepository.save(student1);

		List<Student> list = studentRepository.findAll();
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(16, list.size());

	}

	/**
	 * test_fetchStudentById
	 */
	@Test
	@DisplayName("It should show the student by id ")
	void test_fetchStudentById_showOneRecord() {

		studentRepository.save(student);
		Student studentById = studentRepository.findById(student.getId()).get();

		assertNotNull(studentById);
		assertEquals("Alandi", student.getAddress());
		assertEquals(120, student.getId());
	}

	/**
	 * test_updateStudent
	 */
	@DisplayName("It will update the existing entry")
	@Test
	void test_updateStudent_updateParticularRecord_updateOneRecordById() {

		studentRepository.save(student);
		Student existingStudent = studentRepository.findById(student.getId()).get();

		existingStudent.setAddress("Pune");
		existingStudent.setSalary(250000);
		Student newStudent = studentRepository.save(existingStudent);

		assertEquals("Pune", newStudent.getAddress());
		assertEquals(250000, newStudent.getSalary());

	}

	/**
	 * test_updateStatus
	 */
	@DisplayName("it should update the status")
	@Test
	void test_updateStatus_updateParticularRecord_updateOneRecordById() {
		studentRepository.save(student);
		Student existingStudent = studentRepository.findById(student.getId()).get();

		existingStudent.setStatus(Status.DEACTIVATED);
		int newStudent = studentRepository.updateStatus(Status.DEACTIVATED, 120);

		assertNotNull(newStudent);
		System.out.println(newStudent);
		assertEquals(Status.DEACTIVATED, existingStudent.getStatus());

	}

	/**
	 * test_delete
	 */
	@Test
	@DisplayName("It should delete the student record")
	void test_delete_deleteOneRecord() {

		studentRepository.save(student);
		int id = student.getId();

		studentRepository.save(student1);

		studentRepository.delete(student);
		Optional<Student> deletedstudentid = studentRepository.findById(id);
		List<Student> list = studentRepository.findAll();

		assertEquals(15, list.size());
		assertThat(deletedstudentid).isEmpty();

	}

}
