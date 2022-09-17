package com.recipe;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.recipe.exception.BussinessException;
import com.recipe.exception.ContractException;
import com.recipe.exception.ErrorResponse;
import com.recipe.exception.TechnicalException;
import com.recipe.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

	private static final String ERR_LOG_MSG = "Detailed Exception!";

	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<Object> handleBussinessException(BussinessException e) {
		log.error(ERR_LOG_MSG, e);
		ErrorResponse error = new ErrorResponse(e.getErrorcode().toString(), e.getDescription(), e.getParameter(),
				BussinessException.class.getSimpleName());
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.ERROR, error);
		map.put(Constants.SUCCESS, null);
		return new ResponseEntity<>(map, e.getErrorcode());
	}

	@ExceptionHandler(ContractException.class)
	public ResponseEntity<Object> handleContractException(ContractException e) {
		log.error(ERR_LOG_MSG, e);
		ErrorResponse error = new ErrorResponse(e.getErrorcode().toString(), e.getDescription(), e.getParameter(),
				ContractException.class.getSimpleName());
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.ERROR, error);
		map.put(Constants.SUCCESS, null);
		return new ResponseEntity<>(map, e.getErrorcode());

	}

	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<Object> handleTechnicalException(TechnicalException e) {
		log.error(ERR_LOG_MSG, e);
		ErrorResponse error = new ErrorResponse(e.getErrorcode().toString(), e.getDescription(), e.getParameter(),
				TechnicalException.class.getSimpleName());
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.ERROR, error);
		map.put(Constants.SUCCESS, null);
		return new ResponseEntity<>(map, e.getErrorcode());
	}

}
