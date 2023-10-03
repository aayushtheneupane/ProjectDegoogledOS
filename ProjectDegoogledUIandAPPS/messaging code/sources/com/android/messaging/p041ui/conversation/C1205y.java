package com.android.messaging.p041ui.conversation;

import android.app.Activity;
import android.content.DialogInterface;

/* renamed from: com.android.messaging.ui.conversation.y */
class C1205y implements DialogInterface.OnClickListener {

    /* renamed from: SG */
    final /* synthetic */ String f1894SG;
    final /* synthetic */ Activity val$activity;

    C1205y(String str, Activity activity) {
        this.f1894SG = str;
        this.val$activity = activity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C1146O.m2859a(this.f1894SG, this.val$activity);
    }
}
