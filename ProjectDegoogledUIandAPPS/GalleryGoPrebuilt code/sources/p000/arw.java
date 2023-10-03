package p000;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.IOException;

/* renamed from: arw */
/* compiled from: PG */
final class arw {

    /* renamed from: a */
    private final ParcelFileDescriptor f1502a;

    public arw(ParcelFileDescriptor parcelFileDescriptor) {
        this.f1502a = parcelFileDescriptor;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ParcelFileDescriptor mo1539a() {
        try {
            Os.lseek(this.f1502a.getFileDescriptor(), 0, OsConstants.SEEK_SET);
            return this.f1502a;
        } catch (ErrnoException e) {
            throw new IOException(e);
        }
    }
}
