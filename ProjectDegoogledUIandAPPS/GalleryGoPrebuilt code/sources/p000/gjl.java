package p000;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.internal.CheckableImageButton;

/* renamed from: gjl */
/* compiled from: PG */
final class gjl extends AnimatorListenerAdapter {

    /* renamed from: a */
    private final /* synthetic */ gjn f11481a;

    public gjl(gjn gjn) {
        this.f11481a = gjn;
    }

    public final void onAnimationEnd(Animator animator) {
        gjn gjn = this.f11481a;
        CheckableImageButton checkableImageButton = gjn.f11497m;
        int i = gjn.f11483j;
        checkableImageButton.setChecked(gjn.f11487d);
        this.f11481a.f11492i.start();
    }
}
