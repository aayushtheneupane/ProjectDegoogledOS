package android.support.p000v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.support.p000v4.p001os.ResultReceiver;
import java.util.ArrayList;

/* renamed from: android.support.v4.media.MediaBrowserCompat$SearchResultReceiver */
class MediaBrowserCompat$SearchResultReceiver extends ResultReceiver {
    private final MediaBrowserCompat$SearchCallback mCallback;
    private final Bundle mExtras;
    private final String mQuery;

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        MediaSessionCompat.ensureClassLoader(bundle);
        if (i != 0 || bundle == null || !bundle.containsKey("search_results")) {
            this.mCallback.onError(this.mQuery, this.mExtras);
            return;
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
        ArrayList arrayList = null;
        if (parcelableArray != null) {
            arrayList = new ArrayList();
            for (Parcelable parcelable : parcelableArray) {
                arrayList.add((MediaBrowserCompat$MediaItem) parcelable);
            }
        }
        this.mCallback.onSearchResult(this.mQuery, this.mExtras, arrayList);
    }
}
