package com.haskellfusion.foldable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListFoldableTest {

    @Test
    void testFoldr() {
        ListFoldable<Integer> foldable = ListFoldable.of(Arrays.asList(1, 2, 3, 4));
        int result = foldable.foldr(Integer::sum, 0);
        assertEquals(10, result);
    }

    @Test
    void testFoldl() {
        ListFoldable<Integer> foldable = ListFoldable.of(Arrays.asList(1, 2, 3, 4));
        int result = foldable.foldl(Integer::sum, 0);
        assertEquals(10, result);
    }

    @Test
    void testFoldrConcat() {
        ListFoldable<String> foldable = ListFoldable.of(Arrays.asList("a", "b", "c"));
        String result = foldable.foldr((x, acc) -> x + acc, "");
        assertEquals("abc", result);
    }

    @Test
    void testFoldlConcat() {
        ListFoldable<String> foldable = ListFoldable.of(Arrays.asList("a", "b", "c"));
        String result = foldable.foldl((acc, x) -> acc + x, "");
        assertEquals("abc", result);
    }
}

