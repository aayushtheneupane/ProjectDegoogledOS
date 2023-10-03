package p000;

import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* renamed from: ms */
/* compiled from: PG */
public final class C0349ms extends ClickableSpan {

    /* renamed from: a */
    private final int f15241a;

    /* renamed from: b */
    private final C0354mx f15242b;

    /* renamed from: c */
    private final int f15243c;

    public C0349ms(int i, C0354mx mxVar, int i2) {
        this.f15241a = i;
        this.f15242b = mxVar;
        this.f15243c = i2;
    }

    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f15241a);
        C0354mx mxVar = this.f15242b;
        int i = this.f15243c;
        int i2 = Build.VERSION.SDK_INT;
        mxVar.f15257a.performAction(i, bundle);
    }
}
