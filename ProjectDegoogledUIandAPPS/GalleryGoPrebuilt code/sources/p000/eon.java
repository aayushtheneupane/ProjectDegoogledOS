package p000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: eon */
/* compiled from: PG */
public final class eon extends C0147fh implements enw {

    /* renamed from: a */
    public static final WeakHashMap f8717a = new WeakHashMap();

    /* renamed from: b */
    public int f8718b = 0;

    /* renamed from: c */
    public Bundle f8719c;

    /* renamed from: d */
    private final Map f8720d = new C0290kn();

    /* renamed from: a */
    public final void mo5069a(String str, LifecycleCallback lifecycleCallback) {
        if (!this.f8720d.containsKey(str)) {
            this.f8720d.put(str, lifecycleCallback);
            if (this.f8718b > 0) {
                new eui(Looper.getMainLooper()).post(new eom(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder(str.length() + 59);
        sb.append("LifecycleCallback with tag ");
        sb.append(str);
        sb.append(" already added to this fragment.");
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public final void mo5088a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.mo5088a(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.f8720d.values()) {
        }
    }

    /* renamed from: a */
    public final LifecycleCallback mo5068a(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.f8720d.get(str));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Activity mo5067a() {
        return mo5653m();
    }

    /* renamed from: a */
    public final void mo2665a(int i, int i2, Intent intent) {
        for (LifecycleCallback a : this.f8720d.values()) {
            a.mo3514a(i, i2, intent);
        }
    }

    /* renamed from: a */
    public final void mo165a(Bundle bundle) {
        Bundle bundle2;
        super.mo165a(bundle);
        this.f8718b = 1;
        this.f8719c = bundle;
        for (Map.Entry entry : this.f8720d.entrySet()) {
            LifecycleCallback lifecycleCallback = (LifecycleCallback) entry.getValue();
            if (bundle != null) {
                bundle2 = bundle.getBundle((String) entry.getKey());
            } else {
                bundle2 = null;
            }
            lifecycleCallback.mo3515a(bundle2);
        }
    }

    /* renamed from: x */
    public final void mo1836x() {
        super.mo1836x();
        this.f8718b = 5;
        for (LifecycleCallback lifecycleCallback : this.f8720d.values()) {
        }
    }

    /* renamed from: v */
    public final void mo2668v() {
        super.mo2668v();
        this.f8718b = 3;
        for (LifecycleCallback d : this.f8720d.values()) {
            d.mo3518d();
        }
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        for (Map.Entry entry : this.f8720d.entrySet()) {
            Bundle bundle2 = new Bundle();
            ((LifecycleCallback) entry.getValue()).mo3516b(bundle2);
            bundle.putBundle((String) entry.getKey(), bundle2);
        }
    }

    /* renamed from: d */
    public final void mo210d() {
        super.mo210d();
        this.f8718b = 2;
        for (LifecycleCallback c : this.f8720d.values()) {
            c.mo3517c();
        }
    }

    /* renamed from: e */
    public final void mo211e() {
        super.mo211e();
        this.f8718b = 4;
        for (LifecycleCallback e : this.f8720d.values()) {
            e.mo3519e();
        }
    }
}
