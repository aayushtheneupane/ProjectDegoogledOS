package p000;

import p003j$.util.Optional;

/* renamed from: cct */
/* compiled from: PG */
final /* synthetic */ class cct implements hpr {

    /* renamed from: a */
    public static final hpr f4072a = new cct();

    private cct() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Optional optional = (Optional) obj;
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("Unable to fetch the video");
        } else if (!dgt.m6098c(((cxi) optional.get()).f5914f)) {
            String valueOf = String.valueOf(((cxi) optional.get()).f5914f);
            throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Unable to trim non-video type ") : "Unable to trim non-video type ".concat(valueOf));
        } else if ((((cxi) optional.get()).f5909a & 32) != 0) {
            return (cxi) optional.get();
        } else {
            throw new IllegalArgumentException("Unable to fetch the duration of the video");
        }
    }
}
