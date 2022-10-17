package com.clothingstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class customExcetionHandler{

		@ExceptionHandler(NotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public MessageResponse<?> handlerNotFoundException(NotFoundException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
		@ExceptionHandler(BadRequestException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public MessageResponse<?> handlerBadRequestException(BadRequestException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
		@ExceptionHandler(IncorrectException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public MessageResponse<?> handlerIncorrectException(IncorrectException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, ex.getMessage());
		}
}
