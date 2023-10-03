package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.data.mediastore.ThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class MediaStoreVideoThumbLoader implements ModelLoader<Uri, InputStream> {
    private final Context context;

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        private final Context context;

        public Factory(Context context2) {
            this.context = context2;
        }

        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreVideoThumbLoader(this.context);
        }
    }

    public MediaStoreVideoThumbLoader(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public ModelLoader.LoadData buildLoadData(Object obj, int i, int i2, Options options) {
        Uri uri = (Uri) obj;
        if (MediaStoreUtil.isThumbnailSize(i, i2)) {
            Long l = (Long) options.get(VideoDecoder.TARGET_FRAME);
            if (l != null && l.longValue() == -1) {
                return new ModelLoader.LoadData(new ObjectKey(uri), ThumbFetcher.buildVideoFetcher(this.context, uri));
            }
        }
        return null;
    }

    public boolean handles(Object obj) {
        return MediaStoreUtil.isMediaStoreVideoUri((Uri) obj);
    }
}
