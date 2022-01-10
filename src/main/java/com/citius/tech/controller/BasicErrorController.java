package com.citius.tech.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicErrorController implements ErrorController{

	@RequestMapping(value="/error")
	public ResponseEntity errorHandling()
	{
		return new ResponseEntity("Some error occured",HttpStatus.BAD_REQUEST);
		
	}
}
