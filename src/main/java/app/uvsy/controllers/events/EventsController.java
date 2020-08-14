package app.uvsy.controllers.events;

import app.uvsy.controllers.events.payload.CreateEventPayload;
import app.uvsy.controllers.events.payload.UpdateEventPayload;
import app.uvsy.model.Event;
import app.uvsy.response.Response;
import app.uvsy.services.EventsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.Date;
import java.util.List;

public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    public EventsController() {
        this(new EventsService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/events")
    public Response<List<Event>> getEvents(@PathParameter(name = "id") String userId,
                                           @QueryParameter(name = "dateFrom", required = false) Date dateFrom,
                                           @QueryParameter(name = "dateTo", required = false) Date dateTo) {
        return Response.of(eventsService.getEvents(userId, dateFrom, dateTo));
    }


    @Handler(method = HttpMethod.POST, resource = "/v1/students/{id}/events")
    public void createEvent(@PathParameter(name = "id") String userId,
                           @BodyParameter CreateEventPayload payload) {
        eventsService.createEvent(
                userId,
                payload.getTitle(),
                payload.getDescription(),
                payload.getEventType(),
                payload.getDate(),
                payload.getTimeFrom(),
                payload.getTimeTo()
        );
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/events/{eventId}")
    public Response<Event> getEvent(@PathParameter(name = "id") String userId,
                                  @PathParameter(name = "eventId") String eventId) {
        return Response.of(eventsService.getEvent(
                userId,
                eventId
        ));
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/students/{id}/events/{eventId}")
    public void updateEvent(@PathParameter(name = "id") String userId,
                           @PathParameter(name = "eventId") String eventId,
                           @BodyParameter UpdateEventPayload payload) {
        eventsService.updateEvent(
                userId,
                eventId,
                payload.getTitle(),
                payload.getDescription(),
                payload.getEventType(),
                payload.getDate(),
                payload.getTimeFrom(),
                payload.getTimeTo()
        );
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/students/{id}/events/{eventId}")
    public void deleteEvent(@PathParameter(name = "id") String userId,
                           @PathParameter(name = "eventId") String eventId) {
        eventsService.deleteEvent(userId, eventId);
    }
}
