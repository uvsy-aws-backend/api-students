package app.uvsy.controllers.session.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateSessionPayload {
    private final String deviceId;
    private final String programId;

    public CreateSessionPayload(@JsonProperty(value = "deviceId", required = true) String deviceId,
                                @JsonProperty(value = "programId", required = true) String programId) {
        this.deviceId = deviceId;
        this.programId = programId;
    }
}
