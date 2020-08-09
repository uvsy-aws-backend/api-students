package app.uvsy.controllers.career.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateCareerPayload {
    private final int beginYear;
    private final int endYear;

    public UpdateCareerPayload(@JsonProperty(value = "beginYear", required = true) int beginYear,
                               @JsonProperty(value = "endYear") int endYear) {
        this.beginYear = beginYear;
        this.endYear = endYear;
    }
}
