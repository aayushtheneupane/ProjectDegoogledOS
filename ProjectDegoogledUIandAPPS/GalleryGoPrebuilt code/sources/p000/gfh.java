package p000;

import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: gfh */
/* compiled from: PG */
public final class gfh implements View.OnTouchListener {

    /* renamed from: a */
    private final Dialog f11160a;

    /* renamed from: b */
    private final int f11161b;

    /* renamed from: c */
    private final int f11162c;

    /* renamed from: d */
    private final int f11163d;

    public gfh(Dialog dialog, Rect rect) {
        this.f11160a = dialog;
        this.f11161b = rect.left;
        this.f11162c = rect.top;
        this.f11163d = ViewConfiguration.get(dialog.getContext()).getScaledWindowTouchSlop();
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        View findViewById = view.findViewById(16908290);
        int left = this.f11161b + findViewById.getLeft();
        int width = findViewById.getWidth();
        int top = this.f11162c + findViewById.getTop();
        if (new RectF((float) left, (float) top, (float) (left + width), (float) (top + findViewById.getHeight())).contains(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(4);
        if (Build.VERSION.SDK_INT < 28) {
            obtain.setAction(0);
            float f = (float) ((-this.f11163d) - 1);
            obtain.setLocation(f, f);
        }
        view.performClick();
        return this.f11160a.onTouchEvent(obtain);
    }
}
