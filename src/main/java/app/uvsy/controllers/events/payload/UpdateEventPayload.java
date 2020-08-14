package app.uvsy.controllers.events.payload;

import app.uvsy.model.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateEventPayload {
    private final String title;
    private final String description;
    private final EventType eventType;
    private final Date date;
    private final int timeTo;
    private final int timeFrom;

    public UpdateEventPayload(@JsonProperty(value = "title") String title,
                              @JsonProperty(value = "description") String description,
                              @JsonProperty(value = "eventType") EventType eventType,
                              @JsonProperty(value = "date")
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy") Date date,
                              @JsonProperty(value = "timeTo") int timeTo,
                              @JsonProperty(value = "timeFrom") int timeFrom) {
        this.title = title;
        this.description = description;
        this.eventType = eventType;
        this.date = date;
        this.timeTo = timeTo;
        this.timeFrom = timeFrom;
    }
}
