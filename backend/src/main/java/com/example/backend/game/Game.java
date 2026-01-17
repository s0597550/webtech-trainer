package com.example.backend.game;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String opponent;
    private LocalDateTime dateTime;
    private String location;

    // 👍 Zusagen / 👎 Absagen
    private int likes = 0;
    private int dislikes = 0;

    // 🏁 Spielergebnis (z.B. "2:1")
    private String result;

    public Game() {}

    public Game(Long id, String opponent, LocalDateTime dateTime, String location) {
        this.id = id;
        this.opponent = opponent;
        this.dateTime = dateTime;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // ✅ NUR HINZUGEFÜGT – Alias für Frontend
    public LocalDateTime getKickoff() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
