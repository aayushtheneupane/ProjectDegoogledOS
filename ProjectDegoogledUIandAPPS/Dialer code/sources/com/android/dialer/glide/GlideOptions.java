package com.android.dialer.glide;

import android.graphics.drawable.Drawable;
import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.request.RequestOptions;

public final class GlideOptions extends RequestOptions implements Cloneable {
    public final GlideOptions apply(RequestOptions requestOptions) {
        return (GlideOptions) super.apply(requestOptions);
    }

    public RequestOptions autoClone() {
        return (GlideOptions) super.autoClone();
    }

    public final GlideOptions circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

    public RequestOptions clone() {
        return (GlideOptions) super.clone();
    }

    public RequestOptions decode(Class cls) {
        return (GlideOptions) super.decode(cls);
    }

    public RequestOptions diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        return (GlideOptions) super.diskCacheStrategy(diskCacheStrategy);
    }

    public RequestOptions downsample(DownsampleStrategy downsampleStrategy) {
        Option<DownsampleStrategy> option = Downsampler.DOWNSAMPLE_STRATEGY;
        R$style.checkNotNull(downsampleStrategy, "Argument must not be null");
        return (GlideOptions) set(option, downsampleStrategy);
    }

    public final GlideOptions fallback(Drawable drawable) {
        return (GlideOptions) super.fallback(drawable);
    }

    public RequestOptions lock() {
        super.lock();
        return this;
    }

    public RequestOptions optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    public RequestOptions optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    public RequestOptions optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    public RequestOptions override(int i, int i2) {
        return (GlideOptions) super.override(i, i2);
    }

    public final GlideOptions placeholder(Drawable drawable) {
        return (GlideOptions) super.placeholder(drawable);
    }

    public RequestOptions priority(Priority priority) {
        return (GlideOptions) super.priority(priority);
    }

    public RequestOptions set(Option option, Object obj) {
        return (GlideOptions) super.set(option, obj);
    }

    public RequestOptions signature(Key key) {
        return (GlideOptions) super.signature(key);
    }

    public RequestOptions sizeMultiplier(float f) {
        return (GlideOptions) super.sizeMultiplier(f);
    }

    public RequestOptions skipMemoryCache(boolean z) {
        return (GlideOptions) super.skipMemoryCache(z);
    }

    public RequestOptions transform(Transformation transformation) {
        return (GlideOptions) super.transform(transformation);
    }

    public RequestOptions useAnimationPool(boolean z) {
        return (GlideOptions) super.useAnimationPool(z);
    }

    /* renamed from: apply  reason: collision with other method in class */
    public RequestOptions m110apply(RequestOptions requestOptions) {
        return (GlideOptions) super.apply(requestOptions);
    }

    /* renamed from: clone  reason: collision with other method in class */
    public Object m111clone() throws CloneNotSupportedException {
        return (GlideOptions) super.clone();
    }

    /* renamed from: fallback  reason: collision with other method in class */
    public RequestOptions m112fallback(Drawable drawable) {
        return (GlideOptions) super.fallback(drawable);
    }

    /* renamed from: placeholder  reason: collision with other method in class */
    public RequestOptions m113placeholder(Drawable drawable) {
        return (GlideOptions) super.placeholder(drawable);
    }
}
