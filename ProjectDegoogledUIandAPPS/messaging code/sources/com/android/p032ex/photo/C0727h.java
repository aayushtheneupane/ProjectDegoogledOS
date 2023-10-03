package com.android.p032ex.photo;

import android.os.Build;
import android.view.View;

/* renamed from: com.android.ex.photo.h */
class C0727h implements View.OnSystemUiVisibilityChangeListener {
    final /* synthetic */ C0734o this$0;

    C0727h(C0734o oVar) {
        this.this$0 = oVar;
    }

    public void onSystemUiVisibilityChange(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i == 0 && this.this$0.f884Zv == 3846) {
            this.this$0.mo5785d(false, true);
        }
    }
}
