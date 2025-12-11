package com.radianbroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radianbroker.exceptions.CustomExceptionHandler;
import com.radianbroker.service.RisService;

@RestController
@RequestMapping("/api/v1")
public class RisController {
	
	@Autowired
	RisService risService;

	@GetMapping("/admin/ris")
	public ResponseEntity<?> getAllRis() {
		try {
			return new ResponseEntity<>(risService.getAllRis(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new CustomExceptionHandler(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/admin/ris-with-mips")
	public ResponseEntity<?> getAllRisWithMips() {
		try {
			return new ResponseEntity<>(risService.getAllRisWithMips(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new CustomExceptionHandler(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
		}
	}
}
