package com.maven.camel.restFromToExample1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Router {
	
	  public static void main(String args[]) throws Exception
	    {
	    CamelContext context = new DefaultCamelContext();
	    context.addRoutes(new RouteBuilder()
	      {
	      public void configure()
	        {
	           from("direct:start")
	           .to("http://localhost:8080");
	        }
	      });
	    
	    // Start the Camel route
	    context.start();

	    // Wait five minutes, then stop
	    Thread.sleep (60*5*1000);
	    context.stop ();
	    }

}
