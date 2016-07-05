package ie.gmit.sw.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MethodArgumentTypeErrorHandler {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String handlingEmptyFieldException(MethodArgumentTypeMismatchException ex){
		ex.printStackTrace();
		return "users?empnum=empty";
	}
}
