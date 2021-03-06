package gm.camel.one;

import org.apache.camel.*;
import org.apache.camel.impl.*;
import org.apache.camel.builder.*;

public class FileToStreamOut
  {
  public static void main(String args[]) throws Exception
    {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder()
      {
      public void configure()
        {
        //from ("file:/tmp/in?noop=true").to("file:/tmp/out");
    	  from ("file:/tmp/in?noop=true").to("stream:out");
        }
      });
    
    // Start the Camel route
    context.start();

    // Wait five minutes, then stop
    Thread.sleep (60*1*1000);
    context.stop ();
    }
  }
