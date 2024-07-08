package com.haskellfusion.traversable;


import com.haskellfusion.applicative.Applicative;
import com.haskellfusion.foldable.Foldable;
import com.haskellfusion.functor.Functor;

import java.util.function.Function;

public interface Traversable<T> extends Functor<T, Traversable<?>>, Foldable<T> {
    <R, F extends Applicative<?, ?>> Applicative<Traversable<R>, F> traverse(Function<? super T, ? extends Applicative<R, F>> f);
}

