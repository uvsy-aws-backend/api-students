package app.uvsy.services.exceptions;

import org.github.serverless.api.exceptions.apigw.APIException;

import java.net.HttpURLConnection;

public class RecordAlreadyExistsException extends APIException {


    public RecordAlreadyExistsException(String recordId) {
        super(
                String.format("The record %s already exists", recordId),
                HttpURLConnection.HTTP_CONFLICT
        );
    }
}
