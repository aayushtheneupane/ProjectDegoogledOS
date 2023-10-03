package p000;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* renamed from: iho */
/* compiled from: PG */
final class iho extends iht {
    public static final long serialVersionUID = 1;

    /* renamed from: d */
    private final int f14192d;

    /* renamed from: e */
    private final int f14193e;

    public iho(byte[] bArr, int i, int i2) {
        super(bArr);
        m13167c(i, i + i2, bArr.length);
        this.f14192d = i;
        this.f14193e = i2;
    }

    /* renamed from: a */
    public final int mo8597a() {
        return this.f14193e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo8600b() {
        return this.f14192d;
    }

    /* renamed from: a */
    public final byte mo8596a(int i) {
        m13166b(i, this.f14193e);
        return this.f14196a[this.f14192d + i];
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8598a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.f14196a, this.f14192d + i, bArr, i2, i3);
    }

    /* renamed from: b */
    public final byte mo8599b(int i) {
        return this.f14196a[this.f14192d + i];
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return ihw.m13164b(mo8625j());
    }
}
