package gm.camel.two;

import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.*;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.http.common.HttpMethods;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;

public class MySqlServiceCallExchangeProcessor {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		
		Processor processor = new Processor() {
			public void process(Exchange exchange) throws Exception {
				
				Message message=exchange.getIn();
				
				//OK
				message.setHeader(Exchange.HTTP_METHOD, "POST");
				//OK
				//message.setHeader(Exchange.HTTP_METHOD, org.apache.camel.component.http.HttpMethods.POST.name());
				
				//NOT OK
     			//message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
				
				//OK
				message.setHeader("Content-Type", "application/json");
				
				//NOT REQUIRED
     			//message.setHeader(Exchange.CHARSET_NAME, "utf8");
				
				//OK
				message.setBody("{\"hostname\":\"localhost\", \"port\":\"13306\", \"dbname\":\"mkt\", \"username\":\"mktuser\",\"password\":\"mkt123_MKT123\"}");
				
				System.out.println ("Message body   = " + message.getBody(String.class));
				System.out.println ("Message header = " + message.getHeader(Exchange.HTTP_METHOD, String.class));
				System.out.println ("message header Exchange.ACCEPT_CONTENT_TYPE means " + Exchange.ACCEPT_CONTENT_TYPE);
				System.out.println ("Message header = " + message.getHeader(Exchange.ACCEPT_CONTENT_TYPE, String.class));
				System.out.println ("Message header = " + message.getHeader("Content-Type", String.class));
				System.out.println ("Message header = " + message.getHeader(Exchange.CHARSET_NAME, String.class));
			}
		};

		// Dummy processor if necessary for testing
		Processor processor1 = new Processor() {
			public void process(Exchange exchange) throws Exception {		
				//
			}
		};
		
		System.out.println("\nCalling MySqlService");
		
	    context.setTracing(true);
	    
		// Start the Camel route - no need if using template and exchange
	    //System.out.println ("Starting context");
	    //context.start();

		Exchange exchange = producerTemplate.send("http://localhost:8080/gm-mysql-services/MySqlService/json/createdb", processor);
		//Exchange exchange = producerTemplate.send("http://localhost:8080", processor1);

		Message out = exchange.getOut();
		int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody(String.class));
		System.out.println("Response header = " + out.getHeaders());
		
		context.stop();
		System.out.println ("\nContext stopped... coming out");		
	}
}