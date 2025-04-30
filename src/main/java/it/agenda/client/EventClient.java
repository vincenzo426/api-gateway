package it.agenda.client;

import it.agenda.model.Event;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/events")
@RegisterRestClient(configKey = "event-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EventClient {
    
    @GET
    List<Event> getAllEvents();
    
    @GET
    @Path("/{id}")
    Event getEventById(@PathParam("id") Long id);
    
    @GET
    @Path("/contact/{contactId}")
    List<Event> getEventsByContactId(@PathParam("contactId") Long contactId);
    
    @GET
    @Path("/upcoming")
    List<Event> getUpcomingEvents();
    
    @GET
    @Path("/search")
    List<Event> searchEvents(@QueryParam("term") String term);
    
    @POST
    Event createEvent(Event event);
    
    @PUT
    @Path("/{id}")
    Event updateEvent(@PathParam("id") Long id, Event event);
    
    @DELETE
    @Path("/{id}")
    void deleteEvent(@PathParam("id") Long id);
}