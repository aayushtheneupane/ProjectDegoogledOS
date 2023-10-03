package androidx.fragment.app;

import android.view.View;
import androidx.lifecycle.C0449f;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.Lifecycle$Event;

class Fragment$1 implements C0449f {
    final /* synthetic */ C0424j this$0;

    Fragment$1(C0424j jVar) {
        this.this$0 = jVar;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        View view;
        if (lifecycle$Event == Lifecycle$Event.ON_STOP && (view = this.this$0.mView) != null) {
            view.cancelPendingInputEvents();
        }
    }
}
