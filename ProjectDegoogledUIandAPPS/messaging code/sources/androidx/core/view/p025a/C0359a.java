package androidx.core.view.p025a;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* renamed from: androidx.core.view.a.a */
public final class C0359a extends ClickableSpan {

    /* renamed from: Ie */
    private final int f324Ie;

    /* renamed from: Je */
    private final C0363e f325Je;

    /* renamed from: Ke */
    private final int f326Ke;

    public C0359a(int i, C0363e eVar, int i2) {
        this.f324Ie = i;
        this.f325Je = eVar;
        this.f326Ke = i2;
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f324Ie);
        this.f325Je.performAction(this.f326Ke, bundle);
    }
}
