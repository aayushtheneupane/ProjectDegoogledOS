package com.bumptech.glide.load.engine;

import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import java.security.MessageDigest;
import java.util.Map;

class EngineKey implements Key {
    private int hashCode;
    private final int height;
    private final Object model;
    private final Options options;
    private final Class<?> resourceClass;
    private final Key signature;
    private final Class<?> transcodeClass;
    private final Map<Class<?>, Transformation<?>> transformations;
    private final int width;

    EngineKey(Object obj, Key key, int i, int i2, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options2) {
        R$style.checkNotNull(obj, "Argument must not be null");
        this.model = obj;
        R$style.checkNotNull(key, "Signature must not be null");
        this.signature = key;
        this.width = i;
        this.height = i2;
        R$style.checkNotNull(map, "Argument must not be null");
        this.transformations = map;
        R$style.checkNotNull(cls, "Resource class must not be null");
        this.resourceClass = cls;
        R$style.checkNotNull(cls2, "Transcode class must not be null");
        this.transcodeClass = cls2;
        R$style.checkNotNull(options2, "Argument must not be null");
        this.options = options2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EngineKey)) {
            return false;
        }
        EngineKey engineKey = (EngineKey) obj;
        if (!this.model.equals(engineKey.model) || !this.signature.equals(engineKey.signature) || this.height != engineKey.height || this.width != engineKey.width || !this.transformations.equals(engineKey.transformations) || !this.resourceClass.equals(engineKey.resourceClass) || !this.transcodeClass.equals(engineKey.transcodeClass) || !this.options.equals(engineKey.options)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.model.hashCode();
            this.hashCode = this.signature.hashCode() + (this.hashCode * 31);
            this.hashCode = (this.hashCode * 31) + this.width;
            this.hashCode = (this.hashCode * 31) + this.height;
            this.hashCode = this.transformations.hashCode() + (this.hashCode * 31);
            this.hashCode = this.resourceClass.hashCode() + (this.hashCode * 31);
            this.hashCode = this.transcodeClass.hashCode() + (this.hashCode * 31);
            this.hashCode = this.options.hashCode() + (this.hashCode * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        String valueOf = String.valueOf(this.model);
        int i = this.width;
        int i2 = this.height;
        String valueOf2 = String.valueOf(this.resourceClass);
        String valueOf3 = String.valueOf(this.transcodeClass);
        String valueOf4 = String.valueOf(this.signature);
        int i3 = this.hashCode;
        String valueOf5 = String.valueOf(this.transformations);
        String valueOf6 = String.valueOf(this.options);
        StringBuilder sb = new StringBuilder(valueOf6.length() + valueOf5.length() + valueOf4.length() + valueOf3.length() + valueOf2.length() + valueOf.length() + 151);
        sb.append("EngineKey{model=");
        sb.append(valueOf);
        sb.append(", width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append(", resourceClass=");
        sb.append(valueOf2);
        sb.append(", transcodeClass=");
        sb.append(valueOf3);
        sb.append(", signature=");
        sb.append(valueOf4);
        sb.append(", hashCode=");
        sb.append(i3);
        sb.append(", transformations=");
        sb.append(valueOf5);
        sb.append(", options=");
        sb.append(valueOf6);
        sb.append('}');
        return sb.toString();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
