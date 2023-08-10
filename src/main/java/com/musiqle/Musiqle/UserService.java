package com.musiqle.Musiqle;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslBundleProperties.Key;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpSession;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
// import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

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

    public Object getAccessToken() {
        // Dotenv dotenv = Dotenv.load();
        String url = "https://accounts.spotify.com/api/token";
        String encoded = System.getenv("ENCODED");
        String body = "grant_type=client_credentials";
        String contentType = "application/x-www-form-urlencoded";
        RequestEntity request = RequestEntity
                .post(url)
                .header("Authorization", "Basic " + encoded + "=")
                .header("Content-Type", contentType)
                .body(body);
        Object result = getAccessTokenTemplate.exchange(request, Object.class).getBody();

        try {
            JsonNode jsonNode = objectMapper.valueToTree(result);

            // Extract a specific property from the JSON
            String accessToken = jsonNode.get("access_token").asText();

            // Process the extracted property as needed
            System.out.println("Extracted Property: " + accessToken);

            return accessToken;
        } catch (Exception e) {
            // Handle parsing errors
            e.printStackTrace();
            return "Error occurred while parsing the response.";
        }
    }


    /// Get API Call for Genres from Spotify
    private RestTemplate findGenresTemplate = new RestTemplate();

    public Object getGenres() {
        String url = "https://api.spotify.com/v1/browse/categories";
        Object accessToken = getAccessToken();
        System.out.println(accessToken);
        RequestEntity request = RequestEntity
                .get(url)
                .header("Authorization", "Bearer " + accessToken)
                .build();
        Object result = findGenresTemplate
                .exchange(request, Object.class)
                .getBody();
        return result;
    }

    /// Get API Call for Playlists of a Specific Genre from Spotify
    private RestTemplate findPlaylistsTemplate = new RestTemplate();

    public Object getPlaylists(String genreId) {

        String url = "https://api.spotify.com/v1/browse/categories/" + genreId + "/playlists";
        Object accessToken = getAccessToken();
        RequestEntity request = RequestEntity
                .get(url)
                .header("Authorization", "Bearer " + accessToken)
                .build();
        Object result = findPlaylistsTemplate.exchange(request, Object.class).getBody();
        return result;
    }

    /// Get API Call for Tracks of a Specific Playlist from Spotify
    private RestTemplate findTracksFromPlaylistTemplate = new RestTemplate();

    public Object getTracks(String playlistId) {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
        Object accessToken = getAccessToken();
        RequestEntity request = RequestEntity
            .get(url)
            .header("Authorization", "Bearer " + accessToken)
            .build();
        Object result = findTracksFromPlaylistTemplate.exchange(request, Object.class).getBody();
        return result;
    }

    /// Get API Call for Track from Spotify
    private RestTemplate findSpotifyTrackTemplate = new RestTemplate();

    public Object getSpotifyTrack(String trackId) {
        String url = "https://api.spotify.com/v1/tracks/" + trackId;
        Object accessToken = getAccessToken();
        RequestEntity request = RequestEntity
            .get(url)
            .header("Authorization", "Bearer " + accessToken)
            .build();
        Object result = findSpotifyTrackTemplate.exchange(request, Object.class).getBody();

        try {
            JsonNode jsonNode = objectMapper.valueToTree(result);

            // Extract a specific property from the JSON
            String artistName = jsonNode.get("artists").asText();

            // Process the extracted property as needed
            System.out.println("Extracted Property: " + artistName);

            return artistName;
        } catch (Exception e) {
            // Handle parsing errors
            e.printStackTrace();
            return "Error occurred while parsing the response.";
        }
    }

    /// Get API CALL for Lyrics from MusixMix
    private RestTemplate template = new RestTemplate();

    public String getLyrics(Integer trackId) {
        // Dotenv dotenv = Dotenv.load();
        String musicmixapikey = System.getenv("MUSIXAPIKEY");
        String url = "http://api.musixmatch.com/ws/1.1/track.lyrics.get?apikey=" + musicmixapikey + "&track_id=" + trackId;
        String result = template.getForObject(url, String.class);
        return result;
    }

    /// Get API CALL for Search Track from MusixMix
    private RestTemplate findTrackTemplate = new RestTemplate();

    public String findTrack(String qTrack, String qArtist) {
        // Dotenv dotenv = Dotenv.load();
        String musixmixapikey = System.getenv("MUSIXAPIKEY");
        String url = String.format("http://api.musixmatch.com/ws/1.1/matcher.lyrics.get?apikey=" + musixmixapikey + "&q_track=" + qTrack + "&q_artist=" + qArtist);
        String result = findTrackTemplate.getForObject(url, String.class);
        return result;
    }

    /// Get API call to Query Track from MusixMix Using Popular playlist
    private RestTemplate trackTemplate = new RestTemplate();

    public String getTrack() {
        // Dotenv dotenv = Dotenv.load();
        // String musicmixapikey= dotenv.get("MUSIXAPIKEY");
        String musicmixapikey= System.getenv("MUSIXAPIKEY");
        String trackurl = String.format("http://api.musixmatch.com/ws/1.1/chart.tracks.get?chart_name=mxmweekly&f_has_lyrics=1&country=US&page_size=100&apikey="+musicmixapikey);
        String result = trackTemplate.getForObject(trackurl, String.class);
        return result;
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
