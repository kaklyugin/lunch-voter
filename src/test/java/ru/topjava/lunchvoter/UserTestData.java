package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Role;
import ru.topjava.lunchvoter.model.User;

import java.util.Collections;

public class UserTestData {
    
    public static final Integer USER_NOT_EXISTING_ID = 0;
    public static final Integer USER_PETER_ID = 1;
    public static final Integer USER_BORIS_ID = 2;
    public static final User USER_BORIS = new User(USER_BORIS_ID, "Борис", "boris@domain.ru", Collections.singleton(Role.USER));
    
}
