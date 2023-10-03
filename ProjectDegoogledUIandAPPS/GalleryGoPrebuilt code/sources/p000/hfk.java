package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: hfk */
/* compiled from: PG */
public class hfk extends BroadcastReceiver {

    /* renamed from: a */
    public static final hvy f12654a = hvy.m12261a("com/google/apps/tiktok/receiver/IntentFilterAcledReceiver");

    /* renamed from: b */
    private final Class f12655b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public hfl mo3316a(Object obj) {
        throw null;
    }

    public final void onReceive(Context context, Intent intent) {
        ResolveInfo resolveInfo;
        String str;
        ieh ieh;
        hlj a;
        try {
            intent.hasExtra("foo");
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(new Intent(context, getClass()), 0);
            if (!queryBroadcastReceivers.isEmpty()) {
                resolveInfo = queryBroadcastReceivers.get(0);
            } else {
                resolveInfo = null;
            }
            if (resolveInfo != null && resolveInfo.activityInfo.exported) {
                try {
                    frc.m9450a(context, intent, this);
                } catch (frb e) {
                    ((hvv) ((hvv) f12654a.mo8178a()).mo8201a("com/google/apps/tiktok/receiver/IntentFilterAcledReceiver", "onReceive", 61, "IntentFilterAcledReceiver.java")).mo8206a("Got unexpected intent: %s", (Object) intent);
                    return;
                }
            }
            String action = intent.getAction();
            String name = resolveInfo != null ? getClass().getName() : "anonymous";
            if (action != null) {
                StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 14 + action.length());
                sb.append("Broadcast to ");
                sb.append(name);
                sb.append(" ");
                sb.append(action);
                str = sb.toString();
            } else {
                String valueOf = String.valueOf(name);
                str = valueOf.length() == 0 ? new String("Broadcast to ") : "Broadcast to ".concat(valueOf);
            }
            try {
                hlp a2 = hok.m11840b(context).mo7579a(str, hnf.f13084a);
                try {
                    isOrderedBroadcast();
                    hlj a3 = hnb.m11765a("getEntryPoint");
                    try {
                        ieh = ife.m12820a(hgh.m11442a(context, this.f12655b));
                    } catch (IllegalStateException e2) {
                        ieh = ife.m12822a((Throwable) new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e2));
                    } catch (Throwable th) {
                        if (a3 != null) {
                            a3.close();
                        }
                        throw th;
                    }
                    ieh a4 = a3.mo7548a(ieh);
                    if (a3 != null) {
                        a3.close();
                    }
                    getResultCode();
                    a = hnb.m11765a("handleBroadcast");
                    ieh a5 = a.mo7548a(ibv.m12658a(a4, hmq.m11744a((icf) new hfh(this, intent)), (Executor) idh.f13918a));
                    if (a != null) {
                        a.close();
                    }
                    if (!a5.isDone()) {
                        BroadcastReceiver.PendingResult goAsync = goAsync();
                        goAsync.getClass();
                        a5.mo53a(hmq.m11748a((Runnable) new hfi(goAsync)), idh.f13918a);
                    }
                    a5.mo53a(hmq.m11748a((Runnable) new hfg(a5)), idh.f13918a);
                    if (a2 != null) {
                        a2.close();
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    if (a2 != null) {
                        a2.close();
                    }
                    throw th2;
                }
                throw th;
            } catch (Throwable th3) {
                hqo.m11925a(th3);
                throw new RuntimeException(th3);
            }
        } catch (Throwable th4) {
            ((hvv) ((hvv) ((hvv) f12654a.mo8178a()).mo8202a(th4)).mo8201a("com/google/apps/tiktok/receiver/IntentFilterAcledReceiver", "onReceive", 54, "IntentFilterAcledReceiver.java")).mo8203a("Got invalid intent");
        }
    }

    protected hfk(Class cls) {
        this.f12655b = cls;
    }
}
