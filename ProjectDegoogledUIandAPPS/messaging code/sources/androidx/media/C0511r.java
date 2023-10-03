package androidx.media;

import android.os.Parcel;
import android.support.p016v4.media.MediaBrowserCompat$MediaItem;

/* renamed from: androidx.media.r */
class C0511r extends C0519y {

    /* renamed from: Iq */
    final /* synthetic */ C0520z f493Iq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0511r(C0514t tVar, Object obj, C0520z zVar) {
        super(obj);
        this.f493Iq = zVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) obj;
        if (mediaBrowserCompat$MediaItem == null) {
            this.f493Iq.sendResult((Object) null);
            return;
        }
        Parcel obtain = Parcel.obtain();
        mediaBrowserCompat$MediaItem.writeToParcel(obtain, 0);
        this.f493Iq.sendResult(obtain);
    }
}
