package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.j */
class C1332j extends AsyncTask {
    private Exception mException;
    final /* synthetic */ C1352t this$0;

    C1332j(C1352t tVar) {
        this.this$0 = tVar;
    }

    private void cleanup() {
        int unused = this.this$0.f2161eI = -1;
        if (this.this$0.f2160dI == null || this.this$0.f2160dI.getStatus() != AsyncTask.Status.PENDING) {
            AsyncTask unused2 = this.this$0.f2160dI = null;
            return;
        }
        this.this$0.f2160dI.execute(new Integer[]{Integer.valueOf(this.this$0.f2152WH)});
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        try {
            int intValue = ((Integer[]) objArr)[0].intValue();
            if (Log.isLoggable("MessagingApp", 2)) {
                C1430e.m3628v("MessagingApp", "Opening camera " + this.this$0.f2152WH);
            }
            return ((C1328h) C1352t.f2148jI).open(intValue);
        } catch (Exception e) {
            C1430e.m3623e("MessagingApp", "Exception while opening camera", e);
            this.mException = e;
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
        cleanup();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        Camera camera = (Camera) obj;
        if (this.this$0.f2160dI != this || !this.this$0.f2154YH) {
            this.this$0.m3442c(camera);
            cleanup();
            return;
        }
        cleanup();
        if (Log.isLoggable("MessagingApp", 2)) {
            StringBuilder Pa = C0632a.m1011Pa("Opened camera ");
            Pa.append(this.this$0.f2152WH);
            Pa.append(" ");
            Pa.append(camera != null);
            C1430e.m3628v("MessagingApp", Pa.toString());
        }
        this.this$0.setCamera(camera);
        if (camera == null) {
            if (this.this$0.mListener != null) {
                this.this$0.mListener.mo7677a(1, this.mException);
            }
            C1430e.m3622e("MessagingApp", "Error opening camera");
        }
    }
}
