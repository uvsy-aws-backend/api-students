package app.uvsy.controllers.career.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateCareerPayload {
    private final String programId;
    private final int beginYear;
    private final int endYear;

    public CreateCareerPayload(@JsonProperty(value = "programId", required = true) String programId,
                               @JsonProperty(value = "beginYear", required = true) int beginYear,
                               @JsonProperty(value = "endYear") int endYear) {
        this.programId = programId;
        this.beginYear = beginYear;
        this.endYear = endYear;
    }
}
