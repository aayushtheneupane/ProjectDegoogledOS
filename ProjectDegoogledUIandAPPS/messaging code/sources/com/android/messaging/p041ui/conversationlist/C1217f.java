package com.android.messaging.p041ui.conversationlist;

import android.content.Context;
import android.view.View;
import com.android.messaging.R;
import com.android.messaging.datamodel.action.C0806U;
import com.android.messaging.datamodel.action.UpdateDestinationBlockedAction;
import com.android.messaging.util.C1486ya;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversationlist.f */
class C1217f implements C0806U {

    /* renamed from: lG */
    private final List f1932lG;
    private final Context mContext;

    /* renamed from: oG */
    private final View f1933oG;

    /* renamed from: sH */
    private final Runnable f1934sH;

    C1217f(Context context, View view, Runnable runnable, List list) {
        this.mContext = context;
        this.f1933oG = view;
        this.f1934sH = runnable;
        this.f1932lG = list;
    }

    /* renamed from: a */
    public void mo6019a(UpdateDestinationBlockedAction updateDestinationBlockedAction, boolean z, boolean z2, String str) {
        if (z) {
            C1486ya.m3853a(this.mContext, this.f1933oG, this.mContext.getResources().getString(z2 ? R.string.blocked_toast_message : R.string.unblocked_toast_message, new Object[]{1}), this.f1934sH, 0, this.f1932lG);
        }
    }
}
