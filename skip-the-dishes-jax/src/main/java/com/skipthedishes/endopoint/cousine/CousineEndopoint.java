package com.skipthedishes.endopoint.cousine;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.skipthedishes.model.cousine.repository.CousineRepositoryLocal;

@Path(value = "Cousine")
public class CousineEndopoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private CousineRepositoryLocal cousineRepository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findALl() {
		return Response.ok().entity(cousineRepository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response findById(@PathParam("id") @NotNull Long id) {
		return Response.ok().entity(cousineRepository.findById(id)).build();
	}
	
	@GET
	@Path("/search/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response search(@PathParam("text") @NotBlank String text) {
		return Response.ok().entity(cousineRepository.search(text)).build();
	}

}
