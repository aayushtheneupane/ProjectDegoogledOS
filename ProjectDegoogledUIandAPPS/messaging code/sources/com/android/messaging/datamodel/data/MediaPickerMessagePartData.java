package com.android.messaging.datamodel.data;

import android.graphics.Rect;
import android.net.Uri;

public class MediaPickerMessagePartData extends MessagePartData {

    /* renamed from: bl */
    private final Rect f1158bl;

    public MediaPickerMessagePartData(Rect rect, String str, Uri uri, int i, int i2) {
        super((String) null, str, uri, i, i2, false);
        this.f1158bl = rect;
    }

    /* renamed from: c */
    public void mo6240c(Rect rect) {
        this.f1158bl.set(rect);
    }

    /* renamed from: kh */
    public Rect mo6241kh() {
        return this.f1158bl;
    }
}
