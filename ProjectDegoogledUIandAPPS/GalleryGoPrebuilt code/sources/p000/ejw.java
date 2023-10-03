package p000;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.apps.photosgo.R;

/* renamed from: ejw */
/* compiled from: PG */
public final class ejw extends ejx {

    /* renamed from: a */
    public static final ejw f8454a = new ejw();

    /* renamed from: b */
    public static final int f8455b = ejx.f8457c;

    /* renamed from: e */
    private static final Object f8456e = new Object();

    /* renamed from: a */
    public static final Dialog m7637a(Context context, int i, epq epq, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = null;
        if (i == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843529, typedValue, true);
        if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId))) {
            builder = new AlertDialog.Builder(context, 5);
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(epl.m8004c(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String e = epl.m8006e(context, i);
        if (e != null) {
            builder.setPositiveButton(e, epq);
        }
        String a = epl.m7999a(context, i);
        if (a != null) {
            builder.setTitle(a);
        }
        return builder.create();
    }

    /* renamed from: a */
    public final ens mo4912a(Context context, enr enr) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        ens ens = new ens(enr);
        context.registerReceiver(ens, intentFilter);
        ens.f8689a = context;
        if (ekh.m7670a(context, "com.google.android.gms")) {
            return ens;
        }
        enr.mo4982a();
        ens.mo5065a();
        return null;
    }

    /* renamed from: a */
    public static final void m7638a(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof C0149fj) {
                C0171gd d = ((C0149fj) activity).mo5851d();
                ekj ekj = new ekj();
                Dialog dialog2 = (Dialog) abj.m86a((Object) dialog, (Object) "Cannot display null dialog");
                dialog2.setOnCancelListener((DialogInterface.OnCancelListener) null);
                dialog2.setOnDismissListener((DialogInterface.OnDismissListener) null);
                ekj.f8473Z = dialog2;
                if (onCancelListener != null) {
                    ekj.f8474aa = onCancelListener;
                }
                ekj.mo5427a(d, str);
                return;
            }
        } catch (NoClassDefFoundError e) {
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        ejs ejs = new ejs();
        Dialog dialog3 = (Dialog) abj.m86a((Object) dialog, (Object) "Cannot display null dialog");
        dialog3.setOnCancelListener((DialogInterface.OnCancelListener) null);
        dialog3.setOnDismissListener((DialogInterface.OnDismissListener) null);
        ejs.f8447a = dialog3;
        if (onCancelListener != null) {
            ejs.f8448b = onCancelListener;
        }
        ejs.show(fragmentManager, str);
    }

    /* renamed from: a */
    public final void mo4913a(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog a = m7637a((Context) activity, i, (epq) new epo(mo4917a(activity, i, "d"), activity, i2), onCancelListener);
        if (a != null) {
            m7638a(activity, a, "GooglePlayServicesErrorDialog", onCancelListener);
        }
    }

    /* renamed from: a */
    public final void mo4915a(Context context, int i) {
        mo4916a(context, i, mo4920b(context, i, "n"));
    }

    /* renamed from: a */
    public final void mo4916a(Context context, int i, PendingIntent pendingIntent) {
        int i2;
        if (i == 18) {
            mo4914a(context);
        } else if (pendingIntent != null) {
            String b = epl.m8003b(context, i);
            String d = epl.m8005d(context, i);
            Resources resources = context.getResources();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            C0211hp hpVar = new C0211hp(context);
            hpVar.f13199i = true;
            hpVar.f13202l.flags |= 16;
            hpVar.f13194d = C0211hp.m11861a((CharSequence) b);
            C0212hq hqVar = new C0212hq((byte[]) null);
            hqVar.f13244b = C0211hp.m11861a((CharSequence) d);
            hpVar.mo7639a(hqVar);
            if (!esv.m8127a(context)) {
                hpVar.mo7638a(17301642);
                hpVar.f13202l.tickerText = C0211hp.m11861a((CharSequence) resources.getString(R.string.common_google_play_services_notification_ticker));
                hpVar.f13202l.when = System.currentTimeMillis();
                hpVar.f13196f = pendingIntent;
                hpVar.f13195e = C0211hp.m11861a((CharSequence) d);
            } else {
                int i3 = Build.VERSION.SDK_INT;
                abj.m107a(true);
                hpVar.mo7638a(context.getApplicationInfo().icon);
                hpVar.f13197g = 2;
                esv.m8128b(context);
                hpVar.f13196f = pendingIntent;
            }
            int i4 = Build.VERSION.SDK_INT;
            int i5 = Build.VERSION.SDK_INT;
            abj.m107a(true);
            synchronized (f8456e) {
            }
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
            String a = epl.m7998a(context);
            if (notificationChannel == null) {
                notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", a, 4));
            } else if (!a.contentEquals(notificationChannel.getName())) {
                notificationChannel.setName(a);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            hpVar.f13201k = "com.google.android.gms.availability";
            C0213hr hrVar = new C0213hr(hpVar);
            C0212hq hqVar2 = hrVar.f13290b.f13198h;
            if (hqVar2 != null) {
                int i6 = Build.VERSION.SDK_INT;
                new Notification.BigTextStyle(hrVar.f13289a).setBigContentTitle((CharSequence) null).bigText(hqVar2.f13244b);
            }
            int i7 = Build.VERSION.SDK_INT;
            Notification build = hrVar.f13289a.build();
            int i8 = Build.VERSION.SDK_INT;
            int i9 = Build.VERSION.SDK_INT;
            int i10 = Build.VERSION.SDK_INT;
            if (hqVar2 != null) {
                int i11 = Build.VERSION.SDK_INT;
                Bundle bundle = build.extras;
            }
            if (i == 1 || i == 2 || i == 3) {
                ekh.f8470b.set(false);
                i2 = 10436;
            } else {
                i2 = 39789;
            }
            notificationManager.notify(i2, build);
        } else if (i == 6) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }

    /* renamed from: a */
    public final void mo4914a(Context context) {
        new ejv(this, context).sendEmptyMessageDelayed(1, 120000);
    }
}
