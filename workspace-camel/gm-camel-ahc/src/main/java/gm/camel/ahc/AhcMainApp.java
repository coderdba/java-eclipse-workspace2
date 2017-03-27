package gm.camel.ahc;

import org.apache.camel.CamelContext;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.*;
import org.apache.camel.impl.DefaultCamelContext;

public class AhcMainApp {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		
		Processor processor = new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader(Exchange.HTTP_QUERY, "?hl=en&q=activemq");
			}
		};

		// with google
		System.out.println("\nWith Google");

		Exchange exchange = producerTemplate.send("ahc:http://www.google.com/search", processor);
		//Exchange exchange = producerTemplate.send ("ahc:http://www.google.com/search?q=activemq", processor);

		Message out = exchange.getOut();
		int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		
		
		// with yahoo
		System.out.println("\nWith Yahoo");
		exchange = producerTemplate.send("ahc:http://www.yahoo.com/search", processor);

		out = exchange.getOut();
		responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		
		// with oracle
		System.out.println("\nWith Oracle");
		exchange = producerTemplate.send("ahc:http://www.oracle.com", processor);

		out = exchange.getOut();
		responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		
		// stop and come out
		context.stop();
	}
}
