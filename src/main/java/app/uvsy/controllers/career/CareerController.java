package app.uvsy.controllers.career;

import app.uvsy.controllers.career.payload.CreateCareerPayload;
import app.uvsy.controllers.career.payload.UpdateCareerPayload;
import app.uvsy.model.Career;
import app.uvsy.response.Response;
import app.uvsy.services.CareerService;
import org.github.serverless.api.annotations.HttpMethod;
import org.github.serverless.api.annotations.handler.Handler;
import org.github.serverless.api.annotations.parameters.BodyParameter;
import org.github.serverless.api.annotations.parameters.PathParameter;

import java.util.List;

public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    public CareerController() {
        this(new CareerService());
    }


    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/careers")
    public Response<List<Career>> getCareers(@PathParameter(name = "id") String userId) {
        return Response.of(careerService.getCareers(userId));
    }

    @Handler(method = HttpMethod.POST, resource = "/v1/students/{id}/careers")
    public void createCareer(@PathParameter(name = "id") String userId,
                             @BodyParameter CreateCareerPayload payload) {
        careerService.createCareer(
                userId,
                payload.getProgramId(),
                payload.getBeginYear(),
                payload.getEndYear()
        );
    }

    @Handler(method = HttpMethod.GET, resource = "/v1/students/{id}/careers/{programId}")
    public Response<Career> getCareer(@PathParameter(name = "id") String userId,
                                      @PathParameter(name = "programId") String programId) {
        return Response.of(careerService.getCareer(userId, programId));
    }


    @Handler(method = HttpMethod.PUT, resource = "/v1/students/{id}/careers/{programId}")
    public void updateCareer(@PathParameter(name = "id") String userId,
                             @PathParameter(name = "programId") String programId,
                             @BodyParameter UpdateCareerPayload payload) {
        careerService.updateCareer(
                userId,
                programId,
                payload.getBeginYear(),
                payload.getEndYear()
        );
    }

    @Handler(method = HttpMethod.DELETE, resource = "/v1/students/{id}/careers/{programId}")
    public void deleteCareer(@PathParameter(name = "id") String userId,
                             @PathParameter(name = "programId") String programId) {
        careerService.deleteCareer(userId, programId);
    }
}
