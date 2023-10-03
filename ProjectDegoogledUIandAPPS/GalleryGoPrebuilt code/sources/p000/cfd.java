package p000;

import java.util.Arrays;
import p003j$.util.Optional;

/* renamed from: cfd */
/* compiled from: PG */
final class cfd extends cia {

    /* renamed from: a */
    public final Optional f4239a;

    /* renamed from: b */
    public final long f4240b;

    /* renamed from: c */
    public final String f4241c;

    /* renamed from: d */
    public final byte[] f4242d;

    /* renamed from: e */
    public final boolean f4243e;

    /* renamed from: f */
    public final boolean f4244f;

    public /* synthetic */ cfd(Optional optional, long j, String str, byte[] bArr, boolean z, boolean z2) {
        this.f4239a = optional;
        this.f4240b = j;
        this.f4241c = str;
        this.f4242d = bArr;
        this.f4243e = z;
        this.f4244f = z2;
    }

    /* renamed from: a */
    public final Optional mo3107a() {
        return this.f4239a;
    }

    /* renamed from: b */
    public final long mo3108b() {
        return this.f4240b;
    }

    /* renamed from: c */
    public final String mo3109c() {
        return this.f4241c;
    }

    /* renamed from: d */
    public final byte[] mo3110d() {
        return this.f4242d;
    }

    /* renamed from: e */
    public final boolean mo3111e() {
        return this.f4243e;
    }

    /* renamed from: f */
    public final boolean mo3113f() {
        return this.f4244f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cia) {
            cia cia = (cia) obj;
            if (this.f4239a.equals(cia.mo3107a()) && this.f4240b == cia.mo3108b() && this.f4241c.equals(cia.mo3109c())) {
                return Arrays.equals(this.f4242d, cia instanceof cfd ? ((cfd) cia).f4242d : cia.mo3110d()) && this.f4243e == cia.mo3111e() && this.f4244f == cia.mo3113f();
            }
        }
    }

    public final int hashCode() {
        int hashCode = this.f4239a.hashCode();
        long j = this.f4240b;
        int i = 1237;
        int hashCode2 = (((((((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.f4241c.hashCode()) * 1000003) ^ Arrays.hashCode(this.f4242d)) * 1000003) ^ (!this.f4243e ? 1237 : 1231)) * 1000003;
        if (this.f4244f) {
            i = 1231;
        }
        return hashCode2 ^ i;
    }

    /* renamed from: g */
    public final chz mo3114g() {
        return new chz((cia) this);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f4239a);
        long j = this.f4240b;
        String str = this.f4241c;
        String arrays = Arrays.toString(this.f4242d);
        boolean z = this.f4243e;
        boolean z2 = this.f4244f;
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 117 + String.valueOf(str).length() + String.valueOf(arrays).length());
        sb.append("Person{id=");
        sb.append(valueOf);
        sb.append(", thumbnailFaceId=");
        sb.append(j);
        sb.append(", personClusterKey=");
        sb.append(str);
        sb.append(", thumbnail=");
        sb.append(arrays);
        sb.append(", thumbnailingRun=");
        sb.append(z);
        sb.append(", hidden=");
        sb.append(z2);
        sb.append("}");
        return sb.toString();
    }
}
