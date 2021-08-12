package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationRouteBuilder extends RouteBuilder {

    public static final String TARGET_WITH_AUTH = "https:8080-ssl-sample-dmn.ocp.nvsconsulting.io/services/rest/server/containers/Sample-DMN_1.0.0-SNAPSHOT/dmn" +
            "?authMethod=Basic&authUsername=kie-admin&authPassword=kie-redhat1!&bridgeEndpoint=true";

    public static final String MODEL_NAMESPACE = "https://kiegroup.org/dmn/_5388429C-0B33-4FA1-95DD-FA7A69AF31E6";
    public static final String MODEL_NAME = "SampleDMN";
    public static final String DMN_CONTEXT = "{\n    \"Input\": [\n        {\"DOM\": \"01/01/1970\", \"hasLicense?\": true, \"isLicenseValid?\": true},\n        {\"DOM\": \"01/01/1970\", \"hasLicense?\": false, \"isLicenseValid?\": true},\n        {\"DOM\": \"01/01/1970\", \"hasLicense?\": true, \"isLicenseValid?\": false},\n        {\"DOM\": \"01/01/1970\", \"hasLicense?\": false, \"isLicenseValid?\": false}\n      ]\n    }";

    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet");

        rest("/api")
                .get("/records")
                .to("direct:start");

//        from("direct:dbStart")
//                .to("...");

        from("direct:startOne").to("mock:response");

        // Input Data to JSON Request
        from("file:input/?fileName=Data.json&noop=true")
                .to("freemarker:templates/requestObject.ftl")
                .log("\n${body}")
                .to("mock:responseOne");


        from("direct:start").id("start")
                    .setHeader("modelNamespace", constant(MODEL_NAMESPACE))
                    .setHeader("modelName", constant(MODEL_NAME))
                    .setHeader("dmnContext", constant(DMN_CONTEXT))
                .to("freemarker:templates/template.ftl")
                    .setHeader("Content-Type", constant("application/json"))
                    .setHeader("Accept", constant("application/json"))
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .removeHeader(Exchange.HTTP_PATH)
                .to(TARGET_WITH_AUTH)
                .split().jsonpath("result.dmn-evaluation-result.decision-results[*]")
                .choice()
                    .when().jsonpath("$.[?(@.decision-name == 'Decision')]")
                        .split().jsonpath(".result")
                        .log("${body}")
                .to("file:output/response.txt")
                .to("mock:response");
//                .otherwise()
//                        .setHeader("h_isSuspended").jsonpath("$.result")
//                        .log("...suspensin result is parsed from response body: ${headers.h_isSuspended}");
    }
}