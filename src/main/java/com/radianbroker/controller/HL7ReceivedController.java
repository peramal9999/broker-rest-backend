package com.radianbroker.controller;

import com.radianbroker.payload.request.HL7ReceivedSearchRequest;
import com.radianbroker.service.HL7ReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class HL7ReceivedController {

	@Autowired
	HL7ReceivedService hl7ReceivedService;

	@PostMapping("/hl7-received-messages")
	public ResponseEntity<?> getHL7ReceivedMessages(@RequestBody HL7ReceivedSearchRequest hl7ReceivedSearchRequest) {
		try {
			return new ResponseEntity<>(hl7ReceivedService.getHL7ReceivedMessages(hl7ReceivedSearchRequest),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/hl7-received-message")
	public ResponseEntity<?> getHL7ReceivedMessage(@RequestParam long id) {
		Resource file;
		try {
			file = hl7ReceivedService.getHL7ReceivedMessage(id);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//	@DeleteMapping("/hl7-received-message")
//	public ResponseEntity<?> deleteL7ReceivedMessage() {
//		 
//		try {
//	 
//			return new ResponseEntity<>(hl7ReceivedService.deleteOldHL7ReceivedMessages(),HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
}
