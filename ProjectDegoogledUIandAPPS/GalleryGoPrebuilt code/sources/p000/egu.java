package p000;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

/* renamed from: egu */
/* compiled from: PG */
public final class egu {

    /* renamed from: a */
    private final C0147fh f8227a;

    public egu(C0147fh fhVar) {
        this.f8227a = fhVar;
    }

    /* renamed from: d */
    private final void m7448d() {
        Window e = m7449e();
        if (e != null) {
            e.setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    /* renamed from: e */
    private final Window m7449e() {
        C0149fj m = this.f8227a.mo5653m();
        if (m != null) {
            return m.getWindow();
        }
        return null;
    }

    /* renamed from: c */
    public final void mo4804c() {
        Window e = m7449e();
        if (e != null) {
            e.clearFlags(201326592);
        }
        int i = this.f8227a.mo5657p().getConfiguration().uiMode & 48;
        int i2 = 8208;
        int i3 = i != 32 ? 8208 : 0;
        if (i != 32) {
            i2 = 0;
        }
        m7447a(0, i3, i2);
    }

    /* renamed from: a */
    private final void m7447a(int i, int i2, int i3) {
        Window e = m7449e();
        if (e != null) {
            e.addFlags(i);
            View decorView = e.getDecorView();
            decorView.setSystemUiVisibility(i2 | ((i3 ^ -1) & decorView.getSystemUiVisibility()));
            WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
            if (rootWindowInsets != null) {
                decorView.dispatchApplyWindowInsets(rootWindowInsets);
            }
        }
    }

    /* renamed from: b */
    public final void mo4803b() {
        m7447a(201326592, 4, 8208);
        m7448d();
    }

    /* renamed from: a */
    public final void mo4802a() {
        m7447a(201326592, 0, 8208);
        m7448d();
    }
}
