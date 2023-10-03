package com.android.messaging.datamodel.action;

import android.content.Context;
import android.widget.Toast;
import com.android.messaging.R;

/* renamed from: com.android.messaging.datamodel.action.h */
class C0818h implements C0806U {
    private final Context mContext;

    C0818h(Context context) {
        this.mContext = context;
    }

    /* renamed from: a */
    public void mo6019a(UpdateDestinationBlockedAction updateDestinationBlockedAction, boolean z, boolean z2, String str) {
        if (z) {
            Toast.makeText(this.mContext, z2 ? R.string.update_destination_blocked : R.string.update_destination_unblocked, 1).show();
        }
    }
}
