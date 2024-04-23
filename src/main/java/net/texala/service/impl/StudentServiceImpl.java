package net.texala.service.impl;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.texala.constants.Utility;
import net.texala.enums.Status;
import net.texala.exception.ServiceException;
import net.texala.model.Student;
import net.texala.repository.StudentRepository;
import net.texala.service.StudentService;
import net.texala.specification.SearchSpecification;
import net.texala.web.mappers.StudentMapper;
import net.texala.web.vo.StudentVo;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repo;

	@Autowired
	private StudentMapper studentMapper;

	/**
	 * Implementation method for Get Request to Search all records
	 */
	@Override
	public Page<StudentVo> search(Integer pageNo, Integer pageSize, String sortBy, String filterby, String searchtext) {
		final Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Utility.sortByValues(sortBy)));
		final Specification<Student> specification = SearchSpecification.searchStudent(filterby, searchtext);
		return repo.findAll(specification, pageable).map(studentMapper::toDto);
	}

	/**
	 * Implementation method for Get Request to show all records
	 */
	@Override
	public List<StudentVo> fetchAll() {
		return studentMapper.toDtos(repo.findAll());
	}

	/**
	 * Implementation method for Get Request to show one record
	 */
	@Override
	public StudentVo fetchById(int studentId) {
		final Student student = this.repo.findById(studentId)
				.orElseThrow(() -> new ServiceException("Id is Not Present"));
		return studentMapper.toDto(student);
	}

	/**
	 * Implementation method for Post Request to Add 1 record
	 */
	@Override
	@Transactional
	public StudentVo add(StudentVo studentvo) {
		final Student student = studentMapper.toEntity(studentvo);
		Objects.requireNonNull(studentvo);
		return studentMapper.toDto(repo.save(student));
	}

	/**
	 * Implementation method for Put Request to Update 1 record
	 */
	@Override
	@Transactional
	public StudentVo update(StudentVo studentvo) {
		Objects.requireNonNull(studentvo.getId());
		final Student student = studentMapper.toEntity(studentvo);
		return studentMapper.toDto(repo.save(student));
	}

	/**
	 * Implementation method for Patch Request to UPDATE status of 1 record
	 */
	@Override
	@Transactional
	public void update(Status status, int studentId) throws ServiceException {
		repo.updateStatus(status, studentId);
	}

	/**
	 * Implementation method for Delete Request to delete record
	 */
	@Override
	@Transactional
	public void delete(int studentId) {
		final Student student = repo.findById(studentId).orElseThrow(() -> new ServiceException());
		repo.delete(student);
	}

}