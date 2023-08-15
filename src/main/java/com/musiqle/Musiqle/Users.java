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
    private int totalScore;
    private int longestStreak;
    private int bestOverallScore;
    private int bestScoreArtist;
    private int bestScoreSong;

    public Users() {}

    //Constructor
    public Users(Long id, String name, int score, int streak, int totalScore, int longestStreak, int bestOverallScore,
            int bestScoreArtist, int bestScoreSong) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.streak = streak;
        this.totalScore = totalScore;
        this.longestStreak = longestStreak;
        this.bestOverallScore = bestOverallScore;
        this.bestScoreArtist = bestScoreArtist;
        this.bestScoreSong = bestScoreSong;
    }

    //Getters and Setters 

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }


    public int getStreak() {
        return streak;
    }


    public void setStreak(int streak) {
        this.streak = streak;
    }


    public int getTotalScore() {
        return totalScore;
    }


    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }


    public int getLongestStreak() {
        return longestStreak;
    }


    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }


    public int getBestOverallScore() {
        return bestOverallScore;
    }


    public void setBestOverallScore(int bestOverallScore) {
        this.bestOverallScore = bestOverallScore;
    }


    public int getBestScoreArtist() {
        return bestScoreArtist;
    }


    public void setBestScoreArtist(int bestScoreArtist) {
        this.bestScoreArtist = bestScoreArtist;
    }


    public int getBestScoreSong() {
        return bestScoreSong;
    }


    public void setBestScoreSong(int bestScoreSong) {
        this.bestScoreSong = bestScoreSong;
    }

}
