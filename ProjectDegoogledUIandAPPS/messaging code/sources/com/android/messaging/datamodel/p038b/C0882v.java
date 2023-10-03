package com.android.messaging.datamodel.p038b;

import android.util.Log;
import android.util.LruCache;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.b.v */
public class C0882v extends LruCache {
    private final String mName;

    public C0882v(int i, int i2, String str) {
        super(i);
        this.mName = str;
    }

    /* renamed from: a */
    public synchronized C0846I mo6095a(String str, C0846I i) {
        i.mo6100Oh();
        return (C0846I) put(str, i);
    }

    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: protected */
    public int sizeOf(Object obj, Object obj2) {
        String str = (String) obj;
        int mediaSize = ((C0846I) obj2).getMediaSize() / 1024;
        if (mediaSize == 0) {
            return 1;
        }
        return mediaSize;
    }

    /* renamed from: u */
    public synchronized C0846I mo6163u(String str) {
        C0846I i;
        i = (C0846I) get(str);
        if (i != null) {
            if (Log.isLoggable("MessagingAppImage", 2)) {
                C1430e.m3628v("MessagingAppImage", "cache hit in mediaCache @ " + this.mName + ", total cache hit = " + hitCount() + ", total cache miss = " + missCount());
            }
            i.mo6100Oh();
        } else if (Log.isLoggable("MessagingAppImage", 2)) {
            C1430e.m3628v("MessagingAppImage", "cache miss in mediaCache @ " + this.mName + ", total cache hit = " + hitCount() + ", total cache miss = " + missCount());
        }
        return i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void entryRemoved(boolean z, String str, C0846I i, C0846I i2) {
        i.release();
    }
}
