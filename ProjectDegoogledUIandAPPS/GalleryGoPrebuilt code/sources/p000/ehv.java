package p000;

import android.app.WallpaperManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: ehv */
/* compiled from: PG */
final /* synthetic */ class ehv implements Callable {

    /* renamed from: a */
    private final ehy f8316a;

    /* renamed from: b */
    private final egw f8317b;

    public ehv(ehy ehy, egw egw) {
        this.f8316a = ehy;
        this.f8317b = egw;
    }

    public final Object call() {
        FileInputStream fileInputStream;
        ehy ehy = this.f8316a;
        egw egw = this.f8317b;
        int i = 0;
        try {
            fileInputStream = new FileInputStream(egw.mo4806f());
            WallpaperManager.getInstance(ehy.f8321b.mo2634k()).setStream(fileInputStream);
            fileInputStream.close();
            egw.mo4807g();
            i = -1;
        } catch (IOException e) {
            try {
                cwn.m5515b((Throwable) e, "CropAndSetWallpaperFragmentPeer: Unable to set wallpaper.", new Object[0]);
            } finally {
                egw.mo4807g();
            }
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        return Integer.valueOf(i);
        throw th;
    }
}
