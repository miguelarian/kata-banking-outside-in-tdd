package com.miguelvela;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClockTests {

    @Test
    void todayAsString_should_return_todays_date() {
        Clock clock = new TesteableClock();
        String today = clock.todayAsString();
        assertThat(today, is("01/04/2014"));
    }

    private class TesteableClock extends Clock {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2014, 4, 1);
        }
    }
}