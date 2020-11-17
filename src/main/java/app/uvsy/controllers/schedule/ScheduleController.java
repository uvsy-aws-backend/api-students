package app.uvsy.controllers.schedule;

import app.uvsy.controllers.schedule.payload.CreateSchedulePayload;
import app.uvsy.controllers.schedule.payload.UpdateSchedulePayload;
import app.uvsy.model.ScheduleScratch;
import app.uvsy.response.Response;
import app.uvsy.services.ScheduleService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;
import org.github.serverless.api.annotations.parameters.QueryParameter;

import java.util.List;

public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public ScheduleController() {
        this(new ScheduleService());
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/schedules")
    public Response<List<ScheduleScratch>> getSchedules(@QueryParameter(name = "userId") String userId,
                                                        @QueryParameter(name = "programId") String programId) {
        return Response.of(scheduleService.getSchedules(userId, programId));
    }

    @Handler(method = HttpMethod.POST, resource = "/v1/schedules")
    public void createSchedule(@BodyParameter CreateSchedulePayload payload) {
        scheduleService.createSchedule(
                payload.getUserId(),
                payload.getProgramId(),
                payload.getName(),
                payload.getBeginDate(),
                payload.getEndDate(),
                payload.getSelectedCourses()
        );
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/schedules/{id}")
    public Response<ScheduleScratch> getSchedule(@PathParameter(name = "id") String scheduleId) {
        return Response.of(scheduleService.getSchedule(scheduleId));
    }

    @Handler(method = HttpMethod.PUT, resource = "/v1/schedules/{id}")
    public void createSchedule(@PathParameter(name = "id") String scheduleId,
                               @BodyParameter UpdateSchedulePayload payload) {
        scheduleService.updateSchedule(
                scheduleId,
                payload.getName(),
                payload.getBeginDate(),
                payload.getEndDate(),
                payload.getSelectedCourses()
        );
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/schedules/{id}")
    public void deleteSchedule(@PathParameter(name = "id") String scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

}
