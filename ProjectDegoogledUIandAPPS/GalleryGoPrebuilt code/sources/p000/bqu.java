package p000;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import com.google.android.libraries.material.progress.MaterialProgressBar;

/* renamed from: bqu */
/* compiled from: PG */
public final class bqu implements DialogInterface {

    /* renamed from: a */
    public final bqr f3396a;

    /* renamed from: b */
    public final bqq f3397b;

    /* renamed from: c */
    public final gvi f3398c;

    /* renamed from: d */
    public final bqo f3399d;

    /* renamed from: e */
    public final gvc f3400e = new bqt(this);

    /* renamed from: f */
    public MaterialProgressBar f3401f;

    public bqu(bqr bqr, bqq bqq, gvi gvi, bqo bqo) {
        this.f3396a = bqr;
        this.f3397b = bqq;
        this.f3398c = gvi;
        this.f3399d = bqo;
    }

    public final void cancel() {
    }

    public final void dismiss() {
        MaterialProgressBar materialProgressBar = this.f3401f;
        if (!materialProgressBar.mo3529a()) {
            materialProgressBar.setVisibility(4);
            return;
        }
        Drawable b = materialProgressBar.mo3530b();
        if (b instanceof fgv) {
            ((fgv) b).mo5606a(new fhk(materialProgressBar));
        } else {
            materialProgressBar.setVisibility(4);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo2682a(int i) {
        this.f3401f.setProgress(i);
    }
}
