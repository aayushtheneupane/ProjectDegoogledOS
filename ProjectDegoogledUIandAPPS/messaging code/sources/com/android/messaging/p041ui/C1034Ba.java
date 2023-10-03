package com.android.messaging.p041ui;

import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import com.google.common.base.C1504A;

/* renamed from: com.android.messaging.ui.Ba */
class C1034Ba implements Runnable {
    final /* synthetic */ C1038Da this$0;

    /* renamed from: vG */
    final /* synthetic */ C1380sa f1599vG;

    C1034Ba(C1038Da da, C1380sa saVar) {
        this.this$0 = da;
        this.f1599vG = saVar;
    }

    public void run() {
        this.this$0.f1640BG.setEnabled(true);
        C1038Da.m2557f(this.this$0);
        String hf = this.f1599vG.mo8006hf();
        if (!TextUtils.isEmpty(hf) && TextUtils.getTrimmedLength(hf) > 0) {
            String trim = hf.trim();
            String Xi = this.f1599vG.mo7997Xi();
            if (!C0107q.isAllWhitespace(Xi)) {
                trim = C1504A.m3943Ua(", ").mo8514a(trim, Xi, new Object[0]);
            }
            C0107q.m128a(this.f1599vG.mo8002cj(), (AccessibilityManager) null, (CharSequence) trim);
        }
    }
}
