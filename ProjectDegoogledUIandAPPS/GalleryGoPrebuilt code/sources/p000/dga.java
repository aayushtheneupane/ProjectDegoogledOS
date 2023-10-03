package p000;

import java.util.List;

/* renamed from: dga */
/* compiled from: PG */
final class dga {

    /* renamed from: a */
    private List f6488a;

    /* renamed from: b */
    private List f6489b;

    /* renamed from: c */
    private List f6490c;

    public dga(byte[] bArr) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final dgb mo4110a() {
        String str = this.f6488a == null ? " newMedia" : "";
        if (this.f6489b == null) {
            str = str.concat(" updatedMedia");
        }
        if (this.f6490c == null) {
            str = String.valueOf(str).concat(" deletedMedia");
        }
        if (str.isEmpty()) {
            return new del(this.f6488a, this.f6489b, this.f6490c);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4111a(List list) {
        if (list != null) {
            this.f6490c = list;
            return;
        }
        throw new NullPointerException("Null deletedMedia");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo4112b(List list) {
        if (list != null) {
            this.f6488a = list;
            return;
        }
        throw new NullPointerException("Null newMedia");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo4113c(List list) {
        if (list != null) {
            this.f6489b = list;
            return;
        }
        throw new NullPointerException("Null updatedMedia");
    }

    dga() {
    }
}
