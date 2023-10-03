package p000;

import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar$Behavior;

/* renamed from: gdb */
/* compiled from: PG */
public final class gdb implements View.OnLayoutChangeListener {

    /* renamed from: a */
    private final /* synthetic */ BottomAppBar$Behavior f11011a;

    public gdb(BottomAppBar$Behavior bottomAppBar$Behavior) {
        this.f11011a = bottomAppBar$Behavior;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (((gdc) this.f11011a.f5156b.get()) == null || !(view instanceof gfo)) {
            view.removeOnLayoutChangeListener(this);
        } else {
            gfo gfo = (gfo) view;
            throw null;
        }
    }
}
