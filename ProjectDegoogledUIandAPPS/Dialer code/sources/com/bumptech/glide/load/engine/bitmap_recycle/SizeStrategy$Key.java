package com.bumptech.glide.load.engine.bitmap_recycle;

final class SizeStrategy$Key implements Poolable {
    private final SizeStrategy$KeyPool pool;
    int size;

    SizeStrategy$Key(SizeStrategy$KeyPool sizeStrategy$KeyPool) {
        this.pool = sizeStrategy$KeyPool;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SizeStrategy$Key) || this.size != ((SizeStrategy$Key) obj).size) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.size;
    }

    public void offer() {
        this.pool.offer(this);
    }

    public String toString() {
        int i = this.size;
        StringBuilder sb = new StringBuilder(13);
        sb.append("[");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }
}
