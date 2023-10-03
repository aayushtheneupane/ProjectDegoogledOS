package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;

public final class LazyBitmapDrawableResource implements Resource<BitmapDrawable>, Initializable {
    private final Resource<Bitmap> bitmapResource;
    private final Resources resources;

    private LazyBitmapDrawableResource(Resources resources2, Resource<Bitmap> resource) {
        R$style.checkNotNull(resources2, "Argument must not be null");
        this.resources = resources2;
        R$style.checkNotNull(resource, "Argument must not be null");
        this.bitmapResource = resource;
    }

    public static Resource<BitmapDrawable> obtain(Resources resources2, Resource<Bitmap> resource) {
        if (resource == null) {
            return null;
        }
        return new LazyBitmapDrawableResource(resources2, resource);
    }

    public Object get() {
        return new BitmapDrawable(this.resources, this.bitmapResource.get());
    }

    public Class<BitmapDrawable> getResourceClass() {
        return BitmapDrawable.class;
    }

    public int getSize() {
        return this.bitmapResource.getSize();
    }

    public void initialize() {
        Resource<Bitmap> resource = this.bitmapResource;
        if (resource instanceof Initializable) {
            ((Initializable) resource).initialize();
        }
    }

    public void recycle() {
        this.bitmapResource.recycle();
    }
}
