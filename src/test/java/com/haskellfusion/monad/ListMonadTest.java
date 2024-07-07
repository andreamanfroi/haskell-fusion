package com.haskellfusion.monad;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListMonadTest {

    @Test
    void testMap() {
        ListMonad<Integer> list = ListMonad.of(Arrays.asList(1, 2, 3));
        ListMonad<Integer> result = list.map(x -> x * 2);
        assertEquals(Arrays.asList(2, 4, 6), result.toList());
    }

    @Test
    void testFlatMap() {
        ListMonad<Integer> list = ListMonad.of(Arrays.asList(1, 2, 3));
        ListMonad<Integer> result = list.flatMap(x -> ListMonad.of(Arrays.asList(x, x * 2)));
        assertEquals(Arrays.asList(1, 2, 2, 4, 3, 6), result.toList());
    }

    @Test
    void testFilter() {
        ListMonad<Integer> list = ListMonad.of(Arrays.asList(1, 2, 3, 4));
        ListMonad<Integer> result = list.filter(x -> x % 2 == 0);
        assertEquals(Arrays.asList(2, 4), result.toList());
    }
}
