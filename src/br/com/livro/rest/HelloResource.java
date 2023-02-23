package br.com.livro.rest;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
	@GET
	public String get() {
		return "HTTP GET";
	}
	
	@POST
	public String post(){
		return "HTTP POST";
	}
	
	@PUT
	public String put() {
		return "HTTP PUT";
	}
	
	@DELETE
	public String delete() {
		return "HTTP DELETE";
	}
}
