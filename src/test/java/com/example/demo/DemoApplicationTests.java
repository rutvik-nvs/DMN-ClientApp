package com.example.demo;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {

	@EndpointInject(uri = "mock:response")
	private MockEndpoint mock;

	@EndpointInject(uri = "direct:start")
	private ProducerTemplate template;

	@EndpointInject(uri = "direct:start")
	private Endpoint endpoint;

	@Test
	public void contextLoads() throws InterruptedException {
		mock.expectedBodiesReceived("true,false,false,false");
		mock.expectedMessageCount(1);

		template.send("direct:start", endpoint.createExchange());

		mock.assertIsSatisfied();
	}

}
