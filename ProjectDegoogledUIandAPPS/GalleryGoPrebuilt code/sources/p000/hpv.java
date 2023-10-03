package p000;

import java.io.IOException;
import java.util.Iterator;

/* renamed from: hpv */
/* compiled from: PG */
public final class hpv {

    /* renamed from: a */
    private final String f13236a;

    private hpv(String str) {
        this.f13236a = (String) ife.m12898e((Object) str);
    }

    /* renamed from: a */
    public final StringBuilder mo7670a(StringBuilder sb, Iterator it) {
        try {
            ife.m12898e((Object) sb);
            if (it.hasNext()) {
                sb.append(m11888a(it.next()));
                while (it.hasNext()) {
                    sb.append(this.f13236a);
                    sb.append(m11888a(it.next()));
                }
            }
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public final String mo7669a(Iterable iterable) {
        return mo7670a(new StringBuilder(), iterable.iterator()).toString();
    }

    /* renamed from: a */
    public static hpv m11887a(String str) {
        return new hpv(str);
    }

    /* renamed from: a */
    private static final CharSequence m11888a(Object obj) {
        ife.m12898e(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
