package com.maven.webapp;

//Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//Extend HttpServlet class
public class HelloWorld extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "Hello, I am ";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if ("asis".equals(request.getParameter("modifyValues"))) {

			// Set response content type
			response.setContentType("text/html");

			// Actual logic goes here.
			PrintWriter out = response.getWriter();
			out.println("<h1>" + message + " "
					+ request.getParameter("first_name") + " "
					+ request.getParameter("last_name") + "<br><br>"
					+ getLikes(request) + "</h1>");

		} else {

			doPost(request, response);

		}

	}

	// Method to handle POST method request.
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");

		if ("uppercase".equals(request.getParameter("modifyValues"))) {
			firstName = firstName.toUpperCase();
			lastName = lastName.toUpperCase();
		} else if ("lowercase".equals(request.getParameter("modifyValues"))) {
			firstName = firstName.toLowerCase();
			lastName = lastName.toLowerCase();

		}
		// As this is POST, let us do some small modification to input values
		// (say, convert to upper case)
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + " " + firstName + " " + lastName + "<br><br>"
				+ getLikes(request) + "</h1>");

	}

	public void destroy() {
		// do nothing.
	}
	
	private String getLikes(HttpServletRequest request) {
		
		String[] likes = request.getParameterValues("likes");
		StringBuffer likesMessage = new StringBuffer();
		
		likesMessage.append("I like these subjects: ");
		
		for (String s : likes) {
			
			likesMessage.append(s);
			likesMessage.append(" ");
		}
		
		return likesMessage.toString();
      
	}	
}
