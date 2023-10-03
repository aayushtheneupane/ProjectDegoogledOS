package com.android.incallui;

import com.android.dialer.common.concurrent.DialerExecutor;

/* renamed from: com.android.incallui.-$$Lambda$InCallActivity$PdafVQkcqLAvpJqLIYQtzrrkqoE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$InCallActivity$PdafVQkcqLAvpJqLIYQtzrrkqoE implements DialerExecutor.FailureListener {
    public static final /* synthetic */ $$Lambda$InCallActivity$PdafVQkcqLAvpJqLIYQtzrrkqoE INSTANCE = new $$Lambda$InCallActivity$PdafVQkcqLAvpJqLIYQtzrrkqoE();

    private /* synthetic */ $$Lambda$InCallActivity$PdafVQkcqLAvpJqLIYQtzrrkqoE() {
    }

    public final void onFailure(Throwable th) {
        InCallActivity.lambda$showPhoneAccountSelectionDialog$1(th);
        throw null;
    }
}
