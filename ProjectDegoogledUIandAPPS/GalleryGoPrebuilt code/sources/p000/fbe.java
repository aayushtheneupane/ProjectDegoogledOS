package p000;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.gms.googlehelp.GoogleHelp;
import java.lang.ref.WeakReference;

@Deprecated
/* renamed from: fbe */
/* compiled from: PG */
final class fbe implements fbc {

    /* renamed from: a */
    private final etb f9252a;

    /* renamed from: a */
    public final void mo5458a(Intent intent) {
        etb etb = this.f9252a;
        if (!intent.getAction().equals("com.google.android.gms.googlehelp.HELP") || !intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            throw new IllegalArgumentException("The intent you are trying to launch is not GoogleHelp intent! This class only supports GoogleHelp intents.");
        }
        int a = ekh.m7667a((Context) etb.f8974a, 11925000);
        if (a == 0) {
            etu a2 = etd.m8139a(etb.f8974a);
            abj.m85a((Object) a2.f9017h);
            esz esz = etu.f9016g;
            ekv ekv = a2.f8489f;
            C0652xy.m16066a((ekx) ekv.mo4948a(new etp(ekv, intent, new WeakReference(a2.f9017h))));
            return;
        }
        Intent data = new Intent("android.intent.action.VIEW").setData(((GoogleHelp) intent.getParcelableExtra("EXTRA_GOOGLE_HELP")).f5080b);
        if (a == 7 || etb.f8974a.getPackageManager().queryIntentActivities(data, 0).size() <= 0) {
            Activity activity = etb.f8974a;
            if (ekh.m7673b(activity, a)) {
                a = 18;
            }
            ejw.f8454a.mo4913a(activity, a, 0, (DialogInterface.OnCancelListener) null);
            return;
        }
        etb.f8974a.startActivity(data);
    }

    public fbe(Activity activity) {
        this.f9252a = new etb(activity);
    }
}
