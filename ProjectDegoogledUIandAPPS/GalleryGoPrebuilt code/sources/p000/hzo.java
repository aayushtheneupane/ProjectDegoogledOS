package p000;

import java.io.Serializable;

/* renamed from: hzo */
/* compiled from: PG */
final class hzo implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final String f13685a;

    /* renamed from: b */
    private final int f13686b;

    /* renamed from: c */
    private final String f13687c;

    public /* synthetic */ hzo(String str, int i, String str2) {
        this.f13685a = str;
        this.f13686b = i;
        this.f13687c = str2;
    }

    private Object readResolve() {
        return new hzp(this.f13685a, this.f13686b, this.f13687c);
    }
}
