package com.citius.tech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@GetMapping("/")
	public ResponseEntity<String> test()
	{
		log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return new ResponseEntity<String>("Hello",HttpStatus.ACCEPTED);
	}
}
