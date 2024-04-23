package net.texala.web.rest;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.texala.exception.BaseException;
import net.texala.response.entity.RestCustom;
import net.texala.response.entity.RestResponse;
import net.texala.response.entity.RestStatus;

@RestControllerAdvice
public class CustomResponseEntity extends ResponseEntityExceptionHandler {

	/**
	 * Base Exception
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<RestResponse<Object>> error(BaseException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_FOUND, "currently Server are down"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * InvalidDataAccessApiUsageException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
	public ResponseEntity<RestResponse<Object>> error(InvalidDataAccessApiUsageException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		custom.setCause("Invalid Data Access Api Exception is occuring");
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.BAD_REQUEST, "InvalidDataAccessApiUsageException is occuring"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * PropertyReferenceException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = PropertyReferenceException.class)
	public final ResponseEntity<RestResponse<Object>> error(PropertyReferenceException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.BAD_REQUEST, "Given Value for sorting is not Found"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * PropertyReferenceException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<RestResponse<Object>> error(Exception ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.BAD_REQUEST, "Given Value is not Found"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * PropertyReferenceException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(HttpRequestMethodNotSupportedException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.BAD_REQUEST, "Request method is not supported"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * HttpMediaTypeNotSupportedException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(HttpMediaTypeNotSupportedException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_ACCEPTABLE, "HTTP media type is not supported"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * HttpMediaTypeNotAcceptableException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(HttpMediaTypeNotAcceptableException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_ACCEPTABLE, "HTTP media type is not Acceptable"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * MissingPathVariableException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(MissingPathVariableException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_ACCEPTABLE, "Path variable is missing"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * MissingServletRequestParameterException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(MissingServletRequestParameterException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_ACCEPTABLE, "Servlet Request Parameter is missing"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * MethodArgumentNotValidException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(MethodArgumentNotValidException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_ACCEPTABLE, "method argument  is not valid"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * NoHandlerFoundException
	 * 
	 * @param ex
	 * @return
	 */
	public final ResponseEntity<RestResponse<Object>> error(NoHandlerFoundException ex) {
		RestCustom custom = RestCustom.builder().build();
		custom.setMessage(ex);
		RestResponse<Object> response = new RestResponse<>(null,
				new RestStatus<>(HttpStatus.NOT_ACCEPTABLE, "Given Handler is not found"), custom);
		return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.NOT_FOUND);
	}

}
