package it.agenda.client;

import it.agenda.model.Contact;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/contacts")
@RegisterRestClient(configKey = "contact-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ContactClient {
    
    @GET
    List<Contact> getAllContacts();
    
    @GET
    @Path("/{id}")
    Contact getContactById(@PathParam("id") Long id);
    
    @GET
    @Path("/search")
    List<Contact> searchContacts(@QueryParam("term") String term);
    
    @POST
    Contact createContact(Contact contact);
    
    @PUT
    @Path("/{id}")
    Contact updateContact(@PathParam("id") Long id, Contact contact);
    
    @DELETE
    @Path("/{id}")
    void deleteContact(@PathParam("id") Long id);
}