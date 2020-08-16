package app.uvsy.business.subjects;

import app.uvsy.business.subjects.exceptions.InvalidSubjectException;
import app.uvsy.model.Milestone;
import app.uvsy.model.MilestoneType;
import app.uvsy.model.Subject;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MilestoneValidator implements Validator<Subject> {

    private static final int ONE_TIME = 1;

    @Override
    public void validate(Subject studentSubject) {
        studentSubject.getMilestones()
                .parallelStream()
                .map(Milestone::getMilestoneType)
                .filter(this::isValidMilestone)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .parallelStream()
                .filter(freq -> freq > ONE_TIME)
                .findAny()
                .ifPresent(this::throwRepeatedMilestoneException);
    }

    private boolean isValidMilestone(String milestone) {
        return Stream.of(MilestoneType.APPROVED, MilestoneType.REGULAR, MilestoneType.TAKING)
                .filter(milestoneType -> milestoneType.equals(milestone))
                .findAny()
                .map(o -> Boolean.TRUE)
                .orElseThrow(() -> new InvalidSubjectException("Invalid Milestone"));
    }

    private void throwRepeatedMilestoneException(@SuppressWarnings("unused") long value) {
        throw new InvalidSubjectException("Repeated milestones found");
    }
}
