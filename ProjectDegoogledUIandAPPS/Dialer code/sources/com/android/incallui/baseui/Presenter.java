package com.android.incallui.baseui;

import android.os.Bundle;
import com.android.incallui.baseui.C0701Ui;

public abstract class Presenter<U extends C0701Ui> {

    /* renamed from: ui */
    private U f43ui;

    public U getUi() {
        return this.f43ui;
    }

    public void onRestoreInstanceState(Bundle bundle) {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public final void onUiDestroy(U u) {
        onUiUnready(u);
        this.f43ui = null;
    }

    public void onUiReady(U u) {
        this.f43ui = u;
    }

    public void onUiUnready(U u) {
    }
}
