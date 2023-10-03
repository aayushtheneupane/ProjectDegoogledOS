package androidx.savedstate;

import androidx.lifecycle.C0449f;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.Lifecycle$Event;

class SavedStateRegistry$1 implements C0449f {
    final /* synthetic */ C0607c this$0;

    SavedStateRegistry$1(C0607c cVar) {
        this.this$0 = cVar;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event != Lifecycle$Event.ON_START) {
            Lifecycle$Event lifecycle$Event2 = Lifecycle$Event.ON_STOP;
        }
    }
}
