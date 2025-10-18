package com.example.backend.note;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/notes")
public class NoteController {
  @GetMapping("/hello")
  public String hello() { return "Hello from Spring"; }
}
