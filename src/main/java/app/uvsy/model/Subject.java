package app.uvsy.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@DynamoDBTable(tableName = "student-subjects")
public class Subject {

    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    @DynamoDBRangeKey(attributeName = "subject_id")
    private String subjectId;

    @DynamoDBAttribute(attributeName = "program_id")
    private String programId;

    @DynamoDBAttribute(attributeName = "score")
    private Integer score;

    @DynamoDBAttribute(attributeName = "milestones")
    private List<Milestone> milestones;

    @DynamoDBIgnore
    public boolean isApproved() {
        return milestones.stream()
                .anyMatch(Milestone::isApproved);
    }

    @DynamoDBIgnore
    public boolean hasScore() {
        return Optional.ofNullable(score).isPresent();
    }

}
