package app.uvsy.controllers.subjects;

import app.uvsy.controllers.subjects.payload.CreateSubjectPayload;
import app.uvsy.controllers.subjects.payload.UpdateSubjectPayload;
import app.uvsy.model.Subject;
import app.uvsy.response.Response;
import app.uvsy.services.SubjectsService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.List;

public class SubjectsController {

    private final SubjectsService subjectsService;

    public SubjectsController(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    public SubjectsController() {
        this(new SubjectsService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/subjects")
    public Response<List<Subject>> getSubjects(@PathParameter(name = "id") String userId,
                                               @QueryParameter(name = "programId") String programId) {
        return Response.of(subjectsService.getSubjects(userId, programId));
    }


    @Handler(method = HttpMethod.POST, resource = "/v1/students/{id}/subjects")
    public void createSubject(@PathParameter(name = "id") String userId,
                              @BodyParameter CreateSubjectPayload payload) {
        subjectsService.createSubject(
                userId,
                payload.getSubjectId(),
                payload.getProgramId(),
                payload.getScore(),
                payload.getMilestones()
        );
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/subjects/{subjectId}")
    public Response<Subject> getSubject(@PathParameter(name = "id") String userId,
                                        @PathParameter(name = "subjectId") String subjectId) {
        return Response.of(subjectsService.getSubject(
                userId,
                subjectId
        ));
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/students/{id}/subjects/{subjectId}")
    public void updateSubject(@PathParameter(name = "id") String userId,
                              @PathParameter(name = "subjectId") String subjectId,
                              @BodyParameter UpdateSubjectPayload payload) {
        subjectsService.updateSubject(
                userId,
                subjectId,
                payload.getProgramId(),
                payload.getScore(),
                payload.getMilestones()
        );
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/students/{id}/subjects/{subjectId}")
    public void deleteSubject(@PathParameter(name = "id") String userId,
                              @PathParameter(name = "subjectId") String subjectId) {
        subjectsService.deleteSubject(userId, subjectId);
    }
}
