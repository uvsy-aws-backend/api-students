package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.ScheduleScratch;
import app.uvsy.model.SelectedCourse;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.Date;
import java.util.List;

public class ScheduleService {

    public List<ScheduleScratch> getSchedules(String userId, String programId) {
        ScheduleScratch schedule = new ScheduleScratch();
        schedule.setUserId(userId);
        schedule.setProgramId(programId);

        DynamoDBDAO<ScheduleScratch> scheduleDAO = DynamoDBDAO.createFor(ScheduleScratch.class);
        return scheduleDAO.query(schedule, ScheduleScratch.USER_PROGRAM_INDEX);
    }

    public void createSchedule(String userId, String programId, String name,
                               Date beginDate, Date endDate, List<SelectedCourse> courses) {

        ScheduleScratch schedule = new ScheduleScratch();
        schedule.setUserId(userId);
        schedule.setProgramId(programId);
        schedule.setName(name);
        schedule.setBeginDate(beginDate);
        schedule.setEndDate(endDate);
        schedule.setSelectedCourses(courses);

        DynamoDBDAO<ScheduleScratch> scheduleDAO = DynamoDBDAO.createFor(ScheduleScratch.class);
        scheduleDAO.save(schedule);
    }

    public ScheduleScratch getSchedule(String scheduleId) {
        DynamoDBDAO<ScheduleScratch> scheduleDAO = DynamoDBDAO.createFor(ScheduleScratch.class);
        return scheduleDAO.get(scheduleId).orElseThrow(() -> new RecordNotFoundException(scheduleId));
    }

    public void updateSchedule(String scheduleId, String name, Date beginDate, Date endDate, List<SelectedCourse> selectedCourses) {
        DynamoDBDAO<ScheduleScratch> scheduleDAO = DynamoDBDAO.createFor(ScheduleScratch.class);

        ScheduleScratch schedule = scheduleDAO.get(scheduleId)
                .orElseThrow(() -> new RecordNotFoundException(scheduleId));

        schedule.setName(name);
        schedule.setBeginDate(beginDate);
        schedule.setEndDate(endDate);
        schedule.setSelectedCourses(selectedCourses);

        scheduleDAO.save(schedule);
    }

    public void deleteSchedule(String scheduleId) {
        DynamoDBDAO<ScheduleScratch> scheduleDAO = DynamoDBDAO.createFor(ScheduleScratch.class);
        scheduleDAO.get(scheduleId).ifPresent(scheduleDAO::delete);
    }
}
