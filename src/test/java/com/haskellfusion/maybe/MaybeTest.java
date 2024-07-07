package com.haskellfusion.maybe;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MaybeTest {

    @Test
    void testJust() {
        Maybe<String> maybe = Maybe.just("Hello");
        assertTrue(maybe.isJust());
        assertEquals("Hello", maybe.get());
    }

    @Test
    void testNothing() {
        Maybe<String> maybe = Maybe.nothing();
        assertThrows(NoSuchElementException.class, maybe::get);
    }

    @Test
    void testMap() {
        Maybe<String> maybe = Maybe.just("Hello");
        Maybe<Integer> mapped = maybe.map(String::length);
        assertTrue(mapped.isJust());
        assertEquals(5, mapped.get());

        Maybe<String> nothing = Maybe.nothing();
        Maybe<Integer> mappedNothing = nothing.map(String::length);
        assertFalse(mappedNothing.isJust());
    }

    @Test
    void testFlatMap() {
        Maybe<String> maybe = Maybe.just("Hello");
        Maybe<Integer> flatMapped = maybe.flatMap(s -> Maybe.just(s.length()));
        assertTrue(flatMapped.isJust());
        assertEquals(5, flatMapped.get());

        Maybe<String> nothing = Maybe.nothing();
        Maybe<Integer> flatMappedNothing = nothing.flatMap(s -> Maybe.just(s.length()));
        assertFalse(flatMappedNothing.isJust());
    }

    @Test
    void testOrElse() {
        Maybe<String> maybe = Maybe.just("Hello");
        assertEquals("Hello", maybe.orElse("Default"));

        Maybe<String> nothing = Maybe.nothing();
        assertEquals("Default", nothing.orElse("Default"));
    }
}