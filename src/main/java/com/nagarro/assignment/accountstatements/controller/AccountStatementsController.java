package com.nagarro.assignment.accountstatements.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementRequest;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementResponse;
import com.nagarro.assignment.accountstatements.service.AccountStatementsService;

@RestController
@RequestMapping(path = "/customer")
public class AccountStatementsController {
	
	@Autowired
	private AccountStatementsService accountStatementService;
	
	private static final Logger logger = LogManager.getLogger(AccountStatementsController.class);

	@PostMapping(path= "/fetchStatement", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AccountStatementResponse> fetchAccountStatement(@RequestBody AccountStatementRequest accountStatementRequest) throws Exception {
		logger.info("Inside Account Statement Controller");
		AccountStatementResponse accountStatementResponse = accountStatementService.fetchAccountStatement(accountStatementRequest);
		return new ResponseEntity<>(accountStatementResponse, HttpStatus.OK);
 
    }
}
