package app.uvsy.services;

import app.uvsy.business.subjects.MilestoneValidator;
import app.uvsy.business.subjects.ScoreValidator;
import app.uvsy.business.subjects.Validator;
import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.Milestone;
import app.uvsy.model.Subject;
import app.uvsy.services.exceptions.RecordAlreadyExistsException;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SubjectsService {

    private final List<Validator<Subject>> subjectValidators;

    public SubjectsService() {
        this.subjectValidators = Arrays.asList(
                new MilestoneValidator(),
                new ScoreValidator()
        );
    }


    public List<Subject> getSubjects(String userId, String programId) {
        DynamoDBDAO<Subject> subjectsDao = DynamoDBDAO.createFor(Subject.class);

        Subject subject = new Subject();
        subject.setUserId(userId);
        return subjectsDao.query(subject, Collections.singletonMap("program_id", programId));
    }

    public void createSubject(String userId, String subjectId, String programId,
                              Integer score, List<Milestone> milestones) {

        DynamoDBDAO<Subject> subjectsDao = DynamoDBDAO.createFor(Subject.class);

        Optional<Subject> subjectInDb = subjectsDao.get(userId, subjectId);

        if(!subjectInDb.isPresent()){
            Subject subject = new Subject();
            subject.setUserId(userId);
            subject.setSubjectId(subjectId);
            subject.setProgramId(programId);
            subject.setScore(score);
            subject.setMilestones(milestones);

            subjectValidators.forEach(v -> v.validate(subject));

            subjectsDao.save(subject);
        } else {
            throw new RecordAlreadyExistsException(programId);
        }
    }

    public Subject getSubject(String userId, String subjectId) {
        DynamoDBDAO<Subject> subjectsDao = DynamoDBDAO.createFor(Subject.class);
        return subjectsDao.get(userId, subjectId)
                .orElseThrow(() -> new RecordNotFoundException(userId));
    }

    public void updateSubject(String userId, String subjectId, String programId,
                              Integer score, List<Milestone> milestones) {
        DynamoDBDAO<Subject> subjectsDao = DynamoDBDAO.createFor(Subject.class);

        Subject subject = subjectsDao.get(userId, subjectId).orElseThrow(() -> new RecordNotFoundException(userId));
        subject.setUserId(userId);
        subject.setSubjectId(subjectId);
        subject.setProgramId(programId);
        subject.setScore(score);
        subject.setMilestones(milestones);

        subjectValidators.forEach(v -> v.validate(subject));

        subjectsDao.save(subject);
    }

    public void deleteSubject(String userId, String programId) {
        DynamoDBDAO<Subject> subjectsDao = DynamoDBDAO.createFor(Subject.class);

        Subject subjectsToDelete = new Subject();
        subjectsToDelete.setUserId(userId);
        subjectsToDelete.setSubjectId(programId);
        subjectsDao.delete(subjectsToDelete);
    }
}
