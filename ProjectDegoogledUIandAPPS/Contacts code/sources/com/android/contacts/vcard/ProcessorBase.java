package com.android.contacts.vcard;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

public abstract class ProcessorBase implements RunnableFuture<Object> {
    public abstract boolean cancel(boolean z);

    public abstract int getType();

    public abstract boolean isDone();

    public final Object get() {
        throw new UnsupportedOperationException();
    }

    public final Object get(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
}
