package android.support.p016v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.p016v4.media.session.C0107q;
import android.support.p016v4.p017os.ResultReceiver;
import java.util.ArrayList;

/* renamed from: android.support.v4.media.MediaBrowserCompat$SearchResultReceiver */
class MediaBrowserCompat$SearchResultReceiver extends ResultReceiver {
    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        C0107q.m130b(bundle);
        if (i != 0 || bundle == null || !bundle.containsKey("search_results")) {
            throw null;
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
        if (parcelableArray != null) {
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaBrowserCompat$MediaItem) parcelable);
            }
        }
        throw null;
    }
}
