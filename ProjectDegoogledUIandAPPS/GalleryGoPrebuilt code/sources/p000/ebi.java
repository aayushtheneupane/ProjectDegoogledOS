package p000;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: ebi */
/* compiled from: PG */
public final class ebi {

    /* renamed from: a */
    public final Context f7846a;

    public ebi(Context context) {
        this.f7846a = context;
    }

    /* renamed from: a */
    public final InputStream mo4664a(Uri uri) {
        return fra.m9440a(this.f7846a, uri);
    }

    /* renamed from: a */
    public final OutputStream mo4665a(Uri uri, fqz fqz) {
        AssetFileDescriptor a = fra.m9438a(this.f7846a, uri, "w", fqz);
        if (a == null) {
            return null;
        }
        try {
            return a.createOutputStream();
        } catch (IOException e) {
            FileNotFoundException fileNotFoundException = new FileNotFoundException("Unable to create stream");
            fileNotFoundException.initCause(e);
            fra.m9444a(a, fileNotFoundException);
            throw fileNotFoundException;
        }
    }
}
