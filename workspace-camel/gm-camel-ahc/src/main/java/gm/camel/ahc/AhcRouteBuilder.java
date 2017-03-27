package gm.camel.ahc;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/*** A Camel Java DSL Router  */
public class AhcRouteBuilder extends RouteBuilder {

     public void configure() {

    	ProducerTemplate template;
    	
    	Exchange exchange = template.send("ahc:http://www.google.com/search", 
    			new Processor() {
            		public void process(Exchange exchange) throws Exception {
            		exchange.getIn().setHeader(Exchange.HTTP_QUERY, constant("hl=en&q=activemq"));
            		}
    			});
    	
   Message out = exchange.getOut();
   int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);

    }

}
