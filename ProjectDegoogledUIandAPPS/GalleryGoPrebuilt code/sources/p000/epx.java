package p000;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: epx */
/* compiled from: PG */
public final class epx implements Handler.Callback {

    /* renamed from: a */
    public final epw f8807a;

    /* renamed from: b */
    public final ArrayList f8808b = new ArrayList();

    /* renamed from: c */
    public final ArrayList f8809c = new ArrayList();

    /* renamed from: d */
    public final ArrayList f8810d = new ArrayList();

    /* renamed from: e */
    public volatile boolean f8811e = false;

    /* renamed from: f */
    public final AtomicInteger f8812f = new AtomicInteger(0);

    /* renamed from: g */
    public boolean f8813g = false;

    /* renamed from: h */
    public final Handler f8814h;

    /* renamed from: i */
    public final Object f8815i = new Object();

    public epx(Looper looper, epw epw) {
        this.f8807a = epw;
        this.f8814h = new eui(looper, this);
    }

    /* renamed from: a */
    public final void mo5139a() {
        this.f8811e = false;
        this.f8812f.incrementAndGet();
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            ekt ekt = (ekt) message.obj;
            synchronized (this.f8815i) {
                if (this.f8811e && this.f8807a.mo5029e() && this.f8808b.contains(ekt)) {
                    ekt.mo4993a((Bundle) null);
                }
            }
            return true;
        }
        int i = message.what;
        StringBuilder sb = new StringBuilder(45);
        sb.append("Don't know how to handle message: ");
        sb.append(i);
        Log.wtf("GmsClientEvents", sb.toString(), new Exception());
        return false;
    }

    /* renamed from: a */
    public final void mo5140a(ekt ekt) {
        abj.m85a((Object) ekt);
        synchronized (this.f8815i) {
            if (this.f8808b.contains(ekt)) {
                String valueOf = String.valueOf(ekt);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 62);
                sb.append("registerConnectionCallbacks(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.f8808b.add(ekt);
            }
        }
        if (this.f8807a.mo5029e()) {
            Handler handler = this.f8814h;
            handler.sendMessage(handler.obtainMessage(1, ekt));
        }
    }

    /* renamed from: a */
    public final void mo5141a(eku eku) {
        abj.m85a((Object) eku);
        synchronized (this.f8815i) {
            if (this.f8810d.contains(eku)) {
                String valueOf = String.valueOf(eku);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 67);
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.f8810d.add(eku);
            }
        }
    }
}
