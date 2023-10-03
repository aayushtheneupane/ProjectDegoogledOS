package p000;

import java.security.PrivilegedAction;

/* renamed from: cn */
/* compiled from: PG */
final class C0070cn implements PrivilegedAction {

    /* renamed from: a */
    private final /* synthetic */ Class f4715a;

    /* renamed from: b */
    private final /* synthetic */ String f4716b;

    public C0070cn(Class cls, String str) {
        this.f4715a = cls;
        this.f4716b = str;
    }

    public final /* bridge */ /* synthetic */ Object run() {
        return this.f4715a.getResourceAsStream(this.f4716b);
    }
}
