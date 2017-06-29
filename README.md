# moneewise
MoneeWise app backend restful service.
A microservice exposing rest endpoints built with Java Spring boot framework and can be deployed in docker containers.

#### Still under development. (Personal project)
The front end will be an Ionic hybrid mobile app.

## Building, Deployment and Accessing

### Locally
Follow these steps to set up the environment, build and run the service:

* Clone the repository and make sure you have java 8 and Gradle installed.
* Since the microservice uses MySQL as the database, make sure MySQL is installed.
* Start your MySQL server locally and create a database called test_budget(default database).
* Build will fail if no MySQL running instance exists.
* Make sure the database uri, username and password is then updated in application.properties as per your local db instance.
* No need to create tables as the JPA and hibernate will take care of it.

To Build and run the system with one command, navigate to the directory and type
```
gradle bootRun
```
The service can be then tested using POSTMAN.
Since service will be running locally, the url will be
```
http://localhost:8080/{rest endpoints}
```
To just build the system, type
```
gradle build
```

## REST API
The service has 4 endpoints which produce JSON responses.

**POST** */moneewise/api/expense*
* Returns the posted message or throws exception
* Consumes content-type: application/x-www-form-urlencoded
* Requires values of 6 keys: 

**GET** */moneewise/api/expense/month*
* Returns the list of all expenses for the specified month
* Consumes content-type: application/json
* Requires values of 3 keys:

**POST** */moneewise/api/expense/description*
* Returns the description of the specific expense
* Consumes content-type: application/x-www-form-urlencoded
* requires values of 2 keys:

**GET** */moneewise/api/expense/desc/{expense_id}*
* Returns the description of the specific expense
* Consumes content-type: application/json
* requires the id of the expense


## Implementation and Architecture

#### Technologies Used
* *Java Spring boot framework(Spring4):* For creating rest controllers and mapping 
* *Hibernate ORM and JPA:* To connect with database, automated generation of tables in database
* *MySQL:* Stores all the information
* *Gradle:* Build automation. Also automates tests and ceation of docker containers
* *Docker:* Run and deploy the service in containers

#### Tools
* Spring Tool Suite (STS)
* Postman
