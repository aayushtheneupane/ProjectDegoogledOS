package p003j$.util;

import java.security.AccessController;

/* renamed from: j$.util.Tripwire */
abstract class Tripwire {
    static final boolean ENABLED = ((Boolean) AccessController.doPrivileged(Tripwire$$Lambda$0.$instance)).booleanValue();

    static void trip(Class cls, String str) {
        String valueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45 + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(" tripwire tripped but logging not supported: ");
        sb.append(str);
        throw new UnsupportedOperationException(sb.toString());
    }
}
