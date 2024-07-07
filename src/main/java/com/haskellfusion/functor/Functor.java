package com.haskellfusion.functor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Functor<T, F extends Functor<?, ?>> {
    <R> Functor<R, F> map(Function<? super T, ? extends R> mapper);
}


//examples...

class ListFunctor<T> implements Functor<T, ListFunctor<?>> {
    private final List<T> values;

    public ListFunctor(List<T> values) {
        this.values = values;
    }

    @Override
    public <R> ListFunctor<R> map(Function<? super T, ? extends R> mapper) {
        return new ListFunctor<>(values.stream().map(mapper).collect(Collectors.toList()));
    }

    public List<T> getValues() {
        return new ArrayList<>(values);
    }

    public static <T> ListFunctor<T> of(List<T> values) {
        return new ListFunctor<>(values);
    }
}


abstract class Maybe<T> implements Functor<T, Maybe<?>> {

    public abstract <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper);

    public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        return flatMap(value -> new Just<>(mapper.apply(value)));
    }

    public static class Just<T> extends Maybe<T> {
        private final T value;

        public Just(T value) {
            this.value = value;
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

