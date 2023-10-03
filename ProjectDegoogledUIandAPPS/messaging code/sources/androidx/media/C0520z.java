package androidx.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.media.z */
class C0520z {

    /* renamed from: Kq */
    MediaBrowserService.Result f502Kq;

    C0520z(MediaBrowserService.Result result) {
        this.f502Kq = result;
    }

    public void sendResult(Object obj) {
        ArrayList arrayList = null;
        if (obj instanceof List) {
            MediaBrowserService.Result result = this.f502Kq;
            List<Parcel> list = (List) obj;
            if (list != null) {
                arrayList = new ArrayList();
                for (Parcel parcel : list) {
                    parcel.setDataPosition(0);
                    arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                    parcel.recycle();
                }
            }
            result.sendResult(arrayList);
        } else if (obj instanceof Parcel) {
            Parcel parcel2 = (Parcel) obj;
            parcel2.setDataPosition(0);
            this.f502Kq.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel2));
            parcel2.recycle();
        } else {
            this.f502Kq.sendResult((Object) null);
        }
    }
}
