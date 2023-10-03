package com.android.messaging.p041ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.Y */
class C1071Y implements View.OnClickListener {
    final /* synthetic */ PermissionCheckActivity this$0;

    C1071Y(PermissionCheckActivity permissionCheckActivity) {
        this.this$0 = permissionCheckActivity;
    }

    public void onClick(View view) {
        StringBuilder Pa = C0632a.m1011Pa("package:");
        Pa.append(this.this$0.getPackageName());
        this.this$0.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(Pa.toString())));
    }
}
