package com.haskellfusion.monad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonadTest {

    @Test
    void testJustFlatMap() {
        Monad<Integer> just = new Monad.Just<>(5);
        Monad<Integer> result = just.flatMap(value -> new Monad.Just<>(value * 2));
        assertInstanceOf(Monad.Just.class, result);
        assertEquals(10, ((Monad.Just<Integer>) result).getValue());
    }

    @Test
    void testNothingFlatMap() {
        Monad<Integer> nothing = new Monad.Nothing<>();
        Monad<Integer> result = nothing.flatMap(value -> new Monad.Just<>(value * 2));
        assertInstanceOf(Monad.Nothing.class, result);
    }

    @Test
    void testJustMap() {
        Monad<Integer> just = new Monad.Just<>(5);
        Monad<Integer> result = just.map(value -> value * 2);
        assertInstanceOf(Monad.Just.class, result);
        assertEquals(10, ((Monad.Just<Integer>) result).getValue());
    }

    @Test
    void testNothingMap() {
        Monad<Integer> nothing = new Monad.Nothing<>();
        Monad<Integer> result = nothing.map(value -> value * 2);
        assertInstanceOf(Monad.Nothing.class, result);
    }

    @Test
    void testJustConstructor() {
        Monad.Just<Integer> just = new Monad.Just<>(5);
        assertInstanceOf(Monad.Just.class, just);
        assertEquals(5, just.getValue());
    }

    @Test
    void testNothingConstructor() {
        Monad<Integer> nothing = new Monad.Nothing<>();
        assertInstanceOf(Monad.Nothing.class, nothing);
    }
}
