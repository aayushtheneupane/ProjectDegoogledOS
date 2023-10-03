package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;

/* renamed from: img */
/* compiled from: PG */
public final class img implements imh {
    public static final Parcelable.Creator CREATOR = new imf();

    /* renamed from: a */
    private volatile byte[] f14510a;

    /* renamed from: b */
    private volatile ikf f14511b;

    public final int describeContents() {
        return 0;
    }

    public /* synthetic */ img(byte[] bArr, ikf ikf) {
        boolean z = true;
        if (bArr == null && ikf == null) {
            z = false;
        }
        ife.m12845a(z, (Object) "Must have a message or bytes");
        this.f14510a = bArr;
        this.f14511b = ikf;
    }

    /* renamed from: a */
    public final ikf mo8995a(ikf ikf, iij iij) {
        try {
            return mo8996b(ikf, iij);
        } catch (ijh e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final ikf mo8996b(ikf ikf, iij iij) {
        if (this.f14511b == null) {
            this.f14511b = ikf.mo8796m().mo8507a(this.f14510a, iij).mo8770g();
        }
        return this.f14511b;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        if (this.f14510a == null) {
            byte[] bArr = new byte[this.f14511b.mo8795i()];
            try {
                this.f14511b.mo8789a(iie.m13406a(bArr));
                this.f14510a = bArr;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
        parcel.writeInt(this.f14510a.length);
        parcel.writeByteArray(this.f14510a);
    }
}
