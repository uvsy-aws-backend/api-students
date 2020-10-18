package app.uvsy.controllers.profile;

import app.uvsy.controllers.profile.payload.CreateProfilePayload;
import app.uvsy.controllers.profile.payload.GetAliasPayload;
import app.uvsy.controllers.profile.payload.UpdateProfilePayload;
import app.uvsy.model.Profile;
import app.uvsy.model.UserAlias;
import app.uvsy.response.Response;
import app.uvsy.services.ProfileService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    public ProfileController() {
        this(new ProfileService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/profile/{id}")
    public Response<Profile> getProfile(@PathParameter(name = "id") String userId) {
        return Response.of(profileService.getProfile(userId));
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/alias/availability")
    public void checkAlias(@QueryParameter(name = "user_id") String userId,
                           @QueryParameter(name = "alias") String alias) {
        profileService.checkAlias(userId, alias);
    }

    @Handler(method = HttpMethod.POST, resource = "/v1/alias")
    public Response<List<UserAlias>> getAlias(@BodyParameter GetAliasPayload payload) {
        return Response.of(profileService.getAlias(
                Optional.ofNullable(payload)
                        .map(GetAliasPayload::getUsersIds)
                        .orElseGet(ArrayList::new)
        ));
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
