package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Util;
import java.io.IOException;

public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {

    private static final class NonOwnedBitmapResource implements Resource<Bitmap> {
        private final Bitmap bitmap;

        NonOwnedBitmapResource(Bitmap bitmap2) {
            this.bitmap = bitmap2;
        }

        public Object get() {
            return this.bitmap;
        }

        public Class<Bitmap> getResourceClass() {
            return Bitmap.class;
        }

        public int getSize() {
            return Util.getBitmapByteSize(this.bitmap);
        }

        public void recycle() {
        }
    }

    public Resource decode(Object obj, int i, int i2, Options options) throws IOException {
        return new NonOwnedBitmapResource((Bitmap) obj);
    }

    public boolean handles(Object obj, Options options) throws IOException {
        Bitmap bitmap = (Bitmap) obj;
        return true;
    }
}
