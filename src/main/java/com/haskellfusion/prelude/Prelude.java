package com.haskellfusion.prelude;

import com.haskellfusion.tuple.Tuple;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Prelude {

    // id :: a -> a
    static <T> Function<T, T> id() {
        return x -> x;
    }

    // const :: a -> b -> a
    static <A, B> Function<B, A> constant(A value) {
        return b -> value;
    }

    // flip :: (a -> b -> c) -> b -> a -> c
    static <A, B, C> Function<B, Function<A, C>> flip(BiFunction<A, B, C> f) {
        return b -> a -> f.apply(a, b);
    }

    // compose :: (b -> c) -> (a -> b) -> a -> c
    static <A, B, C> Function<A, C> compose(Function<B, C> f, Function<A, B> g) {
        return f.compose(g); // f(g(x))
    }

    // not :: Boolean -> Boolean
    Function<Boolean, Boolean> not = x -> !x;

    // and :: Boolean -> Boolean -> Boolean
    BiFunction<Boolean, Boolean, Boolean> and = (a, b) -> a && b;

    // or :: Boolean -> Boolean -> Boolean
    BiFunction<Boolean, Boolean, Boolean> or = (a, b) -> a || b;

    // eq :: a -> a -> boolean
    static <T> BiFunction<T, T, Boolean> eq() {
        return Objects::equals;
    }

    // ne :: a -> a -> boolean
    static <T> BiFunction<T, T, Boolean> ne() {
        return (a, b) -> !eq().apply(a, b);
    }

    // succ :: Integer -> Integer
    Function<Integer, Integer> succ = x -> x + 1;

    // pred :: Integer -> Integer
    Function<Integer, Integer> pred = x -> x - 1;

    // fst :: (a, b) -> a
    static <A, B> Function<Tuple<A, B>, A> fst() {
        return Tuple::first;
    }

    // snd :: (a, b) -> b
    static <A, B> Function<Tuple<A, B>, B> snd() {
        return Tuple::second;
    }

    // curry :: ((a,b) -> c) -> a -> b -> c
    static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> f) {
        return a -> b -> f.apply(a, b);
    }

    // uncurry :: (a -> b -> c) -> (a,b) -> c
    static <A, B, C> Function<Tuple<A, B>, C> uncurry(Function<A, Function<B, C>> f) {
        return p -> f.apply(p.first()).apply(p.second());
    }


}

