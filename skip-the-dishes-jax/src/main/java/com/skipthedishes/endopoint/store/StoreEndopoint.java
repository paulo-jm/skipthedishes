package com.skipthedishes.endopoint.store;

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

import com.skipthedishes.model.product.repository.ProductRepositoryLocal;
import com.skipthedishes.model.store.repository.StoreRepositoryLocal;

@Path(value = "Store")
public class StoreEndopoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private StoreRepositoryLocal storeRepository;
	
	@EJB
	private ProductRepositoryLocal productRepository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findALl() {
		return Response.ok().entity(storeRepository.findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response findById(@PathParam("id") @NotNull Long id) {
		return Response.ok().entity(storeRepository.findById(id)).build();
	}
	
	@GET
	@Path("/{id}/products")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response findAllProductByStoreId(@PathParam("id") @NotNull Long id) {
		return Response.ok().entity(productRepository.findAllProductsByStoreId(id)).build();
	}
	
	
	@GET
	@Path("/search/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response search(@PathParam("text") @NotBlank String text) {
		return Response.ok().entity(storeRepository.search(text)).build();
	}

}
