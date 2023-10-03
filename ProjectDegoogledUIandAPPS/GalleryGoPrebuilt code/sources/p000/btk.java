package p000;

import java.util.List;

/* renamed from: btk */
/* compiled from: PG */
public final class btk {

    /* renamed from: a */
    public int f3545a;

    /* renamed from: b */
    private cxe f3546b;

    /* renamed from: c */
    private List f3547c;

    /* renamed from: d */
    private Long f3548d;

    /* renamed from: e */
    private String f3549e;

    public btk(byte[] bArr) {
    }

    /* renamed from: a */
    public final btl mo2752a() {
        String str = this.f3546b == null ? " folder" : "";
        if (this.f3545a == 0) {
            str = str.concat(" storageType");
        }
        if (this.f3547c == null) {
            str = String.valueOf(str).concat(" mediaList");
        }
        if (this.f3548d == null) {
            str = String.valueOf(str).concat(" size");
        }
        if (this.f3549e == null) {
            str = String.valueOf(str).concat(" humanReadableSize");
        }
        if (str.isEmpty()) {
            return new bti(this.f3546b, this.f3545a, this.f3547c, this.f3548d.longValue(), this.f3549e);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo2754a(cxe cxe) {
        if (cxe != null) {
            this.f3546b = cxe;
            return;
        }
        throw new NullPointerException("Null folder");
    }

    /* renamed from: a */
    public final void mo2755a(String str) {
        if (str != null) {
            this.f3549e = str;
            return;
        }
        throw new NullPointerException("Null humanReadableSize");
    }

    /* renamed from: a */
    public final void mo2756a(List list) {
        if (list != null) {
            this.f3547c = list;
            return;
        }
        throw new NullPointerException("Null mediaList");
    }

    /* renamed from: a */
    public final void mo2753a(long j) {
        this.f3548d = Long.valueOf(j);
    }

    btk() {
    }
}
