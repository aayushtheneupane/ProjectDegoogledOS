package p000;

import com.google.android.material.textfield.TextInputLayout;

/* renamed from: gjz */
/* compiled from: PG */
public final class gjz implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ TextInputLayout f11532a;

    public gjz(TextInputLayout textInputLayout) {
        this.f11532a = textInputLayout;
    }

    public final void run() {
        this.f11532a.f5290l.performClick();
        this.f11532a.f5290l.jumpDrawablesToCurrentState();
    }
}
