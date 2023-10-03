package p000;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: aps */
/* compiled from: PG */
public final class aps {

    /* renamed from: a */
    public final String f1372a;

    /* renamed from: b */
    public final long[] f1373b;

    /* renamed from: c */
    public File[] f1374c;

    /* renamed from: d */
    public boolean f1375d;

    /* renamed from: e */
    public apr f1376e;

    /* renamed from: f */
    public final /* synthetic */ apu f1377f;

    /* renamed from: g */
    private File[] f1378g;

    public /* synthetic */ aps(apu apu, String str) {
        this.f1377f = apu;
        this.f1372a = str;
        int i = apu.f1383d;
        this.f1373b = new long[i];
        this.f1374c = new File[i];
        this.f1378g = new File[i];
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        for (int i2 = 0; i2 < apu.f1383d; i2 = 1) {
            sb.append(0);
            this.f1374c[0] = new File(apu.f1380a, sb.toString());
            sb.append(".tmp");
            this.f1378g[0] = new File(apu.f1380a, sb.toString());
            sb.setLength(length);
        }
    }

    /* renamed from: b */
    public final File mo1459b() {
        return this.f1374c[0];
    }

    /* renamed from: c */
    public final File mo1460c() {
        return this.f1378g[0];
    }

    /* renamed from: a */
    public final String mo1458a() {
        StringBuilder sb = new StringBuilder();
        for (long append : this.f1373b) {
            sb.append(' ');
            sb.append(append);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static final IOException m1411a(String[] strArr) {
        String valueOf = String.valueOf(Arrays.toString(strArr));
        throw new IOException(valueOf.length() == 0 ? new String("unexpected journal line: ") : "unexpected journal line: ".concat(valueOf));
    }
}
