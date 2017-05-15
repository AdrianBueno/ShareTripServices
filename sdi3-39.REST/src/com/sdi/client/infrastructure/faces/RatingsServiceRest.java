package com.sdi.client.infrastructure.faces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.client.infrastructure.exceptions.BusinessException;
import com.sdi.client.infrastructure.model.Rating;
import com.sdi.client.infrastructure.model.Trip;
import com.sdi.client.infrastructure.model.User;

@Path("/RatingsServiceRs")
public interface RatingsServiceRest {

	@GET
	@Path("/listLastMonth/{rating}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Rating> listLastMonthRatings() throws BusinessException;

	@DELETE
	@Path("/delete/{rating}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Rating removeRating(@PathParam("rating") Long ratingId)
			throws BusinessException;

	@GET
	@Path("/userAbout/{rating}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User getUserAbout(@PathParam("rating") Long ratingId)
			throws BusinessException;

	@GET
	@Path("/userFrom/{rating}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User getUserFrom(@PathParam("rating") Long ratingId)
			throws BusinessException;

	@GET
	@Path("/tripAbout/{rating}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Trip getTripAbout(@PathParam("rating") Long ratingId)
			throws BusinessException;

	@GET
	@Path("/userFrom/{rating}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Trip getTripFrom(@PathParam("rating") Long ratingId)
			throws BusinessException;

}
