package app.uvsy.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Response<T> implements Serializable {

    @JsonProperty(value = "data")
    private final T data;

    private Response(T data) {
        this.data = data;
    }

    public static <R> Response<R> of(R object) {
        return new Response<>(object);
    }
}
