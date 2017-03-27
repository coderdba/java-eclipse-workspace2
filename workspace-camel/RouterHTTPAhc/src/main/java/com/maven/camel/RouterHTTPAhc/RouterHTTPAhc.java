package com.maven.camel.RouterHTTPAhc;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class RouterHTTPAhc {
	
	  public static void main(String args[]) throws Exception
	    {
	    CamelContext context = new DefaultCamelContext();
	    
	    System.out.println ("About to build the root");
	    
	    context.addRoutes(new RouteBuilder()
	      {
	      /*
	    	public void configure()
	        {
	    	  
	    	  System.out.println ("In configure() to build the route");
	    	  
	    	  //from("direct:start").to("ahc:http://camel.apache.org/ahc.html").to("file://tmp/out.html");
	    	  from("direct:start")
	            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
	        .to("ahc:http://www.google.com")
	        //.to("log:out?showAll=true&multiline=true");
	        //.to("file:/tmp/out.html");
	        .to("stream:out");
	            //.to("mock:results");
	    	  
	    	  System.out.println ("In configure() completed building the route");
	        }
	      */
	    	
            public void configure() throws Exception {
            	
            	System.out.println ("In configure() to build the route");
            	
                from("direct:start")
                    .to("ahc:http://www.google.com")
                    .to("log:result?showAll=true")
                    .convertBodyTo(String.class)
                    .log("Google responded with body:\n${body}")
                    .to("mock:result");
                
                System.out.println ("In configure() completed building the route");
            }
	    	
	      });
	    
	    System.out.println ("Starting context");
	    // Start the Camel route
	    context.start();

	    // Wait five minutes, then stop
	   // Thread.sleep (60*5*1000);
	    
	    System.out.println ("Stopping context");
	    context.stop ();
	    
	    System.out.println ("Stopped context");
	    }
}