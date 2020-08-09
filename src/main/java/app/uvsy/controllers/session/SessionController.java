package app.uvsy.controllers.session;

import app.uvsy.controllers.session.payload.CreateSessionPayload;
import app.uvsy.controllers.session.payload.UpdateSessionPayload;
import app.uvsy.model.Session;
import app.uvsy.response.Response;
import app.uvsy.services.SessionService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;

public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SessionController() {
        this(new SessionService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/student/{id}/sessions/{deviceId}")
    public Response<Session> getSession(@PathParameter(name = "id") String userId,
                                        @PathParameter(name = "deviceId") String deviceId) {
        return Response.of(sessionService.getSession(userId, deviceId));
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/student/{id}/sessions/{deviceId}")
    public void deleteSession(@PathParameter(name = "id") String userId,
                              @PathParameter(name = "deviceId") String deviceId) {
        sessionService.deleteSession(userId, deviceId);
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/student/{id}/sessions")
    public void updateSession(@PathParameter(name = "id") String userId,
                              @BodyParameter UpdateSessionPayload payload) {
        sessionService.updateSession(
                userId,
                payload.getDeviceId(),
                payload.getProgramId()
        );
    }

    @Handler(method = HttpMethod.POST, resource = "/v1/student/{id}/sessions")
    public void createSession(@PathParameter(name = "id") String userId,
                              @BodyParameter CreateSessionPayload payload) {
        sessionService.createSession(
                userId,
                payload.getDeviceId(),
                payload.getProgramId()
        );
    }
}
