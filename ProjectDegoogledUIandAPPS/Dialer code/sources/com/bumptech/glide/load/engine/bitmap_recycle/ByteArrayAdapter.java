package com.bumptech.glide.load.engine.bitmap_recycle;

public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {
    public int getArrayLength(Object obj) {
        return ((byte[]) obj).length;
    }

    public int getElementSizeInBytes() {
        return 1;
    }

    public String getTag() {
        return "ByteArrayPool";
    }

    public Object newArray(int i) {
        return new byte[i];
    }
}
