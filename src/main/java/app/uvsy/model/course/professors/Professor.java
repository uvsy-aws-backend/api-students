package app.uvsy.model.course.professors;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DynamoDBDocument
public class Professor {

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "last_name")
    private String lastName;

    public Professor(@JsonProperty(value = "name", required = true) String name,
                     @JsonProperty(value = "lastName", required = true) String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
