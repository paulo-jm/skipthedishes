package com.skipthedishes.endopoint.customer;

import java.io.Serializable;
import java.net.URI;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.skipthedishes.model.auth.entity.Auth;
import com.skipthedishes.model.customer.entity.Customer;
import com.skipthedishes.service.auth.AuthServiceLocal;
import com.skipthedishes.service.auth.exception.AuthException;
import com.skipthedishes.service.customer.CustomerServiceLocal;



@Path(value = "/Customer")
public class CustomerEndopoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest request;

	@EJB
	private CustomerServiceLocal customerService;

	@EJB
	private AuthServiceLocal authService;

	@POST
	@Path("/auth")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response auth(@Valid Auth auth) throws AuthException {

		authService.auth(auth.getEmail(), auth.getPassword());

		return Response.ok().build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response create(@Valid Customer customer) {

		customerService.create(customer);

		URI uri = uriInfo.getAbsolutePathBuilder().path(customer.getId().toString()).build();
		return Response.created(uri).build();

	}

}
