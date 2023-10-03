package p000;

import java.lang.reflect.Method;

/* renamed from: n */
/* compiled from: PG */
final class C0357n {

    /* renamed from: a */
    public final int f15261a;

    /* renamed from: b */
    public final Method f15262b;

    public C0357n(int i, Method method) {
        this.f15261a = i;
        this.f15262b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0357n) {
            C0357n nVar = (C0357n) obj;
            return this.f15261a == nVar.f15261a && this.f15262b.getName().equals(nVar.f15262b.getName());
        }
    }

    public final int hashCode() {
        return (this.f15261a * 31) + this.f15262b.getName().hashCode();
    }
}
