package p000;

import java.io.File;

/* renamed from: ath */
/* compiled from: PG */
public final class ath implements asv {

    /* renamed from: a */
    private final ava f1611a;

    /* renamed from: b */
    private volatile avc f1612b;

    public ath(ava ava) {
        this.f1611a = ava;
    }

    /* renamed from: a */
    public final avc mo1566a() {
        if (this.f1612b == null) {
            synchronized (this) {
                if (this.f1612b == null) {
                    ava ava = this.f1611a;
                    avh avh = ((avi) ava).f1759b;
                    File cacheDir = ((avk) avh).f1765a.getCacheDir();
                    avj avj = null;
                    File file = cacheDir != null ? new File(cacheDir, ((avk) avh).f1766b) : null;
                    if (file != null) {
                        if (!file.mkdirs()) {
                            if (file.exists()) {
                                if (!file.isDirectory()) {
                                }
                            }
                        }
                        avj = new avj(file, ((avi) ava).f1758a);
                    }
                    this.f1612b = avj;
                }
                if (this.f1612b == null) {
                    this.f1612b = new avd();
                }
            }
        }
        return this.f1612b;
    }
}
