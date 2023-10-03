package p000;

import java.util.Stack;

/* renamed from: hci */
/* compiled from: PG */
public final class hci {

    /* renamed from: a */
    public static final ThreadLocal f12472a = new hcg();

    /* renamed from: a */
    public static void m11204a(hch hch) {
        boolean z;
        if (((hch) ((Stack) f12472a.get()).pop()) == hch) {
            z = true;
        } else {
            z = false;
        }
        ife.m12896d(z);
    }
}
