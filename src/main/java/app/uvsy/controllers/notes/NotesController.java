package app.uvsy.controllers.notes;

import app.uvsy.controllers.notes.payload.CreateNotePayload;
import app.uvsy.controllers.notes.payload.UpdateNotePayload;
import app.uvsy.model.Note;
import app.uvsy.response.Response;
import app.uvsy.services.NotesService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.List;

public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    public NotesController() {
        this(new NotesService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/notes")
    public Response<List<Note>> getNotes(@PathParameter(name = "id") String userId) {
        return Response.of(notesService.getNotes(userId));
    }


    @Handler(method = HttpMethod.POST, resource = "/v1/students/{id}/notes")
    public void createNote(@PathParameter(name = "id") String userId,
                           @BodyParameter CreateNotePayload payload) {
        notesService.createNote(
                userId,
                payload.getTitle(),
                payload.getDescription()
        );
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/notes/{noteId}")
    public Response<Note> getNote(@PathParameter(name = "id") String userId,
                                  @PathParameter(name = "noteId") String noteId) {
        return Response.of(notesService.getNote(
                userId,
                noteId
        ));
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/students/{id}/notes/{noteId}")
    public void updateNote(@PathParameter(name = "id") String userId,
                           @PathParameter(name = "noteId") String noteId,
                           @BodyParameter UpdateNotePayload payload) {
        notesService.updateNote(
                userId,
                noteId,
                payload.getTitle(),
                payload.getDescription()
        );
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/students/{id}/notes/{noteId}")
    public void deleteNote(@PathParameter(name = "id") String userId,
                           @PathParameter(name = "noteId") String noteId) {
        notesService.deleteNote(userId, noteId);
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/students/{id}/notes")
    public void batchDelete(@PathParameter(name = "id") String userId,
                            @QueryParameter(name = "noteId") List<String> noteIds) {
        notesService.deleteNotes(userId, noteIds);
    }
}
