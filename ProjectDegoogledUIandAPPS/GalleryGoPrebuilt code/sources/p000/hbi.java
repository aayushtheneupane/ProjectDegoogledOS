package p000;

import java.io.RandomAccessFile;
import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: hbi */
/* compiled from: PG */
final /* synthetic */ class hbi implements Callable {

    /* renamed from: a */
    private final hbk f12446a;

    /* renamed from: b */
    private final RandomAccessFile f12447b;

    /* renamed from: c */
    private final int f12448c;

    public hbi(hbk hbk, RandomAccessFile randomAccessFile, int i) {
        this.f12446a = hbk;
        this.f12447b = randomAccessFile;
        this.f12448c = i;
    }

    /* JADX INFO: finally extract failed */
    public final Object call() {
        hbk hbk = this.f12446a;
        RandomAccessFile randomAccessFile = this.f12447b;
        int i = this.f12448c;
        try {
            for (hbg a : (Set) hbk.f12452b.mo2097a()) {
                a.mo6819a();
            }
            hbk.m11137a(randomAccessFile, i);
            randomAccessFile.close();
            return null;
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }
}
