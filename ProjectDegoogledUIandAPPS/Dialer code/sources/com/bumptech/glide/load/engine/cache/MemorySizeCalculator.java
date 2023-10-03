package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class MemorySizeCalculator {
    static final int BYTES_PER_ARGB_8888_PIXEL = 4;
    private final int arrayPoolSize;
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    public static final class Builder {
        static final int BITMAP_POOL_TARGET_SCREENS = 1;
        static final int MEMORY_CACHE_TARGET_SCREENS = 2;
        ActivityManager activityManager;
        int arrayPoolSizeBytes = 4194304;
        float bitmapPoolScreens = ((float) BITMAP_POOL_TARGET_SCREENS);
        final Context context;
        float lowMemoryMaxSizeMultiplier = 0.33f;
        float maxSizeMultiplier = 0.4f;
        float memoryCacheScreens = 2.0f;
        ScreenDimensions screenDimensions;

        static {
            int i = Build.VERSION.SDK_INT;
        }

        public Builder(Context context2) {
            this.context = context2;
            this.activityManager = (ActivityManager) context2.getSystemService("activity");
            this.screenDimensions = new DisplayMetricsScreenDimensions(context2.getResources().getDisplayMetrics());
            int i = Build.VERSION.SDK_INT;
            if (MemorySizeCalculator.isLowMemoryDevice(this.activityManager)) {
                this.bitmapPoolScreens = 0.0f;
            }
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }

        /* access modifiers changed from: package-private */
        public Builder setActivityManager(ActivityManager activityManager2) {
            this.activityManager = activityManager2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setScreenDimensions(ScreenDimensions screenDimensions2) {
            this.screenDimensions = screenDimensions2;
            return this;
        }
    }

    private static final class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics2) {
            this.displayMetrics = displayMetrics2;
        }

        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }

        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }
    }

    interface ScreenDimensions {
    }

    MemorySizeCalculator(Builder builder) {
        int i;
        this.context = builder.context;
        if (isLowMemoryDevice(builder.activityManager)) {
            i = builder.arrayPoolSizeBytes / 2;
        } else {
            i = builder.arrayPoolSizeBytes;
        }
        this.arrayPoolSize = i;
        ActivityManager activityManager = builder.activityManager;
        float f = builder.maxSizeMultiplier;
        int round = Math.round(((float) (activityManager.getMemoryClass() * 1024 * 1024)) * (isLowMemoryDevice(activityManager) ? builder.lowMemoryMaxSizeMultiplier : f));
        float widthPixels = (float) (((DisplayMetricsScreenDimensions) builder.screenDimensions).getWidthPixels() * ((DisplayMetricsScreenDimensions) builder.screenDimensions).getHeightPixels() * BYTES_PER_ARGB_8888_PIXEL);
        int round2 = Math.round(builder.bitmapPoolScreens * widthPixels);
        int round3 = Math.round(widthPixels * builder.memoryCacheScreens);
        int i2 = round - this.arrayPoolSize;
        int i3 = round3 + round2;
        if (i3 <= i2) {
            this.memoryCacheSize = round3;
            this.bitmapPoolSize = round2;
        } else {
            float f2 = (float) i2;
            float f3 = builder.bitmapPoolScreens;
            float f4 = builder.memoryCacheScreens;
            float f5 = f2 / (f3 + f4);
            this.memoryCacheSize = Math.round(f4 * f5);
            this.bitmapPoolSize = Math.round(f5 * builder.bitmapPoolScreens);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            String mb = toMb(this.memoryCacheSize);
            String mb2 = toMb(this.bitmapPoolSize);
            String mb3 = toMb(this.arrayPoolSize);
            boolean z = i3 > round;
            String mb4 = toMb(round);
            int memoryClass = builder.activityManager.getMemoryClass();
            boolean isLowMemoryDevice = isLowMemoryDevice(builder.activityManager);
            StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(mb4, GeneratedOutlineSupport.outline1(mb3, GeneratedOutlineSupport.outline1(mb2, GeneratedOutlineSupport.outline1(mb, 177)))));
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(mb);
            sb.append(", pool size: ");
            sb.append(mb2);
            sb.append(", byte array size: ");
            sb.append(mb3);
            sb.append(", memory class limited? ");
            sb.append(z);
            sb.append(", max size: ");
            sb.append(mb4);
            sb.append(", memoryClass: ");
            sb.append(memoryClass);
            sb.append(", isLowMemoryDevice: ");
            sb.append(isLowMemoryDevice);
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }

    @TargetApi(19)
    static boolean isLowMemoryDevice(ActivityManager activityManager) {
        int i = Build.VERSION.SDK_INT;
        return activityManager.isLowRamDevice();
    }

    private String toMb(int i) {
        return Formatter.formatFileSize(this.context, (long) i);
    }

    public int getArrayPoolSizeInBytes() {
        return this.arrayPoolSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }
}
