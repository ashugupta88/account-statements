package com.nagarro.assignment.accountstatements.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.assignment.accountstatements.common.AppConstants;
import com.nagarro.assignment.accountstatements.config.JwtTokenUtil;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatement;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementRequest;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementResponse;
import com.nagarro.assignment.accountstatements.dao.AccountStatementRepo;
import com.nagarro.assignment.accountstatements.exception.ErrorCodes;
import com.nagarro.assignment.accountstatements.exception.ServiceException;
import com.nagarro.assignment.accountstatements.utils.APIValidationHelper;
import com.nagarro.assignment.accountstatements.utils.DateTimeHelper;

@Service
public class AccountStatementsService {
	
	@Autowired
	private AccountStatementRepo accountStatementRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static final Logger logger = LogManager.getLogger(AccountStatementsService.class);

	public AccountStatementResponse fetchAccountStatement(AccountStatementRequest accountStatementRequest) throws Exception{
		logger.info("Inside Account Statement Service");
		String token = request.getHeader(AppConstants.AUTHORIZATION).substring(7);
		String loggedInUser = jwtTokenUtil.getUsernameFromToken(token);
		APIValidationHelper.validateMandatoryParameters(accountStatementRequest, loggedInUser);
		AccountStatementResponse accountStatementResponse = accountStatementRepo.fetchAccountStatement(accountStatementRequest);
		
		List<AccountStatement> statementList = new ArrayList<AccountStatement>();
		//filter response based on request
		if(loggedInUser.equals(AppConstants.USER)) {
			//by default fetch last 3 months statement
			logger.info("Fetch default last 3 months statements");
			statementList = accountStatementResponse.getStatement().stream().filter(
					statement -> statement.getDate().after(DateTimeHelper.getThreeMonthBackDate())  && 
					statement.getDate().before(Date.from(LocalDate.now().minusMonths(0).atStartOfDay(ZoneId.systemDefault()).toInstant()))).
					collect(Collectors.toList());
		} else if(loggedInUser.equals(AppConstants.ADMIN)) {
			//fetch statements based on filter parameters
			if(StringUtils.isBlank(accountStatementRequest.getFromAmount()) && StringUtils.isBlank(accountStatementRequest.getToAmount())
					&& StringUtils.isBlank(accountStatementRequest.getFromDate()) && StringUtils.isBlank(accountStatementRequest.getToDate())) {
				logger.info("Fetch default last 3 months statements");
				//by default fetch last 3 months statement
				statementList = accountStatementResponse.getStatement().stream().filter(
						statement -> statement.getDate().after(DateTimeHelper.getThreeMonthBackDate())  && 
						statement.getDate().before(Date.from(LocalDate.now().minusMonths(0).atStartOfDay(ZoneId.systemDefault()).toInstant()))).
						collect(Collectors.toList());
			}

			statementList = accountStatementResponse.getStatement().stream().filter(
					statement -> (StringUtils.isNotBlank(accountStatementRequest.getFromAmount()) ? 
							statement.getAmount() > Double.valueOf(accountStatementRequest.getFromAmount()) :
								true) &&
								(StringUtils.isNotBlank(accountStatementRequest.getToAmount()) ? 
										statement.getAmount() < Double.valueOf(accountStatementRequest.getToAmount()) :
											true) &&
											(StringUtils.isNotBlank(accountStatementRequest.getFromDate()) ? 
													statement.getDate().after(DateTimeHelper.formatDate(accountStatementRequest.getFromDate(), AppConstants.DATE_FORMAT)) :
														true) &&
														(StringUtils.isNotBlank(accountStatementRequest.getToDate()) ? 
																statement.getDate().before(DateTimeHelper.formatDate(accountStatementRequest.getToDate(), AppConstants.DATE_FORMAT)) :
																	true)).collect(Collectors.toList());
			logger.info("Filtered statements: {}", statementList);
			
		}
		
		if(statementList.size() == 0) {
        	logger.error("No records found");
        	throw new ServiceException(ErrorCodes.SYS_NO_RECORDS_FOUND);
        }
		
		accountStatementResponse.setStatement(statementList);
		return accountStatementResponse;
	}
}
