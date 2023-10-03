package p000;

import android.content.Context;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: eja */
/* compiled from: PG */
public final class eja {
    @Deprecated

    /* renamed from: a */
    public static final ekn f8395a = new ekn("ClearcutLogger.API", f8399o, f8398n, (byte[]) null, (byte[]) null);

    /* renamed from: b */
    public static volatile int f8396b = -1;

    /* renamed from: k */
    public static final List f8397k = new CopyOnWriteArrayList();

    /* renamed from: n */
    private static final eki f8398n = new eki((byte[]) null);

    /* renamed from: o */
    private static final eov f8399o = new eiv();

    /* renamed from: c */
    public final Context f8400c;

    /* renamed from: d */
    public final String f8401d;

    /* renamed from: e */
    public String f8402e;

    /* renamed from: f */
    public int f8403f;

    /* renamed from: g */
    public String f8404g;

    /* renamed from: h */
    public final boolean f8405h;

    /* renamed from: i */
    public final ejb f8406i;

    /* renamed from: j */
    public final eiy f8407j;

    /* renamed from: l */
    public final List f8408l;

    /* renamed from: m */
    public int f8409m;

    public eja(Context context, String str, String str2, boolean z, ejb ejb, eiy eiy) {
        this.f8403f = -1;
        boolean z2 = true;
        this.f8409m = 1;
        this.f8408l = new CopyOnWriteArrayList();
        this.f8400c = context.getApplicationContext();
        this.f8401d = context.getPackageName();
        this.f8403f = -1;
        this.f8402e = str;
        this.f8404g = str2;
        this.f8405h = z;
        this.f8406i = ejb;
        this.f8409m = 1;
        this.f8407j = eiy;
        if (z) {
            abj.m117b(str2 != null ? false : z2, (Object) "can't be anonymous with an upload account");
        }
    }

    public eja(Context context, String str) {
        this(context, str, (String) null, false, ejh.m7623a(context), new ejn(context));
    }

    @Deprecated
    public eja(Context context, String str, String str2) {
        this(context, str, str2, false, ejh.m7623a(context), new ejn(context));
    }

    /* renamed from: a */
    public final eix mo4867a(byte[] bArr) {
        return new eix(this, bArr);
    }
}
