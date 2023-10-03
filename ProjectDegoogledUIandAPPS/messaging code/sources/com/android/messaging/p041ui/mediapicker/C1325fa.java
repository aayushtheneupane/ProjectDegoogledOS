package com.android.messaging.p041ui.mediapicker;

import androidx.viewpager.widget.C0626k;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.fa */
class C1325fa implements C0626k {
    final /* synthetic */ C1345pa this$0;

    C1325fa(C1345pa paVar) {
        this.this$0 = paVar;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (C1486ya.m3860pk()) {
            i = (this.this$0.f2133hb.size() - 1) - i;
        }
        C1345pa paVar = this.this$0;
        paVar.mo7898a((C1323ea) paVar.f2133hb.get(i));
    }
}
