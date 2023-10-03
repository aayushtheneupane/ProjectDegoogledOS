package p000;

import com.google.android.gms.common.api.Status;

@Deprecated
/* renamed from: ezf */
/* compiled from: PG */
public final class ezf implements eyr {

    /* renamed from: a */
    public static final ezh f9217a = new eze();

    /* renamed from: b */
    private final Status f9218b;

    /* renamed from: a */
    public final eyr mo5406a() {
        return this;
    }

    /* renamed from: c */
    public final boolean mo5409c() {
        return this.f9218b.f4977f == 14;
    }

    /* renamed from: d */
    public final int mo5410d() {
        return this.f9218b.f4977f;
    }

    /* renamed from: e */
    public final String mo5411e() {
        return this.f9218b.f4978g;
    }

    public ezf(Status status) {
        this.f9218b = status;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ezf) {
            return this.f9218b.equals(((ezf) obj).f9218b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f9218b.hashCode();
    }

    /* renamed from: b */
    public final boolean mo5408b() {
        return this.f9218b.mo3499b();
    }

    public final String toString() {
        return this.f9218b.toString();
    }
}
