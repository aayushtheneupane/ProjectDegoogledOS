package p000;

import java.lang.reflect.InvocationTargetException;

/* renamed from: hxe */
/* compiled from: PG */
final class hxe {

    /* renamed from: a */
    public static final hxg f13572a = m12375a(hxg.f13576d);

    /* renamed from: a */
    private static hxg m12375a(String[] strArr) {
        hxg hxg;
        StringBuilder sb = new StringBuilder();
        try {
            hxg = ife.m12894d();
        } catch (NoClassDefFoundError e) {
            hxg = null;
        }
        if (hxg != null) {
            return hxg;
        }
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            try {
                return (hxg) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                th = th;
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append(10);
                sb.append(str);
                sb.append(": ");
                sb.append(th);
                i++;
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
