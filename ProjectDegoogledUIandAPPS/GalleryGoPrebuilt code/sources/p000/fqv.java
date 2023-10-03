package p000;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: fqv */
/* compiled from: PG */
public final class fqv extends bil implements fqw {
    public fqv(IBinder iBinder) {
        super(iBinder, "com.google.android.libraries.photos.backup.api.IPhotosBackup");
    }

    /* renamed from: b */
    public final fqu mo6039b() {
        Parcel a = mo2086a(1, mo2085a());
        fqu fqu = (fqu) bin.m2617a(a, fqu.CREATOR);
        a.recycle();
        return fqu;
    }
}
