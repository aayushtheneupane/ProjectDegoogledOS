package p000;

import android.provider.DocumentsContract;
import java.io.IOException;
import java.util.concurrent.Callable;
import p003j$.util.Optional;

/* renamed from: czs */
/* compiled from: PG */
final /* synthetic */ class czs implements Callable {

    /* renamed from: a */
    private final Optional f6114a;

    public czs(Optional optional) {
        this.f6114a = optional;
    }

    public final Object call() {
        Optional optional = this.f6114a;
        int i = czu.f6115a;
        if (optional.isPresent()) {
            abt abt = (abt) optional.get();
            try {
                if (DocumentsContract.deleteDocument(abt.f105a.getContentResolver(), abt.f106b)) {
                    return bip.f2457a;
                }
            } catch (Exception e) {
            }
            throw new IOException("Failed to delete media file");
        }
        throw new IOException("Failed to get media file");
    }
}
