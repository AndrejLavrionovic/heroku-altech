package ie.gmit.sw.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseErrorHandler {

	@ExceptionHandler(DataAccessException.class)
	public String handlingDatabaseException(DataAccessException ex){
		ex.printStackTrace();
		return "error";
	}
}
