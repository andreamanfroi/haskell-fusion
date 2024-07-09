package com.haskellfusion.lazy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LazyTest {

    @Test
    void testLazyEvaluation() {
        Lazy<Integer> lazy = Lazy.of(() -> {
            System.out.println("Evaluating...");
            return 42;
        });
        System.out.println("Before get");
        assertEquals(42, lazy.get());
        System.out.println("After get");
    }
}
