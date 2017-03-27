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

public class UnmarshalJsonToPOJO  // unmarshals json input
{
    public static void main(String args[])
    {
        try
        {
            String input = "{\"name\": \"customer1\",\"id\": 1009}";

            //Jersey 1.x
            //Client client = Client.create();
            Client client = ClientBuilder.newClient();

            //Jersey 1.x
            //WebResource resource = client.resource("http://localhost:8080/BasicRestJJ/rest/createjson/post");
            WebTarget target = client.target("http://localhost:8080/BasicRestJJ/rest/createjson/post");

            //Jersey 1.x
            //ClientResponse response = resource.type("application/json").post(ClientResponse.class, input);
            Response response = target.request().post(Entity.json(input));
            
            
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                 + response.getStatus());
             }
      
            //Jersey 1.x
            //Customer customer = response.getEntity(Customer.class);
            Customer customer = response.readEntity(Customer.class);
            System.out.println("Customer Name : "+customer.getName());
            System.out.println("Customer ID   : "+customer.getId());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}