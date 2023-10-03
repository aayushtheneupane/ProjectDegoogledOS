package androidx.activity;

import androidx.lifecycle.C0450g;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.Lifecycle$State;
import java.util.ArrayDeque;
import java.util.Iterator;

/* renamed from: androidx.activity.f */
public final class C0124f {

    /* renamed from: Kl */
    private final Runnable f128Kl;

    /* renamed from: Ll */
    final ArrayDeque f129Ll = new ArrayDeque();

    public C0124f(Runnable runnable) {
        this.f128Kl = runnable;
    }

    /* renamed from: a */
    public void mo620a(C0453j jVar, C0122d dVar) {
        C0450g lifecycle = jVar.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle$State.DESTROYED) {
            dVar.mo614a(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(this, lifecycle, dVar));
        }
    }

    public void onBackPressed() {
        Iterator descendingIterator = this.f129Ll.descendingIterator();
        while (descendingIterator.hasNext()) {
            C0122d dVar = (C0122d) descendingIterator.next();
            if (dVar.isEnabled()) {
                dVar.mo616ec();
                return;
            }
        }
        Runnable runnable = this.f128Kl;
        if (runnable != null) {
            runnable.run();
        }
    }
}
