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

    /// Post a new user
    @Override
    public Users create(Users users) {
        return repository.save(users);
    }

    /// Get User by Id
    // @Override
    public Users showEntry(Long id) {
        return repository.findById(id).orElse(null);
    }

    /// Patch score, streak, totalScore, longestStreak, bestOverallScore,
    /// bestScoreAlbum, bestScoreSong
    @Override
    public Users update(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setScore(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    public Users updateStreak(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setStreak(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    public Users updateTotalScore(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setTotalScore(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    public Users updateLongestStreak(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setLongestStreak(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    public Users updateBestOverallScore(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setBestOverallScore(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    public Users updateBestScoreAlbum(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setBestScoreAlbum(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    public Users updateBestScoreSong(Long id, int score) {
        return repository.findById(id)
                .map(existingUser -> {
                    existingUser.setBestScoreSong(score);
                    return repository.save(existingUser);
                })
                .orElse(null);
    }

    /// Delete a user
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
