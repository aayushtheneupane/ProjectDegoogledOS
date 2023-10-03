package androidx.media;

import android.os.Parcel;
import android.support.p016v4.media.MediaBrowserCompat$MediaItem;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.o */
class C0508o extends C0519y {

    /* renamed from: Iq */
    final /* synthetic */ C0520z f490Iq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0508o(C0510q qVar, Object obj, C0520z zVar) {
        super(obj);
        this.f490Iq = zVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        ArrayList arrayList;
        List<MediaBrowserCompat$MediaItem> list = (List) obj;
        if (list != null) {
            arrayList = new ArrayList();
            for (MediaBrowserCompat$MediaItem writeToParcel : list) {
                Parcel obtain = Parcel.obtain();
                writeToParcel.writeToParcel(obtain, 0);
                arrayList.add(obtain);
            }
        } else {
            arrayList = null;
        }
        this.f490Iq.sendResult(arrayList);
    }
}
