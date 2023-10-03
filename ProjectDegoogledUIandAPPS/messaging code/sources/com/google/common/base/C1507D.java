package com.google.common.base;

/* renamed from: com.google.common.base.D */
public final class C1507D {

    /* renamed from: TM */
    private C1506C f2384TM = new C1506C((C1505B) null);

    /* renamed from: UM */
    private C1506C f2385UM = this.f2384TM;

    /* renamed from: VM */
    private boolean f2386VM = false;
    private final String className;

    /* synthetic */ C1507D(String str, C1505B b) {
        if (str != null) {
            this.className = str;
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: Ro */
    private C1506C m3954Ro() {
        C1506C c = new C1506C((C1505B) null);
        this.f2385UM.next = c;
        this.f2385UM = c;
        return c;
    }

    /* renamed from: b */
    private C1507D m3955b(String str, Object obj) {
        C1506C Ro = m3954Ro();
        Ro.value = obj;
        if (str != null) {
            Ro.name = str;
            return this;
        }
        throw new NullPointerException();
    }

    public C1507D add(String str, Object obj) {
        m3955b(str, obj);
        return this;
    }

    /* renamed from: h */
    public C1507D mo8519h(String str, int i) {
        m3955b(str, String.valueOf(i));
        return this;
    }

    public String toString() {
        boolean z = this.f2386VM;
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.className);
        sb.append('{');
        String str = "";
        for (C1506C c = this.f2384TM.next; c != null; c = c.next) {
            if (!z || c.value != null) {
                sb.append(str);
                String str2 = c.name;
                if (str2 != null) {
                    sb.append(str2);
                    sb.append('=');
                }
                sb.append(c.value);
                str = ", ";
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: w */
    public C1507D mo8521w(Object obj) {
        m3954Ro().value = obj;
        return this;
    }
}
