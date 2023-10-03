package com.google.common.base;

import com.google.common.base.Splitter;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class AbstractIterator<T> implements Iterator<T> {
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
    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    public final boolean hasNext() {
        T t;
        int i;
        MoreObjects.checkState(this.state != State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal == 2) {
            return false;
        }
        this.state = State.FAILED;
        Splitter.SplittingIterator splittingIterator = (Splitter.SplittingIterator) this;
        int i2 = splittingIterator.offset;
        while (true) {
            int i3 = splittingIterator.offset;
            if (i3 == -1) {
                splittingIterator.endOfData();
                t = null;
                break;
            }
            int separatorStart = splittingIterator.separatorStart(i3);
            if (separatorStart == -1) {
                separatorStart = splittingIterator.toSplit.length();
                splittingIterator.offset = -1;
            } else {
                splittingIterator.offset = splittingIterator.separatorEnd(separatorStart);
            }
            int i4 = splittingIterator.offset;
            if (i4 == i2) {
                splittingIterator.offset = i4 + 1;
                if (splittingIterator.offset > splittingIterator.toSplit.length()) {
                    splittingIterator.offset = -1;
                }
            } else {
                while (i2 < separatorStart && splittingIterator.trimmer.matches(splittingIterator.toSplit.charAt(i2))) {
                    i2++;
                }
                while (i > i2) {
                    int i5 = i - 1;
                    if (!splittingIterator.trimmer.matches(splittingIterator.toSplit.charAt(i5))) {
                        break;
                    }
                    separatorStart = i5;
                }
                if (!splittingIterator.omitEmptyStrings || i2 != i) {
                    int i6 = splittingIterator.limit;
                } else {
                    i2 = splittingIterator.offset;
                }
            }
        }
        int i62 = splittingIterator.limit;
        if (i62 == 1) {
            i = splittingIterator.toSplit.length();
            splittingIterator.offset = -1;
            while (i > i2) {
                int i7 = i - 1;
                if (!splittingIterator.trimmer.matches(splittingIterator.toSplit.charAt(i7))) {
                    break;
                }
                i = i7;
            }
        } else {
            splittingIterator.limit = i62 - 1;
        }
        t = splittingIterator.toSplit.subSequence(i2, i).toString();
        this.next = t;
        if (this.state == State.DONE) {
            return false;
        }
        this.state = State.READY;
        return true;
    }

    public final T next() {
        T t;
        int i;
        boolean z = true;
        MoreObjects.checkState(this.state != State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                this.state = State.FAILED;
                Splitter.SplittingIterator splittingIterator = (Splitter.SplittingIterator) this;
                int i2 = splittingIterator.offset;
                while (true) {
                    int i3 = splittingIterator.offset;
                    if (i3 == -1) {
                        splittingIterator.endOfData();
                        t = null;
                        break;
                    }
                    int separatorStart = splittingIterator.separatorStart(i3);
                    if (separatorStart == -1) {
                        separatorStart = splittingIterator.toSplit.length();
                        splittingIterator.offset = -1;
                    } else {
                        splittingIterator.offset = splittingIterator.separatorEnd(separatorStart);
                    }
                    int i4 = splittingIterator.offset;
                    if (i4 == i2) {
                        splittingIterator.offset = i4 + 1;
                        if (splittingIterator.offset > splittingIterator.toSplit.length()) {
                            splittingIterator.offset = -1;
                        }
                    } else {
                        while (i2 < separatorStart && splittingIterator.trimmer.matches(splittingIterator.toSplit.charAt(i2))) {
                            i2++;
                        }
                        while (i > i2) {
                            int i5 = i - 1;
                            if (!splittingIterator.trimmer.matches(splittingIterator.toSplit.charAt(i5))) {
                                break;
                            }
                            separatorStart = i5;
                        }
                        if (!splittingIterator.omitEmptyStrings || i2 != i) {
                            int i6 = splittingIterator.limit;
                        } else {
                            i2 = splittingIterator.offset;
                        }
                    }
                }
                int i62 = splittingIterator.limit;
                if (i62 == 1) {
                    i = splittingIterator.toSplit.length();
                    splittingIterator.offset = -1;
                    while (i > i2) {
                        int i7 = i - 1;
                        if (!splittingIterator.trimmer.matches(splittingIterator.toSplit.charAt(i7))) {
                            break;
                        }
                        i = i7;
                    }
                } else {
                    splittingIterator.limit = i62 - 1;
                }
                t = splittingIterator.toSplit.subSequence(i2, i).toString();
                this.next = t;
                if (this.state != State.DONE) {
                    this.state = State.READY;
                }
            }
            z = false;
        }
        if (z) {
            this.state = State.NOT_READY;
            T t2 = this.next;
            this.next = null;
            return t2;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
