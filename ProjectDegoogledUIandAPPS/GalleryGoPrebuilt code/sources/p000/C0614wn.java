package p000;

import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

/* renamed from: wn */
/* compiled from: PG */
final class C0614wn implements View.OnTouchListener {

    /* renamed from: a */
    private final /* synthetic */ C0616wp f16240a;

    public C0614wn(C0616wp wpVar) {
        this.f16240a = wpVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (action == 0) {
            PopupWindow popupWindow = this.f16240a.f16259q;
            if (popupWindow == null || !popupWindow.isShowing() || x < 0 || x >= this.f16240a.f16259q.getWidth() || y < 0 || y >= this.f16240a.f16259q.getHeight()) {
                return false;
            }
            C0616wp wpVar = this.f16240a;
            wpVar.f16257o.postDelayed(wpVar.f16256n, 250);
            return false;
        } else if (action != 1) {
            return false;
        } else {
            C0616wp wpVar2 = this.f16240a;
            wpVar2.f16257o.removeCallbacks(wpVar2.f16256n);
            return false;
        }
    }
}
