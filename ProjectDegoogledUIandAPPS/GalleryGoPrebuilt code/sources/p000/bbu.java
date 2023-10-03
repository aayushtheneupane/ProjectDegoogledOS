package p000;

import android.util.Log;
import java.io.File;
import java.io.IOException;

/* renamed from: bbu */
/* compiled from: PG */
public final class bbu implements arc {
    /* renamed from: a */
    public final int mo1509a() {
        return 1;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1488a(Object obj, File file, aqz aqz) {
        try {
            bfd.m2406a(((bbt) ((aua) obj).mo1605b()).mo1784b(), file);
            return true;
        } catch (IOException e) {
            if (!Log.isLoggable("GifEncoder", 5)) {
                return false;
            }
            Log.w("GifEncoder", "Failed to encode GIF drawable data", e);
            return false;
        }
    }
}
