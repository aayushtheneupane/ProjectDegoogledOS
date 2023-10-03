package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Striped<L> {

    static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
    }

    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
    }

    static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
    }

    static {
        new Supplier<ReadWriteLock>() {
            public Object get() {
                return new ReentrantReadWriteLock();
            }
        };
    }

    private Striped() {
    }
}
