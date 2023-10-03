package com.android.p032ex.photo.p035b;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.loader.content.C0471b;
import com.android.p032ex.photo.C0734o;
import com.android.p032ex.photo.util.C0745e;

/* renamed from: com.android.ex.photo.b.a */
public class C0714a extends C0471b implements C0716c {

    /* renamed from: kq */
    private String f842kq;
    private Bitmap mBitmap;

    public C0714a(Context context, String str) {
        super(context);
        this.f842kq = str;
    }

    /* renamed from: a */
    public void deliverResult(C0715b bVar) {
        Bitmap bitmap = bVar != null ? bVar.bitmap : null;
        if (!isReset()) {
            Bitmap bitmap2 = this.mBitmap;
            this.mBitmap = bitmap;
            if (isStarted()) {
                super.deliverResult(bVar);
            }
            if (bitmap2 != null && bitmap2 != bitmap && !bitmap2.isRecycled()) {
                mo5724a(bitmap2);
            }
        } else if (bitmap != null) {
            mo5724a(bitmap);
        }
    }

    /* renamed from: c */
    public void mo5726c(String str) {
        this.f842kq = str;
    }

    public Object loadInBackground() {
        C0715b bVar = new C0715b();
        Context context = getContext();
        if (!(context == null || this.f842kq == null)) {
            try {
                bVar = C0745e.m1194a(context.getContentResolver(), Uri.parse(this.f842kq), C0734o.f877yw);
                if (bVar.bitmap != null) {
                    bVar.bitmap.setDensity(160);
                }
            } catch (UnsupportedOperationException unused) {
                bVar.status = 1;
            }
        }
        return bVar;
    }

    public void onCanceled(Object obj) {
        C0715b bVar = (C0715b) obj;
        super.onCanceled(bVar);
        if (bVar != null) {
            mo5724a(bVar.bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        cancelLoad();
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            mo5724a(bitmap);
            this.mBitmap = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        if (this.mBitmap != null) {
            C0715b bVar = new C0715b();
            bVar.status = 0;
            bVar.bitmap = this.mBitmap;
            deliverResult(bVar);
        }
        if (takeContentChanged() || this.mBitmap == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo5724a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
