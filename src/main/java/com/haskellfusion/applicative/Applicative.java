package com.haskellfusion.applicative;


import com.haskellfusion.functor.Functor;

import java.util.Objects;
import java.util.function.Function;

public interface Applicative<T, F extends Applicative<?, ?>> extends Functor<T, F> {

    <R> Applicative<R, F> ap(Applicative<Function<? super T, ? extends R>, F> fn);

    <R> Applicative<R, F> pure(R value);
}

//examples
abstract class Maybe<T> implements Applicative<T, Maybe<?>> {

    public abstract <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper);

    @Override
    public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        return flatMap(value -> new Just<>(mapper.apply(value)));
    }

    @Override
    public <R> Maybe<R> ap(Applicative<Function<? super T, ? extends R>, Maybe<?>> fn) {
        if (this instanceof Nothing || fn instanceof Nothing) {
            return new Nothing<>();
        } else {
            Just<Function<? super T, ? extends R>> justFn = (Just<Function<? super T, ? extends R>>) fn;
            Just<T> justValue = (Just<T>) this;
            return new Just<>(justFn.getValue().apply(justValue.getValue()));
        }
    }

    @Override
    public <R> Maybe<R> pure(R value) {
        return new Just<>(value);
    }

    public static class Just<T> extends Maybe<T> {
        private final T value;

        public Just(T value) {
            this.value = Objects.requireNonNull(value);
        }

        @Override
        public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
            return (Maybe<R>) mapper.apply(value);
        }

        public T getValue() {
            return value;
        }
    }

    public static class Nothing<T> extends Maybe<T> {

        @Override
        public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
            return new Nothing<>();
        }
    }
}

