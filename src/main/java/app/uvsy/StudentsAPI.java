package app.uvsy;

import app.uvsy.controllers.career.CareerController;
import app.uvsy.controllers.events.EventsController;
import app.uvsy.controllers.notes.NotesController;
import app.uvsy.controllers.profile.ProfileController;
import app.uvsy.controllers.subjects.SubjectsController;
import org.github.serverless.api.ServerlessApiHandler;

import java.util.List;

public class StudentsAPI extends ServerlessApiHandler {

    @Override
    protected void registerControllers(List<Object> controllers) {
        controllers.add(new ProfileController());
        controllers.add(new CareerController());
        controllers.add(new SubjectsController());
        controllers.add(new NotesController());
        controllers.add(new EventsController());
    }
}
