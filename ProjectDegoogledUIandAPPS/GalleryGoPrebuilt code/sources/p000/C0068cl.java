package p000;

import java.security.PrivilegedAction;

/* renamed from: cl */
/* compiled from: PG */
final class C0068cl implements PrivilegedAction {

    /* renamed from: a */
    private final /* synthetic */ String f4593a;

    public C0068cl(String str) {
        this.f4593a = str;
    }

    public final /* bridge */ /* synthetic */ Object run() {
        return System.getProperty(this.f4593a);
    }
}
