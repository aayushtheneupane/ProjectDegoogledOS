package p000;

/* renamed from: fdb */
/* compiled from: PG */
public final class fdb {

    /* renamed from: a */
    private String f9296a;

    /* renamed from: b */
    private ikf f9297b;

    public fdb(byte[] bArr) {
    }

    /* renamed from: a */
    public final fdc mo5505a() {
        String str = this.f9296a == null ? " logSource" : "";
        if (this.f9297b == null) {
            str = str.concat(" message");
        }
        if (str.isEmpty()) {
            return new fda(this.f9296a, this.f9297b);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo5507a(String str) {
        if (str != null) {
            this.f9296a = str;
            return;
        }
        throw new NullPointerException("Null logSource");
    }

    /* renamed from: a */
    public final void mo5506a(ikf ikf) {
        if (ikf != null) {
            this.f9297b = ikf;
            return;
        }
        throw new NullPointerException("Null message");
    }

    fdb() {
    }
}
