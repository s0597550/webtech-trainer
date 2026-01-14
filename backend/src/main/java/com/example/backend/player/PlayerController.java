package com.example.backend.player;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "*")
public class PlayerController {

    private final PlayerRepository repo;

    public PlayerController(PlayerRepository repo) {
        this.repo = repo;
    }

    // ==========================
    // ALLE SPIELER
    // ==========================
    @GetMapping
    public List<Player> all() {
        return repo.findAll();
    }

    // ==========================
    // SPIELER ANLEGEN
    // ==========================
    @PostMapping
    public Player create(@RequestBody Player p) {
        p.setId(null); // ID von DB
        return repo.save(p);
    }

    // ==========================
    // STATUS ÄNDERN (Anwesend / Krank / etc.)
    // ==========================
    @PutMapping("/{id}/status")
    public Player updateStatus(@PathVariable Long id, @RequestBody String status) {
        Player p = repo.findById(id).orElseThrow();
        p.setStatus(status);
        return repo.save(p);
    }

    // ==========================
    // SPIELER LÖSCHEN
    // ==========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
