package com.google.android.libraries.social.silentfeedback.tiktok;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/* compiled from: PG */
public final class SilentFeedbackReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        fth fth = (fth) iol.m14235b(context.getApplicationContext(), fth.class);
        eym a = fth.mo2318cr().mo5391a(fth.mo2319cs().mo5424a()).mo5392a();
        BroadcastReceiver.PendingResult goAsync = goAsync();
        fad ct = fth.mo2320ct();
        ezy a2 = fth.mo2321cu().mo5421a(a);
        ftg ftg = new ftg(goAsync);
        try {
            if (context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode >= 6577000) {
                a.mo5398a((eyk) new ftd(a2, intent, ct, a, ftg));
                a.mo5399a((eyl) new ftb(ftg));
                a.mo5397a();
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        ftg.mo6176a();
    }
}
