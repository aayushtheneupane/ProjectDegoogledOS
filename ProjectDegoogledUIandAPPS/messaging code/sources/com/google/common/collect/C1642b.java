package com.google.common.collect;

import com.google.common.base.C1508E;
import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.b */
public abstract class C1642b extends C1692rb {
    private Object next;
    private AbstractIterator$State state = AbstractIterator$State.NOT_READY;

    protected C1642b() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: Gl */
    public abstract Object mo9135Gl();

    /* access modifiers changed from: protected */
    /* renamed from: Hl */
    public final Object mo9136Hl() {
        this.state = AbstractIterator$State.DONE;
        return null;
    }

    public final boolean hasNext() {
        C1508E.checkState(this.state != AbstractIterator$State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal == 2) {
            return false;
        }
        this.state = AbstractIterator$State.FAILED;
        this.next = mo9135Gl();
        if (this.state == AbstractIterator$State.DONE) {
            return false;
        }
        this.state = AbstractIterator$State.READY;
        return true;
    }

    public final Object next() {
        boolean z = true;
        C1508E.checkState(this.state != AbstractIterator$State.FAILED);
        int ordinal = this.state.ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                this.state = AbstractIterator$State.FAILED;
                this.next = mo9135Gl();
                if (this.state != AbstractIterator$State.DONE) {
                    this.state = AbstractIterator$State.READY;
                }
            }
            z = false;
        }
        if (z) {
            this.state = AbstractIterator$State.NOT_READY;
            Object obj = this.next;
            this.next = null;
            return obj;
        }
        throw new NoSuchElementException();
    }
}
