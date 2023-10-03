package com.android.p032ex.photo.views;

import android.widget.ProgressBar;

/* renamed from: com.android.ex.photo.views.f */
public class C0753f {

    /* renamed from: Vw */
    private final ProgressBar f973Vw;

    /* renamed from: Ww */
    private final ProgressBar f974Ww;

    /* renamed from: Xw */
    private boolean f975Xw;

    public C0753f(ProgressBar progressBar, ProgressBar progressBar2, boolean z) {
        this.f973Vw = progressBar;
        this.f974Ww = progressBar2;
        this.f975Xw = z;
        setVisibility(this.f975Xw);
    }

    public void setVisibility(int i) {
        if (i == 4 || i == 8) {
            this.f974Ww.setVisibility(i);
            this.f973Vw.setVisibility(i);
            return;
        }
        setVisibility(this.f975Xw);
    }

    private void setVisibility(boolean z) {
        int i = 0;
        this.f974Ww.setVisibility(z ? 0 : 8);
        ProgressBar progressBar = this.f973Vw;
        if (z) {
            i = 8;
        }
        progressBar.setVisibility(i);
    }
}
