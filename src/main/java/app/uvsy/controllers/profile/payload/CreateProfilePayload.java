package app.uvsy.controllers.profile.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CreateProfilePayload {
    // This attribute may not be needed if the integration with cognito happens
    // When the integration is in place we can get the calling authority from the APIGateway
    // event context.
    private final String userId;
    private final String name;
    private final String lastName;
    private final String alias;

    public CreateProfilePayload(@JsonProperty(value = "userId", required = true) String userId,
                                @JsonProperty(value = "name", required = true) String name,
                                @JsonProperty(value = "lastName", required = true) String lastName,
                                @JsonProperty(value = "alias", required = true) String alias) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.alias = alias;
    }
}
