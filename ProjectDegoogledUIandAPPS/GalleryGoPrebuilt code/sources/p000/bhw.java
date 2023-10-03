package p000;

import android.os.Bundle;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: bhw */
/* compiled from: PG */
public final class bhw implements bhx {

    /* renamed from: a */
    public final String f2412a;

    /* renamed from: b */
    public final String f2413b;

    /* renamed from: c */
    public final boolean f2414c;

    /* renamed from: d */
    public final int f2415d;

    /* renamed from: e */
    public final int[] f2416e;

    /* renamed from: f */
    public final Bundle f2417f;

    /* renamed from: g */
    public final bih f2418g;

    /* renamed from: h */
    public final cns f2419h;

    /* renamed from: i */
    private final boolean f2420i;

    /* renamed from: j */
    private final cof f2421j;

    public /* synthetic */ bhw(bhv bhv) {
        this.f2412a = bhv.f2402a;
        this.f2413b = bhv.f2403b;
        this.f2419h = bhv.f2410i;
        this.f2418g = bhv.f2408g;
        this.f2414c = bhv.f2404c;
        this.f2415d = bhv.f2405d;
        this.f2416e = bhv.f2406e;
        this.f2417f = bhv.f2407f;
        this.f2420i = bhv.f2409h;
        this.f2421j = bhv.f2411j;
    }

    /* renamed from: b */
    public final String mo2047b() {
        return this.f2413b;
    }

    /* renamed from: c */
    public final String mo2048c() {
        return this.f2412a;
    }

    /* renamed from: d */
    public final int mo2049d() {
        return this.f2415d;
    }

    /* renamed from: e */
    public final boolean mo2050e() {
        return this.f2414c;
    }

    /* renamed from: f */
    public final int[] mo2051f() {
        return this.f2416e;
    }

    /* renamed from: g */
    public final Bundle mo2052g() {
        return this.f2417f;
    }

    /* renamed from: h */
    public final bih mo2053h() {
        return this.f2418g;
    }

    /* renamed from: i */
    public final boolean mo2054i() {
        return this.f2420i;
    }

    /* renamed from: k */
    public final cns mo2056k() {
        return this.f2419h;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        bhw bhw = (bhw) obj;
        return this.f2412a.equals(bhw.f2412a) && this.f2413b.equals(bhw.f2413b);
    }

    public final int hashCode() {
        return (this.f2412a.hashCode() * 31) + this.f2413b.hashCode();
    }

    public final String toString() {
        String quote = JSONObject.quote(this.f2412a);
        String str = this.f2413b;
        String valueOf = String.valueOf(this.f2419h);
        boolean z = this.f2414c;
        int i = this.f2415d;
        String arrays = Arrays.toString(this.f2416e);
        String valueOf2 = String.valueOf(this.f2417f);
        String valueOf3 = String.valueOf(this.f2418g);
        boolean z2 = this.f2420i;
        String valueOf4 = String.valueOf(this.f2421j);
        int length = String.valueOf(quote).length();
        int length2 = String.valueOf(str).length();
        int length3 = String.valueOf(valueOf).length();
        int length4 = String.valueOf(arrays).length();
        int length5 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 159 + length2 + length3 + length4 + length5 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("JobInvocation{tag='");
        sb.append(quote);
        sb.append("', service='");
        sb.append(str);
        sb.append("', trigger=");
        sb.append(valueOf);
        sb.append(", recurring=");
        sb.append(z);
        sb.append(", lifetime=");
        sb.append(i);
        sb.append(", constraints=");
        sb.append(arrays);
        sb.append(", extras=");
        sb.append(valueOf2);
        sb.append(", retryStrategy=");
        sb.append(valueOf3);
        sb.append(", replaceCurrent=");
        sb.append(z2);
        sb.append(", triggerReason=");
        sb.append(valueOf4);
        sb.append('}');
        return sb.toString();
    }
}
