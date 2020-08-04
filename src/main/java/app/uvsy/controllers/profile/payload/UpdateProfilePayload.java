package app.uvsy.controllers.profile.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UpdateProfilePayload {
    private final String name;
    private final String lastName;
    private final String alias;

    public UpdateProfilePayload(@JsonProperty(value = "name", required = true) String name,
                                @JsonProperty(value = "lastName", required = true) String lastName,
                                @JsonProperty(value = "alias", required = true) String alias) {
        this.name = name;
        this.lastName = lastName;
        this.alias = alias;
    }
}
