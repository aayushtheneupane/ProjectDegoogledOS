package com.bumptech.glide.load.engine.bitmap_recycle;

public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {
    public int getArrayLength(Object obj) {
        return ((int[]) obj).length;
    }

    public int getElementSizeInBytes() {
        return 4;
    }

    public String getTag() {
        return "IntegerArrayPool";
    }

    public Object newArray(int i) {
        return new int[i];
    }
}
