package p000;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: day */
/* compiled from: PG */
final /* synthetic */ class day implements Callable {

    /* renamed from: a */
    private final Optional f6154a;

    /* renamed from: b */
    private final iek f6155b;

    /* renamed from: c */
    private final Uri f6156c;

    /* renamed from: d */
    private final ebi f6157d;

    public day(Optional optional, iek iek, Uri uri, ebi ebi) {
        this.f6154a = optional;
        this.f6155b = iek;
        this.f6156c = uri;
        this.f6157d = ebi;
    }

    public final Object call() {
        AssetFileDescriptor a;
        Optional optional = this.f6154a;
        iek iek = this.f6155b;
        Uri uri = this.f6156c;
        ebi ebi = this.f6157d;
        if (optional.isPresent()) {
            File file = new File((String) optional.get());
            File parentFile = file.getParentFile();
            if (parentFile != null && parentFile.canWrite()) {
                return dbf.m5840a(gpc.m10580a((Callable) new dbd(file), (Executor) iek), uri);
            }
            throw new IOException("Cannot write raw file.");
        }
        try {
            a = fra.m9438a(ebi.f7846a, uri, "w", fqz.f10297a);
            dbf a2 = dbf.m5840a(gpc.m10580a((Callable) new dbe(ebi, uri), (Executor) iek), uri);
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File with given URI was not found.", e);
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
