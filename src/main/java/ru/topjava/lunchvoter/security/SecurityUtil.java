package ru.topjava.lunchvoter.security;

public class SecurityUtil {
    private final static Integer USER_ID = 1;
    
    public static int authUserId() {
        return USER_ID;
    }
}
