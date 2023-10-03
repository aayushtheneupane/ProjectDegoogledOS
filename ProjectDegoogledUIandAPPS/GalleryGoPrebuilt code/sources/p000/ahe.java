package p000;

import java.util.HashMap;
import java.util.Map;

/* renamed from: ahe */
/* compiled from: PG */
public final class ahe {

    /* renamed from: a */
    private final Map f486a = new HashMap();

    /* renamed from: a */
    public final ahf mo468a() {
        ahf ahf = new ahf(this.f486a);
        ahf.m483a(ahf);
        return ahf;
    }

    /* renamed from: a */
    public final void mo469a(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                this.f486a.put(str, (Object) null);
            } else {
                Class<?> cls = value.getClass();
                if (cls == Boolean.class || cls == Byte.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Byte[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                    this.f486a.put(str, value);
                } else if (cls == boolean[].class) {
                    this.f486a.put(str, ahf.m484a((boolean[]) value));
                } else if (cls == byte[].class) {
                    this.f486a.put(str, ahf.m489b((byte[]) value));
                } else if (cls == int[].class) {
                    this.f486a.put(str, ahf.m487a((int[]) value));
                } else if (cls == long[].class) {
                    this.f486a.put(str, ahf.m488a((long[]) value));
                } else if (cls == float[].class) {
                    this.f486a.put(str, ahf.m486a((float[]) value));
                } else if (cls == double[].class) {
                    this.f486a.put(str, ahf.m485a((double[]) value));
                } else {
                    throw new IllegalArgumentException(String.format("Key %s has invalid type %s", new Object[]{str, cls}));
                }
            }
        }
    }
}
