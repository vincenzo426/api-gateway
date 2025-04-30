package it.agenda;

import it.agenda.client.ContactClient;
import it.agenda.client.EventClient;
import it.agenda.model.Contact;
import it.agenda.model.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GatewayResource {
    
    @Inject
    @RestClient
    ContactClient contactClient;
    
    @Inject
    @RestClient
    EventClient eventClient;
    
    // Endpoint per i contatti
    @GET
    @Path("/contacts")
    public List<Contact> getAllContacts() {
        return contactClient.getAllContacts();
    }
    
    @GET
    @Path("/contacts/{id}")
    public Response getContactById(@PathParam("id") Long id) {
        try {
            Contact contact = contactClient.getContactById(id);
            return Response.ok(contact).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    @GET
    @Path("/contacts/search")
    public List<Contact> searchContacts(@QueryParam("term") String term) {
        return contactClient.searchContacts(term);
    }
    
    @POST
    @Path("/contacts")
    public Response createContact(Contact contact) {
        try {
            Contact created = contactClient.createContact(contact);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getResponse().readEntity(String.class))
                    .build();
        }
    }
    
    @PUT
    @Path("/contacts/{id}")
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        try {
            Contact updated = contactClient.updateContact(id, contact);
            return Response.ok(updated).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    @DELETE
    @Path("/contacts/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        try {
            contactClient.deleteContact(id);
            return Response.noContent().build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    // Endpoint per gli eventi
    @GET
    @Path("/events")
    public List<Event> getAllEvents() {
        return eventClient.getAllEvents();
    }
    
    @GET
    @Path("/events/{id}")
    public Response getEventById(@PathParam("id") Long id) {
        try {
            Event event = eventClient.getEventById(id);
            return Response.ok(event).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    @GET
    @Path("/events/contact/{contactId}")
    public List<Event> getEventsByContactId(@PathParam("contactId") Long contactId) {
        return eventClient.getEventsByContactId(contactId);
    }
    
    @GET
    @Path("/events/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventClient.getUpcomingEvents();
    }
    
    @GET
    @Path("/events/search")
    public List<Event> searchEvents(@QueryParam("term") String term) {
        return eventClient.searchEvents(term);
    }
    
    @POST
    @Path("/events")
    public Response createEvent(Event event) {
        try {
            Event created = eventClient.createEvent(event);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getResponse().readEntity(String.class))
                    .build();
        }
    }
    
    @PUT
    @Path("/events/{id}")
    public Response updateEvent(@PathParam("id") Long id, Event event) {
        try {
            Event updated = eventClient.updateEvent(id, event);
            return Response.ok(updated).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    @DELETE
    @Path("/events/{id}")
    public Response deleteEvent(@PathParam("id") Long id) {
        try {
            eventClient.deleteEvent(id);
            return Response.noContent().build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    // Endpoint per ottenere i dettagli completi di un contatto con i suoi eventi
    @GET
    @Path("/contacts/{id}/details")
    public Response getContactDetails(@PathParam("id") Long id) {
        try {
            // Ottieni il contatto
            Contact contact = contactClient.getContactById(id);
            
            // Ottieni gli eventi associati al contatto
            List<Event> events = eventClient.getEventsByContactId(id);
            
            // Crea un oggetto per contenere entrambi i dati
            ContactDetailsDTO details = new ContactDetailsDTO();
            details.setContact(contact);
            details.setEvents(events);
            
            return Response.ok(details).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).build();
        }
    }
    
    // Inner class per rappresentare i dettagli di un contatto
    public static class ContactDetailsDTO {
        private Contact contact;
        private List<Event> events;
        
        public Contact getContact() {
            return contact;
        }
        
        public void setContact(Contact contact) {
            this.contact = contact;
        }
        
        public List<Event> getEvents() {
            return events;
        }
        
        public void setEvents(List<Event> events) {
            this.events = events;
        }
    }
}
