  
Feature: mssql query test

  Background: testing
    * def config = { username: 'sa', password: 'mssql123!', url: 'jdbc:sqlserver://localhost:1433;databaseName=master;user=%s;password=%s', driverClassName: 'com.microsoft.sqlserver.jdbc.SQLServerDriver' }


  @db
  Scenario: query mssql using jdbc shim
    * def MSSqlUtil = Java.type('qa.karate.db.MSSqlUtil')
    * def db = new MSSqlUtil(config)
    * def queryResult = db.queryDB("SELECT TOP (3) OrderNumber, Id FROM [dbo].[Order] WHERE CustomerId = 79")
    * match queryResult[0] == {OrderNumber: '542379', Id: 2}
    * match queryResult contains {OrderNumber: '542379', Id: 2}
    * karate.log("queryResult:\n" + queryResult)
    * print "queryResult:\n" + queryResult

  @db
  Scenario: query mssql using spring boot template
    * def MSSqlUtil = Java.type('qa.karate.db.SpringBootDBUtil')
    * def db = new MSSqlUtil(config)
    * def queryResult = db.readRows("SELECT TOP (3) OrderNumber, Id FROM [dbo].[Order] WHERE CustomerId = 79")
    * match queryResult[0] == {OrderNumber: '542379', Id: 2}
    * match queryResult contains {OrderNumber: '542379', Id: 2}
    * karate.log("queryResult:\n" + queryResult)
    * print "queryResult:\n" + queryResult

