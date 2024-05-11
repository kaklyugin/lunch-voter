package ru.topjava.lunchvoter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.User;

@Repository
public class DataJpaUserRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaUserRepository.class);
    
    private final CrudUserRepository crudUserRepository;
    
    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }
    
    public User get(Integer id) {
        return crudUserRepository.findById(id).orElse(null);
    }
    
}
