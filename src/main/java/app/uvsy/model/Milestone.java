package app.uvsy.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@DynamoDBDocument
public class Milestone {

    @DynamoDBAttribute(attributeName = "milestone_type")
    private String milestoneType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
    @DynamoDBAttribute(attributeName = "date")
    private Date date;

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isApproved() {
        return MilestoneType.APPROVED.equals(milestoneType);
    }
}
