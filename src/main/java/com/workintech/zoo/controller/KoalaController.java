package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAll() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable int id) {
        Koala k = koalas.get(id);
        if (k == null) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return k;
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala) {
        if (koala.getId() == 0 || koala.getName() == null) {
            throw new RuntimeException("Invalid Koala");
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable int id, @RequestBody Koala koala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id) {
        Koala removed = koalas.remove(id);
        if (removed == null) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return removed;
    }
}
