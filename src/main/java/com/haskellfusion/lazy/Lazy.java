package com.haskellfusion.lazy;

import java.util.function.Supplier;

public class Lazy<T> {
    private final Supplier<T> supplier;
    private T value;
    private boolean isEvaluated;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    public T get() {
        if (!isEvaluated) {
            value = supplier.get();
            isEvaluated = true;
        }
        return value;
    }
}

