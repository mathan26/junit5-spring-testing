package com.mathan26.github.junit5springtesting.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Hero {
    String firstname;
    String lastname;
    String heroname;
}
