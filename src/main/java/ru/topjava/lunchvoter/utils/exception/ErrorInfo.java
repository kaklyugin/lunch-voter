package ru.topjava.lunchvoter.utils.exception;

public class ErrorInfo {
    private final String url;
    private final String details[];
    
    public ErrorInfo(String url, String[] details) {
        this.url = url;
        this.details = details;
    }
}
