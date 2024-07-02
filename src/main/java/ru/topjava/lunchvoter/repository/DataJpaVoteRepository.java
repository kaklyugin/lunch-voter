package ru.topjava.lunchvoter.repository;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class DataJpaVoteRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaVoteRepository.class);
    
    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudMenuRepository crudMenuRepository;
    
    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudMenuRepository crudMenuRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudMenuRepository = crudMenuRepository;
    }
    
    @Transactional
    public Vote save(Vote vote, Integer userId) {
        logger.info(String.format("Save vote=%s for user id=%s", vote, userId));
        if ((!vote.isNew()) && (get(vote.getId(), userId) == null)) {
            logger.warn(String.format("Failed to update vote=%s for user id=%s. Reason: userId in vote doesn't match userId", vote, userId));
            return null;
        }
        // TODO How to obtain only menu id lazily: w\o dishes
        if (crudMenuRepository.findById(vote.getMenu().getId()).get() == null) {
            logger.warn(String.format("Failed to save vote=%s for user id=%s. Reason: cannot find menu with id ", vote, vote.getMenu().getId()));
            return null;
        }
        vote.setUser(crudUserRepository.getReferenceById(userId));
        vote.setMenu(crudMenuRepository.getReferenceById(vote.getMenu().getId()));
        return crudVoteRepository.save(vote);
    }
    
    //TODO check if on service layer other user cannot get other's user vote
    public Vote getByDateAndUserId(LocalDate date, Integer userId) {
        return crudVoteRepository.getByDateAndUserId(date, userId);
    }
    
    public Vote get(Integer id, Integer userId) {
        Optional<Vote> vote = crudVoteRepository.findById(id);
        return vote.filter(v -> v.getUser().getId().equals(userId)).orElse(null);
    }
}
