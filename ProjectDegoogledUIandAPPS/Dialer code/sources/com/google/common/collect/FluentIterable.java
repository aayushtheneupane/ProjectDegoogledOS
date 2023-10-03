package com.google.common.collect;

import com.google.common.base.Optional;

public abstract class FluentIterable<E> implements Iterable<E> {
    private final Optional<Iterable<E>> iterableDelegate = Optional.absent();

    protected FluentIterable() {
    }

    public String toString() {
        return Collections2.toString(this.iterableDelegate.mo10247or(this).iterator());
    }
}
