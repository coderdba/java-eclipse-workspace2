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

public class AhcCallMySqlServiceExchangeProcessor {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		
		Processor processor = new Processor() {
			public void process(Exchange exchange) throws Exception {
				
				Message message=exchange.getIn();
				message.setHeader(Exchange.HTTP_METHOD, "POST");	
				message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
				message.setBody("{\"hostname\":\"localhost\", \"port\":\"13306\", \"dbname\":\"mkt\", \"username\":\"mktuser\",\"password\":\"mkt123_MKT123\"}");
				
				System.out.println ("Message body = " + message.getBody(String.class));
				System.out.println ("Message header = " + message.getHeader(Exchange.HTTP_METHOD, String.class));
				System.out.println ("Message header = " + message.getHeader(Exchange.ACCEPT_CONTENT_TYPE, String.class));
			}
		};

		Processor processor1 = new Processor() {
			public void process(Exchange exchange) throws Exception {		
				//
			}
		};
		
		// with google
		System.out.println("\nWith MySqlService");

		Exchange exchange = producerTemplate.send("ahc:http://localhost:8080/gm-mysql-services/MySqlService/json/createdb", processor);
		//Exchange exchange = producerTemplate.send("http://localhost:8080", processor1);

		Message out = exchange.getOut();
		//int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		
		//System.out.println("Response code = " + responseCode);
		System.out.println("Response body = " + out.getBody());
		System.out.println("Response header = " + out.getHeaders());
		
		context.stop();
		System.out.println ("\nContext stopped... coming out");		
	}
}