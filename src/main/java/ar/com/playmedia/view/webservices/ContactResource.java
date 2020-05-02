package ar.com.playmedia.view.webservices;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;

import org.json.*;


@Path("/product")
public class ContactResource {
	private ar.com.playmedia.controller.Product handler = new ar.com.playmedia.controller.Product();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search (
		@QueryParam("surname") @DefaultValue("%") String surname
	) {
		ArrayList<ar.com.playmedia.model.Product> contactList;

		handler.connect();
		contactList = handler.search(surname);
		handler.disconnect();
		
		JSONArray contacts = new JSONArray();

		for(ar.com.playmedia.model.Product contactIterator : contactList) {
			JSONObject contact = new JSONObject();

			contact.put("id", contactIterator.getId());
			contact.put("description", contactIterator.getDescription());
			contact.put("platform", contactIterator.getPlatform());
			contact.put("price", contactIterator.getPrice());
			contact.put("quantity", contactIterator.getQuantity());
			contact.put("category", contactIterator.getCategory());
			contacts.put(contact);
		}

		return Response.status(Status.OK).entity(contacts.toString()).build();
	}


}