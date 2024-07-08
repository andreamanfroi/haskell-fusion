package com.haskellfusion.lens;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Lens<A, B> {
    private final Function<A, B> getter;
    private final BiFunction<A, B, A> setter;

    public Lens(Function<A, B> getter, BiFunction<A, B, A> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public B get(A a) {
        return getter.apply(a);
    }

    public A set(A a, B b) {
        return setter.apply(a, b);
    }

    public <C> Lens<A, C> compose(Lens<B, C> other) {
        return new Lens<>(a -> other.get(getter.apply(a)),
                (a, c) -> setter.apply(a, other.set(getter.apply(a), c)));
    }
}

