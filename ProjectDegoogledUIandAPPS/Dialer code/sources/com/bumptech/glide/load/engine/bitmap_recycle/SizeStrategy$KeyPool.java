package com.bumptech.glide.load.engine.bitmap_recycle;

class SizeStrategy$KeyPool extends BaseKeyPool<SizeStrategy$Key> {
    SizeStrategy$KeyPool() {
    }

    /* access modifiers changed from: protected */
    public SizeStrategy$Key create() {
        return new SizeStrategy$Key(this);
    }
}
