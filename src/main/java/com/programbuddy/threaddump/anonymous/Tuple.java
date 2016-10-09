package com.programbuddy.threaddump.anonymous;

public class Tuple<T, U> {
    private T el1;
    private U el2;

    public Tuple(T el1, U el2) {
        this.el1 = el1;
        this.el2 = el2;
    }

    public T getEl1() {
        return el1;
    }

    public U getEl2() {
        return el2;
    }
}
