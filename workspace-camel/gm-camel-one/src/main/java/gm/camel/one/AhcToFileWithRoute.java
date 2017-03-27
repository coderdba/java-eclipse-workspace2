package gm.camel.one;

import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.*;
import org.apache.camel.impl.DefaultCamelContext;

import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;

public class AhcToFileWithRoute {

	public static void main(String args[]) throws Exception {

		   CamelContext context = new DefaultCamelContext();
		   
		    context.addRoutes(new RouteBuilder()
		      {
		      public void configure()
		        {
		        //from ("ahc:http://www.google.com/search?hl=en&q=activemq&clientConfig.maxRequestRetry=3&clientConfig.followRedirects=true").to("file:/tmp/out1");
		    	//.to("ahc:http://localhost:8080?clientConfig.maxRequestRetry=3&clientConfig.followRedirects=true")
		    	  //from("direct:start")
		    	  	from("timer:foo?repeatCount=1")
		    	    .to("ahc:http://localhost:8080")
		    	    //.to("log:result?showAll=true")
                    .convertBodyTo(String.class)
		    	    .to("file:C:/tmp/out1?fileName=ahc.out");
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
