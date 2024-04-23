package net.texala.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import net.texala.enums.Status;
import net.texala.model.Student;
import net.texala.repository.StudentRepository;
import net.texala.web.mappers.StudentMapper;
import net.texala.web.vo.StudentVo;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTesting {

	private Student student;
	private Student student1;

	List<Student> list;

	@InjectMocks
	private StudentServiceImpl studentServiceIMPL;

	@Mock
	private StudentRepository studentRepository;

	@Spy
	private StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

	@BeforeEach
	void init() {

		student = new Student();

		student.setId(120);
		student.setName("Prathamesh");
		student.setAddress("Alandi");
		student.setSalary(200000);
		student.setStatus(Status.Activated);

		student1 = new Student();

		student1.setId(121);
		student1.setName("Sourabh");
		student1.setAddress("pune");
		student1.setSalary(220000);
		student1.setStatus(Status.DEACTIVATED);

	}

	/**
	 * add_validStudent_addOneRecord
	 */
	@Test
	@DisplayName("It will add new student")
	void add_validStudent_addOneRecord() {
		
		
		when(studentRepository.save(any(Student.class))).thenReturn(student);

		StudentVo addStudentVo=studentMapper.toDto(student);
		StudentVo studentvo=studentServiceIMPL.add(addStudentVo);
		
		
		assertNotNull(studentvo);
		assertThat(studentvo.getName()).isEqualTo("Prathamesh");
		
	}

	/**
	 * featchAll_allValidRecords_ShowAllRecordsOfStudents
	 */
	@Test
	@DisplayName("Shows all students")
	void featchAll_allValidRecords_ShowAllRecordsOfStudents() {

		list = new ArrayList<>();
		list.add(student);
		list.add(student1);

		when(studentRepository.findAll()).thenReturn(list);

		// List<StudentVo> allstudent= studentMapper.toEntities(List<StudentVo>, list);

		List<StudentVo> allstudents = studentServiceIMPL.fetchAll();

		System.out.println("**" + list.size());
		assertEquals(2, list.size());
		assertNotNull(allstudents);

	}

	/**
	 * getStudentById_showParticularRecord_showOneRecordById
	 */
	@Test
	@DisplayName("It should show the student by id ")
	void getStudentById_showParticularRecord_showOneRecordById() {
		
	when(studentRepository.findById(anyInt())).thenReturn(Optional.of(student));
	
	StudentVo  existingstudent= studentServiceIMPL.fetchById(120);
	System.out.println(existingstudent);
		
		assertNotNull(existingstudent);
		assertThat(existingstudent.getId()).isEqualTo(120);
	}

	/**
	 * updateStudent_updateParticularRecord_updateOneRecordById
	 */
	@Test
	@DisplayName("It Should Update One Record")
	void updateStudent_updateParticularRecord_updateOneRecordById() {
		
		
		when(studentRepository.save(any(Student.class))).thenReturn(student);
		
		
		student.setAddress("pune");
		StudentVo addStudentVo=studentMapper.toDto(student);

		StudentVo updateStudent=studentServiceIMPL.update(addStudentVo);

		assertNotNull(updateStudent);
		assertEquals("pune", updateStudent.getAddress());
		
	}

	/**
	 * updateStudentStatus_updateParticularRecord_updateOneRecordById
	 */
	@Test
	@DisplayName("It Should Update Status of One Record")
	void updateStudentStatus_updateParticularRecord_updateOneRecordById() {
		
		when(studentRepository.findById(anyInt())).thenReturn(Optional.of(student));
		
		student.setStatus(Status.Activated);
		when(studentRepository.save(any(Student.class))).thenReturn(student);
		
		StudentVo addStudentVo=studentMapper.toDto(student);

		StudentVo updateStudent=studentServiceIMPL.update(addStudentVo);

		assertNotNull(updateStudent);
		assertEquals("Activated", updateStudent.getStatus());
		
	}

	/**
	 * delete_particularRecord_deleterecordById
	 */
	@Test
	@DisplayName("It should delete the student record")
	void delete_particularRecord_deleterecordById() {
		
		
		when(studentRepository.findById(anyInt())).thenReturn(Optional.of(student));
		
		doNothing().when(studentRepository).delete(any(Student.class));
		
		studentServiceIMPL.delete(120);
		
		verify(studentRepository,times(1)).delete(student);
	}

}
