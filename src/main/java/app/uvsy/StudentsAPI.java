package app.uvsy;

import app.uvsy.controllers.profile.ProfileController;
import org.github.serverless.api.ServerlessApiHandler;

import java.util.List;

public class StudentsAPI extends ServerlessApiHandler {

    @Override
    protected void registerControllers(List<Object> controllers) {
        controllers.add(new ProfileController());
    }
}
