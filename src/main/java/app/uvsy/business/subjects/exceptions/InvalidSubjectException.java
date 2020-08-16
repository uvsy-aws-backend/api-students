package app.uvsy.business.subjects.exceptions;

import org.github.serverless.api.exceptions.apigw.APIException;

import java.net.HttpURLConnection;

public class InvalidSubjectException extends APIException {
    public InvalidSubjectException(String message) {
        super(message, HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
