package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

public class UnitDrawableDecoder implements ResourceDecoder<Drawable, Drawable> {
    public Resource decode(Object obj, int i, int i2, Options options) throws IOException {
        return NonOwnedDrawableResource.newInstance((Drawable) obj);
    }

    public boolean handles(Object obj, Options options) throws IOException {
        Drawable drawable = (Drawable) obj;
        return true;
    }
}
