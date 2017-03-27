package com.gm.scratch1.rest;

// Jersey 1.x
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;

// Jersey 2.x
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Weather
{
    public static void main(String args[])
    {
        try
        {
            Client client = ClientBuilder.newClient();

            WebTarget target = client.target("http://localhost:8080/BasicRestJJ/rest/createjson/post");

            Response response = target.request().post(Entity.json(input));
            
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                 + response.getStatus());
             }
      
            Customer customer = response.readEntity(Customer.class);
            System.out.println("Customer Name : "+customer.getName());
            System.out.println("Customer ID   : "+customer.getId());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}