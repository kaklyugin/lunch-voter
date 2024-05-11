package ru.topjava.lunchvoter.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.model.Vote;
import ru.topjava.lunchvoter.repository.DataJpaMenuRepository;
import ru.topjava.lunchvoter.repository.DataJpaVoteRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.lunchvoter.MenuTestData.LUNCH_MENU_DATE;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_MAMA_ITALIA_ID;
import static ru.topjava.lunchvoter.UserTestData.USER_BORIS_ID;
import static ru.topjava.lunchvoter.UserTestData.USER_NOT_EXISTING_ID;
import static ru.topjava.lunchvoter.UserTestData.USER_PETER_ID;
import static ru.topjava.lunchvoter.VoteTestData.*;

@SpringBootTest
@Sql(scripts = "classpath:test-data/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaVoteTest {
    @Autowired
    private DataJpaVoteRepository dataJpaVoteRepository;
    @Autowired
    private DataJpaMenuRepository dataJpaMenuRepository;
    
    @Test
    void save() {
        Vote created = dataJpaVoteRepository.save(getNewVoteBorisGinza(LUNCH_MENU_DATE), USER_BORIS_ID);
        Vote newVote = getNewVoteBorisGinza(LUNCH_MENU_DATE);
        newVote.setId(created.getId());
        assertThat(newVote).isEqualTo(created);
    }
    
    @Test
    void update() {
        Vote vote = dataJpaVoteRepository.getByUserAndDate(USER_PETER_ID, LUNCH_MENU_DATE);
        Menu newMenu = dataJpaMenuRepository.getByRestaurantAndDate(RESTAURANT_MAMA_ITALIA_ID, LUNCH_MENU_DATE);
        vote.setMenu(newMenu);
        dataJpaVoteRepository.save(vote, USER_PETER_ID);
        Vote updatedVote = dataJpaVoteRepository.getByUserAndDate(USER_PETER_ID, LUNCH_MENU_DATE);
        assertThat(updatedVote.getMenu().equals(newMenu));
    }
    
    @Test
    void updateVoteWithNotMatchingMenuDate() {
        Vote created = dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_BORIS_ID);
        Menu newMenu = dataJpaMenuRepository.getByRestaurantAndDate(RESTAURANT_MAMA_ITALIA_ID, VOTE_DATE);
        created.setMenu(newMenu);
        assertThrows(Exception.class, () -> dataJpaVoteRepository.save(created, USER_BORIS_ID));
    }
    
    @Test
    void failToSaveWithNotExistingUser() {
        assertThrows(Exception.class, () -> dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_NOT_EXISTING_ID));
    }
    
    @Test
    void failToUpdateOthersVote() {
        Vote created = dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_BORIS_ID);
        Vote obtained = dataJpaVoteRepository.get(created.getId(), USER_BORIS_ID); // Need to get from DB, otherwise Session exception is thrown
        Vote updated = dataJpaVoteRepository.save(obtained, USER_PETER_ID);
        assertNull(updated);
    }
    
    @Test
    void failToSaveTwoIdenticalVotesAtOneDate() {
        Vote created = dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_BORIS_ID);
        Vote newVote = getNewVoteBorisGinzaAtNewDate();
        newVote.setId(created.getId());
        assertThat(newVote).isEqualTo(created);
        assertThrows(Exception.class, () -> dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_BORIS_ID));
    }
    
    @Test
    void failToSaveTwoVotesForDifferentMenusAtOneDate() {
        Vote created = dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_BORIS_ID);
        Vote newVote = getNewVoteBorisGinzaAtNewDate();
        newVote.setId(created.getId());
        assertThat(newVote).isEqualTo(created);
        assertThrows(Exception.class, () -> dataJpaVoteRepository.save(getNewVoteBorisGinzaAtNewDate(), USER_BORIS_ID));
        assertThrows(Exception.class, () -> dataJpaVoteRepository.save(getNewVoteBorisMenuMamaItalia(), USER_BORIS_ID));
    }
}
