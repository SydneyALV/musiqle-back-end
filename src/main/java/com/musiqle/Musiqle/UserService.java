package com.musiqle.Musiqle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<Users> find() {
        return repository.findAllByOrderByIdAsc();
    }
///Post a new user
    @Override
    public Users create(Users users) {
        return repository.save(users);
    }
///Get User by Id
    // @Override
    public Users showEntry(Long id) {
        return repository.findById(id).orElse(null);
    }
///Get API CALL for Lyrics from MusixMix
    // @Value("${musicmix.apiKey}")
    // private String musicmix;

    private RestTemplate template = new RestTemplate();
    public String getLyrics(Integer trackId) {
        String url = String.format("http://api.musixmatch.com/ws/1.1/track.lyrics.get?apikey="+"c9a60b4934a2596c302eda29d998f07f"+"&track_id="+trackId);
        String result = template.getForObject(url, String.class);
        return result;
    }
    
///Get API call to Query Track from MusixMix
    private RestTemplate trackTemplate = new RestTemplate();
    public String getTrack(String track) {
        String trackurl = String.format("http://api.musixmatch.com/ws/1.1/track.search?apikey="+"c9a60b4934a2596c302eda29d998f07f"+"&q_track="+track+"&s_track_rating=desc");
        String result = trackTemplate.getForObject(trackurl, String.class);
        return result;
    }

/// Patch score, streak, totalScore, longestStreak, bestOverallScore, bestScoreAlbum, bestScoreSong 
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
