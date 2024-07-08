package com.haskellfusion.semigroup;

@FunctionalInterface
public interface Semigroup<T> {
    T combine(T other);
}

