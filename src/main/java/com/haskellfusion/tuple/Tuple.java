package com.haskellfusion.tuple;

import java.util.Objects;

public record Tuple<T1, T2>(T1 first, T2 second) {

    public Tuple(T1 first, T2 second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

}
