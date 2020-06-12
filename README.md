# karate-db-sample

DB Query Testing Example In Karate

## Setup

See Karate website.

## NOTES

I used this project to debug a scenario described here: https://github.com/kirksl/karate-runner/issues/53

## Execute

    mvn clean test

    

### MSSQL Server

To get MSSQL server on a mac:

- install   Azure Data Studio
- Install Docker image justin2004/mssql_server_tiny. https://hub.docker.com/r/justin2004/mssql_server_tiny

    docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=mssql123!' -p 1433:1433 -d justin2004/mssql_server_tiny

- Connect to the docker image using Azure data studio and load the .sql script data included in this repo.


## Screenshot

![Failure](https://github.com/djangofan/karate-db-sample/blob/master/debug.png)


