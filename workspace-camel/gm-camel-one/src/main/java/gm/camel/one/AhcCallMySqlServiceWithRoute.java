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

public class AhcCallMySqlServiceWithRoute {

	public static void main(String args[]) throws Exception {

		   CamelContext context = new DefaultCamelContext();
		   
		    context.addRoutes(new RouteBuilder()
		      {
		      public void configure()
		        {
		    	    from("file:///C:/tmp/mysqlCreateDbJsons")
		    	    .to("stream:out");
		        }
		      });
		   
		    
    	    /***
    	    //.marshal().json()
    	    //.to("stream:out");
    	    //.to("file:C:/tmp/out1/mysqlCreateDb.out");
    	    //.to("ahc:http://localhost:8080/gm-mysql-services/MySqlService/json/createdb")
            //.convertBodyTo(String.class)
    	    //.to("file:C:/tmp/out1/mysqlCreateDb.out");
    	     * 
    	     */
		    
		    //context.setTracing(true);
		    
 
		    // Start the Camel route
		    System.out.println ("Starting context");
		    context.start();

		    // Wait x minutes, then stop
		    Thread.sleep (60*1*1000);
		    System.out.println ("Stopping context");

		    context.stop ();
		    }
}
