package com.example.demo;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoAppFileTests {

    @EndpointInject(uri = "mock:responseOne")
    private MockEndpoint mock;

    @EndpointInject(uri = "direct:startOne")
    private ProducerTemplate template;

    @EndpointInject(uri = "direct:startOne")
    private Endpoint context;

    private Exchange createRequest(){
        Exchange exchange = context.createExchange();
        return exchange;
    }

    @Test
    public void contextLoads() throws InterruptedException {
        mock.expectedMessageCount(1);

        template.send("direct:startOne", createRequest());

        mock.assertIsSatisfied();
    }
}
