package p000;

/* renamed from: amg */
/* compiled from: PG */
public final class amg implements Runnable {

    /* renamed from: a */
    private final aip f762a;

    /* renamed from: b */
    private final String f763b;

    /* renamed from: c */
    private final ckx f764c;

    public amg(aip aip, String str, ckx ckx, byte[] bArr, byte[] bArr2) {
        this.f762a = aip;
        this.f763b = str;
        this.f764c = ckx;
    }

    public final void run() {
        this.f762a.f557f.mo507a(this.f763b, this.f764c);
    }
}
