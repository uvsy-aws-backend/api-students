package app.uvsy.controllers.subjects.payload;

import app.uvsy.model.Milestone;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateSubjectPayload {

    private String programId;
    private Integer score;
    private List<Milestone> milestones;

    public UpdateSubjectPayload(@JsonProperty(value = "programId", required = true) String programId,
                                @JsonProperty(value = "score") Integer score,
                                @JsonProperty(value = "milestones") List<Milestone> milestones) {
        this.programId = programId;
        this.score = score;
        this.milestones = milestones;
    }
}
