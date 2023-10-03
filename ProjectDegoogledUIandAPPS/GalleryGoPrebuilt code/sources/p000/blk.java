package p000;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.google.android.apps.photosgo.assassin.AssassinActivity;

/* renamed from: blk */
/* compiled from: PG */
public final class blk implements blo {

    /* renamed from: a */
    public final AssassinActivity f3107a;

    /* renamed from: b */
    public final hlz f3108b;

    /* renamed from: c */
    public final String f3109c;

    /* renamed from: d */
    public final fee f3110d;

    public blk(AssassinActivity assassinActivity, hlz hlz, String str, fee fee) {
        this.f3107a = assassinActivity;
        this.f3108b = hlz;
        this.f3109c = str;
        this.f3110d = fee;
    }

    /* renamed from: b */
    public final int mo2565b() {
        return 2;
    }

    /* renamed from: a */
    public final void mo2564a() {
        String packageName = this.f3107a.getPackageName();
        try {
            AssassinActivity assassinActivity = this.f3107a;
            String valueOf = String.valueOf(packageName);
            assassinActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(valueOf.length() == 0 ? new String("market://details?id=") : "market://details?id=".concat(valueOf))));
        } catch (ActivityNotFoundException e) {
            AssassinActivity assassinActivity2 = this.f3107a;
            String valueOf2 = String.valueOf(packageName);
            assassinActivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(valueOf2.length() == 0 ? new String("https://play.google.com/store/apps/details?id=") : "https://play.google.com/store/apps/details?id=".concat(valueOf2))));
        }
        this.f3107a.finish();
    }
}
