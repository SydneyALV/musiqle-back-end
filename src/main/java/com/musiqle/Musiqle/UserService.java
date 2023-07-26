package com.musiqle.Musiqle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<Users> find() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public Users create(Users users) {
        return repository.save(users);
    }

    // @Override
    public Users showEntry(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Users update(Long id, int score) {
        return repository.findById(id)
            .map(existingUser -> {
                existingUser.setscore(score);
                return repository.save(existingUser);
            })
            .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
