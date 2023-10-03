package p000;

import android.graphics.Bitmap;

/* renamed from: cbc */
/* compiled from: PG */
public final class cbc {

    /* renamed from: a */
    private Bitmap f4003a;

    /* renamed from: b */
    private car f4004b;

    /* renamed from: c */
    private Boolean f4005c;

    public cbc(byte[] bArr) {
    }

    public /* synthetic */ cbc(cbd cbd) {
        caz caz = (caz) cbd;
        this.f4003a = caz.f3999a;
        this.f4004b = caz.f4000b;
        this.f4005c = Boolean.valueOf(caz.f4001c);
    }

    /* renamed from: a */
    public final void mo2983a(Bitmap bitmap) {
        if (bitmap != null) {
            this.f4003a = bitmap;
            return;
        }
        throw new NullPointerException("Null bitmap");
    }

    /* renamed from: a */
    public final cbd mo2982a() {
        String str = this.f4003a == null ? " bitmap" : "";
        if (this.f4004b == null) {
            str = str.concat(" preset");
        }
        if (this.f4005c == null) {
            str = String.valueOf(str).concat(" selected");
        }
        if (str.isEmpty()) {
            return new caz(this.f4003a, this.f4004b, this.f4005c.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo2984a(car car) {
        if (car != null) {
            this.f4004b = car;
            return;
        }
        throw new NullPointerException("Null preset");
    }

    /* renamed from: a */
    public final void mo2985a(boolean z) {
        this.f4005c = Boolean.valueOf(z);
    }

    cbc() {
    }
}
