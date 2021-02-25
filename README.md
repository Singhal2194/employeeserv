# employeeserv

## Application Overview
employeeserv is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.

## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.

## Assignment
We would like you to enhance the existing project and see you complete the following requirements:

- `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
- Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
- Implement create and retrieve operations in `EmployeeResourceImpl.java`.
- Resource created using create endpoint should be retrieved using retrieve/get endpoint.
- Please add the unit tests to validate your implementation.
- Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
- Please make sure the validations are done for the requests.
- Response codes are as per rest guidelines.
- Error handling in case of failures.
- Idempotency logic is implemented to avoid duplicate resource creation.

## Assignment submission
- date of birth and address with the above required fields has been added.
- for address, separate table has been created to store the address of an employee. emp_id is foreign key 
- if id is not present in the request, emp id will be generated in the code itself.
- validation and exception handling is being added.
- idempotency logic is handled at the code and at the db level as well.
- if the generated id or the id passed in the request is already present, exception will be thrown.
- unit test cases are being added to validate implementation.

## curls

CREATE RESOURCE curl

curl --location --request POST 'http://localhost:8080/v1/bfs/employees/create' \
--header 'Cache-Control: no-cache' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 9257
    "first_name": "satish",
    "last_name": "agarwal",
    "date_of_birth": "2020-02-23",
    "address": {
        "line1": "harlur road",
        "line2": "sarjapur",
        "city": "bangalore",
        "state": "karnataka",
        "country": "india",
        "zip_code": 5600102
    }
}'

GET RESOURCE CURL

curl --location --request GET 'http://localhost:8080/v1/bfs/employees/9257' \
--header 'Cache-Control: no-cache' \
--header 'Content-Type: application/json' \

