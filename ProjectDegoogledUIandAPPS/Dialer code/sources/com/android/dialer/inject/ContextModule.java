package com.android.dialer.inject;

import android.content.Context;

public final class ContextModule {
    private final Context context;

    public ContextModule(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: package-private */
    public Context provideContext() {
        return this.context;
    }
}
