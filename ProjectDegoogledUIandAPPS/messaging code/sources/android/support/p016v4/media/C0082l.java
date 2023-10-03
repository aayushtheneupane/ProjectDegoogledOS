package android.support.p016v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* renamed from: android.support.v4.media.l */
class C0082l extends MediaBrowser.SubscriptionCallback {
    final /* synthetic */ C0084n this$0;

    C0082l(C0084n nVar) {
        this.this$0 = nVar;
    }

    public void onChildrenLoaded(String str, List list) {
        List list2;
        WeakReference weakReference = this.this$0.f98le;
        C0081k kVar = weakReference == null ? null : (C0081k) weakReference.get();
        if (kVar == null) {
            this.this$0.onChildrenLoaded(str, MediaBrowserCompat$MediaItem.m82c(list));
            return;
        }
        List c = MediaBrowserCompat$MediaItem.m82c(list);
        List callbacks = kVar.getCallbacks();
        List optionsList = kVar.getOptionsList();
        for (int i = 0; i < callbacks.size(); i++) {
            Bundle bundle = (Bundle) optionsList.get(i);
            if (bundle == null) {
                this.this$0.onChildrenLoaded(str, c);
            } else {
                C0084n nVar = this.this$0;
                if (c == null) {
                    list2 = null;
                } else {
                    int i2 = bundle.getInt("android.media.browse.extra.PAGE", -1);
                    int i3 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                    if (i2 == -1 && i3 == -1) {
                        list2 = c;
                    } else {
                        int i4 = i3 * i2;
                        int i5 = i4 + i3;
                        if (i2 < 0 || i3 < 1 || i4 >= c.size()) {
                            list2 = Collections.emptyList();
                        } else {
                            if (i5 > c.size()) {
                                i5 = c.size();
                            }
                            list2 = c.subList(i4, i5);
                        }
                    }
                }
                nVar.onChildrenLoaded(str, list2, bundle);
            }
        }
    }

    public void onError(String str) {
        this.this$0.onError(str);
    }
}
