package com.maven.camel.FileCopier;

import org.apache.camel.*;
import org.apache.camel.impl.*;
import org.apache.camel.builder.*;

public class FileCopier
  {
  public static void main(String args[]) throws Exception
    {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder()
      {
      public void configure()
        {
        // Set up the route — from the intput directory, to
        //  the output directory, with no other processing
        from ("file:/tmp/in?noop=true").to("file:/tmp/out");
        }
      });
    //
    // Start the Camel route
    context.start();

    // Wait five minutes, then stop
    Thread.sleep (60*5*1000);
    context.stop ();
    }
  }