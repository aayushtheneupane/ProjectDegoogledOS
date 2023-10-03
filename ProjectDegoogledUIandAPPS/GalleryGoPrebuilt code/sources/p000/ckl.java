package p000;

import android.net.Uri;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: ckl */
/* compiled from: PG */
final class ckl implements dbo {

    /* renamed from: a */
    private final ebi f4569a;

    /* renamed from: b */
    private final Uri f4570b;

    /* renamed from: c */
    private final String f4571c;

    /* renamed from: d */
    private final String f4572d;

    public ckl(ebi ebi, Uri uri, String str, String str2) {
        if (dgt.m6098c(str2) || dgt.m6097b(str2)) {
            this.f4569a = ebi;
            this.f4570b = uri;
            this.f4571c = str;
            this.f4572d = str2;
            return;
        }
        String valueOf = String.valueOf(str2);
        throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Unsupported mime type ") : "Unsupported mime type ".concat(valueOf));
    }

    /* renamed from: a */
    public final String mo3203a() {
        return this.f4571c;
    }

    /* renamed from: b */
    public final String mo3205b() {
        return this.f4572d;
    }

    /* renamed from: c */
    public final int mo3206c() {
        return 0;
    }

    /* renamed from: d */
    public final int mo3207d() {
        return 0;
    }

    /* renamed from: e */
    public final String mo3208e() {
        throw new IOException("No fallback path available.");
    }

    /* renamed from: a */
    public final void mo3204a(OutputStream outputStream) {
        InputStream inputStream;
        try {
            ebi ebi = this.f4569a;
            inputStream = fra.m9441a(ebi.f7846a, this.f4570b, fqz.f10297a);
        } catch (FileNotFoundException e) {
            inputStream = new FileInputStream((String) ife.m12898e((Object) this.f4570b.getPath()));
        }
        try {
            hzt.m12551a(inputStream, outputStream);
            if (inputStream != null) {
                inputStream.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
