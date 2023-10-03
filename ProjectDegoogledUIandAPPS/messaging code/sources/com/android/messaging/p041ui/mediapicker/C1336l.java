package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.l */
class C1336l extends AsyncTask {

    /* renamed from: Cd */
    final /* synthetic */ Camera f2126Cd;
    final /* synthetic */ C1352t this$0;

    C1336l(C1352t tVar, Camera camera) {
        this.this$0 = tVar;
        this.f2126Cd = camera;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        if (Log.isLoggable("MessagingApp", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("Releasing camera ");
            Pa.append(this.this$0.f2152WH);
            C1430e.m3628v("MessagingApp", Pa.toString());
        }
        ((C1328h) C1352t.f2148jI).mo7861b(this.f2126Cd);
        return null;
    }
}
