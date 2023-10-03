package p000;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: vp */
/* compiled from: PG */
final class C0589vp implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0590vq f16141a;

    public C0589vp(C0590vq vqVar) {
        this.f16141a = vqVar;
    }

    public final void run() {
        C0590vq vqVar = this.f16141a;
        vqVar.mo10399d();
        View view = vqVar.f16142a;
        if (view.isEnabled() && !view.isLongClickable() && vqVar.mo9783b()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            vqVar.f16143b = true;
        }
    }
}
