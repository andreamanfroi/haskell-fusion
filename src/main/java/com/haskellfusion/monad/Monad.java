package com.haskellfusion.monad;

import java.util.Objects;
import java.util.function.Function;

public abstract class Monad<T> {

    public abstract <R> Monad<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper);

    public <R> Monad<R> map(Function<? super T, ? extends R> mapper) {
        return flatMap(value -> new Just<>(mapper.apply(value)));
    }

    public static class Just<T> extends Monad<T> {
        private final T value;

        public Just(T value) {
            this.value = Objects.requireNonNull(value);
        }

        @Override
        public <R> Monad<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper) {
            return (Monad<R>) mapper.apply(value);
        }

        public T getValue() {
            return value;
        }
    }

    public static class Nothing<T> extends Monad<T> {

        @Override
        public <R> Monad<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper) {
            return new Nothing<>();
        }
    }
}
