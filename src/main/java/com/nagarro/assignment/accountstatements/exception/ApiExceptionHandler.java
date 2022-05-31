package com.nagarro.assignment.accountstatements.exception;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.nagarro.assignment.accountstatements.controller.dtos.APIResponse;

@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AppException.class)
	@ResponseBody
	public final APIResponse handleApiResponse(AppException ex, WebRequest request) {
		APIResponse apiResponse = new APIResponse();
		
		if(ex instanceof AppException) {
			AppException ae = (AppException) ex;
			if(Objects.nonNull(ex.getErrorDetails())) {
				apiResponse.setFailure(ex.getErrorDetails().get(0), ex.getErrorDetails().get(1));
			} else {
				ErrorCodes errorCodes = ae.getErrorCode();
				apiResponse.setFailure(errorCodes);
			}
		} else {
			apiResponse.setFailure(ErrorCodes.SYS_TECHNICAL_ERROR);
		}
		
		return apiResponse;
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public final APIResponse handleUnauthorized(UnauthorizedException ex, WebRequest request) {
		APIResponse apiResponse = new APIResponse();

		if(ex instanceof UnauthorizedException) {
			apiResponse.setFailure(ex.getErrorCode());
		} else {
			apiResponse.setFailure(ErrorCodes.SYS_TECHNICAL_ERROR);
		}

		return apiResponse;
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public final ResponseEntity<APIResponse> handleServiceException(ServiceException ex, WebRequest request) {
		APIResponse apiResponse = new APIResponse();
		apiResponse.setFailure(ErrorCodes.SYS_NO_RECORDS_FOUND);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}
}
