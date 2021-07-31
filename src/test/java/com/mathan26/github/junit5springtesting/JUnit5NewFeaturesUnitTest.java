package com.mathan26.github.junit5springtesting;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@Slf4j
public class JUnit5NewFeaturesUnitTest {

    @BeforeAll
    static void setUp(){
      log.info("@BeforeAll executes once before all test method in this class");
    }


    @BeforeEach
    void init(){
        log.info("@BeforeEach executes before each test method in this class");
    }

    @DisplayName("single test successful")
    @Test
    void singleTest(){
        log.info("single test successful");
    }

    @Test
    @Disabled("Not implemented yet.")
    void testShowSomething() {
    }

    // used java 8 way to test assertions
    @Test
    void lambdaExpressions(){
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                .mapToInt(i->i)
                .sum() > 5,"Sum should be greater than 5"
        );
    }

    // Grouping assertions
    @Test
    void groupAssertions(){
        int[] numbers = {0, 1, 2, 3, 4, 5};
        assertAll("number",
                ()->assertEquals(numbers[1],1),
                ()->assertEquals(numbers[2],2)
                );
    }

    // execute the test based on the assumption
    @Test
    void trueAssumption() {
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void falseAssumption() {
        assumeFalse(5 < 1);
        assertEquals(5 + 2, 7);
    }


    // Catch the exception then test message, exception ...etc
    @Test
    void shouldThrowExceptions(){
        Throwable throwable = assertThrows(UnsupportedOperationException.class,()->{
           throw new UnsupportedOperationException("Operation not supported.");
        });

        assertEquals(throwable.getMessage(),"Operation not supported.");
    }

    @Test
    void assertThrowsException(){
        String str = null;
        assertThrows(IllegalArgumentException.class,()->{
            Integer.valueOf(str);
        });
    }

    @AfterEach
    void tearDown(){
        log.info("@AfterEach executed after each test method.");
    }

    @AfterAll
    static void done(){
        log.info("@AfterAll executed after all test methods.");
    }
}
