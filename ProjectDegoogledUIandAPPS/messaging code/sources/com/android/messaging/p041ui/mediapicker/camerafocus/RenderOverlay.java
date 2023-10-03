package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.RenderOverlay */
public class RenderOverlay extends FrameLayout {

    /* renamed from: Pg */
    private C1318o f2063Pg;
    /* access modifiers changed from: private */

    /* renamed from: Qg */
    public List f2064Qg;
    /* access modifiers changed from: private */

    /* renamed from: Rg */
    public List f2065Rg;
    private int[] mPosition = new int[2];

    public RenderOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2063Pg = new C1318o(this, context);
        addView(this.f2063Pg, new FrameLayout.LayoutParams(-1, -1));
        this.f2064Qg = new ArrayList(10);
        this.f2065Rg = new ArrayList(10);
        setWillNotDraw(false);
        mo7781a((C1319p) new C1317n(context));
    }

    /* renamed from: Lb */
    public C1317n mo7780Lb() {
        for (C1319p pVar : this.f2064Qg) {
            if (pVar instanceof C1317n) {
                return (C1317n) pVar;
            }
        }
        return null;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void update() {
        this.f2063Pg.invalidate();
    }

    /* renamed from: a */
    public void mo7781a(C1319p pVar) {
        this.f2064Qg.add(pVar);
        ((C1307d) pVar).mOverlay = this;
        this.f2065Rg.add(0, pVar);
        ((C1317n) pVar).layout(getLeft(), getTop(), getRight(), getBottom());
    }
}
