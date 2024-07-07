package com.haskellfusion.tuple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TupleTest {

    @Test
    void testGetFirst() {
        var t = new Tuple("hello1", "hello2");
        assertEquals("hello1", t.getFirst());
    }

    @Test
    void testGetSecond() {
        var t = new Tuple("hello1", "hello2");
        assertEquals("hello2", t.getSecond());
    }

    @Test
    void testThrowsWhenFirstNull() {
        assertThrows(NullPointerException.class, () -> new Tuple<>(null, "hello2"));
    }

    @Test
    void testThrowsWhenSecondNull() {
        assertThrows(NullPointerException.class, () -> new Tuple<>("hello", null));
    }

}