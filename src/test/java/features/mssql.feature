  
Feature: mssql query test

  Background: testing
    * def config = { username: 'sa', password: 'mssql123', url: 'jdbc:sqlserver://localhost:1433;databaseName=TEST;user=%s;password=%s', driverClassName: 'com.microsoft.sqlserver.jdbc.SQLServerDriver' }
    * def DbUtils = Java.type('qa.test.DbUtils')
    * def db = new DbUtils(config)

  @db
  Scenario: query mssql
    * print ("Check the DEBUG CONSOLE to see if there is a JDBC error.")
    * karate.log(db.queryDB("SELECT TOP (1) * FROM [TEST].[dbo].[Order]"))


