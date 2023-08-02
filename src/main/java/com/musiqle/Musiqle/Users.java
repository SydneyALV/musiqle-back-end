package com.musiqle.Musiqle;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="score")
    private int score;
    @Column(name="streak")
    private int streak;
    @Column(name="totalScore")
    private int totalScore;
    @Column(name="longestStreak")
    private int longestStreak;
    @Column(name="bestOverallScore")
    private int bestOverallScore;
    @Column(name="bestScoreAlbum")
    private int bestScoreAlbum;
    @Column(name="bestScoreSong")
    private int bestScoreSong;

    
    public Users() {}
    
    //Constructor
    public Users(Long id, String name, int score, int streak, int totalScore, int longestStreak, int bestOverallScore,
            int bestScoreAlbum, int bestScoreSong) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.streak = streak;
        this.totalScore = totalScore;
        this.longestStreak = longestStreak;
        this.bestOverallScore = bestOverallScore;
        this.bestScoreAlbum = bestScoreAlbum;
        this.bestScoreSong = bestScoreSong;
        
    }
    
    //Getters and Setters 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int getBestScoreAlbum() {
        return bestScoreAlbum;
    }

    public void setBestScoreAlbum(int bestScoreAlbum) {
        this.bestScoreAlbum = bestScoreAlbum;
    }

    public int getBestScoreSong() {
        return bestScoreSong;
    }

    public void setBestScoreSong(int bestScoreSong) {
        this.bestScoreSong = bestScoreSong;
    }
}
