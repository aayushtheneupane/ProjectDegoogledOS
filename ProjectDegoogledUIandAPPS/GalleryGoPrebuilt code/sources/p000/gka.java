package p000;

import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gka */
/* compiled from: PG */
public final class gka implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ TextInputLayout f11535a;

    public gka(TextInputLayout textInputLayout) {
        this.f11535a = textInputLayout;
    }

    public final void run() {
        this.f11535a.f5254a.requestLayout();
    }
}
