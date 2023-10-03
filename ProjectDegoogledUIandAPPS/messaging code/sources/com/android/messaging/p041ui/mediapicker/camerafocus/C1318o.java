package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import java.util.Iterator;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.o */
class C1318o extends View {

    /* renamed from: Uk */
    private C1319p f2116Uk;
    final /* synthetic */ RenderOverlay this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1318o(RenderOverlay renderOverlay, Context context) {
        super(context);
        this.this$0 = renderOverlay;
        setWillNotDraw(false);
    }

    public void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        if (this.this$0.f2064Qg != null) {
            Iterator it = this.this$0.f2064Qg.iterator();
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    C1319p pVar = (C1319p) it.next();
                    C1307d dVar = (C1307d) pVar;
                    if (dVar.mVisible) {
                        dVar.onDraw(canvas);
                    }
                    if (z || ((C1307d) pVar).mVisible) {
                        z = true;
                    }
                }
            }
            if (z) {
                invalidate();
            }
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.this$0.getLocationInWindow(this.this$0.mPosition);
        super.onLayout(z, i, i2, i3, i4);
        if (this.this$0.f2064Qg != null) {
            for (C1317n layout : this.this$0.f2064Qg) {
                layout.layout(i, i2, i3, i4);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        C1319p pVar = this.f2116Uk;
        if (pVar != null) {
            return ((C1317n) pVar).onTouchEvent(motionEvent);
        }
        boolean z = false;
        if (this.this$0.f2065Rg != null) {
            for (C1317n onTouchEvent : this.this$0.f2065Rg) {
                z |= onTouchEvent.onTouchEvent(motionEvent);
            }
        }
        return z;
    }
}
