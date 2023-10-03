package com.bumptech.glide.load;

import android.support.p000v4.util.ArrayMap;
import java.security.MessageDigest;

public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> values = new ArrayMap<>();

    public boolean equals(Object obj) {
        if (obj instanceof Options) {
            return this.values.equals(((Options) obj).values);
        }
        return false;
    }

    public <T> T get(Option<T> option) {
        return this.values.indexOfKey(option) >= 0 ? this.values.get(option) : option.getDefaultValue();
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public void putAll(Options options) {
        this.values.putAll(options.values);
    }

    public <T> Options set(Option<T> option, T t) {
        this.values.put(option, t);
        return this;
    }

    public String toString() {
        String valueOf = String.valueOf(this.values);
        StringBuilder sb = new StringBuilder(valueOf.length() + 16);
        sb.append("Options{values=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (int i = 0; i < this.values.size(); i++) {
            this.values.keyAt(i).update(this.values.valueAt(i), messageDigest);
        }
    }
}
