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

public class MySqlServiceCallWithRoute {

	public static void main(String args[]) throws Exception {

		   CamelContext context = new DefaultCamelContext();
		   
		    context.addRoutes(new RouteBuilder()
		      {
		      public void configure()
		        {
		    	  	//NOT SURE how this exception handler works
		    	    //onException(NullPointerException.class).to("stream:out");
		    	    //onException(NullPointerException.class)
		    	    //.handled(true)
		    	    //.transform(constant("Null pointer - malformed json input"))
		    	    //.to("stream:out");
		    	    

		    	    //
		    	  	//NOT OK - this needs a xml file for marshaling as json - not clear yet
		    	    //from("file:///C:/tmp/mysqlCreateDbJsons?fileName=mysqlCreateDbJson1.txt")
		    	  	//NOT OK - Dont use direct:start - use a timer with 1 invocation instead
		    	  	//from("direct:start")
		    	    //OK
		    	    from("timer:foo?repeatCount=1")
		    	    //OK
		    	    //.setHeader(Exchange.HTTP_METHOD, constant("POST"))
		    	    //OK
		    	    .setHeader("Method", constant("POST"))
		    	    //NOT OK - gives 514 unsupported media type
		    	    //.setHeader(Exchange.ACCEPT_CONTENT_TYPE, constant("application/json"))
		    	    //OK
		    	    .setHeader("Content-Type", constant("application/json"))
		    	    //NOT OK - sends null json to api
		    	    //.setBody(body().append("{\"hostname\":\"localhost\", \"port\":\"13306\", \"dbname\":\"mkt\", \"username\":\"mktuser\",\"password\":\"mkt123_MKT123\"}"))
		    	    //OK
		    	    .setBody(constant("{\"hostname\":\"localhost\", \"port\":\"13306\", \"dbname\":\"mkt\", \"username\":\"mktuser\",\"password\":\"mkt123_MKT123\"}"))
		    	    //OK - no need to give ahc:http
		    	    .to("http://localhost:8080/gm-mysql-services/MySqlService/json/createdb")
		    	    .to("stream:out")
		    	    ;
		        }
		      });
		   
		    context.setTracing(true);
		    
 		    // Start the Camel route
		    System.out.println ("Starting context");
		    context.start();

		    // Wait x minutes, then stop
		    System.out.println ("Begin to wait a bit now");
		    Thread.sleep (60*1*100);
		    System.out.println ("Stopping context");

		    context.stop();
		    }
}
