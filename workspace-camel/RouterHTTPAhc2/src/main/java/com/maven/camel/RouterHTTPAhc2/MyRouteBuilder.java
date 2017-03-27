package com.maven.camel.RouterHTTPAhc2;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("direct:start")
        .to("ahc:http://www.google.se")
        .to("log:result?showAll=true")
        .convertBodyTo(String.class)
        .log("Google responded with body:\n${body}")
        .to("mock:result");
    }

}
