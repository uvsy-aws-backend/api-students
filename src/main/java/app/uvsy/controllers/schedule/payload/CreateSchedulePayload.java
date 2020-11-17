package app.uvsy.controllers.schedule.payload;

import app.uvsy.model.SelectedCourse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
public class CreateSchedulePayload {

    private final String userId;
    private final String programId;
    private final String name;
    private final Date beginDate;
    private final Date endDate;
    private final List<SelectedCourse> selectedCourses;

    public CreateSchedulePayload(@JsonProperty(value = "userId", required = true) String userId,
                                 @JsonProperty(value = "programId", required = true) String programId,
                                 @JsonProperty(value = "name", required = true) String name,
                                 @JsonProperty(value = "beginDate", required = true) Date beginDate,
                                 @JsonProperty(value = "endDate", required = true) Date endDate,
                                 @JsonProperty(value = "selectedCourses", required = true) List<SelectedCourse> selectedCourses) {
        this.userId = userId;
        this.programId = programId;
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.selectedCourses = selectedCourses;
    }
}
