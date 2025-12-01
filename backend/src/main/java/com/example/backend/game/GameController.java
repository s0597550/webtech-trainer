package com.example.backend.game;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {

    private final List<Game> store = List.of(
            new Game(42L, "test", LocalDateTime.now(), "test")
    );
    private final AtomicLong seq = new AtomicLong(1);

    @GetMapping
    public List<Game> all() {
        return store;
    }

    @PostMapping
    public Game create(@RequestBody Game g) {
        g.setId(seq.getAndIncrement());
        store.add(g);
        return g;
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable Long id, @RequestBody Game g) {
        store.removeIf(x -> Objects.equals(x.getId(), id));
        g.setId(id);
        store.add(g);
        return g;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        store.removeIf(x -> Objects.equals(x.getId(), id));
    }
}
