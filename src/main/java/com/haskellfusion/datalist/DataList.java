package com.haskellfusion.datalist;

import com.haskellfusion.tuple.Tuple;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class DataList {

    private DataList() {
    }

    // map :: (a -> b) -> List a -> List b
    public static <A, B> List<B> map(Function<? super A, ? extends B> f, List<A> list) {
        return list.stream().map(f).collect(Collectors.toList());
    }

    // filter :: (a -> Bool) -> List a -> List a
    public static <A> List<A> filter(Predicate<? super A> predicate, List<A> list) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    // foldr :: (a -> b -> b) -> b -> List a -> b
    public static <A, B> B foldr(BiFunction<A, B, B> f, B base, List<A> list) {
        ListIterator<A> it = list.listIterator(list.size());
        B result = base;
        while (it.hasPrevious()) {
            result = f.apply(it.previous(), result);
        }
        return result;
    }

    // foldl :: (b -> a -> b) -> b -> List a -> b
    public static <A, B> B foldl(BiFunction<B, A, B> f, B base, List<A> list) {
        B result = base;
        for (A a : list) {
            result = f.apply(result, a);
        }
        return result;
    }

    // take :: Int -> List a -> List a
    public static <A> List<A> take(int n, List<A> list) {
        return list.stream().limit(n).toList();
    }

    // drop :: Int -> List a -> List a
    public static <A> List<A> drop(int n, List<A> list) {
        return list.stream().skip(n).toList();
    }

    // zip :: List a -> List b -> List (Tuple a b)
    public static <A, B> List<Tuple<A, B>> zip(List<A> listA, List<B> listB) {
        int size = Math.min(listA.size(), listB.size());
        List<Tuple<A, B>> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(new Tuple<>(listA.get(i), listB.get(i)));
        }
        return result;
    }

    // reverse :: List a -> List a
    public static <A> List<A> reverse(List<A> list) {
        List<A> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }

    // head :: List a -> a
    public static <A> A head(List<A> list) {
        if (list.isEmpty()) throw new NoSuchElementException("head: empty list");
        return list.getFirst();
    }

    // tail :: List a -> List a
    public static <A> List<A> tail(List<A> list) {
        if (list.isEmpty()) throw new NoSuchElementException("tail: empty list");
        return list.subList(1, list.size());
    }

    // init :: List a -> List a
    public static <A> List<A> init(List<A> list) {
        if (list.isEmpty()) throw new NoSuchElementException("init: empty list");
        return list.subList(0, list.size() - 1);
    }

    // last :: List a -> a
    public static <A> A last(List<A> list) {
        if (list.isEmpty()) throw new NoSuchElementException("last: empty list");
        return list.getLast();
    }

    // length :: List a -> Int
    public static <A> int length(List<A> list) {
        return list.size();
    }

    // null :: List a -> Bool
    public static <A> boolean isNull(List<A> list) {
        return list.isEmpty();
    }

    // sum :: Num a => List a -> a
    public static int sum(List<Integer> list) {
        return list.stream().mapToInt(Number::intValue).sum();
    }

    // product :: Num a => List a -> a
    public static int product(List<Integer> list) {
        return list.stream().reduce(1, (a, b) -> a *b);
    }

    // elem :: Eq a => a -> List a -> Bool
    public static <A> boolean elem(A value, List<A> list) {
        return list.contains(value);
    }
}

