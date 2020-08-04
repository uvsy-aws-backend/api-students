package app.uvsy.controllers.profile;

import app.uvsy.controllers.profile.payload.CreateProfilePayload;
import app.uvsy.controllers.profile.payload.UpdateProfilePayload;
import app.uvsy.model.Profile;
import app.uvsy.response.Response;
import app.uvsy.services.ProfileService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public ProfileController() {
        this(new ProfileService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/profile/{id}")
    public Response<Profile> getStudentProfile(@PathParameter(name = "id") String userId) {
        return Response.of(profileService.getProfile(userId));
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/alias/availability")
    public void checkAlias(@QueryParameter(name = "user_id") String userId,
                           @QueryParameter(name = "alias") String alias) {
        profileService.checkAlias(userId, alias);
    }


    @Handler(method = HttpMethod.POST, resource = "/v1/profile")
    public void createProfile(@BodyParameter CreateProfilePayload payload) {
        profileService.createProfile(
                payload.getUserId(),
                payload.getName(),
                payload.getLastName(),
                payload.getAlias()
        );
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/profile/{id}")
    public void updateProfile(@PathParameter(name = "id") String userId, @BodyParameter UpdateProfilePayload payload) {
        profileService.updateProfile(
                userId,
                payload.getName(),
                payload.getLastName(),
                payload.getAlias()
        );
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/profile/{id}")
    public void deleteProfile(@PathParameter(name = "id") String userId) {
        profileService.deleteProfile(userId);
    }
}
