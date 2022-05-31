# account-statements
Assignment solution for fetching account statements

# Import application into Eclipse
Import application into Eclipse as "Existing maven project"

Run the application in local and access using default port 8080

# Fetch token >>>>

Request type: POST

Headers: "content-type": "application/json"

Body: 

  {
    "username":"XXX",
    "password":"YYY"
  }
  
Use the username and password as defined in assignment problem

URL: http://localhost:8080/authenticate

# Fetch account statements >>>>

Request type: POST

Headers: 
  "content-type": "application/json"
  "authorization": "Bearer {token}"
  
Body:

  {
    "accountNumber":"0012250016001",
    "fromAmount": "800",
    "toAmount": "900",
    "fromDate": "12.12.2019" {dd.MM.yyyy format only}
  }

URL: http://localhost:8080/customer/fetchStatement


# Error codes
  
  SYS_INVALID_REQUEST
  
	SYS_TECHNICAL_ERROR
  
	SYS_UNAUTHORIZED
  
	SYS_TOKEN_ERROR
  
	SYS_INVALID_CRED
  
	SYS_INVALID_USER
  
	SYS_USER_DISABLED
  
	SYS_NO_RECORDS_FOUND
  
  
 # Problems faced
 
  I'm a MAC user and don't have alter access for MS DB provided
  > Multiple logins can be stopped using intermediate table to hold sessions of user logged in
  
  
