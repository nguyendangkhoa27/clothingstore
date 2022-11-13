package com.clothingstore.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class customExcetionHandler extends ResponseEntityExceptionHandler{

//		@Override
//		protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		return new ResponseEntity<Object>(ex, HttpStatus.OK);
//		}
		
//		@ExceptionHandler(Exception.class)
//		@ResponseStatus(HttpStatus.NOT_FOUND)
//		public MessageResponse<?> handlerNested(Exception ex,  WebRequest req,HttpServletRequest reqe, HttpServletResponse resp) {
//			return new MessageResponse<>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage());
//		} 
		
		@ExceptionHandler(NotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public MessageResponse<?> handlerNotFoundException(NotFoundException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
		@ExceptionHandler(BadRequestException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public MessageResponse<?> handlerBadRequestException(BadRequestException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, ex.getMessage(),ex.getObject());
		}
		
		@ExceptionHandler(IncorrectException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public MessageResponse<?> handlerIncorrectException(IncorrectException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		@ExceptionHandler(CustomConstainViolationException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public MessageResponse<?> handlerCustomConstainViolationException(CustomConstainViolationException ex,  WebRequest req) {
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST, ex.getMessageCustom());
		}
		@ExceptionHandler(QuantityCartDetailException.class)
		public MessageResponse<?> handlerQuantityCartDetailException(QuantityCartDetailException ex, WebRequest req){
			return new MessageResponse<>(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,ex.getMessage());
		}
//		@ExceptionHandler(Throwable.class)
//		@ResponseStatus(HttpStatus.BAD_REQUEST)
//		public ResponseEntity<?> handlerException(Throwable ex,WebRequest req){
//			Map<String, String> error = new HashMap<>();
//			error.put("error", ex.getLocalizedMessage());
//			error.put("message", ex.getMessage());
//			HttpStatusCodeException a =(HttpStatusCodeException) ex.getCause();
//			return new ResponseEntity<>(error, a.getStatusCode());
//		} 
		 
}
