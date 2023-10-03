package androidx.media;

import android.os.Bundle;
import android.support.p016v4.media.MediaBrowserCompat$MediaItem;
import android.support.p016v4.p017os.ResultReceiver;

/* renamed from: androidx.media.h */
class C0501h extends C0519y {

    /* renamed from: Hq */
    final /* synthetic */ ResultReceiver f486Hq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0501h(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
        super(obj);
        this.f486Hq = resultReceiver;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) obj;
        if ((getFlags() & 2) != 0) {
            this.f486Hq.send(-1, (Bundle) null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaBrowserCompat$MediaItem);
        this.f486Hq.send(0, bundle);
    }
}
