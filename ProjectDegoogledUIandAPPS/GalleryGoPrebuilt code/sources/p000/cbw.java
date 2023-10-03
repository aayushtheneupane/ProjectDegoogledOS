package p000;

import p003j$.util.Optional;

/* renamed from: cbw */
/* compiled from: PG */
public final class cbw extends cce {

    /* renamed from: a */
    private final Optional f4029a;

    public cbw(Optional optional) {
        if (optional != null) {
            this.f4029a = optional;
            return;
        }
        throw new NullPointerException("Null savedMedia");
    }

    /* renamed from: a */
    public final Optional mo3011a() {
        return this.f4029a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cce) {
            return this.f4029a.equals(((cce) obj).mo3011a());
        }
        return false;
    }

    public final int hashCode() {
        return this.f4029a.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f4029a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("VideoTrimmingFinishedEvent{savedMedia=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
