package com.example.backend.game;
import java.time.LocalDateTime;
public class Game {
  private Long id;
  private String opponent;
  private LocalDateTime dateTime;
  private String location;
  public Game() {}
  public Game(Long id, String opponent, LocalDateTime dateTime, String location) {
    this.id = id; this.opponent = opponent; this.dateTime = dateTime; this.location = location;
  }
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getOpponent() { return opponent; }
  public void setOpponent(String opponent) { this.opponent = opponent; }
  public LocalDateTime getDateTime() { return dateTime; }
  public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
  public String getLocation() { return location; }
  public void setLocation(String location) { this.location = location; }
}
