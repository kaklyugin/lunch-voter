package ru.topjava.lunchvoter.security;

public class SecurityUtil {
    private final static Long USER_ID = 1L;
    
    public static Long authUserId() {
        return USER_ID;
    }
}
