package app.uvsy.model;

import app.uvsy.model.course.CoursingPeriod;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DynamoDBDocument
public class SelectedCourse {

    @DynamoDBHashKey(attributeName = "course_id")
    private String courseId;

    @DynamoDBAttribute(attributeName = "commission_id")
    private String commissionId;

    @DynamoDBAttribute(attributeName = "subject_id")
    private String subjectId;

    @DynamoDBAttribute(attributeName = "color")
    private String color;

    @DynamoDBAttribute(attributeName = "coursing_period")
    private CoursingPeriod coursingPeriod;

    public SelectedCourse(@JsonProperty(value = "courseId", required = true) String courseId,
                          @JsonProperty(value = "commissionId", required = true) String commissionId,
                          @JsonProperty(value = "subjectId", required = true) String subjectId,
                          @JsonProperty(value = "color", required = true) String color,
                          @JsonProperty(value = "coursingPeriod", required = true) CoursingPeriod coursingPeriod) {
        this.courseId = courseId;
        this.commissionId = commissionId;
        this.subjectId = subjectId;
        this.color = color;
        this.coursingPeriod = coursingPeriod;
    }
}
