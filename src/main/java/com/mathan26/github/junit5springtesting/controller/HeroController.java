package com.mathan26.github.junit5springtesting.controller;

import com.mathan26.github.junit5springtesting.api.Hero;
import com.mathan26.github.junit5springtesting.service.HeroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HeroController {

    HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/{id}")
    public Hero getHeroById(@PathVariable("id") int index){
        return this.heroService.getHeroById(index);
    }

    @GetMapping
    public Optional<Hero> getByName(@RequestParam("name") String heroname){
        return this.heroService.getHeroByname(heroname);
    }

    @PostMapping("/addHero")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewSuperHero(@RequestBody Hero hero){
        this.heroService.addHero(hero);
    }
}
