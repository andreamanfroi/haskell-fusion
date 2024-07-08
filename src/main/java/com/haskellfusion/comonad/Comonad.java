package com.haskellfusion.comonad;

import java.util.function.Function;

public interface Comonad<W, A> {
    A extract();

    <B> Comonad<W, B> map(Function<? super A, ? extends B> fn);

    <B> Comonad<W, B> coflatMap(Function<? super Comonad<W, A>, ? extends B> fn);
}

