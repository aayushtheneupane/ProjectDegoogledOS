package androidx.media;

import android.os.Bundle;
import android.os.Parcel;
import android.support.p016v4.media.MediaBrowserCompat$MediaItem;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.u */
class C0515u extends C0519y {

    /* renamed from: Iq */
    final /* synthetic */ C0520z f496Iq;

    /* renamed from: Jq */
    final /* synthetic */ Bundle f497Jq;
    final /* synthetic */ C0517w this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0515u(C0517w wVar, Object obj, C0520z zVar, Bundle bundle) {
        super(obj);
        this.this$1 = wVar;
        this.f496Iq = zVar;
        this.f497Jq = bundle;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        List<MediaBrowserCompat$MediaItem> list = (List) obj;
        if (list == null) {
            this.f496Iq.sendResult((Object) null);
            return;
        }
        if ((getFlags() & 1) != 0) {
            list = this.this$1.this$0.mo4562a(list, this.f497Jq);
        }
        ArrayList arrayList = new ArrayList();
        for (MediaBrowserCompat$MediaItem writeToParcel : list) {
            Parcel obtain = Parcel.obtain();
            writeToParcel.writeToParcel(obtain, 0);
            arrayList.add(obtain);
        }
        this.f496Iq.sendResult(arrayList);
    }
}
