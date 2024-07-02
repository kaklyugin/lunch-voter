package ru.topjava.lunchvoter.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Vote;
import ru.topjava.lunchvoter.security.SecurityUtil;
import ru.topjava.lunchvoter.service.VoteService;
import ru.topjava.lunchvoter.to.VoteTo;

@RestController
@RequestMapping(value = "/profile/vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    Logger logger = LoggerFactory.getLogger(VoteController.class);
    
    private final VoteService voteService;
    
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }
    
    @GetMapping
    public ResponseEntity<Vote> getActual() {
        logger.info("Get actual vote");
        Vote vote = voteService.getActualUserVote(SecurityUtil.authUserId());
        return vote == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(vote);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote save(@RequestBody VoteTo voteTo) {
        logger.info(String.format("Save vote = %s", voteTo));
        return voteService.save(voteTo, SecurityUtil.authUserId());
    }
    
}
