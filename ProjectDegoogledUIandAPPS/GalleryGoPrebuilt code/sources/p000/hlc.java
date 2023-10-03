package p000;

import java.util.UUID;

/* renamed from: hlc */
/* compiled from: PG */
final class hlc extends hku {

    /* renamed from: a */
    public static final UUID f12959a = UUID.randomUUID();

    /* renamed from: a */
    public final void mo7544a(int i) {
    }

    /* renamed from: a */
    public final void mo7545a(boolean z) {
    }

    /* renamed from: e */
    public final boolean mo7546e() {
        return false;
    }

    private hlc(hlc hlc, String str, hln hln) {
        super(str, (hlp) hlc, hln);
    }

    public hlc(String str, UUID uuid, hln hln) {
        super(str, uuid, hln);
    }

    /* renamed from: a */
    public final hlp mo7543a(String str, hln hln) {
        return new hlc(this, str, hln);
    }
}
