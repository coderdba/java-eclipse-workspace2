package com.maven.camel.RouterHTTPAhc1;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;


/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
    	/*
        from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .log("UK message")
                    .to("file:target/messages/uk")
                .otherwise()
                    .log("Other message")
                    .to("file:target/messages/others");
        */
    	
    	/*
    	from("direct:start")
        .to("ahc:http://www.google.com")
        .to("log:result?showAll=true")
        .convertBodyTo(String.class)
        .log("Google responded with body:\n${body}")
        .to("mock:result");
        */
    	
    	
    	from("direct:start")
        .to("ahc:http://www.google.com")
         .convertBodyTo(String.class)
        .log("Google responded with body:\n${body}")
        .to("mock:result");
    	
    	
    	//from("timer:start").to("ahc:http://camel.apache.org/ahc.html").to("file://tmp/out.html");
    	//from("timer://foo?fixedRate=true&period=60000"
    	//from("timer://foo?repeatCount=3").to("ahc:http://camel.apache.org/ahc.html").to("file://tmp/out.html");
    	//from("ahc:http://camel.apache.org/ahc.html").to("file://tmp/out.html");
    	
    	//from("direct:start").setHeader(Exchange.HTTP_METHOD, constant("GET")).log("ZZZ-CREATED THE ROUTE-ZZZ").to("ahc:http://www.google.com");
    	
    	/*
  	  from("direct:start")
          .setHeader(Exchange.HTTP_METHOD, constant("GET"))
      .to("ahc:http://www.google.com")
      //.to("log:out?showAll=true&multiline=true");
      //.to("file:/tmp/out.html");
      .to("stream:out");
          //.to("mock:results");
           
           */
    	
    }

}
