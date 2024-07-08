package com.haskellfusion.foldable;

import java.util.List;
import java.util.function.BiFunction;

public interface Foldable<T> {
    <U> U foldr(BiFunction<? super T, ? super U, ? extends U> f, U initial);
    <U> U foldl(BiFunction<? super U, ? super T, ? extends U> f, U initial);
}


//examples
class ListFoldable<T> implements Foldable<T> {
    private final List<T> values;

    public ListFoldable(List<T> values) {
        this.values = values;
    }

    @Override
    public <U> U foldr(BiFunction<? super T, ? super U, ? extends U> f, U initial) {
        U result = initial;
        for (int i = values.size() - 1; i >= 0; i--) {
            result = f.apply(values.get(i), result);
        }
        return result;
    }

    @Override
    public <U> U foldl(BiFunction<? super U, ? super T, ? extends U> f, U initial) {
        U result = initial;
        for (T value : values) {
            result = f.apply(result, value);
        }
        return result;
    }

    public List<T> getValues() {
        return values;
    }

    public static <T> ListFoldable<T> of(List<T> values) {
        return new ListFoldable<>(values);
    }
}

