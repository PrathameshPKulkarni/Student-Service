package net.texala.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.texala.enums.Status;
import net.texala.web.vo.StudentVo;

public interface StudentService {

	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @param filterby
	 * @param searchtext
	 * @return
	 */
	public Page<StudentVo> search(Integer pageNo, Integer pageSize, String sortBy, String filterby, String searchtext);

	/**
	 * 
	 * @return
	 */
	public List<StudentVo> fetchAll();

	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public StudentVo fetchById(int studentId);

	/**
	 * 
	 * @param studentvo
	 * @return
	 */
	public StudentVo add(StudentVo studentvo);

	/**
	 * 
	 * @param studentvo
	 * @return
	 */
	public StudentVo update(StudentVo studentvo);

	/**
	 * 
	 * @param status
	 * @param studentId
	 */
	public void update(Status status, int studentId);

	/**
	 * 
	 * @param studentId
	 */
	public void delete(int studentId);

}