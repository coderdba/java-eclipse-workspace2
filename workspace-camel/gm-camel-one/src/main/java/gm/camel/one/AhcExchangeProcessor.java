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

public class AhcExchangeProcessor {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		
		Processor processor = new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader(Exchange.HTTP_QUERY, "?hl=en&q=activemq");
			}
		};

		Processor processor2 = new Processor() {
			public void process(Exchange exchange) throws Exception {
				//exchange.getIn().setHeader(Exchange.HTTP_QUERY, "?hl=en&q=activemq");
			}
		};

		// both followRedirects and redirectEnabled seem to work
		Processor processor3 = new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader(Exchange.HTTP_QUERY, 
						"?hl=en&q=activemq&clientConfig.maxRequestRetry=3&clientConfig.redirectEnabled=true");
						//"?hl=en&q=activemq&clientConfig.maxRequestRetry=3&clientConfig.followRedirects=true");
			}
		};
				
		
		// with google
		System.out.println("\nWith Google");

		Exchange exchange = producerTemplate.send("ahc:http://www.google.com/search", processor);
    	//Exchange exchange = producerTemplate.send ("ahc:http://www.google.com/search?hl=en&q=activemq", processor2);

		Message out = exchange.getOut();
		int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		
		// REDIRECTION URL
		String redirectUrl = out.getHeader("Location").toString();
		System.out.println("Redirect URL = " + redirectUrl);
		
		// TBD - need a if-then-else here = do this only if response code means redirection
		System.out.println("\nTrying Redirection");
		exchange = producerTemplate.send (redirectUrl, processor2);

		out = exchange.getOut();
		responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		System.out.println("Response body class = " + out.getBody().getClass());
		System.out.println("Response body string = " + out.getBody().toString());
		System.out.println("Response body string.class = " + out.getBody(String.class));
		
		System.out.println("\n\n");
		
		/*****
		// TRY THE SAME redirection URL
		
		System.out.println("\nAgain Trying the same Redirection URL");
		responseCode=0; //just reset response code to see if exchange really processes again
		out = null; //set previous output to null also
		exchange = producerTemplate.send (redirectUrl, processor2);

		out = exchange.getOut();
		responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response body class = " + out.getBody().getClass());
		System.out.println("Response body string = " + out.getBody().toString());
		System.out.println("Response body string.class = " + out.getBody(String.class));
		System.out.println("Response header = " + out.getHeaders());
		
		System.out.println("\n\n");
		
		// at this point, redirect will be mostly null - either get it and check or ignore and comment out
     	//redirectUrl = out.getHeader("Location").toString();
		//System.out.println("Redirect URL = " + redirectUrl);
	
		// stop and come out
		////context.stop();
		*****/
		
		// Try with REDIRECT DIRECTIVE given to Exchange already
		System.out.println("\n\nTrying with implicit redirection directive in header");
		
		responseCode=0; //just reset response code to see if exchange really processes again
		out = null; //set previous output to null also
		
		////producerTemplate.stop();
		////producerTemplate.start();
		
		exchange = producerTemplate.send("ahc:http://www.google.co.in/search", processor3);
		out = exchange.getOut();
		responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		System.out.println("Response body class = " + out.getBody().getClass());
		System.out.println("Response body string = " + out.getBody().toString());
		System.out.println("Response body string.class = " + out.getBody(String.class));
		System.out.println("Response header = " + out.getHeaders());
		
		context.stop();
		System.out.println ("\nContext stopped... coming out");
		
	}
}
