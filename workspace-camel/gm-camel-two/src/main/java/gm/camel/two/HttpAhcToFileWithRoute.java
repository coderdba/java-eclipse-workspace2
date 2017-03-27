package gm.camel.two;

import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.*;
import org.apache.camel.impl.DefaultCamelContext;

import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;

public class HttpAhcToFileWithRoute {

	public static void main(String args[]) throws Exception {

		   CamelContext context = new DefaultCamelContext();
		   
		    context.addRoutes(new RouteBuilder()
		      {
		      public void configure()
		        {
		    	  
		    	    //this did not work - from ("direct:start")
		    	    //--
		    	  	from("timer:foo?repeatCount=1")
		    	  	//OK
		    	    //.to("http://www.google.com/search?hl=en&q=camel&clientConfig.maxRequestRetry=3&clientConfig.followRedirects=true")
		    	  	//OK
		    	  	//.to("http4://www.google.com/search?hl=en&q=camel&clientConfig.maxRequestRetry=3&clientConfig.followRedirects=true")
		    	  	//OK
		    	    //.to("ahc:http://localhost:8080")
		    	  	//OK
		    	  	.to("http://localhost:8080")
		    	  	//Not sure what this does - .to("log:result?showAll=true")
                    .convertBodyTo(String.class)
		    	  	.to("file:C:/tmp/out1?fileName=http.txt")
		    	  	.to("stream:out")
		    	  	;
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
