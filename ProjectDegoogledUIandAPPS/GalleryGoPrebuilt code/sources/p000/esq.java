package p000;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;
import java.util.Locale;

/* renamed from: esq */
/* compiled from: PG */
public final class esq extends epv {

    /* renamed from: r */
    public final Context f8950r;

    public esq(Context context, Looper looper, ekt ekt, eku eku, epk epk) {
        super(context, looper, 29, epk, ekt, eku);
        this.f8950r = context;
        exl.m8321a(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo4883a() {
        return "com.google.android.gms.feedback.internal.IFeedbackService";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final String mo4884b() {
        return "com.google.android.gms.feedback.internal.IFeedbackService";
    }

    /* renamed from: c */
    public final int mo4885c() {
        return 11925000;
    }

    /* renamed from: t */
    public final ejt[] mo5129t() {
        return erw.f8885c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ IInterface mo4882a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.feedback.internal.IFeedbackService");
        if (!(queryLocalInterface instanceof esu)) {
            return new est(iBinder);
        }
        return (esu) queryLocalInterface;
    }

    /* renamed from: a */
    public final void mo5211a(esi esi) {
        String str;
        iir g = eue.f9025n.mo8793g();
        if (TextUtils.isEmpty(esi.f8930g)) {
            String packageName = this.f8950r.getApplicationContext().getPackageName();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue = (eue) g.f14318b;
            packageName.getClass();
            eue.f9027a |= 2;
            eue.f9029c = packageName;
        } else {
            String str2 = esi.f8930g;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue2 = (eue) g.f14318b;
            str2.getClass();
            eue2.f9027a |= 2;
            eue2.f9029c = str2;
        }
        try {
            str = this.f8950r.getPackageManager().getPackageInfo(((eue) g.f14318b).f9029c, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            str = null;
        }
        if (str != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue3 = (eue) g.f14318b;
            str.getClass();
            eue3.f9028b |= 2;
            eue3.f9036j = str;
        }
        String str3 = esi.f8924a;
        if (!TextUtils.isEmpty(str3) && !str3.equals("anonymous")) {
            String num = Integer.toString(new Account(str3, "com.google").name.toLowerCase(Locale.ENGLISH).hashCode());
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue4 = (eue) g.f14318b;
            num.getClass();
            eue4.f9027a |= 4;
            eue4.f9030d = num;
        }
        String str4 = esi.f8937n;
        if (str4 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue5 = (eue) g.f14318b;
            str4.getClass();
            eue5.f9027a |= 64;
            eue5.f9032f = str4;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        eue eue6 = (eue) g.f14318b;
        "feedback.android".getClass();
        eue6.f9027a |= 16;
        eue6.f9031e = "feedback.android";
        int i = ejw.f8455b;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        eue eue7 = (eue) g.f14318b;
        eue7.f9027a |= 1073741824;
        eue7.f9035i = i;
        long currentTimeMillis = System.currentTimeMillis();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        eue eue8 = (eue) g.f14318b;
        eue8.f9027a |= 16777216;
        eue8.f9034h = currentTimeMillis;
        if (!(esi.f8936m == null && esi.f8929f == null)) {
            eue8.f9028b |= 16;
            eue8.f9039m = true;
        }
        Bundle bundle = esi.f8925b;
        if (bundle != null) {
            int size = bundle.size();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue9 = (eue) g.f14318b;
            eue9.f9028b |= 4;
            eue9.f9037k = size;
        }
        List list = esi.f8931h;
        if (list != null && list.size() > 0) {
            int size2 = esi.f8931h.size();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            eue eue10 = (eue) g.f14318b;
            eue10.f9028b |= 8;
            eue10.f9038l = size2;
        }
        eue eue11 = (eue) g.mo8770g();
        iir iir = (iir) eue11.mo8790b(5);
        iir.mo8503a((iix) eue11);
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        eue eue12 = (eue) iir.f14318b;
        eue12.f9033g = 164;
        eue12.f9027a |= 256;
        eue eue13 = (eue) iir.mo8770g();
        Context context = this.f8950r;
        if (TextUtils.isEmpty(eue13.f9029c)) {
            Log.e("gF_BaseMetricsLogger", "MetricsData requires appPackageName to be set");
        }
        if (TextUtils.isEmpty(eue13.f9032f)) {
            Log.e("gF_BaseMetricsLogger", "MetricsData requires sessionId to be set");
        }
        if (TextUtils.isEmpty(eue13.f9031e)) {
            Log.e("gF_BaseMetricsLogger", "MetricsData requires flow to be set");
        }
        if (eue13.f9035i <= 0) {
            Log.e("gF_BaseMetricsLogger", "MetricsData requires clientVersion to be set");
        }
        if (eue13.f9034h <= 0) {
            Log.e("gF_BaseMetricsLogger", "MetricsData requires timestamp to be set");
        }
        int a = imu.m14134a(eue13.f9033g);
        if (a == 0 || a == 1) {
            Log.e("gF_BaseMetricsLogger", "MetricsData requires user action type to be set");
        }
        context.sendBroadcast(new Intent().setClassName("com.google.android.gms", "com.google.android.gms.chimera.GmsIntentOperationService$GmsExternalReceiver").setAction("com.google.android.gms.googlehelp.metrics.MetricsIntentOperation.LOG_METRIC").putExtra("EXTRA_METRIC_DATA", eue13.mo8512ag()));
    }
}
