package net.texala.web.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.texala.constants.Constants;
import net.texala.enums.Status;
import net.texala.response.entity.RestResponse;
import net.texala.response.entity.RestStatus;
import net.texala.service.StudentService;
import net.texala.web.vo.StudentVo;

@RestController
@RequestMapping(value = "/v0/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	/**
	 * Get Request to Search all Students records.
	 * 
	 * @param pageno
	 * @param pagesize
	 * @param sortby
	 * @param filterby
	 * @param searchtext
	 * @return
	 */
	@GetMapping("/search")
	public ResponseEntity<RestResponse<Page<StudentVo>>> search(
			@RequestParam(value = "pageno", defaultValue = "0", required = false) Integer pageno,
			@RequestParam(value = "pagesize", defaultValue = "" + Integer.MAX_VALUE, required = false) Integer pagesize,
			@RequestParam(value = "sortby", defaultValue = "id:desc", required = false) String sortby,
			@RequestParam(value = "filterby", required = false) String filterby,
			@RequestParam(value = "searchtext", required = false) String searchtext) {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.OK, Constants.Fetch_RECORDS);
		final Page<StudentVo> page = studentService.search(pageno, pagesize, sortby, filterby, searchtext);
		final RestResponse<Page<StudentVo>> response = new RestResponse<>(page, restStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get Request to show all Students records.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<RestResponse<List<StudentVo>>> fetchAll() {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.OK, Constants.Fetch_RECORDS);
		final List<StudentVo> list = studentService.fetchAll();
		final RestResponse<List<StudentVo>> response = new RestResponse<>(list, restStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Get Request to show one Student record.
	 * 
	 * @param studentId
	 * @return
	 */
	@GetMapping("/{studentId}")
	public ResponseEntity<RestResponse<StudentVo>> getStudentById(@PathVariable("studentId") int studentId) {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.OK, Constants.Fetch_RECORDS);
		final RestResponse<StudentVo> response = new RestResponse<>(studentService.fetchById(studentId), restStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Post Request to Add 1 Student record.
	 * 
	 * @param studentvo
	 * @return
	 */
	@PostMapping
	public ResponseEntity<RestResponse<StudentVo>> add(@Valid @RequestBody StudentVo studentvo) {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.CREATED, Constants.ADD_RECORD);
		final RestResponse<StudentVo> response = new RestResponse<>(studentService.add(studentvo), restStatus);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Put Request to Update Student record.
	 * 
	 * @param studentId
	 * @param studentvo
	 * @return
	 */
	@PutMapping("/{studentId}")
	public ResponseEntity<RestResponse<StudentVo>> update(@PathVariable("studentId") int studentId,
			@RequestBody StudentVo studentvo) {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.OK, Constants.UPDATE_RECORD);
		final RestResponse<StudentVo> response = new RestResponse<>(studentService.update(studentvo), restStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Patch Request to UPDATE status of 1 Student record.
	 * 
	 * @param studentId
	 * @param status
	 * @return
	 */
	@PatchMapping("/{studentId}")
	public ResponseEntity<RestResponse<Void>> UpdateStatus(@PathVariable("studentId") int studentId,
			@RequestParam(name = "status") Status status) {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.OK, Constants.UPDATE_RECORD);
		studentService.update(status, studentId);
		final RestResponse<Void> response = new RestResponse<>(null, restStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Delete Request to delete 1 Student record.
	 * 
	 * @param studentId
	 * @return
	 */
	@DeleteMapping("/{studentId}")
	public ResponseEntity<RestResponse<Void>> delete(@PathVariable("studentId") int studentId) {
		final RestStatus<?> restStatus = new RestStatus<>(HttpStatus.OK, Constants.DELETE_MSG);
		studentService.delete(studentId);
		final RestResponse<Void> response = new RestResponse<>(null, restStatus);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}