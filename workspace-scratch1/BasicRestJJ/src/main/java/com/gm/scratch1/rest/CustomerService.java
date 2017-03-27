package com.gm.scratch1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/createjson")
public class CustomerService
{
    @GET
    @Path("/get")
    @Produces("application/json")
    public Customer getCustomerInJSON(@QueryParam("username") String name, @QueryParam("id") int id)
    {
        Customer cust = new Customer();
        cust.setName(name);
        cust.setId(id);
        return cust;
    }
    
    
    // Accept JSON and return Customer POJO in the response
    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createCustomerJSON(Customer customer)
    {
        //String result = "Saved Customer : " + customer;
        System.out.println("Post before response: " + customer.getName() + "  " + customer.getId());

        return Response.status(200).entity(customer).build(); // USE MOXY - WITH JACKSON IT GIVES HTTP 500
        //return Response.ok().entity(customer).build();
        //return Response.ok("response ok").build();
    }
    
    @POST
    @Path("/post2")
    @Consumes("application/json")
    @Produces("application/json")
    public Customer createCustomerJSON2(Customer customer)
    {
        System.out.println("Post2: " + customer.getName() + "  " + customer.getId());
        return customer;
    } 
    
    @POST
    @Path("/post3")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createCustomerJSON3(Customer customer)
    {
        System.out.println("Post3: " + customer.getName() + "  " + customer.getId());
        return Response.status(200).entity(customer).build();
    } 
}