package com.example.backend.game;

import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    // ==========================
    // 👍 ZUSAGE (LIKE)
    // ==========================
    @PostMapping("/{id}/like")
    public Game like(@PathVariable Long id) {
        Game game = repo.findById(id).orElseThrow();
        game.setLikes(game.getLikes() + 1);
        return repo.save(game);
    }

    // ==========================
    // 👎 ABSAGE (DISLIKE)
    // ==========================
    @PostMapping("/{id}/dislike")
    public Game dislike(@PathVariable Long id) {
        Game game = repo.findById(id).orElseThrow();
        game.setDislikes(game.getDislikes() + 1);
        return repo.save(game);
    }

    // ==========================
    // 🏁 ERGEBNIS SETZEN
    // ==========================
    @PutMapping("/{id}/result")
    public Game setResult(@PathVariable Long id, @RequestBody String result) {
        Game game = repo.findById(id).orElseThrow();

        // 🔑 EINZIG WICHTIGE ERGÄNZUNG:
        // JSON-Quotes entfernen ("2:1" → 2:1)
        game.setResult(result.replace("\"", ""));

        return repo.save(game);
    }
}
