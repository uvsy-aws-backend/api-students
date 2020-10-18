package app.uvsy.controllers.profile.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GetAliasPayload {

    private final List<String> usersIds;

    public GetAliasPayload(@JsonProperty(value = "usersIds") List<String> usersIds) {
        this.usersIds = usersIds;
    }
}
