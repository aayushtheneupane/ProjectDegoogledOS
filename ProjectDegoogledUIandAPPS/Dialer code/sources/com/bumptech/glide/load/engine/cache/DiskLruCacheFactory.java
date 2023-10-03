package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import java.io.File;

public class DiskLruCacheFactory implements DiskCache.Factory {
    private final CacheDirectoryGetter cacheDirectoryGetter;
    private final long diskCacheSize;

    public interface CacheDirectoryGetter {
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter2, long j) {
        this.diskCacheSize = j;
        this.cacheDirectoryGetter = cacheDirectoryGetter2;
    }

    public DiskCache build() {
        InternalCacheDiskCacheFactory.C08131 r0 = (InternalCacheDiskCacheFactory.C08131) this.cacheDirectoryGetter;
        File cacheDir = r4.getCacheDir();
        if (cacheDir == null) {
            cacheDir = null;
        } else {
            String str = "image_manager_disk_cache";
            if (str != null) {
                cacheDir = new File(cacheDir, str);
            }
        }
        if (cacheDir == null) {
            return null;
        }
        if (cacheDir.mkdirs() || (cacheDir.exists() && cacheDir.isDirectory())) {
            return new DiskLruCacheWrapper(cacheDir, this.diskCacheSize);
        }
        return null;
    }
}
