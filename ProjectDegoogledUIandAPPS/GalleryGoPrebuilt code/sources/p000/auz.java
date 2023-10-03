package p000;

import android.graphics.Bitmap;
import android.os.Build;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* renamed from: auz */
/* compiled from: PG */
public final class auz implements auu {

    /* renamed from: a */
    public static final Bitmap.Config[] f1743a;

    /* renamed from: b */
    public static final Bitmap.Config[] f1744b;

    /* renamed from: c */
    public static final Bitmap.Config[] f1745c = {Bitmap.Config.RGB_565};

    /* renamed from: d */
    public static final Bitmap.Config[] f1746d = {Bitmap.Config.ARGB_4444};

    /* renamed from: e */
    public static final Bitmap.Config[] f1747e = {Bitmap.Config.ALPHA_8};

    /* renamed from: f */
    public final auy f1748f = new auy();

    /* renamed from: g */
    public final auo f1749g = new auo();

    /* renamed from: h */
    private final Map f1750h = new HashMap();

    static {
        Bitmap.Config[] configArr = {Bitmap.Config.ARGB_8888, null};
        int i = Build.VERSION.SDK_INT;
        Bitmap.Config[] configArr2 = (Bitmap.Config[]) Arrays.copyOf(configArr, 3);
        configArr2[configArr2.length - 1] = Bitmap.Config.RGBA_F16;
        f1743a = configArr2;
        f1744b = configArr2;
    }

    /* renamed from: a */
    public final void mo1663a(Integer num, Bitmap bitmap) {
        NavigableMap a = mo1662a(bitmap.getConfig());
        Integer num2 = (Integer) a.get(num);
        if (num2 == null) {
            String valueOf = String.valueOf(num);
            String a2 = m1748a(bfp.m2426a(bitmap), bitmap.getConfig());
            String valueOf2 = String.valueOf(this);
            int length = String.valueOf(valueOf).length();
            StringBuilder sb = new StringBuilder(length + 56 + String.valueOf(a2).length() + String.valueOf(valueOf2).length());
            sb.append("Tried to decrement empty size, size: ");
            sb.append(valueOf);
            sb.append(", removed: ");
            sb.append(a2);
            sb.append(", this: ");
            sb.append(valueOf2);
            throw new NullPointerException(sb.toString());
        } else if (num2.intValue() == 1) {
            a.remove(num);
        } else {
            a.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    /* renamed from: a */
    static String m1748a(int i, Bitmap.Config config) {
        String valueOf = String.valueOf(config);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 15);
        sb.append("[");
        sb.append(i);
        sb.append("](");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    public final NavigableMap mo1662a(Bitmap.Config config) {
        NavigableMap navigableMap = (NavigableMap) this.f1750h.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f1750h.put(config, treeMap);
        return treeMap;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.f1749g);
        sb.append(", sortedSizes=(");
        for (Map.Entry entry : this.f1750h.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!this.f1750h.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }
}
