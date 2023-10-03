package p000;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: eny */
/* compiled from: PG */
public final class eny extends Fragment implements enw {

    /* renamed from: a */
    public static final WeakHashMap f8695a = new WeakHashMap();

    /* renamed from: b */
    public int f8696b = 0;

    /* renamed from: c */
    public Bundle f8697c;

    /* renamed from: d */
    private final Map f8698d = new C0290kn();

    /* renamed from: a */
    public final void mo5069a(String str, LifecycleCallback lifecycleCallback) {
        if (!this.f8698d.containsKey(str)) {
            this.f8698d.put(str, lifecycleCallback);
            if (this.f8696b > 0) {
                new eui(Looper.getMainLooper()).post(new enx(this, lifecycleCallback, str));
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

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.f8698d.values()) {
        }
    }

    /* renamed from: a */
    public final LifecycleCallback mo5068a(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.f8698d.get(str));
    }

    /* renamed from: a */
    public final Activity mo5067a() {
        return getActivity();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback a : this.f8698d.values()) {
            a.mo3514a(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        this.f8696b = 1;
        this.f8697c = bundle;
        for (Map.Entry entry : this.f8698d.entrySet()) {
            LifecycleCallback lifecycleCallback = (LifecycleCallback) entry.getValue();
            if (bundle != null) {
                bundle2 = bundle.getBundle((String) entry.getKey());
            } else {
                bundle2 = null;
            }
            lifecycleCallback.mo3515a(bundle2);
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.f8696b = 5;
        for (LifecycleCallback lifecycleCallback : this.f8698d.values()) {
        }
    }

    public final void onResume() {
        super.onResume();
        this.f8696b = 3;
        for (LifecycleCallback d : this.f8698d.values()) {
            d.mo3518d();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry entry : this.f8698d.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).mo3516b(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        this.f8696b = 2;
        for (LifecycleCallback c : this.f8698d.values()) {
            c.mo3517c();
        }
    }

    public final void onStop() {
        super.onStop();
        this.f8696b = 4;
        for (LifecycleCallback e : this.f8698d.values()) {
            e.mo3519e();
        }
    }
}
