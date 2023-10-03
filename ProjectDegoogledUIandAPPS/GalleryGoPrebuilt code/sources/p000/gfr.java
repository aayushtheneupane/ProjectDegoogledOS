package p000;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.material.internal.CheckableImageButton;

/* renamed from: gfr */
/* compiled from: PG */
public final class gfr extends C0315ll {

    /* renamed from: b */
    private final /* synthetic */ CheckableImageButton f11178b;

    public gfr(CheckableImageButton checkableImageButton) {
        this.f11178b = checkableImageButton;
    }

    /* renamed from: d */
    public final void mo6587d(View view, AccessibilityEvent accessibilityEvent) {
        super.mo6587d(view, accessibilityEvent);
        accessibilityEvent.setChecked(this.f11178b.f5216a);
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        super.mo232a(view, mxVar);
        mxVar.mo9426a(this.f11178b.f5217b);
        mxVar.f15257a.setChecked(this.f11178b.f5216a);
    }
}
