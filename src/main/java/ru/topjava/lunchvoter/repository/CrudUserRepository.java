package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Long> {

}
