package com.android.messaging.datamodel.p038b;

import android.os.AsyncTask;
import android.util.Log;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.b.z */
class C0886z extends AsyncTask {
    private Exception mException;
    final /* synthetic */ C0840C this$0;

    /* renamed from: wd */
    final /* synthetic */ C0865e f1130wd;

    /* renamed from: xd */
    final /* synthetic */ C0883w f1131xd;

    C0886z(C0840C c, C0865e eVar, C0883w wVar) {
        this.this$0 = c;
        this.f1130wd = eVar;
        this.f1131xd = wVar;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        C0865e eVar = this.f1130wd;
        if (eVar != null && !eVar.isBound()) {
            return null;
        }
        try {
            return this.this$0.m1505e(this.f1131xd);
        } catch (Exception e) {
            this.mException = e;
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        C0838A a = (C0838A) obj;
        boolean z = true;
        if (a != null) {
            C1424b.isNull(this.mException);
            if (a.f1089KC.getRefCount() <= 0) {
                z = false;
            }
            C1424b.m3592ia(z);
            try {
                if (this.f1130wd != null) {
                    this.f1130wd.onMediaResourceLoaded(this.f1130wd, a.f1089KC, a.f1090LC);
                }
            } finally {
                a.f1089KC.release();
                a.mo6077Nh();
            }
        } else if (this.mException != null) {
            StringBuilder Pa = C0632a.m1011Pa("Asynchronous media loading failed, key=");
            Pa.append(this.f1131xd.getKey());
            C1430e.m3623e("MessagingApp", Pa.toString(), this.mException);
            C0865e eVar = this.f1130wd;
            if (eVar != null) {
                eVar.onMediaResourceLoadError(eVar, this.mException);
            }
        } else {
            C0865e eVar2 = this.f1130wd;
            if (eVar2 != null && eVar2.isBound()) {
                z = false;
            }
            C1424b.m3592ia(z);
            if (Log.isLoggable("MessagingApp", 2)) {
                StringBuilder Pa2 = C0632a.m1011Pa("media request not processed, no longer bound; key=");
                Pa2.append(C1430e.m3633xa(this.f1131xd.getKey()));
                C1430e.m3628v("MessagingApp", Pa2.toString());
            }
        }
    }
}
