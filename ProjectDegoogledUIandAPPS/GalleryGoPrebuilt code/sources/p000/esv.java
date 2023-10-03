package p000;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Pair;
import java.util.List;

/* renamed from: esv */
/* compiled from: PG */
public class esv {

    /* renamed from: a */
    public static Boolean f8957a;

    /* renamed from: b */
    private static Boolean f8958b;

    /* renamed from: c */
    private static Boolean f8959c;

    /* renamed from: a */
    public static int m8121a(int i) {
        if (i != -1) {
            return i / 1000;
        }
        return -1;
    }

    /* renamed from: a */
    public static boolean m8127a(Context context) {
        if (f8958b == null) {
            int i = Build.VERSION.SDK_INT;
            f8958b = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return f8958b.booleanValue();
    }

    /* renamed from: a */
    public List mo5221a() {
        throw null;
    }

    /* renamed from: b */
    public List mo5222b() {
        throw null;
    }

    /* renamed from: b */
    public static void m8128b(Context context) {
        if (m8127a(context)) {
            int i = Build.VERSION.SDK_INT;
            if (f8959c == null) {
                int i2 = Build.VERSION.SDK_INT;
                f8959c = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
            }
            if (f8959c.booleanValue()) {
                int i3 = Build.VERSION.SDK_INT;
            }
        }
    }

    /* renamed from: a */
    public static void m8124a(Bundle bundle) {
        if (((Boolean) esx.f8965d.mo5369a()).booleanValue() && bundle != null) {
            Parcel obtain = Parcel.obtain();
            obtain.writeBundle(bundle);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize > ((Integer) esx.f8964c.mo5369a()).intValue()) {
                String valueOf = String.valueOf(esx.f8964c.mo5369a());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 86);
                sb.append("Max allowed bundle size of ");
                sb.append(valueOf);
                sb.append(" exceeded, you are passing in a bundle of ");
                sb.append(dataSize);
                sb.append(" size.");
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    /* renamed from: a */
    public static void m8125a(esi esi) {
        if (((Boolean) esx.f8965d.mo5369a()).booleanValue() && esi != null) {
            Parcel obtain = Parcel.obtain();
            esj.m8099a(esi, obtain, 0);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize > ((Integer) esx.f8964c.mo5369a()).intValue()) {
                String valueOf = String.valueOf(esx.f8964c.mo5369a());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 104);
                sb.append("Max allowed feedback options size of ");
                sb.append(valueOf);
                sb.append(" exceeded, you are passing in feedback options of ");
                sb.append(dataSize);
                sb.append(" size.");
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    /* renamed from: a */
    public static Bundle m8122a(List list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        Bundle bundle = new Bundle(size);
        for (int i = 0; i < size; i++) {
            Pair pair = (Pair) list.get(i);
            bundle.putString((String) pair.first, (String) pair.second);
        }
        return bundle;
    }

    /* renamed from: a */
    public static void m8123a(Context context, esv esv, long j) {
        m8126a((Runnable) new esr(context, esv, j, (byte[]) null));
        m8126a((Runnable) new ess(context, esv, j, (byte[]) null));
    }

    /* renamed from: a */
    private static void m8126a(Runnable runnable) {
        Thread thread = new Thread(runnable, "Feedback");
        thread.setPriority(4);
        thread.start();
    }
}
