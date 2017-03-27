package gm.rest.example1;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/CustomerService")
public class CustomerService {
	
	@GET
	@Path("/getit/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getCustomer(@PathParam("id") String id) {
		return ("In getCustomer() - Customer id = " + id + "mock GET success message");
	}

	@POST
	@Path("/postit/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String postCustomer(@PathParam("id") String id) {
		return ("In postCustomer() - Customer id = " + id + "mock POST success message");
	}

}
