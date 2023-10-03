package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;

public final class InternalCacheDiskCacheFactory extends DiskLruCacheFactory {
    public InternalCacheDiskCacheFactory(final Context context) {
        super(new DiskLruCacheFactory.CacheDirectoryGetter("image_manager_disk_cache") {
        }, 262144000);
    }
}
