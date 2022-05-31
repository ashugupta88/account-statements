package com.nagarro.assignment.accountstatements.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nagarro.assignment.accountstatements.common.AppConstants;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementRequest;
import com.nagarro.assignment.accountstatements.exception.AppException;
import com.nagarro.assignment.accountstatements.exception.ErrorCodes;
import com.nagarro.assignment.accountstatements.exception.UnauthorizedException;

public class APIValidationHelper {
	
	private static final Logger logger = LogManager.getLogger(APIValidationHelper.class);

	public static void validateMandatoryParameters(AccountStatementRequest accountStatementRequest, String user) 
			throws Exception {
		
		if(StringUtils.isBlank(accountStatementRequest.getAccountNumber())) {
			logger.error("Account ID is not passed in request");
			throw new AppException(ErrorCodes.SYS_INVALID_REQUEST);
		}
		
		if(StringUtils.isBlank(user)) {
			logger.error("User cannot be fetched from token");
			throw new AppException(ErrorCodes.SYS_INVALID_REQUEST);
		} else if(user.equals("user")) {
			if(StringUtils.isNotBlank(accountStatementRequest.getFromAmount()) || StringUtils.isNotBlank(accountStatementRequest.getToAmount())
					|| StringUtils.isNotBlank(accountStatementRequest.getFromDate()) || StringUtils.isNotBlank(accountStatementRequest.getToDate())){
				//user isn't allowed to query using filters other than account number
				logger.error("Users other than admin isn't allowed to access filter parameters");
				throw new UnauthorizedException(ErrorCodes.SYS_UNAUTHORIZED);
			}
		} else if(user.equals("admin")) {
			if(StringUtils.isNotBlank(accountStatementRequest.getFromDate())) {
				DateTimeHelper.formatDate(accountStatementRequest.getFromDate(), AppConstants.DATE_FORMAT);
			} else if(StringUtils.isNotBlank(accountStatementRequest.getToDate())) {
				DateTimeHelper.formatDate(accountStatementRequest.getToDate(), AppConstants.DATE_FORMAT);
			}
		}
		
		
	}
}
