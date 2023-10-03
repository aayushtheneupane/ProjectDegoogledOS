package com.android.p032ex.photo.p035b;

import android.content.Context;
import android.net.Uri;
import androidx.loader.content.C0472c;
import com.android.p032ex.photo.p036c.C0719a;

/* renamed from: com.android.ex.photo.b.d */
public class C0717d extends C0472c {

    /* renamed from: jq */
    private final Uri f843jq;
    private final String[] mProjection;

    public C0717d(Context context, Uri uri, String[] strArr) {
        super(context);
        this.f843jq = uri;
        this.mProjection = strArr == null ? C0719a.f844Wu : strArr;
    }

    public Object loadInBackground() {
        setUri(this.f843jq.buildUpon().appendQueryParameter("contentType", "image/").build());
        setProjection(this.mProjection);
        return super.loadInBackground();
    }
}
