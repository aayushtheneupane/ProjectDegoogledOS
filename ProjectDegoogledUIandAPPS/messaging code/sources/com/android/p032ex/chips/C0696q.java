package com.android.p032ex.chips;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import p000a.p005b.C0020g;

/* renamed from: com.android.ex.chips.q */
public class C0696q implements C0707y {
    /* access modifiers changed from: private */

    /* renamed from: fv */
    public final C0020g f811fv = new C0020g(20);
    /* access modifiers changed from: private */
    public final ContentResolver mContentResolver;

    public C0696q(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public void populatePhotoBytesAsync(C0699ra raVar, C0706x xVar) {
        Uri yd = raVar.mo5666yd();
        if (yd != null) {
            byte[] bArr = (byte[]) this.f811fv.get(yd);
            if (bArr != null) {
                raVar.mo5658j(bArr);
                if (xVar != null) {
                    xVar.onPhotoBytesPopulated();
                    return;
                }
                return;
            }
            new C0692o(this, yd, raVar, xVar).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        } else if (xVar != null) {
            xVar.onPhotoBytesAsyncLoadFailed();
        }
    }
}
