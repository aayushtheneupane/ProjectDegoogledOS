package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool implements BitmapPool {
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private final Set<Bitmap.Config> allowedConfigs;
    private long currentSize;
    private int evictions;
    private int hits;
    private long maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker = new NullBitmapTracker();

    private interface BitmapTracker {
    }

    private static final class NullBitmapTracker implements BitmapTracker {
        NullBitmapTracker() {
        }

        public void add(Bitmap bitmap) {
        }

        public void remove(Bitmap bitmap) {
        }
    }

    public LruBitmapPool(long j) {
        int i = Build.VERSION.SDK_INT;
        SizeConfigStrategy sizeConfigStrategy = new SizeConfigStrategy();
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        int i2 = Build.VERSION.SDK_INT;
        hashSet.add((Object) null);
        int i3 = Build.VERSION.SDK_INT;
        hashSet.remove(Bitmap.Config.HARDWARE);
        Set<Bitmap.Config> unmodifiableSet = Collections.unmodifiableSet(hashSet);
        this.maxSize = j;
        this.strategy = sizeConfigStrategy;
        this.allowedConfigs = unmodifiableSet;
    }

    @TargetApi(26)
    private static void assertNotHardwareConfig(Bitmap.Config config) {
        int i = Build.VERSION.SDK_INT;
        if (config == Bitmap.Config.HARDWARE) {
            String valueOf = String.valueOf(config);
            StringBuilder sb = new StringBuilder(valueOf.length() + 176);
            sb.append("Cannot create a mutable Bitmap with config: ");
            sb.append(valueOf);
            sb.append(". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private void dump() {
        if (Log.isLoggable("LruBitmapPool", 2)) {
            dumpUnchecked();
        }
    }

    private void dumpUnchecked() {
        int i = this.hits;
        int i2 = this.misses;
        int i3 = this.puts;
        int i4 = this.evictions;
        long j = this.currentSize;
        long j2 = this.maxSize;
        String valueOf = String.valueOf(this.strategy);
        StringBuilder sb = new StringBuilder(valueOf.length() + 151);
        sb.append("Hits=");
        sb.append(i);
        sb.append(", misses=");
        sb.append(i2);
        sb.append(", puts=");
        sb.append(i3);
        sb.append(", evictions=");
        sb.append(i4);
        sb.append(", currentSize=");
        sb.append(j);
        sb.append(", maxSize=");
        sb.append(j2);
        sb.append("\nStrategy=");
        sb.append(valueOf);
        Log.v("LruBitmapPool", sb.toString());
    }

    private synchronized Bitmap getDirtyOrNull(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap;
        assertNotHardwareConfig(config);
        bitmap = this.strategy.get(i, i2, config != null ? config : DEFAULT_CONFIG);
        if (bitmap == null) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                String valueOf = String.valueOf(this.strategy.logBitmap(i, i2, config));
                Log.d("LruBitmapPool", valueOf.length() != 0 ? "Missing bitmap=".concat(valueOf) : new String("Missing bitmap="));
            }
            this.misses++;
        } else {
            this.hits++;
            this.currentSize -= (long) this.strategy.getSize(bitmap);
            ((NullBitmapTracker) this.tracker).remove(bitmap);
            bitmap.setHasAlpha(true);
            int i3 = Build.VERSION.SDK_INT;
            bitmap.setPremultiplied(true);
        }
        if (Log.isLoggable("LruBitmapPool", 2)) {
            String valueOf2 = String.valueOf(this.strategy.logBitmap(i, i2, config));
            Log.v("LruBitmapPool", valueOf2.length() != 0 ? "Get bitmap=".concat(valueOf2) : new String("Get bitmap="));
        }
        dump();
        return bitmap;
    }

    private synchronized void trimToSize(long j) {
        while (this.currentSize > j) {
            Bitmap removeLast = this.strategy.removeLast();
            if (removeLast == null) {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    dumpUnchecked();
                }
                this.currentSize = 0;
                return;
            }
            ((NullBitmapTracker) this.tracker).remove(removeLast);
            this.currentSize -= (long) this.strategy.getSize(removeLast);
            this.evictions++;
            if (Log.isLoggable("LruBitmapPool", 3)) {
                String valueOf = String.valueOf(this.strategy.logBitmap(removeLast));
                Log.d("LruBitmapPool", valueOf.length() != 0 ? "Evicting bitmap=".concat(valueOf) : new String("Evicting bitmap="));
            }
            dump();
            removeLast.recycle();
        }
    }

    public void clearMemory() {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        trimToSize(0);
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i, i2, config);
        if (dirtyOrNull != null) {
            dirtyOrNull.eraseColor(0);
            return dirtyOrNull;
        }
        if (config == null) {
            config = DEFAULT_CONFIG;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i, i2, config);
        if (dirtyOrNull != null) {
            return dirtyOrNull;
        }
        if (config == null) {
            config = DEFAULT_CONFIG;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void put(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    if (bitmap.isMutable() && ((long) this.strategy.getSize(bitmap)) <= this.maxSize) {
                        if (this.allowedConfigs.contains(bitmap.getConfig())) {
                            int size = this.strategy.getSize(bitmap);
                            this.strategy.put(bitmap);
                            ((NullBitmapTracker) this.tracker).add(bitmap);
                            this.puts++;
                            this.currentSize += (long) size;
                            if (Log.isLoggable("LruBitmapPool", 2)) {
                                String valueOf = String.valueOf(this.strategy.logBitmap(bitmap));
                                Log.v("LruBitmapPool", valueOf.length() != 0 ? "Put bitmap in pool=".concat(valueOf) : new String("Put bitmap in pool="));
                            }
                            dump();
                            trimToSize(this.maxSize);
                            return;
                        }
                    }
                    if (Log.isLoggable("LruBitmapPool", 2)) {
                        String logBitmap = this.strategy.logBitmap(bitmap);
                        boolean isMutable = bitmap.isMutable();
                        boolean contains = this.allowedConfigs.contains(bitmap.getConfig());
                        StringBuilder sb = new StringBuilder(String.valueOf(logBitmap).length() + 78);
                        sb.append("Reject bitmap from pool, bitmap: ");
                        sb.append(logBitmap);
                        sb.append(", is mutable: ");
                        sb.append(isMutable);
                        sb.append(", is allowed config: ");
                        sb.append(contains);
                        Log.v("LruBitmapPool", sb.toString());
                    }
                    bitmap.recycle();
                    return;
                }
                throw new IllegalStateException("Cannot pool recycled bitmap");
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new NullPointerException("Bitmap must not be null");
        }
    }

    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        if (Log.isLoggable("LruBitmapPool", 3)) {
            StringBuilder sb = new StringBuilder(29);
            sb.append("trimMemory, level=");
            sb.append(i);
            Log.d("LruBitmapPool", sb.toString());
        }
        if (i >= 40) {
            if (Log.isLoggable("LruBitmapPool", 3)) {
                Log.d("LruBitmapPool", "clearMemory");
            }
            trimToSize(0);
        } else if (i >= 20) {
            trimToSize(this.maxSize / 2);
        }
    }
}
