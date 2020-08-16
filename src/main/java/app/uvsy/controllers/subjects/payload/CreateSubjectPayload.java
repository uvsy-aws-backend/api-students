package app.uvsy.controllers.subjects.payload;

import app.uvsy.model.Milestone;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateSubjectPayload {
    private String subjectId;
    private String programId;
    private Integer score;
    private List<Milestone> milestones;

    public CreateSubjectPayload(@JsonProperty(value = "subjectId", required = true) String subjectId,
                                @JsonProperty(value = "programId", required = true) String programId,
                                @JsonProperty(value = "score") Integer score,
                                @JsonProperty(value = "milestones") List<Milestone> milestones) {
        this.subjectId = subjectId;
        this.programId = programId;
        this.score = score;
        this.milestones = milestones;
    }
}
