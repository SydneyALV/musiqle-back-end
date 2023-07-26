package com.musiqle.Musiqle;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int score;
    private int streak;
    private int total_score;
    private int longest_streak;
    private int best_overall_score;
    private int best_score_album;
    private int best_score_song;
    
    
    public Users() {}
    
    //Constructor
    public Users(Long id, String name, int score, int streak, int total_score, int longest_streak,
    int best_overall_score, int best_score_album, int best_score_song) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.streak = streak;
        this.total_score = total_score;
        this.longest_streak = longest_streak;
        this.best_overall_score = best_overall_score;
        this.best_score_album = best_score_album;
        this.best_score_song = best_score_song;
    }
    
    //Getters and Setters 
    public int getscore() {
        return score;
    }

    public void setscore(int score) {
        this.score = score;
    }
    public int getLongest_streak() {
        return longest_streak;
    }
    public void setLongest_streak(int longest_streak) {
        this.longest_streak = longest_streak;
    }
    public int getTotal_score() {
        return total_score;
    }
    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }
    public int getBest_overall_score() {
        return best_overall_score;
    }
    public void setBest_overall_score(int best_overall_score) {
        this.best_overall_score = best_overall_score;
    }

    public int getBest_score_album() {
        return best_score_album;
    }

    public void setBest_score_album(int best_score_album) {
        this.best_score_album = best_score_album;
    }

    public int getBest_score_song() {
        return best_score_song;
    }

    public void setBest_score_song(int best_score_song) {
        this.best_score_song = best_score_song;
    }
    public Long getId() {
        return id;
    }
    public int getStreak() {
        return streak;
    }
    public void setStreak(int streak) {
        this.streak = streak;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
