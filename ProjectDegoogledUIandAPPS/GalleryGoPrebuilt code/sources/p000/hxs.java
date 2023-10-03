package p000;

import dalvik.system.VMStack;

/* renamed from: hxs */
/* compiled from: PG */
class hxs extends hxf {
    /* renamed from: a */
    public hwg mo8244a(Class cls, int i) {
        return hwg.f13519a;
    }

    /* renamed from: a */
    public String mo8245a(Class cls) {
        StackTraceElement a;
        if (hxu.f13594e) {
            try {
                Class l = hxu.m12421l();
                if (cls.equals(l)) {
                    return VMStack.getStackClass2().getName();
                }
                String valueOf = String.valueOf(cls);
                String valueOf2 = String.valueOf(l);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43 + String.valueOf(valueOf2).length());
                sb.append("Unexpected stack depth, expected: ");
                sb.append(valueOf);
                sb.append(" but was ");
                sb.append(valueOf2);
                throw new IllegalStateException(sb.toString());
            } catch (Throwable th) {
            }
        }
        if (!hxu.f13595f || (a = hyv.m12483a(cls, new Throwable(), 1)) == null) {
            return null;
        }
        return a.getClassName();
    }
}
