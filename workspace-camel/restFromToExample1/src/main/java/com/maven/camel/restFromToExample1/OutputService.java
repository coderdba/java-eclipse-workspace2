package com.maven.camel.restFromToExample1;

//Jersey 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/OutputService")
public class OutputService {

	@GET
	@Path("/showit")
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.TEXT_PLAIN)
	public String doPostAction() {
		TheObject theObject = new TheObject();
		int id = 1;
		String name = "one";

		theObject.setId(id);
		theObject.setName(name);

		return ("OutputService - Object Id: " + theObject.getId() + ", Name: " + theObject.getName());
	}
}

