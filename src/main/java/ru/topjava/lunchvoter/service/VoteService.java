package ru.topjava.lunchvoter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.model.Vote;
import ru.topjava.lunchvoter.repository.DataJpaMenuRepository;
import ru.topjava.lunchvoter.repository.DataJpaVoteRepository;
import ru.topjava.lunchvoter.to.VoteTo;
import ru.topjava.lunchvoter.utils.DateUtils;

import java.time.LocalDate;

@Service
public class VoteService {
    //TODO ADD LOGGER
    
    private final DataJpaVoteRepository dataJpaVoteRepository;
    private final MenuService menuService;
    
    public VoteService(DataJpaVoteRepository dataJpaVoteRepository, DataJpaMenuRepository dataJpaMenuRepository, MenuService menuService) {
        this.dataJpaVoteRepository = dataJpaVoteRepository;
        this.menuService = menuService;
    }
    
    public Vote getActualUserVote(Integer userId) {
        return dataJpaVoteRepository.getByDateAndUserId(DateUtils.getActualMenuDate(), userId);
    }
    
    public Vote save(@RequestBody VoteTo voteTo, Integer userId) {
        menuService.checkMenuIsActual(voteTo.getMenuId());
        return dataJpaVoteRepository.save(createVoteForCurrentDate(voteTo), userId);
    }
    
    private final Vote createVoteForCurrentDate(VoteTo voteTo) {
        Vote vote = new Vote();
        vote.setMenu(new Menu());
        vote.getMenu().setId(voteTo.getMenuId());
        vote.setDate(LocalDate.now());
        return vote;
    }
}
