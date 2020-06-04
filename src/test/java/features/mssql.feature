  
Feature: mssql query test

  Background:
    * print "Nothing in background yet"

  @db
  Scenario: query mssql
    * def config = { username: 'sa', password: 'mssql123', url: 'jdbc:sqlserver://localhost:1433;databaseName=TEST', driverClassName: 'com.microsoft.sqlserver.jdbc.SQLServerDriver' }
    * def DbUtils = Java.type('qa.test.DbUtils')
    * def db = new DbUtils(config)
    * karate.log(db.readRow("SELECT TOP (1) * FROM [TEST].[dbo].[Order]"))


