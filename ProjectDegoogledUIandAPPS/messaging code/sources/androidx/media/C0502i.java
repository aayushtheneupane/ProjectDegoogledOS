package androidx.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.p016v4.media.MediaBrowserCompat$MediaItem;
import android.support.p016v4.p017os.ResultReceiver;
import java.util.List;

/* renamed from: androidx.media.i */
class C0502i extends C0519y {

    /* renamed from: Hq */
    final /* synthetic */ ResultReceiver f487Hq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0502i(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
        super(obj);
        this.f487Hq = resultReceiver;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        List list = (List) obj;
        if ((getFlags() & 4) != 0 || list == null) {
            this.f487Hq.send(-1, (Bundle) null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat$MediaItem[0]));
        this.f487Hq.send(0, bundle);
    }
}
