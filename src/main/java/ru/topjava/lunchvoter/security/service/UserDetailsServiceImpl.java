package ru.topjava.lunchvoter.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.topjava.lunchvoter.model.User;
import ru.topjava.lunchvoter.repository.CrudUserRepository;
import ru.topjava.lunchvoter.security.UserDetailModel;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private CrudUserRepository crudUserRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = crudUserRepository.findUserByEmail(email);
        UserDetailModel userDetailModel = new UserDetailModel(user.orElseThrow(() -> new UsernameNotFoundException("Invalid Username")));
        return userDetailModel;
    }
}
