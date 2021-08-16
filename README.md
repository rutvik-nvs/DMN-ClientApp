## Sample KIE Server Client Application
- This repository contains the source code for a sample Maven Java Project with Spring Boot which allows the application to connect to the kie-server running on an OCP Cluster and evaluation on the data can be performed.
- It can be programmed to act as a REST Api using Spring Boot Servlet or as a client Java Application.
- Use Cases:
## Prerequisites
- KIE-server running on a local machine or on an OCP Cluster
- Java IDE, IntelliJ IDEA is recommended
- Java JDK 11

## Setting up the Developement Environment
Step 1. Clone this Repository
Step 2. Open the project in your preffered Java IDE.
Step 3. Open the `DemoApplicationTests.java` file from `/src/test/java/com.example.demo/` directory in the IDE and start the test to check if the application builds and runs successfully.
### Dependencies
 - <b>Apache Camel</b> is an integration framework supporting over 100+ components to use with various technologies allowing developers to easily plug in and connect various services together. [Learn more](https://camel.apache.org/manual/latest/faq/what-is-camel.html)
 - <b>Spring Boot</b> is a tool that makes developing web application and microservices with Spring Framework faster and easier. [Learn more](https://www.ibm.com/cloud/learn/java-spring-boot)
 - <b>Apache Freemarker</b> is a Templating Engine that allows developers to use templates to create data strings. It is used here to generate JSON Requests which are sent to the KIE-server.
 
 
 ## Application Architecture
 - 
