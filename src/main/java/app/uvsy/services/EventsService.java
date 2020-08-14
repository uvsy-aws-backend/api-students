package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.Event;
import app.uvsy.model.EventType;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventsService {
    public List<Event> getEvents(String userId, Date dateFrom, Date dateTo) {
        DynamoDBDAO<Event> eventsDao = DynamoDBDAO.createFor(Event.class);

        Event event = new Event();
        event.setUserId(userId);

        return eventsDao.query(event)
                .stream()
                .filter(e -> e.belongsToRange(dateFrom, dateTo))
                .collect(Collectors.toList());
    }

    public void createEvent(String userId, String title, String description,
                            EventType eventType, Date date, int timeFrom, int timeTo) {

        DynamoDBDAO<Event> eventsDao = DynamoDBDAO.createFor(Event.class);

        Event event = new Event();
        event.setUserId(userId);
        event.setTitle(title);
        event.setDescription(description);
        event.setEventType(eventType);
        event.setDate(date);
        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        eventsDao.save(event);
    }

    public Event getEvent(String userId, String eventId) {
        DynamoDBDAO<Event> eventsDao = DynamoDBDAO.createFor(Event.class);
        return eventsDao.get(userId, eventId)
                .orElseThrow(() -> new RecordNotFoundException(userId));
    }

    public void updateEvent(String userId, String eventId, String title, String description,
                            EventType eventType, Date date, int timeFrom, int timeTo) {
        DynamoDBDAO<Event> eventsDao = DynamoDBDAO.createFor(Event.class);

        Event event = eventsDao.get(userId, eventId).orElseThrow(() -> new RecordNotFoundException(userId));
        event.setUserId(userId);
        event.setTitle(title);
        event.setDescription(description);
        event.setEventType(eventType);
        event.setDate(date);
        event.setTimeFrom(timeFrom);
        event.setTimeTo(timeTo);

        eventsDao.save(event);
    }

    public void deleteEvent(String userId, String nodeId) {
        DynamoDBDAO<Event> eventsDao = DynamoDBDAO.createFor(Event.class);

        Event eventsToDelete = new Event();
        eventsToDelete.setUserId(userId);
        eventsToDelete.setEventId(nodeId);
        eventsDao.delete(eventsToDelete);
    }
}
