package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.Career;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

public class CareerService {
    public Career getCareer(String userId, String programId) {
        DynamoDBDAO<Career> careerDao = DynamoDBDAO.createFor(Career.class);
        Optional<Career> career = careerDao.get(userId, programId);
        return career.orElseThrow(() -> new RecordNotFoundException(userId));
    }

    public List<Career> getCareers(String userId) {
        DynamoDBDAO<Career> careerDao = DynamoDBDAO.createFor(Career.class);
        Career career = new Career();
        career.setUserId(userId);
        return careerDao.query(career);
    }

    public void deleteCareer(String userId, String programId) {
        DynamoDBDAO<Career> careerDao = DynamoDBDAO.createFor(Career.class);

        Career career = new Career();
        career.setUserId(userId);
        career.setProgramId(programId);

        careerDao.delete(career);
    }

    public void updateCareer(String userId, String programId, Integer beingYear, Integer endYear) {
        DynamoDBDAO<Career> careerDao = DynamoDBDAO.createFor(Career.class);

        Career career = careerDao.get(userId, programId)
                .orElseThrow(() -> new RecordNotFoundException(userId));

        career.setBeginYear(beingYear);
        career.setEndYear(endYear);
        careerDao.save(career);
    }

    public void createCareer(String userId, String programId, Integer beingYear, Integer endYear) {
        DynamoDBDAO<Career> careerDao = DynamoDBDAO.createFor(Career.class);

        Career career = new Career();
        career.setUserId(userId);
        career.setProgramId(programId);
        career.setBeginYear(beingYear);
        career.setEndYear(endYear);

        careerDao.save(career);
    }
}
