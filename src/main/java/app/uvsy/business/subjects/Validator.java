package app.uvsy.business.subjects;

public interface Validator<T> {
    void validate(T object);
}
