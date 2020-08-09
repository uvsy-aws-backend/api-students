package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.Session;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.Optional;

public class SessionService {
    public Session getSession(String userId, String deviceId) {

        DynamoDBDAO<Session> sessionDao = DynamoDBDAO.createFor(Session.class);

        Optional<Session> session = sessionDao.get(userId, deviceId);

        if (!session.isPresent()) {
            Session userSession = new Session();
            userSession.setUserId(userId);
            session = sessionDao.query(userSession).stream().findAny();
            session.ifPresent((s) -> {
                s.setDeviceId(deviceId);
                sessionDao.save(s);
            });
        }

        return session.orElseThrow(() -> new RecordNotFoundException(userId));
    }

    public void deleteSession(String userId, String deviceId) {
        DynamoDBDAO<Session> sessionDao = DynamoDBDAO.createFor(Session.class);

        Session session = new Session();
        session.setUserId(userId);
        session.setDeviceId(deviceId);

        sessionDao.delete(session);
    }

    public void updateSession(String userId, String deviceId, String programId) {

        DynamoDBDAO<Session> sessionDao = DynamoDBDAO.createFor(Session.class);

        Session session = sessionDao.get(userId, deviceId)
                .orElseThrow(() -> new RecordNotFoundException(userId));

        session.setProgramId(programId);
        sessionDao.save(session);
    }

    public void createSession(String userId, String deviceId, String programId) {
        DynamoDBDAO<Session> sessionDao = DynamoDBDAO.createFor(Session.class);

        Session session = new Session();
        session.setUserId(userId);
        session.setDeviceId(deviceId);
        session.setProgramId(programId);

        sessionDao.save(session);
    }
}
