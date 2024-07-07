package com.haskellfusion.either;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void testLeft() {
        Either<String, Integer> either = Either.left("Error");
        assertTrue(either.isLeft());
        assertFalse(either.isRight());
        assertEquals("Error", either.getLeft());
        assertThrows(NoSuchElementException.class, either::getRight);
    }

    @Test
    void testRight() {
        Either<String, Integer> either = Either.right(42);
        assertTrue(either.isRight());
        assertFalse(either.isLeft());
        assertEquals(42, either.getRight());
        assertThrows(NoSuchElementException.class, either::getLeft);
    }

    @Test
    void testMapLeft() {
        Either<String, Integer> either = Either.left("Error");
        Either<Integer, Integer> mapped = either.mapLeft(String::length);
        assertTrue(mapped.isLeft());
        assertEquals(5, mapped.getLeft());

        Either<String, Integer> right = Either.right(42);
        Either<Integer, Integer> mappedRight = right.mapLeft(String::length);
        assertTrue(mappedRight.isRight());
        assertEquals(42, mappedRight.getRight());
    }

    @Test
    void testMapRight() {
        Either<String, Integer> either = Either.right(42);
        Either<String, String> mapped = either.mapRight(Object::toString);
        assertTrue(mapped.isRight());
        assertEquals("42", mapped.getRight());

        Either<String, Integer> left = Either.left("Error");
        Either<String, String> mappedLeft = left.mapRight(Object::toString);
        assertTrue(mappedLeft.isLeft());
        assertEquals("Error", mappedLeft.getLeft());
    }

    @Test
    void testFlatMapLeft() {
        Either<String, Integer> either = Either.left("Error");
        Either<Integer, Integer> flatMapped = either.flatMapLeft(s -> Either.left(s.length()));
        assertTrue(flatMapped.isLeft());
        assertEquals(5, flatMapped.getLeft());

        Either<String, Integer> right = Either.right(42);
        Either<Integer, Integer> flatMappedRight = right.flatMapLeft(s -> Either.left(s.length()));
        assertTrue(flatMappedRight.isRight());
        assertEquals(42, flatMappedRight.getRight());
    }

    @Test
    void testFlatMapRight() {
        Either<String, Integer> either = Either.right(42);
        Either<String, String> flatMapped = either.flatMapRight(i -> Either.right(i.toString()));
        assertTrue(flatMapped.isRight());
        assertEquals("42", flatMapped.getRight());

        Either<String, Integer> left = Either.left("Error");
        Either<String, String> flatMappedLeft = left.flatMapRight(i -> Either.right(i.toString()));
        assertTrue(flatMappedLeft.isLeft());
        assertEquals("Error", flatMappedLeft.getLeft());
    }

}