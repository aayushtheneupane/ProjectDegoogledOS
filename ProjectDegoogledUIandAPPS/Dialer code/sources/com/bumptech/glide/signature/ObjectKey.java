package com.bumptech.glide.signature;

import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

public final class ObjectKey implements Key {
    private final Object object;

    public ObjectKey(Object obj) {
        R$style.checkNotNull(obj, "Argument must not be null");
        this.object = obj;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ObjectKey) {
            return this.object.equals(((ObjectKey) obj).object);
        }
        return false;
    }

    public int hashCode() {
        return this.object.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(this.object);
        StringBuilder sb = new StringBuilder(valueOf.length() + 18);
        sb.append("ObjectKey{object=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(this.object.toString().getBytes(Key.CHARSET));
    }
}
