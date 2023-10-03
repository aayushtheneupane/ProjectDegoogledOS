package com.android.messaging.p041ui.photoviewer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.rastermill.FrameSequenceDrawable;
import androidx.loader.content.C0471b;
import com.android.messaging.datamodel.p038b.C0840C;
import com.android.messaging.datamodel.p038b.C0849L;
import com.android.messaging.datamodel.p038b.C0881u;
import com.android.p032ex.photo.C0734o;
import com.android.p032ex.photo.p035b.C0715b;
import com.android.p032ex.photo.p035b.C0716c;

/* renamed from: com.android.messaging.ui.photoviewer.a */
public class C1372a extends C0471b implements C0716c {

    /* renamed from: Nj */
    private C0881u f2190Nj;

    /* renamed from: kq */
    private String f2191kq;
    private Drawable mDrawable;

    public C1372a(Context context, String str) {
        super(context);
        this.f2191kq = str;
    }

    /* renamed from: g */
    private void m3502g(Drawable drawable) {
        if (drawable != null && (drawable instanceof FrameSequenceDrawable)) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) drawable;
            if (!frameSequenceDrawable.isDestroyed()) {
                frameSequenceDrawable.destroy();
            }
        }
    }

    /* renamed from: qn */
    private void m3503qn() {
        m3502g(this.mDrawable);
        this.mDrawable = null;
        C0881u uVar = this.f2190Nj;
        if (uVar != null) {
            uVar.release();
        }
        this.f2190Nj = null;
    }

    /* renamed from: a */
    public void deliverResult(C0715b bVar) {
        Drawable drawable = bVar != null ? bVar.drawable : null;
        if (isReset()) {
            m3502g(drawable);
            return;
        }
        this.mDrawable = drawable;
        if (isStarted()) {
            super.deliverResult(bVar);
        }
    }

    /* renamed from: c */
    public void mo5726c(String str) {
        this.f2191kq = str;
    }

    public Object loadInBackground() {
        String str;
        C0715b bVar = new C0715b();
        Context context = getContext();
        if (context == null || (str = this.f2191kq) == null) {
            bVar.status = 1;
        } else {
            Uri parse = Uri.parse(str);
            int i = C0734o.f877yw;
            C0881u uVar = (C0881u) C0840C.get().mo6082b(new C0849L(parse, i, i, true, false, false, 0, 0).mo6084n(context));
            if (uVar != null) {
                if (this.f2190Nj != uVar) {
                    m3503qn();
                    this.f2190Nj = uVar;
                }
                bVar.status = 0;
                bVar.drawable = this.f2190Nj.mo6156b(context.getResources());
            } else {
                m3503qn();
                bVar.status = 1;
            }
        }
        return bVar;
    }

    public void onCanceled(Object obj) {
        C0715b bVar = (C0715b) obj;
        super.onCanceled(bVar);
        if (bVar != null) {
            m3502g(bVar.drawable);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        cancelLoad();
        m3503qn();
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        if (this.mDrawable != null) {
            C0715b bVar = new C0715b();
            bVar.status = 0;
            bVar.drawable = this.mDrawable;
            deliverResult(bVar);
        }
        if (takeContentChanged() || this.f2190Nj == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }
}
