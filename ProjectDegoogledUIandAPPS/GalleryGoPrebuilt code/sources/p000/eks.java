package p000;

import android.content.Context;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Deprecated
/* renamed from: eks */
/* compiled from: PG */
public final class eks {

    /* renamed from: a */
    public final Set f8491a = new HashSet();

    /* renamed from: b */
    public final Set f8492b = new HashSet();

    /* renamed from: c */
    public final String f8493c;

    /* renamed from: d */
    public final String f8494d;

    /* renamed from: e */
    public final Map f8495e = new C0290kn();

    /* renamed from: f */
    public final Context f8496f;

    /* renamed from: g */
    public final Map f8497g = new C0290kn();

    /* renamed from: h */
    public final Looper f8498h;

    /* renamed from: i */
    public final ejw f8499i = ejw.f8454a;

    /* renamed from: j */
    public final ArrayList f8500j = new ArrayList();

    /* renamed from: k */
    public final ArrayList f8501k = new ArrayList();

    /* renamed from: l */
    public final eov f8502l = ewb.f9124b;

    public eks(Context context) {
        this.f8496f = context;
        this.f8498h = context.getMainLooper();
        this.f8493c = context.getPackageName();
        this.f8494d = context.getClass().getName();
    }
}
