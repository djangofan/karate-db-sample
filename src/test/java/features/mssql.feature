  
Feature: mssql query test

  Background: testing
    * def config = { username: 'sa', password: 'mssql123', url: 'jdbc:sqlserver://localhost:1433;databaseName=TEST;user=%s;password=%s', driverClassName: 'com.microsoft.sqlserver.jdbc.SQLServerDriver' }
    * def MSSqlUtil = Java.type('qa.test.MSSqlUtil')
    * def db = new MSSqlUtil(config)

  @db
  Scenario: query mssql
    * print ("Check the DEBUG CONSOLE to see if there is a JDBC error.")
    * def queryResult = db.queryDB("SELECT TOP (3) * FROM [TEST].[dbo].[Order]")
    * karate.log(queryResult)
    * print "queryResult: " + queryResult
    
