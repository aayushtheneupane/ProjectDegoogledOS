package p000;

import android.graphics.Bitmap;

/* renamed from: bbr */
/* compiled from: PG */
public final class bbr implements apy {

    /* renamed from: a */
    public final auk f2003a;

    /* renamed from: b */
    public final aui f2004b;

    public bbr(auk auk, aui aui) {
        this.f2003a = auk;
        this.f2004b = aui;
    }

    /* renamed from: a */
    public final byte[] mo1475a(int i) {
        aui aui = this.f2004b;
        if (aui != null) {
            return (byte[]) aui.mo1634a(i, byte[].class);
        }
        return new byte[i];
    }

    /* renamed from: a */
    public final void mo1473a(Bitmap bitmap) {
        this.f2003a.mo1645a(bitmap);
    }

    /* renamed from: a */
    public final void mo1474a(byte[] bArr) {
        aui aui = this.f2004b;
        if (aui != null) {
            aui.mo1638a((Object) bArr);
        }
    }
}
