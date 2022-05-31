package com.nagarro.assignment.accountstatements.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.nagarro.assignment.accountstatements.exception.AppException;
import com.nagarro.assignment.accountstatements.exception.ErrorCodes;

public class DateTimeHelper {

	private DateTimeHelper() {
		super();
	}
	
	public static Date formatDate(String date, String format) {
		if(StringUtils.isNotBlank(date) && StringUtils.isNotBlank(format)) {
			try {
				DateFormat sdf = new SimpleDateFormat(format);
				sdf.setLenient(false);
				return sdf.parse(date);
			} catch (Exception e) {
				throw new AppException(ErrorCodes.SYS_INVALID_REQUEST);
			}
		}
		return null;
	}
	
	public static Date formatDate(String date, String inputFormat, String outputFormat) {
		if(StringUtils.isNotBlank(date) && StringUtils.isNotBlank(inputFormat) 
				&& StringUtils.isNotBlank(outputFormat)) {
			try {
				DateFormat sdf = new SimpleDateFormat(inputFormat);
				DateFormat targetSdf = new SimpleDateFormat(outputFormat);
				Date dt = sdf.parse(date);
				date = targetSdf.format(dt);
				return sdf.parse(date);
			} catch (Exception e) {
				throw new AppException(ErrorCodes.SYS_INVALID_REQUEST);
			}
		}
		return null;
	}
	
	public static Date getThreeMonthBackDate() {
		return Date.from(LocalDate.now().minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static void main(String args[]) {
		System.out.println(DateTimeHelper.formatDate("12.12.2016", "dd.MM.yyyy"));
		System.out.println(DateTimeHelper.getThreeMonthBackDate());
	}
}
