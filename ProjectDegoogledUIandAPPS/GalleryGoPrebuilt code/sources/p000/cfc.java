package p000;

import android.graphics.RectF;
import java.util.Arrays;
import p003j$.util.Optional;

/* renamed from: cfc */
/* compiled from: PG */
final class cfc extends cff {

    /* renamed from: a */
    public final Optional f4227a;

    /* renamed from: b */
    public final RectF f4228b;

    /* renamed from: c */
    public final long f4229c;

    /* renamed from: d */
    public final int f4230d;

    /* renamed from: e */
    public final int f4231e;

    /* renamed from: f */
    public final byte[] f4232f;

    /* renamed from: g */
    public final byte[] f4233g;

    /* renamed from: h */
    public final Optional f4234h;

    /* renamed from: i */
    public final Optional f4235i;

    /* renamed from: j */
    public final boolean f4236j;

    /* renamed from: k */
    public final boolean f4237k;

    /* renamed from: l */
    public final boolean f4238l;

    public /* synthetic */ cfc(Optional optional, RectF rectF, long j, int i, int i2, byte[] bArr, byte[] bArr2, Optional optional2, Optional optional3, boolean z, boolean z2, boolean z3) {
        this.f4227a = optional;
        this.f4228b = rectF;
        this.f4229c = j;
        this.f4230d = i;
        this.f4231e = i2;
        this.f4232f = bArr;
        this.f4233g = bArr2;
        this.f4234h = optional2;
        this.f4235i = optional3;
        this.f4236j = z;
        this.f4237k = z2;
        this.f4238l = z3;
    }

    /* renamed from: a */
    public final Optional mo3091a() {
        return this.f4227a;
    }

    /* renamed from: b */
    public final RectF mo3092b() {
        return this.f4228b;
    }

    /* renamed from: c */
    public final long mo3093c() {
        return this.f4229c;
    }

    /* renamed from: d */
    public final int mo3094d() {
        return this.f4230d;
    }

    /* renamed from: e */
    public final int mo3095e() {
        return this.f4231e;
    }

    /* renamed from: f */
    public final byte[] mo3097f() {
        return this.f4232f;
    }

    /* renamed from: g */
    public final byte[] mo3098g() {
        return this.f4233g;
    }

    /* renamed from: h */
    public final Optional mo3099h() {
        return this.f4234h;
    }

    /* renamed from: i */
    public final Optional mo3101i() {
        return this.f4235i;
    }

    /* renamed from: j */
    public final boolean mo3102j() {
        return this.f4236j;
    }

    /* renamed from: k */
    public final boolean mo3103k() {
        return this.f4237k;
    }

    /* renamed from: l */
    public final boolean mo3104l() {
        return this.f4238l;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cff) {
            cff cff = (cff) obj;
            if (this.f4227a.equals(cff.mo3091a()) && this.f4228b.equals(cff.mo3092b()) && this.f4229c == cff.mo3093c() && this.f4230d == cff.mo3094d() && this.f4231e == cff.mo3095e()) {
                boolean z = cff instanceof cfc;
                if (Arrays.equals(this.f4232f, z ? ((cfc) cff).f4232f : cff.mo3097f())) {
                    return Arrays.equals(this.f4233g, z ? ((cfc) cff).f4233g : cff.mo3098g()) && this.f4234h.equals(cff.mo3099h()) && this.f4235i.equals(cff.mo3101i()) && this.f4236j == cff.mo3102j() && this.f4237k == cff.mo3103k() && this.f4238l == cff.mo3104l();
                }
            }
        }
    }

    public final int hashCode() {
        int hashCode = this.f4227a.hashCode();
        int hashCode2 = this.f4228b.hashCode();
        long j = this.f4229c;
        int hashCode3 = (((((((((((((((((hashCode ^ 1000003) * 1000003) ^ hashCode2) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.f4230d) * 1000003) ^ this.f4231e) * 1000003) ^ Arrays.hashCode(this.f4232f)) * 1000003) ^ Arrays.hashCode(this.f4233g)) * 1000003) ^ this.f4234h.hashCode()) * 1000003) ^ this.f4235i.hashCode()) * 1000003;
        int i = 1237;
        int i2 = (((hashCode3 ^ (!this.f4236j ? 1237 : 1231)) * 1000003) ^ (!this.f4237k ? 1237 : 1231)) * 1000003;
        if (this.f4238l) {
            i = 1231;
        }
        return i2 ^ i;
    }

    /* renamed from: m */
    public final cfe mo3105m() {
        return new cfe((cff) this);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f4227a);
        String valueOf2 = String.valueOf(this.f4228b);
        long j = this.f4229c;
        int i = this.f4230d;
        int i2 = this.f4231e;
        String arrays = Arrays.toString(this.f4232f);
        String arrays2 = Arrays.toString(this.f4233g);
        String valueOf3 = String.valueOf(this.f4234h);
        String valueOf4 = String.valueOf(this.f4235i);
        boolean z = this.f4236j;
        boolean z2 = this.f4237k;
        boolean z3 = this.f4238l;
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(arrays).length();
        int length4 = String.valueOf(arrays2).length();
        StringBuilder sb = new StringBuilder(length + 241 + length2 + length3 + length4 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Face{id=");
        sb.append(valueOf);
        sb.append(", boundingBox=");
        sb.append(valueOf2);
        sb.append(", sourceMediaId=");
        sb.append(j);
        sb.append(", scaledSourceWidth=");
        sb.append(i);
        sb.append(", scaledSourceHeight=");
        sb.append(i2);
        sb.append(", metadata=");
        sb.append(arrays);
        sb.append(", template=");
        sb.append(arrays2);
        sb.append(", personClusterKey=");
        sb.append(valueOf3);
        sb.append(", importanceScore=");
        sb.append(valueOf4);
        sb.append(", clusteringRun=");
        sb.append(z);
        sb.append(", embeddingRun=");
        sb.append(z2);
        sb.append(", provisional=");
        sb.append(z3);
        sb.append("}");
        return sb.toString();
    }
}
