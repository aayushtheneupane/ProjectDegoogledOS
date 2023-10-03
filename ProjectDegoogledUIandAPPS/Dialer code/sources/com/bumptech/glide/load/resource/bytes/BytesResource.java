package com.bumptech.glide.load.resource.bytes;

import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.engine.Resource;

public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    public BytesResource(byte[] bArr) {
        R$style.checkNotNull(bArr, "Argument must not be null");
        this.bytes = bArr;
    }

    public Object get() {
        return this.bytes;
    }

    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    public int getSize() {
        return this.bytes.length;
    }

    public void recycle() {
    }
}
