package com.bumptech.glide.load.engine.prefill;

import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.util.LruCache;
import java.util.HashMap;

public final class BitmapPreFiller {
    private final BitmapPool bitmapPool;
    private final MemoryCache memoryCache;

    public BitmapPreFiller(MemoryCache memoryCache2, BitmapPool bitmapPool2, DecodeFormat decodeFormat) {
        new Handler(Looper.getMainLooper());
        this.memoryCache = memoryCache2;
        this.bitmapPool = bitmapPool2;
    }

    /* access modifiers changed from: package-private */
    public PreFillQueue generateAllocationOrder(PreFillType... preFillTypeArr) {
        ((LruCache) this.memoryCache).getMaxSize();
        ((LruCache) this.memoryCache).getCurrentSize();
        this.bitmapPool.getMaxSize();
        if (preFillTypeArr.length <= 0) {
            HashMap hashMap = new HashMap();
            if (preFillTypeArr.length <= 0) {
                return new PreFillQueue(hashMap);
            }
            PreFillType preFillType = preFillTypeArr[0];
            throw null;
        }
        PreFillType preFillType2 = preFillTypeArr[0];
        throw null;
    }
}
