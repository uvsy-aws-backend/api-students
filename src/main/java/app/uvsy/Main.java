package app.uvsy;

import org.github.serverless.api.ServerlessApiHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("event.json")) {
            ServerlessApiHandler handler = new StudentsAPI();
            OutputStream outputStream = System.out;

            handler.handleRequest(in, outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
