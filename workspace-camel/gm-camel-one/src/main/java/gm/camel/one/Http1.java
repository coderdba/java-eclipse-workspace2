package gm.camel.one;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Http1 {

	public static void main(String[] args) throws Exception {
		
		   CamelContext context = new DefaultCamelContext();
		   
		    context.addRoutes(new RouteBuilder()
		      {
		      public void configure()
		        {
		        from ("direct:start").to("http://www.google.com").to("file:/tmp/out1");
		        }
		      });
		    
		    // Start the Camel route
		    System.out.println ("Starting context");
		    context.start();

		    // Wait x minutes, then stop
		    Thread.sleep (60*1*100);
		    System.out.println ("Stopping context");

		    context.stop ();
		    }
}
