package app.uvsy.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@DynamoDBTable(tableName = "student-profile")
public class Profile {

    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "last_name")
    private String lastName;

    @DynamoDBAttribute(attributeName = "alias")
    @DynamoDBIndexHashKey(attributeName = "alias", globalSecondaryIndexName = "AliasIndex")
    private String alias;

}
