package p000;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import p003j$.util.Optional;

/* renamed from: dcd */
/* compiled from: PG */
final /* synthetic */ class dcd implements icf {

    /* renamed from: a */
    private final Optional f6233a;

    /* renamed from: b */
    private final Context f6234b;

    /* renamed from: c */
    private final dbo f6235c;

    public dcd(Optional optional, Context context, dbo dbo) {
        this.f6233a = optional;
        this.f6234b = context;
        this.f6235c = dbo;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream;
        Optional optional = this.f6233a;
        Context context = this.f6234b;
        dbo dbo = this.f6235c;
        OutputStream outputStream = (OutputStream) obj;
        if (optional.isPresent()) {
            fsc fsc = (fsc) optional.get();
            File file = new File(context.getCacheDir(), "compress");
            if (!file.exists() && !file.mkdirs()) {
                throw new IOException("Unable to create temporary file");
            }
            File file2 = new File(file, UUID.randomUUID().toString());
            try {
                fileOutputStream = new FileOutputStream(file2);
                dbo.mo3204a(fileOutputStream);
                fileOutputStream.close();
                FileInputStream fileInputStream = new FileInputStream(file2);
                try {
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    if (outputStream != null) {
                        fsf fsf = new fsf(outputStream, fsc);
                        fsf.f10482a = fsc.f10476s;
                        byte[] bArr = new byte[1024];
                        for (int read = bufferedInputStream.read(bArr, 0, 1024); read != -1; read = bufferedInputStream.read(bArr, 0, 1024)) {
                            fsf.write(bArr, 0, read);
                        }
                        fsf.flush();
                        bufferedInputStream.close();
                        fileInputStream.close();
                        file2.delete();
                    } else {
                        throw new IllegalArgumentException("Argument is null");
                    }
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            } catch (Throwable th2) {
                file2.delete();
                throw th2;
            }
        } else {
            dbo.mo3204a(outputStream);
        }
        return ife.m12820a((Object) bip.f2457a);
        throw th;
        throw th;
    }
}
