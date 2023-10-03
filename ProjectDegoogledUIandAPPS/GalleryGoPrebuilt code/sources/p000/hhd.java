package p000;

/* renamed from: hhd */
/* compiled from: PG */
public final class hhd {

    /* renamed from: a */
    public hha f12727a;

    /* renamed from: b */
    public hgw f12728b;

    /* renamed from: c */
    private iqk f12729c;

    private hhd() {
    }

    public /* synthetic */ hhd(byte[] bArr) {
    }

    /* renamed from: a */
    public final hhe mo7439a() {
        boolean z = true;
        ife.m12876b(this.f12727a != null, (Object) "Every SyncletBinding must have a non-null SyncKey.");
        ife.m12876b(this.f12728b != null, (Object) "Every SyncletBinding must have a non-null SyncConfig.");
        if (this.f12729c == null) {
            z = false;
        }
        ife.m12876b(z, (Object) "Every SyncletBinding must have a non-null Synclet.");
        return new hgu(this.f12727a, this.f12728b, this.f12729c);
    }

    @Deprecated
    /* renamed from: a */
    public final void mo7440a(hhb hhb) {
        this.f12729c = new hhc(hhb);
    }
}
