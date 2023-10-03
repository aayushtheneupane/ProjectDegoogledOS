package p000;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: mn */
/* compiled from: PG */
public final class C0344mn {

    /* renamed from: a */
    public final WeakReference f15239a;

    public C0344mn(View view) {
        this.f15239a = new WeakReference(view);
    }

    /* renamed from: a */
    public final void mo9400a(float f) {
        View view = (View) this.f15239a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
    }

    /* renamed from: a */
    public final void mo9399a() {
        View view = (View) this.f15239a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    /* renamed from: a */
    public final void mo9401a(long j) {
        View view = (View) this.f15239a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
    }

    /* renamed from: a */
    public final void mo9402a(C0345mo moVar) {
        View view = (View) this.f15239a.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            if (moVar != null) {
                view.animate().setListener(new C0342ml(moVar));
            } else {
                view.animate().setListener((Animator.AnimatorListener) null);
            }
        }
    }

    /* renamed from: a */
    public final void mo9403a(C0347mq mqVar) {
        C0343mm mmVar;
        View view = (View) this.f15239a.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            if (mqVar != null) {
                mmVar = new C0343mm(mqVar);
            } else {
                mmVar = null;
            }
            view.animate().setUpdateListener(mmVar);
        }
    }

    /* renamed from: b */
    public final void mo9404b(float f) {
        View view = (View) this.f15239a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
    }
}
