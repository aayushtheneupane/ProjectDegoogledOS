package p000;

import android.media.MediaDataSource;
import java.io.IOException;

/* renamed from: abu */
/* compiled from: PG */
final class abu extends MediaDataSource {

    /* renamed from: a */
    private long f107a;

    /* renamed from: b */
    private final /* synthetic */ abv f108b;

    public abu(abv abv) {
        this.f108b = abv;
    }

    public final void close() {
    }

    public final long getSize() {
        return -1;
    }

    public final int readAt(long j, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (j < 0) {
            return -1;
        }
        try {
            long j2 = this.f107a;
            if (j2 != j) {
                if (j2 >= 0) {
                    if (j >= j2 + ((long) this.f108b.available())) {
                        return -1;
                    }
                }
                this.f108b.mo128a(j);
                this.f107a = j;
            }
            if (i2 > this.f108b.available()) {
                i2 = this.f108b.available();
            }
            int read = this.f108b.read(bArr, i, i2);
            if (read >= 0) {
                this.f107a += (long) read;
                return read;
            }
        } catch (IOException e) {
        }
        this.f107a = -1;
        return -1;
    }
}
