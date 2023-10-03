package p000;

import java.lang.ref.WeakReference;

/* renamed from: eka */
/* compiled from: PG */
abstract class eka extends ejy {

    /* renamed from: b */
    private static final WeakReference f8463b = new WeakReference((Object) null);

    /* renamed from: a */
    private WeakReference f8464a = f8463b;

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract byte[] mo4926d();

    public eka(byte[] bArr) {
        super(bArr);
    }

    /* renamed from: a */
    public final byte[] mo4921a() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.f8464a.get();
            if (bArr == null) {
                bArr = mo4926d();
                this.f8464a = new WeakReference(bArr);
            }
        }
        return bArr;
    }
}
