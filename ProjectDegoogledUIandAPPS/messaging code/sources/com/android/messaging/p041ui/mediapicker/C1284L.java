package com.android.messaging.p041ui.mediapicker;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.android.messaging.p041ui.C1040Ea;

/* renamed from: com.android.messaging.ui.mediapicker.L */
public class C1284L {

    /* renamed from: Zo */
    private final Fragment f2029Zo;
    /* access modifiers changed from: private */
    public final C1283K mListener;

    public C1284L(Fragment fragment, C1283K k) {
        this.f2029Zo = fragment;
        this.mListener = k;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        Uri uri;
        String stringExtra = intent.getStringExtra("photo_url");
        if (stringExtra == null && (stringExtra = intent.getDataString()) == null && (extras = intent.getExtras()) != null && (uri = (Uri) extras.getParcelable("android.intent.extra.STREAM")) != null) {
            stringExtra = uri.toString();
        }
        if (stringExtra != null) {
            new C1282J(this, Uri.parse(stringExtra)).mo8233b(new Void[0]);
        }
    }

    /* renamed from: wj */
    public void mo7726wj() {
        C1040Ea.get().mo6966b(this.f2029Zo);
    }
}
