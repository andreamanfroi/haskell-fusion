package com.haskellfusion.maybe;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Maybe<T> {

    private Maybe() {}

    public abstract boolean isJust();

    public abstract boolean isNothing();

    public abstract T get() throws NoSuchElementException;

    public static <T> Maybe<T> just(T value) {
        return new Just<>(value);
    }

    public static <T> Maybe<T> nothing() {
        return new Nothing<>();
    }

    public T orElse(T other) {
        return isJust() ? get() : other;
    }

    public T orElseGet(Supplier<? extends T> other) {
        return isJust() ? get() : other.get();
    }

    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        return isJust() ? just(mapper.apply(get())) : nothing();
    }

    public <U> Maybe<U> flatMap(Function<? super T, Maybe<U>> mapper) {
        return isJust() ? mapper.apply(get()) : nothing();
    }

    public void ifJust(Consumer<? super T> consumer) {
        if (isJust()) {
            consumer.accept(get());
        }
    }

    private static final class Just<T> extends Maybe<T> {
        private final T value;

        private Just(T value) {
            this.value = Objects.requireNonNull(value);
        }

        @Override
        public boolean isJust() {
            return true;
        }

        @Override
        public boolean isNothing() {
            return false;
        }

        @Override
        public T get() {
            return value;
        }
    }

    private static final class Nothing<T> extends Maybe<T> {

        @Override
        public boolean isJust() {
            return false;
        }

        @Override
        public boolean isNothing() {
            return true;
        }

        @Override
        public T get() {
            throw new NoSuchElementException("No value present");
        }
    }
}

