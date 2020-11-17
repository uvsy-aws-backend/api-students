package app.uvsy.controllers.schedule.payload;

import app.uvsy.model.SelectedCourse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
public class UpdateSchedulePayload {

    private final String name;
    private final Date beginDate;
    private final Date endDate;
    private final List<SelectedCourse> selectedCourses;

    public UpdateSchedulePayload(@JsonProperty(value = "name", required = true) String name,
                                 @JsonProperty(value = "beginDate", required = true) Date beginDate,
                                 @JsonProperty(value = "endDate", required = true) Date endDate,
                                 @JsonProperty(value = "selectedCourses", required = true) List<SelectedCourse> selectedCourses) {
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.selectedCourses = selectedCourses;
    }
}
