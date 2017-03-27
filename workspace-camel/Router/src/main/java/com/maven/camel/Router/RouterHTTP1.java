package com.maven.camel.Router;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RouterHTTP1 {
	
	  public static void main(String args[]) throws Exception
	    {
	    CamelContext context = new DefaultCamelContext();
	    context.addRoutes(new RouteBuilder()
	      {
	      public void configure()
	        {
	          from("direct:start").to("http://localhost:8080").to("file:/tmp/localhost8080.html");
	    	  //from("http://localhost:8080").to("file:/tmp/localhost8080.html");
	        }
	      });
	    
	    // Start the Camel route
	    context.start();

	    // Wait five minutes, then stop
	    Thread.sleep (60*5*1000);
	    context.stop ();
	    }
}