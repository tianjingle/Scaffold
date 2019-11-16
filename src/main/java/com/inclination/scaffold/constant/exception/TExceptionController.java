package com.inclination.scaffold.constant.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class TExceptionController{
	
	  @ResponseBody
	  @ExceptionHandler(TException.class)
	  public Error handleError(HttpServletRequest req, TException e) {
		  Error ew=new Error(e.getCode(),e.getMsg());
	      return ew; 
	  }
	  @ResponseBody
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public Error exceptionHander(HttpServletRequest req, MethodArgumentNotValidException e){
		  List<ObjectError> errorList=e.getBindingResult().getAllErrors();
		  String error="";
		  for (ObjectError objectError : errorList) {
			  error=objectError.getDefaultMessage();
		}	  
		  Error ew=new Error("0001",error);
	      return ew; 
	  }
}
