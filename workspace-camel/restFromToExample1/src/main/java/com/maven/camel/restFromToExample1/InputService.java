package com.maven.camel.restFromToExample1;

// Java
//import java.util.List;

// Java Servlet
//import javax.servlet.http.HttpServletResponse;

// Jersey 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/InputService")
public class InputService {

	@GET
	@Path("/postit")
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

		//return theObject.toString();
		return ("InputService - Object Id: " + theObject.getId() + ", Name: " + theObject.getName());
	}
}
