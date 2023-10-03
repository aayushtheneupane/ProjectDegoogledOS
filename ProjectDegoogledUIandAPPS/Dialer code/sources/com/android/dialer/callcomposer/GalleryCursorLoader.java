package com.android.dialer.callcomposer;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.p000v4.content.CursorLoader;

public class GalleryCursorLoader extends CursorLoader {
    public static final String[] ACCEPTABLE_IMAGE_TYPES = {"image/jpeg", "image/jpg", "image/png", "image/webp"};
    private static final String IMAGE_SELECTION = "mime_type IN ('image/jpeg', 'image/jpg', 'image/png', 'image/webp') AND media_type in (1)";
    private static final Uri STORAGE_URI = MediaStore.Files.getContentUri("external");

    public GalleryCursorLoader(Context context) {
        super(context, STORAGE_URI, GalleryGridItemData.IMAGE_PROJECTION, IMAGE_SELECTION, (String[]) null, "date_modified DESC");
    }
}
