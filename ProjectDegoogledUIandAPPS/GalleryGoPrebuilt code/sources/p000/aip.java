package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import androidx.work.impl.WorkDatabase;
import com.google.android.apps.photosgo.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: aip */
/* compiled from: PG */
public final class aip extends ahq {

    /* renamed from: j */
    public static final Object f549j = new Object();

    /* renamed from: k */
    private static aip f550k = null;

    /* renamed from: l */
    private static aip f551l = null;

    /* renamed from: a */
    public final Context f552a;

    /* renamed from: b */
    public final agz f553b;

    /* renamed from: c */
    public final WorkDatabase f554c;

    /* renamed from: d */
    public final amz f555d;

    /* renamed from: e */
    public final List f556e;

    /* renamed from: f */
    public final ahz f557f;

    /* renamed from: g */
    public final amd f558g;

    /* renamed from: h */
    public boolean f559h = false;

    /* renamed from: i */
    public BroadcastReceiver.PendingResult f560i;

    private aip(Context context, agz agz, amz amz) {
        WorkDatabase a = WorkDatabase.m1113a(context.getApplicationContext(), ((anb) amz).f809a, context.getResources().getBoolean(R.bool.workmanager_test_configuration));
        Context applicationContext = context.getApplicationContext();
        iol.m14232a(new iol((byte[]) null));
        List asList = Arrays.asList(new aia[]{aib.m532a(applicationContext, this), new aiu(applicationContext, amz, this)});
        ahz ahz = new ahz(context, agz, amz, a, asList);
        Context applicationContext2 = context.getApplicationContext();
        this.f552a = applicationContext2;
        this.f553b = agz;
        this.f555d = amz;
        this.f554c = a;
        this.f556e = asList;
        this.f557f = ahz;
        this.f558g = new amd(a);
        this.f555d.mo668a(new ama(applicationContext2, this));
    }

    /* renamed from: a */
    public final aho mo493a(List list) {
        if (!list.isEmpty()) {
            return new aic(this, list).mo517a();
        }
        throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
    }

    /* renamed from: a */
    public final aho mo491a(String str, ahs ahs) {
        return new aic(this, str, Collections.singletonList(ahs)).mo517a();
    }

    /* renamed from: a */
    public final aho mo492a(String str, List list) {
        return new aic(this, str, list).mo517a();
    }

    /* renamed from: a */
    public static aip m549a(Context context) {
        aip aip;
        synchronized (f549j) {
            synchronized (f549j) {
                if (f550k != null) {
                    aip = f550k;
                } else {
                    aip = f551l;
                }
            }
            if (aip == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext instanceof agy) {
                    agz a = ((agy) applicationContext).mo455a();
                    synchronized (f549j) {
                        if (f550k != null) {
                            if (f551l != null) {
                                throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
                            }
                        }
                        if (f550k == null) {
                            Context applicationContext2 = applicationContext.getApplicationContext();
                            if (f551l == null) {
                                f551l = new aip(applicationContext2, a, new anb(a.f464b));
                            }
                            f550k = f551l;
                        }
                    }
                    aip = m549a(applicationContext);
                } else {
                    throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
                }
            }
        }
        return aip;
    }

    /* renamed from: a */
    public final void mo522a() {
        int i = Build.VERSION.SDK_INT;
        ajh.m600a(this.f552a);
        this.f554c.mo1226j().mo614d();
        aib.m533a(this.f554c, this.f556e);
    }

    /* renamed from: a */
    public final void mo523a(String str) {
        mo524a(str, (ckx) null);
    }

    /* renamed from: a */
    public final void mo524a(String str, ckx ckx) {
        this.f555d.mo668a(new amg(this, str, ckx, (byte[]) null, (byte[]) null));
    }

    /* renamed from: b */
    public final void mo525b(String str) {
        this.f555d.mo668a(new amh(this, str, false));
    }
}
