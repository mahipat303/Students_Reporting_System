package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobleException {

	@ExceptionHandler(StudentException.class)
	public ResponseEntity<MyError> userExceptionHandler(StudentException u, WebRequest req) {

		MyError err = new MyError(LocalDateTime.now(), u.getMessage(), req.getDescription(false));

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> genralExceptionHandler(Exception u, WebRequest req) {

		MyError err = new MyError(LocalDateTime.now(), u.getMessage(), req.getDescription(false));

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> noExceptionHandler(NoHandlerFoundException u, WebRequest req) {

		MyError err = new MyError(LocalDateTime.now(), u.getMessage(), req.getDescription(false));

		return new ResponseEntity<MyError>(err, HttpStatus.BAD_REQUEST);

	}

}
