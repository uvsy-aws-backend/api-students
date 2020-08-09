package app.uvsy.services;

import app.uvsy.database.DynamoDBDAO;
import app.uvsy.model.Profile;
import app.uvsy.services.exceptions.RecordAlreadyExistsException;
import app.uvsy.services.exceptions.RecordNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProfileService {
    public Profile getProfile(String userId) {
        DynamoDBDAO<Profile> profileDao = DynamoDBDAO.createFor(Profile.class);
        return profileDao.get(userId).orElseThrow(() -> new RecordNotFoundException(userId));
    }

    public void createProfile(String userId, String name, String lastName, String alias) {

        // TODO: For further validations and API resilience this service could:
        // - Check for format and types of its parameters
        // (actually this should be done in the controller with Jackson)
        // - Check if the userId exists in Cognito

        DynamoDBDAO<Profile> profileDao = DynamoDBDAO.createFor(Profile.class);

        Optional<Profile> profileGet = profileDao.get(userId);
        if (!profileGet.isPresent()) {
            this.checkAlias(userId, alias);
            Profile profile = new Profile();
            profile.setUserId(userId);
            profile.setName(name);
            profile.setLastName(lastName);
            profile.setAlias(alias);
            profileDao.save(profile);
        } else {
            throw new RecordAlreadyExistsException(userId);
        }
    }

    public void updateProfile(String userId, String name, String lastName, String alias) {

        // TODO: For further validations and API resilience this service could:
        // - Check for format and types of its parameters
        // - Check if the userId exists in Cognito

        DynamoDBDAO<Profile> profileDao = DynamoDBDAO.createFor(Profile.class);

        Profile profileInDB = profileDao.get(userId).orElseThrow(() -> new RecordNotFoundException(userId));

        if (!alias.equals(profileInDB.getAlias())) {
            this.checkAlias(userId, alias);
        }

        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setName(name);
        profile.setLastName(lastName);
        profile.setAlias(alias);
        profileDao.save(profile);

    }

    public void deleteProfile(String userId) {
        DynamoDBDAO<Profile> profileDao = DynamoDBDAO.createFor(Profile.class);

        Profile profileToDelete = new Profile();
        profileToDelete.setUserId(userId);
        profileDao.delete(profileToDelete);
    }

    public void checkAlias(String userId, String alias) {
        DynamoDBDAO<Profile> profileDao = DynamoDBDAO.createFor(Profile.class);

        Profile profileWithAlias = new Profile();
        profileWithAlias.setAlias(alias);
        List<Profile> aliasQuery = profileDao.query(
                profileWithAlias,
                "AliasIndex",
                Collections.singletonMap("user_id", userId),
                false
        );

        if (!aliasQuery.isEmpty()) {
            throw new RecordAlreadyExistsException(alias);
        }
    }

}
