package p000;

import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: enp */
/* compiled from: PG */
public final class enp implements Handler.Callback {

    /* renamed from: a */
    public static final Status f8671a = new Status(4, "Sign-out occurred while this API call was in progress.");

    /* renamed from: b */
    public static final Status f8672b = new Status(4, "The user must be signed in to make this API call.");

    /* renamed from: f */
    public static final Object f8673f = new Object();

    /* renamed from: n */
    private static enp f8674n;

    /* renamed from: c */
    public final long f8675c = 5000;

    /* renamed from: d */
    public final long f8676d = 120000;

    /* renamed from: e */
    public long f8677e = 10000;

    /* renamed from: g */
    public final Context f8678g;

    /* renamed from: h */
    public final eqe f8679h;

    /* renamed from: i */
    public final AtomicInteger f8680i = new AtomicInteger(0);

    /* renamed from: j */
    public final Map f8681j = new ConcurrentHashMap(5, 0.75f, 1);

    /* renamed from: k */
    public emh f8682k = null;

    /* renamed from: l */
    public final Set f8683l = new C0292kp();

    /* renamed from: m */
    public final Handler f8684m;

    /* renamed from: o */
    private final ejw f8685o;

    /* renamed from: p */
    private final AtomicInteger f8686p = new AtomicInteger(1);

    /* renamed from: q */
    private final Set f8687q = new C0292kp();

    private enp(Context context, Looper looper, ejw ejw) {
        this.f8678g = context;
        this.f8684m = new eui(looper, this);
        this.f8685o = ejw;
        this.f8679h = new eqe(ejw);
        Handler handler = this.f8684m;
        handler.sendMessage(handler.obtainMessage(6));
    }

    /* renamed from: a */
    public static enp m7890a(Context context) {
        enp enp;
        synchronized (f8673f) {
            if (f8674n == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                f8674n = new enp(context.getApplicationContext(), handlerThread.getLooper(), ejw.f8454a);
            }
            enp = f8674n;
        }
        return enp;
    }

    /* renamed from: a */
    public final int mo5058a() {
        return this.f8686p.getAndIncrement();
    }

    public final boolean handleMessage(Message message) {
        Status status;
        ejt[] a;
        long j = 300000;
        enl enl = null;
        switch (message.what) {
            case 1:
                if (((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.f8677e = j;
                this.f8684m.removeMessages(12);
                for (eln obtainMessage : this.f8681j.keySet()) {
                    Handler handler = this.f8684m;
                    handler.sendMessageDelayed(handler.obtainMessage(12, obtainMessage), this.f8677e);
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                eki eki = (eki) message.obj;
                throw null;
            case 3:
                for (enl enl2 : this.f8681j.values()) {
                    enl2.mo5047e();
                    enl2.mo5050h();
                }
                break;
            case 4:
            case 8:
            case 13:
                eoc eoc = (eoc) message.obj;
                enl enl3 = (enl) this.f8681j.get(eoc.f8702c.f8486c);
                if (enl3 == null) {
                    m7891b(eoc.f8702c);
                    enl3 = (enl) this.f8681j.get(eoc.f8702c.f8486c);
                }
                if (enl3.mo5051i() && this.f8680i.get() != eoc.f8701b) {
                    eoc.f8700a.mo4962a(f8671a);
                    enl3.mo5046d();
                    break;
                } else {
                    enl3.mo5043a(eoc.f8700a);
                    break;
                }
            case 5:
                int i = message.arg1;
                ejq ejq = (ejq) message.obj;
                Iterator it = this.f8681j.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        enl enl4 = (enl) it.next();
                        if (enl4.f8652e == i) {
                            enl = enl4;
                        }
                    }
                }
                if (enl == null) {
                    StringBuilder sb = new StringBuilder(76);
                    sb.append("Could not find API instance ");
                    sb.append(i);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                    break;
                } else {
                    String a2 = ekh.m7669a(ejq.f8443b);
                    String str = ejq.f8445d;
                    StringBuilder sb2 = new StringBuilder(String.valueOf(a2).length() + 69 + String.valueOf(str).length());
                    sb2.append("Error resolution was canceled by the user, original error message: ");
                    sb2.append(a2);
                    sb2.append(": ");
                    sb2.append(str);
                    enl.mo5042a(new Status(17, sb2.toString()));
                    break;
                }
            case 6:
                if (this.f8678g.getApplicationContext() instanceof Application) {
                    Application application = (Application) this.f8678g.getApplicationContext();
                    synchronized (elp.f8513a) {
                        if (!elp.f8513a.f8517e) {
                            application.registerActivityLifecycleCallbacks(elp.f8513a);
                            application.registerComponentCallbacks(elp.f8513a);
                            elp.f8513a.f8517e = true;
                        }
                    }
                    elp elp = elp.f8513a;
                    eng eng = new eng(this);
                    synchronized (elp.f8513a) {
                        elp.f8516d.add(eng);
                    }
                    elp elp2 = elp.f8513a;
                    if (!elp2.f8515c.get()) {
                        int i2 = Build.VERSION.SDK_INT;
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(runningAppProcessInfo);
                        if (!elp2.f8515c.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                            elp2.f8514b.set(true);
                        }
                    }
                    if (!elp2.f8514b.get()) {
                        this.f8677e = 300000;
                        break;
                    }
                }
                break;
            case 7:
                m7891b((ekr) message.obj);
                break;
            case 9:
                if (this.f8681j.containsKey(message.obj)) {
                    enl enl5 = (enl) this.f8681j.get(message.obj);
                    abj.m90a(enl5.f8655h.f8684m);
                    if (enl5.f8653f) {
                        enl5.mo5050h();
                        break;
                    }
                }
                break;
            case 10:
                for (eln remove : this.f8687q) {
                    ((enl) this.f8681j.remove(remove)).mo5046d();
                }
                this.f8687q.clear();
                break;
            case 11:
                if (this.f8681j.containsKey(message.obj)) {
                    enl enl6 = (enl) this.f8681j.get(message.obj);
                    abj.m90a(enl6.f8655h.f8684m);
                    if (enl6.f8653f) {
                        enl6.mo5048f();
                        enp enp = enl6.f8655h;
                        if (enp.f8685o.mo4918b(enp.f8678g) == 18) {
                            status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                        } else {
                            status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                        }
                        enl6.mo5042a(status);
                        enl6.f8649b.mo4931d();
                        break;
                    }
                }
                break;
            case 12:
                if (this.f8681j.containsKey(message.obj)) {
                    enl enl7 = (enl) this.f8681j.get(message.obj);
                    abj.m90a(enl7.f8655h.f8684m);
                    if (enl7.f8649b.mo4932e() && enl7.f8651d.size() == 0) {
                        emg emg = enl7.f8650c;
                        if (emg.f8552a.isEmpty() && emg.f8553b.isEmpty()) {
                            enl7.f8649b.mo4931d();
                            break;
                        } else {
                            enl7.mo5049g();
                            break;
                        }
                    }
                }
                break;
            case 14:
                eki eki2 = (eki) message.obj;
                throw null;
            case 15:
                enm enm = (enm) message.obj;
                if (this.f8681j.containsKey(enm.f8661a)) {
                    enl enl8 = (enl) this.f8681j.get(enm.f8661a);
                    if (enl8.f8654g.contains(enm) && !enl8.f8653f) {
                        if (enl8.f8649b.mo4932e()) {
                            enl8.mo5045c();
                            break;
                        } else {
                            enl8.mo5050h();
                            break;
                        }
                    }
                }
                break;
            case 16:
                enm enm2 = (enm) message.obj;
                if (this.f8681j.containsKey(enm2.f8661a)) {
                    enl enl9 = (enl) this.f8681j.get(enm2.f8661a);
                    if (enl9.f8654g.remove(enm2)) {
                        enl9.f8655h.f8684m.removeMessages(15, enm2);
                        enl9.f8655h.f8684m.removeMessages(16, enm2);
                        ejt ejt = enm2.f8662b;
                        ArrayList arrayList = new ArrayList(enl9.f8648a.size());
                        for (ell ell : enl9.f8648a) {
                            if ((ell instanceof elg) && (a = ((elg) ell).mo4960a(enl9)) != null) {
                                int length = a.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 < length) {
                                        if (!C0652xy.m16068a((Object) a[i3], (Object) ejt)) {
                                            i3++;
                                        } else if (i3 >= 0) {
                                            arrayList.add(ell);
                                        }
                                    }
                                }
                            }
                        }
                        int size = arrayList.size();
                        for (int i4 = 0; i4 < size; i4++) {
                            ell ell2 = (ell) arrayList.get(i4);
                            enl9.f8648a.remove(ell2);
                            ell2.mo4964a((Exception) new elf(ejt));
                        }
                        break;
                    }
                }
                break;
            default:
                int i5 = message.what;
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i5);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
        return true;
    }

    /* renamed from: b */
    public final void mo5063b(ejq ejq, int i) {
        if (!mo5061a(ejq, i)) {
            Handler handler = this.f8684m;
            handler.sendMessage(handler.obtainMessage(5, i, 0, ejq));
        }
    }

    /* renamed from: b */
    public final void mo5062b() {
        Handler handler = this.f8684m;
        handler.sendMessage(handler.obtainMessage(3));
    }

    /* renamed from: a */
    public final void mo5059a(ekr ekr) {
        Handler handler = this.f8684m;
        handler.sendMessage(handler.obtainMessage(7, ekr));
    }

    /* renamed from: b */
    private final void m7891b(ekr ekr) {
        eln eln = ekr.f8486c;
        enl enl = (enl) this.f8681j.get(eln);
        if (enl == null) {
            enl = new enl(this, ekr);
            this.f8681j.put(eln, enl);
        }
        if (enl.mo5051i()) {
            this.f8687q.add(eln);
        }
        enl.mo5050h();
    }

    /* renamed from: a */
    public final void mo5060a(emh emh) {
        synchronized (f8673f) {
            if (this.f8682k != emh) {
                this.f8682k = emh;
                this.f8683l.clear();
            }
            this.f8683l.addAll(emh.f8554e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo5061a(ejq ejq, int i) {
        PendingIntent pendingIntent;
        ejw ejw = this.f8685o;
        Context context = this.f8678g;
        if (!ejq.mo4894a()) {
            pendingIntent = ejw.mo4920b(context, ejq.f8443b, (String) null);
        } else {
            pendingIntent = ejq.f8444c;
        }
        if (pendingIntent == null) {
            return false;
        }
        ejw.mo4916a(context, ejq.f8443b, PendingIntent.getActivity(context, 0, GoogleApiActivity.m4940a(context, pendingIntent, i, true), 134217728));
        return true;
    }
}
