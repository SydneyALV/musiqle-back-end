package com.musiqle.Musiqle;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONPObject;

import io.github.cdimascio.dotenv.Dotenv;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

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

    /// Get API Call for Access Token
    private RestTemplate getAccessTokenTemplate = new RestTemplate();
    public String getAccessToken() {
        Dotenv dotenv = Dotenv.load();
        String url = "https://accounts.spotify.com/api/token";
        String encoded = dotenv.get("ENCODED");
        String body = "grant_type=client_credentials";
        String content_type = "application/x-www-form-urlencoded";
        RequestEntity request = RequestEntity
            .post(url)
            .header("Authorization", "Basic " + encoded + "=")
            .header("Content-Type", content_type)
            .body(body);
        String result = template.exchange(request, String.class).getBody();
        return result;
    }

    /// Get API Call for Genres from Spotify
    private RestTemplate findGenresTemplate = new RestTemplate();
    public String getGenres() {
        String url = "https://api.spotify.com/v1/browse/categories";
        // String access_token = "BQCIJJ6ri-ZrrrPyI5RLbaq3jefabj_OJLnQLP1HaVnKVzgMSWm5D6KFHV3kDaSpc2_GIbOWr_6uB44nEdbv-FOwSerruM4WbVeY5Ut7Hi32eD58hPM";
        RequestEntity request = RequestEntity
            .get(url)
            .header("Authorization", "Bearer " + getAccessToken())
            .build();
        String result = template.exchange(request, String.class).getBody();
        return result;
    }

    /// Get API Call for Playlists of a Specific Genre from Spotify
    private RestTemplate findPlaylistsTemplate = new RestTemplate();
    public String getPlaylists(String genreId) {

        String url = "https://api.spotify.com/v1/browse/categories/" + genreId + "/playlists";
        // String access_token = "BQCIJJ6ri-ZrrrPyI5RLbaq3jefabj_OJLnQLP1HaVnKVzgMSWm5D6KFHV3kDaSpc2_GIbOWr_6uB44nEdbv-FOwSerruM4WbVeY5Ut7Hi32eD58hPM";
        RequestEntity request = RequestEntity
            .get(url)
            .header("Authorization", "Bearer " + getAccessToken())
            .build();
        String result = template.exchange(request, String.class).getBody();
        return result;
    }

    /// Get API Call for Playlists of a Specific Genre from Spotify
    private RestTemplate findTracksFromPlaylistTemplate = new RestTemplate();
    public String getTracks(String playlistId) {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
        // String spotify_access_token = "BQCIJJ6ri-ZrrrPyI5RLbaq3jefabj_OJLnQLP1HaVnKVzgMSWm5D6KFHV3kDaSpc2_GIbOWr_6uB44nEdbv-FOwSerruM4WbVeY5Ut7Hi32eD58hPM";
        RequestEntity request = RequestEntity
            .get(url)
            .header("Authorization", "Bearer " + getAccessToken())
            .build();
        String result = template.exchange(request, String.class).getBody();
        return result;
    }

    ///Get API CALL for Lyrics from MusixMix
    private RestTemplate template = new RestTemplate();
    public String getLyrics(Integer trackId) {
        // Dotenv dotenv = Dotenv.load();
        String musicmixapikey= System.getenv("MUSIXAPIKEY");
        String url = String.format("http://api.musixmatch.com/ws/1.1/track.lyrics.get?apikey="+musicmixapikey+"&track_id="+trackId);
        String result = template.getForObject(url, String.class);
        return result;
    }

/// Get API CALL for Search Track from MusixMix
    private RestTemplate findTrackTemplate = new RestTemplate();
    public String findTrack(String q_track) {
        String musixmixapikey= System.getenv("MUSIXAPIKEY");
        String url = String.format("http://api.musixmatch.com/ws/1.1/matcher.tracker.get?apikey="+musixmixapikey+"&q_track="+q_track);
        String result = template.getForObject(url, String.class);
        return result;
    }
    
///Get API call to Query Track from MusixMix Using Popular playlist
    private RestTemplate trackTemplate = new RestTemplate();
    public String getTrack() {
        // Dotenv dotenv = Dotenv.load();
        String musicmixapikey= System.getenv("MUSIXAPIKEY");
        String trackurl = String.format("http://api.musixmatch.com/ws/1.1/chart.tracks.get?chart_name=top&f_has_lyrics=1&country=US&apikey="+musicmixapikey);
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
