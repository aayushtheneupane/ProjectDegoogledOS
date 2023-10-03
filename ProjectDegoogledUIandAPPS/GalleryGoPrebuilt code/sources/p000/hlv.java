package p000;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: hlv */
/* compiled from: PG */
final /* synthetic */ class hlv implements View.OnTouchListener {

    /* renamed from: a */
    private final hlz f13005a;

    /* renamed from: b */
    private final View.OnTouchListener f13006b;

    /* renamed from: c */
    private final String f13007c;

    public hlv(hlz hlz, View.OnTouchListener onTouchListener, String str) {
        this.f13005a = hlz;
        this.f13006b = onTouchListener;
        this.f13007c = str;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        hlz hlz = this.f13005a;
        View.OnTouchListener onTouchListener = this.f13006b;
        String str = this.f13007c;
        if (motionEvent.getActionMasked() == 3 && hnb.m11774a(hnf.f13084a)) {
            return onTouchListener.onTouch(view, motionEvent);
        }
        hlp a = hlz.mo7577a(str);
        try {
            boolean onTouch = onTouchListener.onTouch(view, motionEvent);
            if (a == null) {
                return onTouch;
            }
            a.close();
            return onTouch;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
