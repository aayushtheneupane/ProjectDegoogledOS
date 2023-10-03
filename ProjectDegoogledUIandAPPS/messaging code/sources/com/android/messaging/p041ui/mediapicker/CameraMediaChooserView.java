package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.android.messaging.p041ui.C1072Z;
import com.android.messaging.util.C1480va;

/* renamed from: com.android.messaging.ui.mediapicker.CameraMediaChooserView */
public class CameraMediaChooserView extends FrameLayout implements C1072Z {

    /* renamed from: Jg */
    private boolean f2010Jg;

    public CameraMediaChooserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!canvas.isHardwareAccelerated() && !this.f2010Jg) {
            this.f2010Jg = true;
            C1480va.getMainThreadHandler().post(new C1276D(this));
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            C1352t.get().mo7932Ka(((Bundle) parcelable).getInt("camera_index"));
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt("camera_index", C1352t.get().mo7942lj());
        return bundle;
    }

    public void resetState() {
        C1352t.get().mo7931Ja(0);
    }

    public void restoreState(Parcelable parcelable) {
        onRestoreInstanceState(parcelable);
    }

    public Parcelable saveState() {
        return onSaveInstanceState();
    }
}
