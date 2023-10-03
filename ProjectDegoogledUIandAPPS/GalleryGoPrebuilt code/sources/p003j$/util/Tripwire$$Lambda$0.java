package p003j$.util;

import java.security.PrivilegedAction;

/* renamed from: j$.util.Tripwire$$Lambda$0 */
final /* synthetic */ class Tripwire$$Lambda$0 implements PrivilegedAction {
    static final PrivilegedAction $instance = new Tripwire$$Lambda$0();

    private Tripwire$$Lambda$0() {
    }

    public Object run() {
        return Boolean.valueOf(Boolean.getBoolean("org.openjdk.java.util.stream.tripwire"));
    }
}
