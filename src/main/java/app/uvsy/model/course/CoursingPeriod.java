package app.uvsy.model.course;

import app.uvsy.model.course.professors.Professor;
import app.uvsy.model.course.schedule.Schedule;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.List;

@Data
@NoArgsConstructor
@DynamoDBDocument
public class CoursingPeriod {

    @DynamoDBAttribute(attributeName = "schedules")
    private List<Schedule> schedules;

    @DynamoDBAttribute(attributeName = "professors")
    private List<Professor> professors;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "begin_month")
    private Month beginMonth;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "end_month")
    private Month endMonth;

    public CoursingPeriod(@JsonProperty(value = "schedules", required = true) List<Schedule> schedules,
                          @JsonProperty(value = "professors", required = true) List<Professor> professors,
                          @JsonProperty(value = "beginMonth", required = true) Month beginMonth,
                          @JsonProperty(value = "endMonth", required = true) Month endMonth) {
        this.schedules = schedules;
        this.professors = professors;
        this.beginMonth = beginMonth;
        this.endMonth = endMonth;
    }
}
