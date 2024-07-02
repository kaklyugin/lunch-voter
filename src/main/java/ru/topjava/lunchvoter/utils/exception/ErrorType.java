package ru.topjava.lunchvoter.utils.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    
    VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, "Ошибка валидации"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Некорректный запрос"),
    CONSTRAINT_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, "Некорректный запрос");
    
    private final HttpStatus status;
    private final String userDescription;
    
    ErrorType(HttpStatus status, String userDescription) {
        this.status = status;
        this.userDescription = userDescription;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public String getUserDescription() {
        return userDescription;
    }
}
