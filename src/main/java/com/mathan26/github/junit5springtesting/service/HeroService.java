package com.mathan26.github.junit5springtesting.service;

import com.mathan26.github.junit5springtesting.api.Hero;
import com.mathan26.github.junit5springtesting.exception.HeroNotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroService {
    List<Hero> heroes =  new ArrayList<>();

    public Hero addHero(Hero hero){
        heroes.add(hero);
        return hero;
    }

    public Optional<Hero> getHeroByname(String heroname){
        return heroes.stream().filter(hero -> hero.getHeroname().equals(heroname)).findAny();
    }

    public Hero getHeroById(int index) {
        if (index > heroes.size()){
            throw new HeroNotExistException();
        }
        return heroes.get(index-1);
    }
}
