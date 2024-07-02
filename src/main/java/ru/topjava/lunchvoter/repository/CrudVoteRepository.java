package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Long> {
    
    Vote getByDateAndUserId(LocalDate date, Long userId);
    
}
