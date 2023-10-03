package com.android.p032ex.chips;

import android.text.Spannable;
import com.android.p032ex.chips.p033a.C0660b;
import java.util.Comparator;

/* renamed from: com.android.ex.chips.P */
class C0648P implements Comparator {

    /* renamed from: lv */
    final /* synthetic */ Spannable f767lv;

    C0648P(C0697qa qaVar, Spannable spannable) {
        this.f767lv = spannable;
    }

    public int compare(Object obj, Object obj2) {
        int spanStart = this.f767lv.getSpanStart((C0660b) obj);
        int spanStart2 = this.f767lv.getSpanStart((C0660b) obj2);
        if (spanStart < spanStart2) {
            return -1;
        }
        return spanStart > spanStart2 ? 1 : 0;
    }
}
