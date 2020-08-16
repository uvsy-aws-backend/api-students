package app.uvsy.business.subjects;

import app.uvsy.business.subjects.exceptions.InvalidSubjectException;
import app.uvsy.model.Subject;

public class ScoreValidator implements Validator<Subject> {

    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = 1;

    @Override
    public void validate(Subject subject) {
        if (subject.hasScore()) {
            Integer score = subject.getScore();
            if (isScoreBetweenLimits(score)) {
                if (!subject.isApproved()) {
                    throw new InvalidSubjectException("Score provided but subject is not approved");
                }
            } else {
                throw new InvalidSubjectException("Score not valid");
            }
        }
    }

    private boolean isScoreBetweenLimits(Integer score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
}
