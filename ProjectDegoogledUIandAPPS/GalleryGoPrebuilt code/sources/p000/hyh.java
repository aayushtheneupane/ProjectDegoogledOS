package p000;

import java.util.logging.Logger;

/* renamed from: hyh */
/* compiled from: PG */
public final class hyh extends hyc {

    /* renamed from: a */
    public static final hyc f13616a = new hyh();

    public final String toString() {
        return "Default logger backend factory";
    }

    private hyh() {
    }

    /* renamed from: a */
    public final hxa mo8263a(String str) {
        return new hya(Logger.getLogger(str.replace('$', '.')));
    }
}
