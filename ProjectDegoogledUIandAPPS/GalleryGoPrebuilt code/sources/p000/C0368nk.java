package p000;

import android.os.Build;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

/* renamed from: nk */
/* compiled from: PG */
final class C0368nk implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0369nl f15275a;

    public C0368nk(C0369nl nlVar) {
        this.f15275a = nlVar;
    }

    public final void run() {
        C0369nl nlVar = this.f15275a;
        if (nlVar.f15281e) {
            if (nlVar.f15279c) {
                nlVar.f15279c = false;
                C0367nj njVar = nlVar.f15277a;
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                njVar.f15268e = currentAnimationTimeMillis;
                njVar.f15272i = -1;
                njVar.f15269f = currentAnimationTimeMillis;
                njVar.f15273j = 0.5f;
                njVar.f15270g = 0;
                njVar.f15271h = 0;
            }
            C0367nj njVar2 = this.f15275a.f15277a;
            if ((njVar2.f15272i <= 0 || AnimationUtils.currentAnimationTimeMillis() <= njVar2.f15272i + ((long) njVar2.f15274k)) && this.f15275a.mo9459a()) {
                C0369nl nlVar2 = this.f15275a;
                if (nlVar2.f15280d) {
                    nlVar2.f15280d = false;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    nlVar2.f15278b.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (njVar2.f15269f != 0) {
                    long currentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float a = njVar2.mo9456a(currentAnimationTimeMillis2);
                    long j = njVar2.f15269f;
                    njVar2.f15269f = currentAnimationTimeMillis2;
                    float f = ((float) (currentAnimationTimeMillis2 - j)) * ((-4.0f * a * a) + (a * 4.0f));
                    njVar2.f15270g = (int) (njVar2.f15266c * f);
                    int i = (int) (f * njVar2.f15267d);
                    njVar2.f15271h = i;
                    ListView listView = this.f15275a.f15282f;
                    int i2 = Build.VERSION.SDK_INT;
                    listView.scrollListBy(i);
                    C0340mj.m14695a(this.f15275a.f15278b, (Runnable) this);
                    return;
                }
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            this.f15275a.f15281e = false;
        }
    }
}
