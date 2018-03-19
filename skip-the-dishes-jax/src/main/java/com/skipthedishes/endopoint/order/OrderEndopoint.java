package com.skipthedishes.endopoint.order;

import java.io.Serializable;
import java.net.URI;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.skipthedishes.model.order.entity.Order;
import com.skipthedishes.model.order.repository.OrderRepositoryLocal;
import com.skipthedishes.service.order.OrderServiceLocal;

@Path(value = "Order")
public class OrderEndopoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest request;

	@EJB
	private OrderServiceLocal orderService;
	
	@EJB
	private OrderRepositoryLocal orderRepository;


	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response create(@NotNull Order order) {

		orderService.createNewOrder(order);

		URI uri = uriInfo.getAbsolutePathBuilder().path(order.getId().toString()).build();
		return Response.created(uri).build();

	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response findAllProductByStoreId(@PathParam("id") @NotNull Long id) {
		return Response.ok().entity(orderRepository.findById(id)).build();
	}
	
	@GET
	@Path("/customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response findOrdersByCustomerId(@PathParam("id") @NotNull Long id) {
		return Response.ok().entity(orderRepository.findOrdersByCustomerId(id)).build();
	}
	
	
	
	

}
