## Sample KIE Server Client Application
- This repository contains the source code for a sample Maven Java Project with Spring Boot which allows the application to connect to the KIE-server running on an OCP Cluster and evaluation on the data can be performed.
- It can be programmed to act as a REST API using Spring Boot Servlet or as a client Java application.
- Use Cases:
## Prerequisites
- KIE-server running on a local machine or an OCP Cluster
- Java IDE, IntelliJ IDEA is recommended
- Java JDK 11

## Setting up the Development Environment
Step 1. Clone this Repository
Step 2. Open the project in your preferred Java IDE.
Step 3. Open the `DemoApplicationTests.java` file from `/src/test/java/com.example.demo/` directory in the IDE and start the test to check if the application builds and runs successfully.
### Dependencies
 - <b>Apache Camel</b> is an integration framework supporting over 100+ components to use with various technologies allowing developers to easily plugin and connect various services. [Learn more](https://camel.apache.org/manual/latest/faq/what-is-camel.html)
 - <b>Spring Boot</b> is a tool that makes developing web applications and microservices with Spring Framework faster and easier. [Learn more](https://www.ibm.com/cloud/learn/java-spring-boot)
 - <b>Apache Freemarker</b> is a Templating Engine that allows developers to use templates to create data strings. It is used here to generate JSON Requests which are sent to the KIE-server.
 
 
 ## Application Architecture
 - `DemoApplicationRouteBuilder.java` under `/src/main/java/com/example/demo/` directory is the route builder file in which the data pipelines are specified using apache camel.
 - Let's take a look at the `direct:start to TARGET_WITH_AUTH` route which calls the KIE-server REST API.
 The route is configured to generate the JSON payload using Freemarker templates and attach it to the POST request which is sent to the KIE-server for evaluation. The results received are logged.
 A `direct` component is used as the entry point and thus can be attached to any other route or can be manually triggered. The results are logged here for demo purposes.
 For Demo, a REST API is also exposed to provide web app integration functionality.

## Payload Configuration
- A File system input source is selected here for demo purposes. The data can be retrieved from various other sources such as a database, etc.
- Two main files are used to generate the JSON request.
- `freemarker:requestObject.ftl` is used to wrap the input source data inside the Input objects which forms the dmn-context object of the JSON request.
- `freemarker:template.ftl` is used to finally wrap the dmn-context with other important meta-data to be sent to the KIE-server.
