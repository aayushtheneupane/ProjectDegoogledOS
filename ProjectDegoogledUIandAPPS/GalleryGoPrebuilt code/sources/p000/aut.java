package p000;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;

/* renamed from: aut */
/* compiled from: PG */
public final class aut implements auk {

    /* renamed from: a */
    private static final Bitmap.Config f1730a = Bitmap.Config.ARGB_8888;

    /* renamed from: b */
    private final auu f1731b;

    /* renamed from: c */
    private final Set f1732c;

    /* renamed from: d */
    private final long f1733d;

    /* renamed from: e */
    private long f1734e;

    /* renamed from: f */
    private int f1735f;

    /* renamed from: g */
    private int f1736g;

    /* renamed from: h */
    private int f1737h;

    /* renamed from: i */
    private int f1738i;

    public aut(long j) {
        int i = Build.VERSION.SDK_INT;
        auz auz = new auz();
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        int i2 = Build.VERSION.SDK_INT;
        hashSet.add((Object) null);
        int i3 = Build.VERSION.SDK_INT;
        hashSet.remove(Bitmap.Config.HARDWARE);
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        this.f1733d = j;
        this.f1731b = auz;
        this.f1732c = unmodifiableSet;
    }

    /* renamed from: a */
    public final void mo1643a() {
        m1736a(0);
    }

    /* renamed from: c */
    private static Bitmap m1737c(int i, int i2, Bitmap.Config config) {
        if (config == null) {
            config = f1730a;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    /* renamed from: a */
    public final Bitmap mo1642a(int i, int i2, Bitmap.Config config) {
        Bitmap d = m1738d(i, i2, config);
        if (d == null) {
            return m1737c(i, i2, config);
        }
        d.eraseColor(0);
        return d;
    }

    /* renamed from: b */
    public final Bitmap mo1646b(int i, int i2, Bitmap.Config config) {
        Bitmap d = m1738d(i, i2, config);
        return d == null ? m1737c(i, i2, config) : d;
    }

    /* renamed from: d */
    private final synchronized Bitmap m1738d(int i, int i2, Bitmap.Config config) {
        Bitmap.Config[] configArr;
        Bitmap bitmap;
        int i3 = Build.VERSION.SDK_INT;
        if (config != Bitmap.Config.HARDWARE) {
            auu auu = this.f1731b;
            if (config == null) {
                config = f1730a;
            }
            int a = bfp.m2425a(i, i2, config);
            aux a2 = ((auz) auu).f1748f.mo1661a(a, config);
            int i4 = Build.VERSION.SDK_INT;
            int i5 = 0;
            if (!Bitmap.Config.RGBA_F16.equals(config)) {
                int i6 = auw.f1739a[config.ordinal()];
                if (i6 == 1) {
                    configArr = auz.f1743a;
                } else if (i6 == 2) {
                    configArr = auz.f1745c;
                } else if (i6 != 3) {
                    configArr = i6 != 4 ? new Bitmap.Config[]{config} : auz.f1747e;
                } else {
                    configArr = auz.f1746d;
                }
            } else {
                configArr = auz.f1744b;
            }
            int length = configArr.length;
            while (true) {
                if (i5 >= length) {
                    break;
                }
                Bitmap.Config config2 = configArr[i5];
                Integer num = (Integer) ((auz) auu).mo1662a(config2).ceilingKey(Integer.valueOf(a));
                if (num != null) {
                    if (num.intValue() <= (a << 3)) {
                        if (num.intValue() == a) {
                            if (config2 != null) {
                                if (config2.equals(config)) {
                                }
                            } else if (config == null) {
                            }
                        }
                        ((auz) auu).f1748f.mo1640a(a2);
                        a2 = ((auz) auu).f1748f.mo1661a(num.intValue(), config2);
                    }
                }
                i5++;
            }
            bitmap = (Bitmap) ((auz) auu).f1749g.mo1650a((auv) a2);
            if (bitmap != null) {
                ((auz) auu).mo1663a(Integer.valueOf(a2.f1740a), bitmap);
                bitmap.reconfigure(i, i2, config);
            }
            if (bitmap != null) {
                this.f1735f++;
                this.f1734e -= (long) bfp.m2426a(bitmap);
                bitmap.setHasAlpha(true);
                int i7 = Build.VERSION.SDK_INT;
                bitmap.setPremultiplied(true);
            } else {
                this.f1736g++;
            }
        } else {
            String valueOf = String.valueOf(config);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 176);
            sb.append("Cannot create a mutable Bitmap with config: ");
            sb.append(valueOf);
            sb.append(". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
            throw new IllegalArgumentException(sb.toString());
        }
        return bitmap;
    }

    /* renamed from: a */
    public final synchronized void mo1645a(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    if (bitmap.isMutable() && ((long) bfp.m2426a(bitmap)) <= this.f1733d) {
                        if (this.f1732c.contains(bitmap.getConfig())) {
                            int a = bfp.m2426a(bitmap);
                            auu auu = this.f1731b;
                            aux a2 = ((auz) auu).f1748f.mo1661a(bfp.m2426a(bitmap), bitmap.getConfig());
                            ((auz) auu).f1749g.mo1651a(a2, bitmap);
                            NavigableMap a3 = ((auz) auu).mo1662a(bitmap.getConfig());
                            Integer num = (Integer) a3.get(Integer.valueOf(a2.f1740a));
                            a3.put(Integer.valueOf(a2.f1740a), Integer.valueOf(num != null ? num.intValue() + 1 : 1));
                            this.f1737h++;
                            this.f1734e += (long) a;
                            m1736a(this.f1733d);
                            return;
                        }
                    }
                    bitmap.recycle();
                    return;
                }
                throw new IllegalStateException("Cannot pool recycled bitmap");
            } finally {
            }
        } else {
            throw new NullPointerException("Bitmap must not be null");
        }
    }

    /* renamed from: a */
    public final void mo1644a(int i) {
        if (i < 40) {
            int i2 = Build.VERSION.SDK_INT;
            if (i < 20) {
                if (i == 15) {
                    m1736a(this.f1733d >> 1);
                    return;
                }
                return;
            }
        }
        mo1643a();
    }

    /* renamed from: a */
    private final synchronized void m1736a(long j) {
        while (true) {
            if (this.f1734e <= j) {
                break;
            }
            auu auu = this.f1731b;
            Bitmap bitmap = (Bitmap) ((auz) auu).f1749g.mo1649a();
            if (bitmap != null) {
                ((auz) auu).mo1663a(Integer.valueOf(bfp.m2426a(bitmap)), bitmap);
            }
            if (bitmap != null) {
                this.f1734e -= (long) bfp.m2426a(bitmap);
                this.f1738i++;
                bitmap.recycle();
            } else {
                if (Log.isLoggable("LruBitmapPool", 5)) {
                    Log.w("LruBitmapPool", "Size mismatch, resetting");
                    int i = this.f1735f;
                    int i2 = this.f1736g;
                    int i3 = this.f1737h;
                    int i4 = this.f1738i;
                    long j2 = this.f1734e;
                    long j3 = this.f1733d;
                    String valueOf = String.valueOf(this.f1731b);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 151);
                    sb.append("Hits=");
                    sb.append(i);
                    sb.append(", misses=");
                    sb.append(i2);
                    sb.append(", puts=");
                    sb.append(i3);
                    sb.append(", evictions=");
                    sb.append(i4);
                    sb.append(", currentSize=");
                    sb.append(j2);
                    sb.append(", maxSize=");
                    sb.append(j3);
                    sb.append("\nStrategy=");
                    sb.append(valueOf);
                    sb.toString();
                }
                this.f1734e = 0;
            }
        }
    }
}
