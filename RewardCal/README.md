# Reward Points Calculator based on customer transaction
#The rest API to get customer rewards based on customer Id
# Run the App  -mvn spring-boot:run

# The Base package name is structured as com.chartercommunication.rewards

# Exception Handling is done. Eg: If any invalid data is passed in the Request then 400 with the 
# equivalent Message will be returned as response.
# H2 in-memory database is to store the information
  - Console URL : http://localhost:8080/h2-console
  - DB Name: rewardsdb
  - UserName: charter 
  - Password: 1234 
  - Tables : CUSTOMER, TRANSACTION 

# While Running the Application the initial Data Set will be loaded automatically. For the Data check the data.sql
# Check schema.sql for Definitions of the Tables.

# APIS:-

a.) http://localhost:8080/transactions
#It will provide all Transactions Data which includes customerId, TransactionId, TransactionAmount etc.

b.) http://localhost:8080/rewards
# It will provide you all the rewards points collected till now for every Customer.

c.) http://localhost:8080/reward/transaction/{transactionId}
# It will provide you rewards for the provided TransactionId.

d.) http://localhost:8080/rewards/customer/{customerId}
# It will provide you the rewards for provided customerId for last three months separately.

# Sample Data:-
CUSTOMER_IDS: 1001, 1002, 1003
TRANSACTION_IDS: 10001, 10002, 10003

# Included the Test Cases As well at the Service Layer by mocking the data
