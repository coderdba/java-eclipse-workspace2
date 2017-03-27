package com.maven.camel.RouterHTTPAhc3;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	/*
	 * public static void main(String... args) throws Exception { Main main =
	 * new Main(); main.addRouteBuilder(new MyRouteBuilder()); main.run(args); }
	 */
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				// Set up the route — from the intput directory, to
				// the output directory, with no other processing
				//from("file:/tmp/in?noop=true").to("file:/tmp/out");
				
		        from("direct:start")
		        .to("ahc:http://www.google.se")
		        .to("log:result?showAll=true")
		        .convertBodyTo(String.class)
		        .log("Google responded with body:\n${body}")
		        .to("mock:result");
			}
		});
		//
		// Start the Camel route
		context.start();

		// Wait five minutes, then stop
		Thread.sleep(60 * 5 * 1000);
		context.stop();
	}
}
