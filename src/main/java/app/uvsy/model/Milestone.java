package app.uvsy.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import lombok.Data;

import java.util.Date;

@Data
@DynamoDBDocument
public class Milestone {

    @DynamoDBAttribute(attributeName = "milestone_type")
    private String milestoneType;

    @DynamoDBAttribute(attributeName = "date")
    private Date date;

    @DynamoDBIgnore
    public boolean isApproved() {
        return MilestoneType.APPROVED.equals(milestoneType);
    }
}
