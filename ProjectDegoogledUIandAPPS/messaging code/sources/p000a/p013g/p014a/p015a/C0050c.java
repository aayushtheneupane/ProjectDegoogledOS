package p000a.p013g.p014a.p015a;

import android.graphics.drawable.Drawable;

/* renamed from: a.g.a.a.c */
class C0050c implements Drawable.Callback {
    final /* synthetic */ C0053f this$0;

    C0050c(C0053f fVar) {
        this.this$0 = fVar;
    }

    public void invalidateDrawable(Drawable drawable) {
        this.this$0.invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.this$0.scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.this$0.unscheduleSelf(runnable);
    }
}
