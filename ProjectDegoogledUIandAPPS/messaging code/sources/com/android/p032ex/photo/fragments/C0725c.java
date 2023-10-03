package com.android.p032ex.photo.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/* renamed from: com.android.ex.photo.fragments.c */
class C0725c extends BroadcastReceiver {
    final /* synthetic */ PhotoViewFragment this$0;

    /* synthetic */ C0725c(PhotoViewFragment photoViewFragment, C0723a aVar) {
        this.this$0 = photoViewFragment;
    }

    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            this.this$0.f873Mo = false;
            return;
        }
        PhotoViewFragment photoViewFragment = this.this$0;
        if (!photoViewFragment.f873Mo && !photoViewFragment.mo5740_b()) {
            PhotoViewFragment photoViewFragment2 = this.this$0;
            if (!photoViewFragment2.f872Lo) {
                photoViewFragment2.getLoaderManager().mo225b(2, (Bundle) null, this.this$0);
            }
            this.this$0.getLoaderManager().mo225b(3, (Bundle) null, this.this$0);
            PhotoViewFragment photoViewFragment3 = this.this$0;
            photoViewFragment3.f873Mo = true;
            photoViewFragment3.f867Go.setVisibility(0);
        }
    }
}
