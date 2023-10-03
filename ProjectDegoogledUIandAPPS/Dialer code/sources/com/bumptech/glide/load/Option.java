package com.bumptech.glide.load;

import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.security.MessageDigest;

public final class Option<T> {
    private static final CacheKeyUpdater<Object> EMPTY_UPDATER = new CacheKeyUpdater<Object>() {
        public void update(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    };
    private final CacheKeyUpdater<T> cacheKeyUpdater;
    private final T defaultValue;
    private final String key;
    private volatile byte[] keyBytes;

    public interface CacheKeyUpdater<T> {
        void update(byte[] bArr, T t, MessageDigest messageDigest);
    }

    private Option(String str, T t, CacheKeyUpdater<T> cacheKeyUpdater2) {
        if (!TextUtils.isEmpty(str)) {
            this.key = str;
            this.defaultValue = t;
            R$style.checkNotNull(cacheKeyUpdater2, "Argument must not be null");
            this.cacheKeyUpdater = cacheKeyUpdater2;
            return;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }

    public static <T> Option<T> disk(String str, T t, CacheKeyUpdater<T> cacheKeyUpdater2) {
        return new Option<>(str, t, cacheKeyUpdater2);
    }

    public static <T> Option<T> memory(String str) {
        return new Option<>(str, (Object) null, EMPTY_UPDATER);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.key.equals(((Option) obj).key);
        }
        return false;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        String str = this.key;
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(str, 14));
        sb.append("Option{key='");
        sb.append(str);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void update(T t, MessageDigest messageDigest) {
        CacheKeyUpdater<T> cacheKeyUpdater2 = this.cacheKeyUpdater;
        if (this.keyBytes == null) {
            this.keyBytes = this.key.getBytes(Key.CHARSET);
        }
        cacheKeyUpdater2.update(this.keyBytes, t, messageDigest);
    }

    public static <T> Option<T> memory(String str, T t) {
        return new Option<>(str, t, EMPTY_UPDATER);
    }
}
