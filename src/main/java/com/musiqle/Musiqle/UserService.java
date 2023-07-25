package com.musiqle.Musiqle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> find() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public User create(User users) {
        return repository.save(users);
    }

    @Override
    public User update(Long id, int score) {
        return repository.findById(id)
            .map(existingUser -> {
                existingUser.setScore(score);
                return repository.save(existingUser);
            })
            .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
