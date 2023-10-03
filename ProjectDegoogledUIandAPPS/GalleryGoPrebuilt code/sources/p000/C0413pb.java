package p000;

import android.content.Context;
import android.support.p002v7.widget.ContentFrameLayout;
import android.view.KeyEvent;
import android.view.MotionEvent;

/* renamed from: pb */
/* compiled from: PG */
final class C0413pb extends ContentFrameLayout {

    /* renamed from: i */
    private final /* synthetic */ C0416pe f15437i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0413pb(C0416pe peVar, Context context) {
        super(context);
        this.f15437i = peVar;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f15437i.mo9604a(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                C0416pe peVar = this.f15437i;
                peVar.mo9602a(peVar.mo9610g(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public final void setBackgroundResource(int i) {
        setBackgroundDrawable(C0436py.m15105b(getContext(), i));
    }
}
