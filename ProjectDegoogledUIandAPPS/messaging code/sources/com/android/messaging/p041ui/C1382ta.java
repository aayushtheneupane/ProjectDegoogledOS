package com.android.messaging.p041ui;

import android.view.View;
import android.view.ViewPropertyAnimator;

/* renamed from: com.android.messaging.ui.ta */
public class C1382ta implements C1384ua {
    private final View mView;

    public C1382ta(View view) {
        if (view != null) {
            this.mView = view;
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: c */
    public ViewPropertyAnimator mo8008c(C1380sa saVar) {
        return this.mView.animate().translationY(0.0f);
    }

    /* renamed from: d */
    public ViewPropertyAnimator mo8009d(C1380sa saVar) {
        return this.mView.animate().translationY((float) (-saVar.getRootView().getMeasuredHeight()));
    }
}
