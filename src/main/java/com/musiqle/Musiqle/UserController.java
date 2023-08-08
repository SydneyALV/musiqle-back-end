package com.musiqle.Musiqle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
// import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService service;

    /// Get All Users
    @GetMapping("/user")
    public List<Users> find() {
        return service.find();
    }

    /// Get User by Id
    @GetMapping("/user/{id}")
    public Users showEntry(@PathVariable Long id) {
        return service.showEntry(id);
    }

    ///API Call to MusixMatch to get Track ID
    @GetMapping("/musixmatch/track/{trackId}")
    public Object musixmatch(@PathVariable Integer trackId) {
        return service.getLyrics(trackId);
    }
    ///API Call to MusixMatch to get Random Track
    @GetMapping("/musixmatch/track")
    public Object trackmusixmatch() {
        return service.getTrack();
    }

    ///API Call to MusixMatch to get Track from Selected Playlist
    @GetMapping("/musixmatch/selected_playlist_track")
    public Object trackmusixmatch(@PathVariable String q_track) {
        return service.findTrack(q_track);
    }

    /// Post a new user
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Users create(@RequestBody Users users) {
        return service.create(users);
    }

    /// Patch score, streak, totalScore, longestStreak, bestOverallScore,
    /// bestScoreAlbum, bestScoreSong
    @PatchMapping("/user/{id}/score")
    public Users update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.update(id, (int) payload.get("score"));
    }

    @PatchMapping("/user/{id}/streak")
    public Users updateStreak(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.updateStreak(id, (int) payload.get("streak"));
    }

    @PatchMapping("/user/{id}/totalscore")
    public Users updateTotalScore(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.updateTotalScore(id, (int) payload.get("totalScore"));
    }

    @PatchMapping("/user/{id}/longeststreak")
    public Users updateLongestStreak(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.updateLongestStreak(id, (int) payload.get("longestStreak"));
    }

    @PatchMapping("/user/{id}/bestoverallscore")
    public Users updateBestOverallScore(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.updateBestOverallScore(id, (int) payload.get("bestOverallScore"));
    }

    @PatchMapping("/user/{id}/bestscorealbum")
    public Users updateBestScoreAlbum(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.updateBestScoreAlbum(id, (int) payload.get("bestScoreAlbum"));
    }

    @PatchMapping("/user/{id}/bestscoresong")
    public Users updateBestScoreSong(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.updateBestScoreSong(id, (int) payload.get("bestScoreSong"));
    }

    /// Delete a user
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}