package com.bumptech.glide.load.resource;

import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.engine.Resource;

public class SimpleResource<T> implements Resource<T> {
    protected final T data;

    public SimpleResource(T t) {
        R$style.checkNotNull(t, "Argument must not be null");
        this.data = t;
    }

    public final T get() {
        return this.data;
    }

    public Class<T> getResourceClass() {
        return this.data.getClass();
    }

    public final int getSize() {
        return 1;
    }

    public void recycle() {
    }
}
