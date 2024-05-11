package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Vote;

import java.time.LocalDate;

import static ru.topjava.lunchvoter.MenuTestData.LUNCH_MENU_GINZA;
import static ru.topjava.lunchvoter.MenuTestData.LUNCH_MENU_NEW_DATE;
import static ru.topjava.lunchvoter.UserTestData.USER_BORIS;

public class VoteTestData {
    public static final LocalDate VOTE_DATE = LUNCH_MENU_NEW_DATE;
    
    public static Vote getNewVoteBorisGinzaAtNewDate() {
        return getNewVoteBorisGinza(LUNCH_MENU_NEW_DATE);
    }
    
    public static Vote getNewVoteBorisGinza(LocalDate voteDate) {
        return new Vote(USER_BORIS, LUNCH_MENU_GINZA, voteDate);
    }
    
    public static Vote getNewVoteBorisMenuMamaItalia() {
        return new Vote(USER_BORIS, LUNCH_MENU_GINZA, LUNCH_MENU_NEW_DATE);
    }
}
