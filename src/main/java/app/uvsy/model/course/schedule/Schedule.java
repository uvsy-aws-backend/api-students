package app.uvsy.model.course.schedule;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@DynamoDBDocument
public class Schedule {

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "day_of_week")
    private DayOfWeek dayOfWeek;

    @DynamoDBAttribute(attributeName = "begin_time")
    private int beginTime;

    @DynamoDBAttribute(attributeName = "end_time")
    private int endTime;

    @DynamoDBAttribute(attributeName = "classroom")
    private String classroom;

    public Schedule(@JsonProperty(value = "dayOfWeek", required = true) DayOfWeek dayOfWeek,
                    @JsonProperty(value = "beginTime", required = true) int beginTime,
                    @JsonProperty(value = "endTime", required = true) int endTime,
                    @JsonProperty(value = "classroom", required = true) String classroom) {
        this.dayOfWeek = dayOfWeek;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }
}
