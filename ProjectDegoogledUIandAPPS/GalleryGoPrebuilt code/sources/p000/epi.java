package p000;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: epi */
/* compiled from: PG */
public abstract class epi {

    /* renamed from: q */
    private static final ejt[] f8746q = new ejt[0];

    /* renamed from: a */
    public int f8747a;

    /* renamed from: b */
    public long f8748b;

    /* renamed from: c */
    public final Context f8749c;

    /* renamed from: d */
    public final Handler f8750d;

    /* renamed from: e */
    public final Object f8751e = new Object();

    /* renamed from: f */
    public final Object f8752f = new Object();

    /* renamed from: g */
    public eqm f8753g;

    /* renamed from: h */
    public epc f8754h;

    /* renamed from: i */
    public final ArrayList f8755i = new ArrayList();

    /* renamed from: j */
    public int f8756j = 1;

    /* renamed from: k */
    public final eoy f8757k;

    /* renamed from: l */
    public final eoz f8758l;

    /* renamed from: m */
    public ejq f8759m = null;

    /* renamed from: n */
    public boolean f8760n = false;

    /* renamed from: o */
    public volatile epm f8761o = null;

    /* renamed from: p */
    public final AtomicInteger f8762p = new AtomicInteger(0);

    /* renamed from: r */
    private long f8763r;

    /* renamed from: s */
    private int f8764s;

    /* renamed from: t */
    private long f8765t;

    /* renamed from: u */
    private eqd f8766u;

    /* renamed from: v */
    private final epz f8767v;

    /* renamed from: w */
    private IInterface f8768w;

    /* renamed from: x */
    private epd f8769x;

    /* renamed from: y */
    private final int f8770y;

    /* renamed from: z */
    private final String f8771z;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract IInterface mo4882a(IBinder iBinder);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract String mo4883a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract String mo4884b();

    /* renamed from: c */
    public int mo4885c() {
        return ejx.f8457c;
    }

    /* renamed from: g */
    public boolean mo5120g() {
        return false;
    }

    /* renamed from: h */
    public final ejt[] mo5121h() {
        epm epm = this.f8761o;
        if (epm != null) {
            return epm.f8785b;
        }
        return null;
    }

    /* renamed from: k */
    public final void mo5123k() {
    }

    /* renamed from: l */
    public final void mo5124l() {
    }

    /* renamed from: q */
    public void mo5127q() {
    }

    /* renamed from: s */
    public ejt[] mo5128s() {
        throw null;
    }

    /* renamed from: t */
    public ejt[] mo5129t() {
        return f8746q;
    }

    protected epi(Context context, Looper looper, epz epz, ejx ejx, int i, eoy eoy, eoz eoz, String str) {
        this.f8749c = (Context) abj.m86a((Object) context, (Object) "Context must not be null");
        Looper looper2 = (Looper) abj.m86a((Object) looper, (Object) "Looper must not be null");
        this.f8767v = (epz) abj.m86a((Object) epz, (Object) "Supervisor must not be null");
        ejx ejx2 = (ejx) abj.m86a((Object) ejx, (Object) "API availability must not be null");
        this.f8750d = new epa(this, looper);
        this.f8770y = i;
        this.f8757k = eoy;
        this.f8758l = eoz;
        this.f8771z = str;
    }

    /* renamed from: a */
    public final boolean mo5116a(int i, int i2, IInterface iInterface) {
        synchronized (this.f8751e) {
            if (this.f8756j != i) {
                return false;
            }
            m7969a(i2, iInterface);
            return true;
        }
    }

    /* renamed from: a */
    public final void mo5112a(epc epc) {
        this.f8754h = (epc) abj.m86a((Object) epc, (Object) "Connection progress callbacks cannot be null.");
        m7969a(2, (IInterface) null);
    }

    /* renamed from: d */
    public final void mo5117d() {
        this.f8762p.incrementAndGet();
        synchronized (this.f8755i) {
            int size = this.f8755i.size();
            for (int i = 0; i < size; i++) {
                ((epb) this.f8755i.get(i)).mo5106e();
            }
            this.f8755i.clear();
        }
        synchronized (this.f8752f) {
            this.f8753g = null;
        }
        m7969a(1, (IInterface) null);
    }

    /* renamed from: a */
    public final void mo5115a(String str, PrintWriter printWriter) {
        int i;
        IInterface iInterface;
        eqm eqm;
        synchronized (this.f8751e) {
            i = this.f8756j;
            iInterface = this.f8768w;
        }
        synchronized (this.f8752f) {
            eqm = this.f8753g;
        }
        printWriter.append(str).append("mConnectState=");
        if (i == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i == 4) {
            printWriter.print("CONNECTED");
        } else if (i != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(mo4884b()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (eqm == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(eqm.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.f8763r > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.f8763r;
            String format = simpleDateFormat.format(new Date(j));
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(format);
            append.println(sb.toString());
        }
        if (this.f8748b > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            int i2 = this.f8747a;
            if (i2 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i2 != 2) {
                printWriter.append(String.valueOf(i2));
            } else {
                printWriter.append("CAUSE_NETWORK_LOST");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.f8748b;
            String format2 = simpleDateFormat.format(new Date(j2));
            StringBuilder sb2 = new StringBuilder(String.valueOf(format2).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(format2);
            append2.println(sb2.toString());
        }
        if (this.f8765t > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(eod.m7920a(this.f8764s));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.f8765t;
            String format3 = simpleDateFormat.format(new Date(j3));
            StringBuilder sb3 = new StringBuilder(String.valueOf(format3).length() + 21);
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(format3);
            append3.println(sb3.toString());
        }
    }

    /* renamed from: j */
    public final void mo5122j() {
        if (!mo5118e() || this.f8766u == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public Bundle mo5125o() {
        return new Bundle();
    }

    /* renamed from: r */
    private final void m7971r() {
        if (this.f8771z == null) {
            this.f8749c.getClass().getName();
        }
    }

    /* renamed from: a */
    public final void mo5114a(eqg eqg, Set set) {
        Bundle o = mo5125o();
        epr epr = new epr(this.f8770y);
        epr.f8795d = this.f8749c.getPackageName();
        epr.f8798g = o;
        if (set != null) {
            epr.f8797f = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (mo5120g()) {
            epr.f8799h = new Account("<<default account>>", "com.google");
            if (eqg != null) {
                epr.f8796e = eqg.asBinder();
            }
        }
        epr.f8800i = mo5128s();
        epr.f8801j = mo5129t();
        try {
            synchronized (this.f8752f) {
                eqm eqm = this.f8753g;
                if (eqm != null) {
                    eqm.mo5161a(new eqj(this, this.f8762p.get()), epr);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            Handler handler = this.f8750d;
            handler.sendMessage(handler.obtainMessage(6, this.f8762p.get(), 1));
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            mo5110a(8, (IBinder) null, (Bundle) null, this.f8762p.get());
        }
    }

    /* renamed from: p */
    public final IInterface mo5126p() {
        boolean z;
        IInterface iInterface;
        synchronized (this.f8751e) {
            if (this.f8756j == 5) {
                throw new DeadObjectException();
            } else if (mo5118e()) {
                if (this.f8768w != null) {
                    z = true;
                } else {
                    z = false;
                }
                abj.m108a(z, (Object) "Client is connected but service is null");
                iInterface = this.f8768w;
            } else {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            }
        }
        return iInterface;
    }

    /* renamed from: e */
    public final boolean mo5118e() {
        boolean z;
        synchronized (this.f8751e) {
            if (this.f8756j == 4) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: f */
    public final boolean mo5119f() {
        boolean z;
        synchronized (this.f8751e) {
            int i = this.f8756j;
            z = true;
            if (i != 2) {
                if (i != 3) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5111a(ejq ejq) {
        this.f8764s = ejq.f8443b;
        this.f8765t = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5110a(int i, IBinder iBinder, Bundle bundle, int i2) {
        Handler handler = this.f8750d;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new epf(this, i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5109a(int i, int i2) {
        Handler handler = this.f8750d;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new epg(this, i)));
    }

    /* renamed from: a */
    public final void mo5113a(eph eph) {
        enk enk = (enk) eph;
        enp enp = enk.f8647a.f8655h;
        Status status = enp.f8671a;
        enp.f8684m.post(new enj(enk));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final void m7969a(int i, IInterface iInterface) {
        boolean z = false;
        if ((i == 4) == (iInterface != null)) {
            z = true;
        }
        abj.m116b(z);
        synchronized (this.f8751e) {
            this.f8756j = i;
            this.f8768w = iInterface;
            mo5127q();
            if (i == 1) {
                epd epd = this.f8769x;
                if (epd != null) {
                    epz epz = this.f8767v;
                    eqd eqd = this.f8766u;
                    String str = eqd.f8834a;
                    String str2 = eqd.f8835b;
                    m7971r();
                    epz.mo5148a(str, str2, epd);
                    this.f8769x = null;
                }
            } else if (i == 2 || i == 3) {
                if (this.f8769x != null) {
                    eqd eqd2 = this.f8766u;
                    if (eqd2 != null) {
                        String str3 = eqd2.f8834a;
                        String str4 = eqd2.f8835b;
                        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 70 + str4.length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(str3);
                        sb.append(" on ");
                        sb.append(str4);
                        Log.e("GmsClient", sb.toString());
                        epz epz2 = this.f8767v;
                        eqd eqd3 = this.f8766u;
                        String str5 = eqd3.f8834a;
                        String str6 = eqd3.f8835b;
                        epd epd2 = this.f8769x;
                        m7971r();
                        epz2.mo5148a(str5, str6, epd2);
                        this.f8762p.incrementAndGet();
                    }
                }
                this.f8769x = new epd(this, this.f8762p.get());
                eqd eqd4 = new eqd("com.google.android.gms", mo4883a());
                this.f8766u = eqd4;
                epz epz3 = this.f8767v;
                String str7 = eqd4.f8834a;
                String str8 = eqd4.f8835b;
                epd epd3 = this.f8769x;
                m7971r();
                if (!epz3.mo5149b(new epy(str7, str8), epd3)) {
                    eqd eqd5 = this.f8766u;
                    String str9 = eqd5.f8834a;
                    String str10 = eqd5.f8835b;
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str9).length() + 34 + str10.length());
                    sb2.append("unable to connect to service: ");
                    sb2.append(str9);
                    sb2.append(" on ");
                    sb2.append(str10);
                    Log.e("GmsClient", sb2.toString());
                    mo5109a(16, this.f8762p.get());
                }
            } else if (i == 4) {
                this.f8763r = System.currentTimeMillis();
            }
        }
    }
}
