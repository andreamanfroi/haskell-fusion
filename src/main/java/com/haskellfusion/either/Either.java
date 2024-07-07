package com.haskellfusion.either;

import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class Either<L, R> {
    private Either() {}

    public abstract boolean isLeft();
    public abstract boolean isRight();
    public abstract L getLeft();
    public abstract R getRight();

    public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> mapper) {
        if (isLeft()) {
            return new Left<>(mapper.apply(getLeft()));
        } else {
            return new Right<>(getRight());
        }
    }

    public <T> Either<L, T> mapRight(Function<? super R, ? extends T> mapper) {
        if (isRight()) {
            return new Right<>(mapper.apply(getRight()));
        } else {
            return new Left<>(getLeft());
        }
    }

    public <T> Either<T, R> flatMapLeft(Function<? super L, Either<T, R>> mapper) {
        if (isLeft()) {
            return mapper.apply(getLeft());
        } else {
            return new Right<>(getRight());
        }
    }

    public <T> Either<L, T> flatMapRight(Function<? super R, Either<L, T>> mapper) {
        if (isRight()) {
            return mapper.apply(getRight());
        } else {
            return new Left<>(getLeft());
        }
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    public static final class Left<L, R> extends Either<L, R> {
        private final L value;

        private Left(L value) {
            this.value = value;
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public L getLeft() {
            return value;
        }

        @Override
        public R getRight() {
            throw new NoSuchElementException("No right value present");
        }
    }

    public static final class Right<L, R> extends Either<L, R> {
        private final R value;

        private Right(R value) {
            this.value = value;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public L getLeft() {
            throw new NoSuchElementException("No left value present");
        }

        @Override
        public R getRight() {
            return value;
        }
    }
}
