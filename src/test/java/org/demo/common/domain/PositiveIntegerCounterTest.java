package org.demo.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PositiveIntegerCounterTest {
    @Test
    void givenCreated_whenIncrease_thenCountIsOne(){
        // Given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // When
        counter.increase();

        // Then
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero(){
        // Given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        // When
        counter.decrease();

        // Then
        assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero(){
        // Given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // When
        counter.decrease();

        // Then
        assertEquals(0, counter.getCount());
    }
}
