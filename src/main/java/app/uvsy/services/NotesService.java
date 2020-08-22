package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.Note;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.List;

public class NotesService {
    public List<Note> getNotes(String userId) {
        DynamoDBDAO<Note> notesDao = DynamoDBDAO.createFor(Note.class);

        Note note = new Note();
        note.setUserId(userId);

        return notesDao.query(note);
    }

    public void createNote(String userId, String title, String description) {

        DynamoDBDAO<Note> notesDao = DynamoDBDAO.createFor(Note.class);

        Note note = new Note();
        note.setUserId(userId);
        note.setTitle(title);
        note.setDescription(description);

        notesDao.save(note);
    }

    public Note getNote(String userId, String noteId) {
        DynamoDBDAO<Note> notesDao = DynamoDBDAO.createFor(Note.class);
        return notesDao.get(userId, noteId)
                .orElseThrow(() -> new RecordNotFoundException(userId));
    }

    public void updateNote(String userId, String noteId, String title, String description) {
        DynamoDBDAO<Note> notesDao = DynamoDBDAO.createFor(Note.class);

        Note note = notesDao.get(userId, noteId).orElseThrow(() -> new RecordNotFoundException(userId));
        note.setUserId(userId);
        note.setNoteId(noteId);
        note.setTitle(title);
        note.setDescription(description);

        notesDao.save(note);
    }

    public void deleteNote(String userId, String noteId) {
        DynamoDBDAO<Note> notesDao = DynamoDBDAO.createFor(Note.class);

        Note notesToDelete = new Note();
        notesToDelete.setUserId(userId);
        notesToDelete.setNoteId(noteId);
        notesDao.delete(notesToDelete);
    }

    public void deleteNotes(String userId, List<String> noteIds) {
        DynamoDBDAO<Note> notesDao = DynamoDBDAO.createFor(Note.class);
        noteIds.stream()
                .map(uuid -> createNote(userId, uuid))
                .forEach(notesDao::delete);
    }

    private Note createNote(String userId, String nodeId){
        Note note = new Note();
        note.setUserId(userId);
        note.setNoteId(nodeId);
        return note;
    }
}
