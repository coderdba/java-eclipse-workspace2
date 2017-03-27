package gm.mysql.services;

import gm.mysql.dh.*;

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

@Path("/MySqlService")
public class MySqlService {

	@POST
	@Path("/json/createdb")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes({"application/json", MediaType.APPLICATION_JSON})
	public String createDb(MySqlDatabase mySqlDatabase) throws Exception {

		try {
		// Print what you got from json input
		System.out.println ("In MySqlService - printing json input values - ");
		System.out.println ("Hostname = " + mySqlDatabase.getHostname());
		System.out.println ("Port = " + mySqlDatabase.getPort());
		System.out.println ("Dbname = " + mySqlDatabase.getDbname());
		System.out.println ("Username = " + mySqlDatabase.getUsername());
		System.out.println ("Password = " + mySqlDatabase.getPassword());
		System.out.println ("Completed printing input parameters");
		
		String rootUsername="root";
		String rootPassword="root123_ROOT123";
		mySqlDatabase.createDb(rootUsername, rootPassword);
		return ("createDb - created the db");
		}
		catch (Exception e) {
			System.out.println("Exception in MySqlService");
			System.out.println(e.getLocalizedMessage());
			//e.printStackTrace();
			return ("Exception in MySqlService - " + e.getClass());
		}
	}
}