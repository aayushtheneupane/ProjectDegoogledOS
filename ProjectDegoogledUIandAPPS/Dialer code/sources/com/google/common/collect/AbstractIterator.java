package com.google.common.collect;

import com.google.common.base.MoreObjects;
import java.util.NoSuchElementException;

public abstract class AbstractIterator<T> extends UnmodifiableIterator<T> {
    private T next;
    private State state = State.NOT_READY;

    private enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    protected AbstractIterator() {
    }

    /* access modifiers changed from: protected */
    public abstract T computeNext();

    /* access modifiers changed from: protected */
    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    public final boolean hasNext() {
        MoreObjects.checkState(this.state != State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal == 2) {
            return false;
        }
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state == State.DONE) {
            return false;
        }
        this.state = State.READY;
        return true;
    }

    public final T next() {
        boolean z = true;
        MoreObjects.checkState(this.state != State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                this.state = State.FAILED;
                this.next = computeNext();
                if (this.state != State.DONE) {
                    this.state = State.READY;
                }
            }
            z = false;
        }
        if (z) {
            this.state = State.NOT_READY;
            T t = this.next;
            this.next = null;
            return t;
        }
        throw new NoSuchElementException();
    }
}
