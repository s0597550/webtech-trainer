package com.example.backend.game;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {

    private final GameRepository repo;

    public GameController(GameRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Game> all() {
        return repo.findAll();
    }

    @PostMapping
    public Game create(@RequestBody Game g) {
        // id wird von DB/JPA vergeben (@GeneratedValue)
        g.setId(null);
        return repo.save(g);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable Long id, @RequestBody Game g) {
        g.setId(id);
        return repo.save(g);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
