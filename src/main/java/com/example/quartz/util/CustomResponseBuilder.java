package com.example.quartz.util;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.quartz.model.ErrorResponse;
import com.example.quartz.model.SuccessResponse;

/**
 * Component class used to build response entity for returning success, error
 * and validation responses
 * 
 * @author Mindbowser | pragati.gawade@mindbowser.com
 *
 */
@Component
public class CustomResponseBuilder {

	/**
	 * Build http succcess response entity
	 * 
	 * @author Mindbowser | pragati.gawade@mindbowser.com
	 * @param <T>
	 * @param message
	 * @param data
	 * @param httpStatus
	 * @return {@link ResponseEntity}
	 */
	public <T> ResponseEntity<SuccessResponse<T>> buildSuccessResponse(String message, T data, HttpStatus httpStatus) {

		SuccessResponse<T> response = new SuccessResponse<>();
		response.setStatus(httpStatus.value());
		response.setData(data);
		response.setMessage(message);
		response.setTimestamp(new Date());

		return new ResponseEntity<>(response, httpStatus);
	}

	/**
	 * Build error response with http status
	 * 
	 * @author Mindbowser | pragati.gawade@mindbowser.com
	 * @param message
	 * @param detail
	 * @param httpStatus
	 * @return {@link ResponseEntity}
	 */
	public <T> ResponseEntity<ErrorResponse<T>> buildErrorResponse(String message, String detail, HttpStatus httpStatus,
			List<T> errors) {

		ErrorResponse<T> errorResponse = new ErrorResponse<>();
		errorResponse.setStatus(httpStatus.value());
		errorResponse.setMessage(message);
		errorResponse.setTimestamp(new Date());
		errorResponse.setDetail(detail);
		errorResponse.setErrors(errors);

		return new ResponseEntity<>(errorResponse, httpStatus);
	}

	/**
	 * Build http validation error response entity
	 * 
	 * @author Mindbowser | pragati.gawade@mindbowser.com
	 * @param <T>
	 * @param message
	 * @param detail
	 * @param fieldErrors
	 * @return {@link ResponseEntity}
	 */
	public <T> ResponseEntity<Object> buildValidationErrorResponse(String message, String detail,
			List<T> validationErrorModels) {

		ErrorResponse<T> errorResponse = new ErrorResponse<>();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(message);
		errorResponse.setTimestamp(new Date());
		errorResponse.setDetail(detail);
		errorResponse.setErrors(validationErrorModels);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
