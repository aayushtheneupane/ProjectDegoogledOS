package p000;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.preference.Preference;

/* renamed from: ebt */
/* compiled from: PG */
final /* synthetic */ class ebt implements ada {

    /* renamed from: a */
    private final eby f7864a;

    /* renamed from: b */
    private final Context f7865b;

    public ebt(eby eby, Context context) {
        this.f7864a = eby;
        this.f7865b = context;
    }

    /* renamed from: a */
    public final boolean mo189a(Preference preference) {
        eby eby = this.f7864a;
        Context context = this.f7865b;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=com.google.android.apps.photos"));
        intent.addFlags(268435456);
        if (eby.f7898x != null) {
            eby.f7886l.mo5552a(fdu.m8653a(), eby.f7898x);
        }
        context.startActivity(intent);
        return true;
    }
}
