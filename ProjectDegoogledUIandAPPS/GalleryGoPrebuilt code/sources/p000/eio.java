package p000;

/* renamed from: eio */
/* compiled from: PG */
public final class eio implements Comparable {

    /* renamed from: a */
    public int f8364a;

    /* renamed from: b */
    public int f8365b;

    private eio() {
    }

    public /* synthetic */ eio(byte[] bArr) {
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        eio eio = (eio) obj;
        int i = this.f8365b;
        int i2 = eio.f8365b;
        return i == i2 ? this.f8364a - eio.f8364a : i - i2;
    }

    public final String toString() {
        return "Order{order=" + this.f8365b + ", index=" + this.f8364a + '}';
    }
}
