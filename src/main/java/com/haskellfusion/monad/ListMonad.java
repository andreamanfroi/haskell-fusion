package com.haskellfusion.monad;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListMonad<T> {

    private final List<T> values;

    public ListMonad(List<T> values) {
        this.values = values;
    }

    public <R> ListMonad<R> map(Function<? super T, ? extends R> mapper) {
        return new ListMonad<>(values.stream().map(mapper).collect(Collectors.toList()));
    }

    public <R> ListMonad<R> flatMap(Function<? super T, ListMonad<? extends R>> mapper) {
        List<R> result = new ArrayList<>();
        for (T value : values) {
            result.addAll(mapper.apply(value).values);
        }
        return new ListMonad<>(result);
    }

    public ListMonad<T> filter(Function<? super T, Boolean> predicate) {
        return new ListMonad<>(values.stream().filter(predicate::apply).collect(Collectors.toList()));
    }

    public List<T> toList() {
        return new ArrayList<>(values);
    }

    public static <T> ListMonad<T> of(List<T> values) {
        return new ListMonad<>(values);
    }
}

