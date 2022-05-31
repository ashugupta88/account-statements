package com.nagarro.assignment.accountstatements.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.nagarro.assignment.accountstatements.common.AppConstants;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatement;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementRequest;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementResponse;
import com.nagarro.assignment.accountstatements.exception.AppException;
import com.nagarro.assignment.accountstatements.exception.ErrorCodes;
import com.nagarro.assignment.accountstatements.utils.DateTimeHelper;

@Repository
public class AccountStatementRepo {
	
	private static final Logger logger = LogManager.getLogger(AccountStatementRepo.class);

	public AccountStatementResponse fetchAccountStatement(AccountStatementRequest accountStatementRequest) {
		
		logger.debug("Inside Account Statement Repository");
		AccountStatementResponse accountStatementResponse = new AccountStatementResponse();
		AccountStatement accountStatement = new AccountStatement();
		List<AccountStatement> listStatements = new ArrayList<AccountStatement>();
		
		String databaseURL = "jdbc:ucanaccess://accountsdb.accdb";
        
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
            String sql = "SELECT a.account_type, a.account_number, s.datefield, s.amount FROM account a\n" + 
            				"inner join statement s\n" + 
            					"on a.id=s.account_id\n" + 
            						"where a.account_number='" + accountStatementRequest.getAccountNumber() + "'";
             
             
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                String accountType = result.getString("account_type");
                String accountNumber = result.getString("account_number");
                String date = result.getString("datefield");
                String amount = result.getString("amount");
                
                accountStatementResponse.setAccountType(accountType);
                accountStatementResponse.setAccountNumber(accountNumber);
                accountStatement.setDate(DateTimeHelper.formatDate(date, AppConstants.DATE_FORMAT));
                accountStatement.setAmount(Double.valueOf(amount));
                listStatements.add(accountStatement);
                accountStatement = new AccountStatement();
            }
            accountStatementResponse.setStatement(listStatements);
            
        } catch (SQLException ex) {
        	logger.error("Some error occured");
            throw new AppException(ErrorCodes.SYS_TECHNICAL_ERROR);
        }
        
        return accountStatementResponse;
        
	}
}
