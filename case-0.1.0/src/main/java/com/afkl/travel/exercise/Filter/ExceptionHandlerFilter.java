
package com.afkl.travel.exercise.Filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.afkl.travel.exercise.Exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerFilter extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity resourceNotFoundException(HttpServletResponse response) throws IOException {
		Map<String, String> map1 = new HashMap<>();
		map1.put("errorMessage", "Requested resource does not exist");
		return new ResponseEntity(map1, HttpStatus.NOT_FOUND);
	}
}
