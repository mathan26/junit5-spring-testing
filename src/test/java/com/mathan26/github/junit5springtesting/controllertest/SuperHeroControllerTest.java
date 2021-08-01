package com.mathan26.github.junit5springtesting.controllertest;

import com.mathan26.github.junit5springtesting.api.Hero;
import com.mathan26.github.junit5springtesting.service.HeroService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperHeroControllerTest {

    @MockBean
    HeroService heroService;

    @Autowired
    TestRestTemplate  restTemplate;

    @Test
    void canRetrieveByIdWhenExists(){
        //given
        given(heroService.getHeroById(1))
                .willReturn(Hero.builder()
                .firstname("Mathan")
                .lastname("S")
                .heroname("spider").build());

        //when
        ResponseEntity<Hero> entity = restTemplate.getForEntity("/1",Hero.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().equals(Hero.builder()
                .firstname("Mathan")
                .lastname("S")
                .heroname("spider").build()));
    }

    @Test
    public void canRetrieveByNameWhenExists() {
        // given
        given(heroService.getHeroByname("RobotMan"))
                .willReturn(Optional.of(new Hero("Rob", "Mannon", "RobotMan")));

        // when
        ResponseEntity<Hero> superHeroResponse = restTemplate
                .getForEntity("/?name=RobotMan", Hero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(superHeroResponse.getBody().equals(new Hero("Rob", "Mannon", "RobotMan")));
    }

    @Test
    public void canRetrieveByNameWhenDoesNotExist() {
        // given
        given(heroService.getHeroByname("RobotMan"))
                .willReturn(Optional.empty());

        // when
        ResponseEntity<Hero> superHeroResponse = restTemplate
                .getForEntity("/?name=RobotMan", Hero.class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(superHeroResponse.getBody()).isNull();
    }

    @Test
    public void canCreateANewSuperHero() {
        // when
        ResponseEntity<Hero> superHeroResponse = restTemplate.postForEntity("/addHero",
                new Hero("Rob", "Mannon", "RobotMan"), Hero.class);

        // then
        Assertions.assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
